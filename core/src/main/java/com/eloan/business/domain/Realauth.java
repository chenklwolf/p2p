package com.eloan.business.domain;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.apache.ibatis.type.Alias;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 实名认证对象，每次失败的认证也应该有认证对象
 *
 * 根据state判断，如果null，待认证，
 * 审核中，状态码为未认证，userinfo中的实名认证realauthId不为空，状态码为未认证
 * 已审核 判断
 * 拒绝： userinfo中的realauthId去掉
 * @author Administrator
 */
@Getter
@Setter
@Alias("Realauth")
public class Realauth extends BaseAuditDomain {
	public static final int SEX_MALE = 0;
	public static final int SEX_FEMALE = 1;

	private String realname;//真实姓名
	private int sex = SEX_MALE;//性别
	private String idNumber;//证件号码;
	private String birthDate;//出生日期;
	private String address;//证件地址

	private String image1;//身份证正面照片
	private String image2;//身份证背面照片

	public String getSexDisplay() {
		return sex == SEX_MALE ? "男" : "女";
	}

	public String getJsonString() {
		Map<String, Object> m = new HashMap<>();
		m.put("id", getId());
		m.put("username", this.getApplier().getUsername());
		m.put("realname", realname);
		m.put("idNumber", idNumber);
		m.put("sex", getSexDisplay());
		m.put("birthDate", birthDate);
		m.put("address", address);
		m.put("image1", image1);
		m.put("image2", image2);
		return JSONObject.toJSONString(m);
	}

	/**
	 * 获取用户真实名字的隐藏字符串，只显示姓氏
	 *
	 *            真实名字
	 * @return
	 */
	public String getAnonymousRealName() {
		if (StringUtils.hasLength(realname)) {
			int len = realname.length();
			String replace = "";
			replace += realname.charAt(0);
			for (int i = 1; i < len; i++) {
				replace += "*";
			}
			return replace;
		}
		return realname;
	}

	/**
	 * 获取用户身份号码的隐藏字符串
	 *
	 */
	public String getAnonymousIdNumber() {
		if (StringUtils.hasLength(idNumber)) {
			int len = idNumber.length();
			String replace = "";
			for (int i = 0; i < len; i++) {
				if ((i > 5 && i < 10) || (i > len - 5)) {
					replace += "*";
				} else {
					replace += idNumber.charAt(i);
				}
			}
			return replace;
		}
		return idNumber;
	}

	/**
	 * 获取用户住址的隐藏字符串

	 *            用户住址
	 * @return
	 */
	public String getAnonymousAddress() {
		if (StringUtils.hasLength(address) && address.length() > 4) {
			String last = address.substring(address.length() - 4,
					address.length());
			String stars = "";
			for (int i = 0; i < address.length() - 4; i++) {
				stars += "*";
			}
			return stars + last;
		}
		return address;
	}
}
