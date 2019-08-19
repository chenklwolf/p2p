package com.eloan.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eloan.base.domain.SystemDictionaryItem;
import com.eloan.base.query.PageResult;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Userfile;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.mapper.UserfileMapper;
import com.eloan.business.query.UserFileQueryObject;
import com.eloan.business.service.IUserFileService;
import com.eloan.business.service.IUserService;

@Service
public class UserFileServiceImpl implements IUserFileService {

	@Autowired
	private UserfileMapper userfileMapper;

	@Autowired
	private IUserService userService;

	@Override
	public List<Userfile> listUnSetTypeFiles(Long id, boolean unselected) {
		return this.userfileMapper.listUnSetTypeFiles(id, unselected);
	}

	/**
	 * 风控资料上传到数据库文件记录中userfile中
	 * @param path
	 */
	@Override
	public void applyFile(String path) {
		Userfile uf = new Userfile();
		uf.setApplier(UserContext.getCurrent());
		uf.setApplyTime(new Date());
		uf.setFile(path);
		uf.setState(Userfile.STATE_APPLY);
		this.userfileMapper.insert(uf);
	}

	@Override
	public void applyTypes(Long[] ids, Long[] fileTypes) {
		for (int i = 0; i < ids.length; i++) {
			Userfile uf = this.userfileMapper.selectByPrimaryKey(ids[i]);
			SystemDictionaryItem type = new SystemDictionaryItem();
			type.setId(fileTypes[i]);
			uf.setFileType(type);
			this.userfileMapper.updateByPrimaryKey(uf);
		}
	}

	@Override
	public PageResult query(UserFileQueryObject qo) {
		int count = this.userfileMapper.queryForCount(qo);
		if (count > 0) {
			List<Userfile> list = this.userfileMapper.query(qo);
			return new PageResult(count, qo.getPageSize(), qo.getCurrentPage(),
					list);
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public List<Userfile> queryList(UserFileQueryObject qo) {
		return this.userfileMapper.query(qo);
	}

	@Override
	public void audit(Long id, String remark, int score, int state) {
		Userfile file = this.userfileMapper.selectByPrimaryKey(id);
		if (file != null && file.getState() == Userfile.STATE_APPLY) {
			file.setAuditor(UserContext.getCurrent()); //审核人
			file.setAuditTime(new Date());
			file.setState(state);
			file.setRemark(remark);

			if (state == Userfile.STATE_PASS) {
				//风控审核成功
				file.setScore(score); //文件风控分数
				Userinfo user = this.userService.get(file.getApplier().getId()); //申请人
				user.setAuthScore(user.getAuthScore() + score); //用户风控分数
				this.userService.update(user);
			}
			this.userfileMapper.updateByPrimaryKey(file);
		}
	}
}
