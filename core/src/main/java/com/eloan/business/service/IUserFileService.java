package com.eloan.business.service;

import java.util.List;

import com.eloan.base.query.PageResult;
import com.eloan.business.domain.Userfile;
import com.eloan.business.query.UserFileQueryObject;

public interface IUserFileService {

	/**
	 * 查询没有设置类型的文件
	 * @param id
	 * @return
	 */
	List<Userfile> listUnSetTypeFiles(Long id, boolean unselected);

	/**
	 * 用上传的文件创建一个UserFile对象
	 * @param path
	 */
	void applyFile(String path);

	/**
	 * 用户选择风控材料类型
	 * @param id
	 * @param fileType
	 */
	void applyTypes(Long[] id, Long[] fileType);

	/**
	 * 分页查询
	 * @param qo
	 * @return
	 */
	PageResult query(UserFileQueryObject qo);
	
	List<Userfile> queryList(UserFileQueryObject qo);

	/**
	 * 审核风控材料
	 * @param id
	 * @param remark
	 * @param score
	 * @param state
	 */
	void audit(Long id, String remark, int score, int state);

}
