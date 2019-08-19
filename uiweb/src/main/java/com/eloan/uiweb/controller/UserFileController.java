package com.eloan.uiweb.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Userfile;
import com.eloan.business.service.IUserFileService;
import com.eloan.uiweb.interceptor.RequiredLogin;
import com.eloan.uiweb.util.UploadUtil;

/**
 * 上传风控资料
 */
@Controller
public class UserFileController extends BaseController {

	@Autowired
	private IUserFileService userFileService;

	@Autowired
	private ServletContext servletContext;

	//请求在leftmenu——tpl.ftl 文件当中
	@RequiredLogin
	@RequestMapping("userFile")
	public String userFile(Model model, HttpSession session) {
		//把没有选择风控文件类型的userFile选择出来
		List<Userfile> unSetFileTypes = this.userFileService
				.listUnSetTypeFiles(UserContext.getCurrent().getId(), true);
		if (unSetFileTypes.size() > 0) {
			model.addAttribute("userFiles", unSetFileTypes);
			return "userFiles_commit";
		} else {
			//已经选择过风控文件，将所有风控文件查询出来
			unSetFileTypes = this.userFileService.listUnSetTypeFiles(
					UserContext.getCurrent().getId(), false);
			model.addAttribute("userFiles", unSetFileTypes); // userFiletype_id有值
			model.addAttribute("sessionid", session.getId()); //防止同一个请求多次发送浏览器不刷新
			return "userFiles";
		}
	}

	/**
	 * 处理风控文件上传，图片等
	 * @param file
	 * @return
	 */
	@RequestMapping("userFileUpload")
	@ResponseBody
	public String userFileUpload(MultipartFile file) {
		String filePath = servletContext.getRealPath("/upload");
		String fileName = UploadUtil.upload(file, filePath);
		String path = "/upload/" + fileName; //上传路径
		this.userFileService.applyFile(path);
		return path;
	}

	@RequestMapping("userFile_selectType")
	public String selectType(Long[] id, Long[] fileType) {
		if (id.length == fileType.length) {
			//风控类型插入到表中
			this.userFileService.applyTypes(id, fileType);
		}
		return "redirect:userFile.do";
	}

}
