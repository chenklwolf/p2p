package com.eloan.business.service.impl;

import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.eloan.base.util.DateUtil;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.EmailActive;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.mapper.EmailActiveMapper;
import com.eloan.business.service.IEmailActiveService;
import com.eloan.business.service.IUserService;
import com.eloan.business.util.BitStatesUtils;

@Service
public class EmailActiveServiceImpl implements IEmailActiveService {

	@Value("${mail.host}")
	private String host;

	@Value("${mail.from}")
	private String from;

	@Value("${mail.siteurl}")
	private String siteurl;

	@Value("${mail.username}")
	private String username;

	@Value("${mail.password}")
	private String password;

	@Autowired
	private EmailActiveMapper emailActiveMapper;

	@Autowired
	private IUserService userService;

	@Override
	public void sendActiveEmail(String email) {
		EmailActive ea = new EmailActive();
		ea.setEmail(email);
		ea.setLogininfoId(UserContext.getCurrent().getId());
		ea.setSendDate(new Date());
		ea.setUuidcode(UUID.randomUUID().toString());

		//发送邮件
		try {
			sendEmail(ea);
			//成功写入邮箱激活表
			emailActiveMapper.insert(ea);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("发送邮件失败");
		}
	}

	private void sendEmail(EmailActive mail) throws Exception {
		//将发邮件的一系列复杂操作封装到了JavaMail中
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(host);

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message,
				"UTF-8");

		messageHelper.setTo(mail.getEmail());
		messageHelper.setFrom(from);
		messageHelper.setSubject("激活你的邮箱");

//		发送邮箱验证
		StringBuilder content = new StringBuilder(100)
				.append("<html><head></head><body><h1>这是你的邮箱激活邮件,请点击<a href='")
				.append(siteurl).append("checkMailActive.do?code=")
				.append(mail.getUuidcode())
				.append("'>这里</a>,激活邮箱.有效期为3天!<h1></body></html>");
		messageHelper.setText(content.toString(), true);

		//设置发送邮件的账号和密码
		sender.setUsername(username);
		sender.setPassword(password);

		Properties p = new Properties();
		//让服务器认证，认证用户名和密码是否正确
		p.setProperty("mail.smtp.auth", "true");
		//连接的超时时间
		p.setProperty("mail.smtp.timeout", "25000");

		sender.setJavaMailProperties(p);
		sender.send(message);

	}

	@Override
	public void bindEmail(String code) {
		//code 是验证时发送的UUID
		EmailActive email = this.emailActiveMapper.selectByCode(code);
		if (email != null
				&& DateUtil.getSecondsBetweenDates(email.getSendDate(),
						new Date()) <= 60 * 60 * 24 * 3) {
			Userinfo user = this.userService.get(email.getLogininfoId());
			if (!user.getIsBindEmail()) {
				user.setEmail(email.getEmail());
				user.addState(BitStatesUtils.OP_BIND_EMAIL);
				this.userService.update(user);
				return ;
			}else{
				throw new RuntimeException("邮件已经激活!");
			}
		}
		throw new RuntimeException("邮件激活时间超时,请登录系统重新激活!");
	}

}
