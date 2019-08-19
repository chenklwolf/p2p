package com.eloan.mgrtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eloan.base.domain.SystemDictionary;
import com.eloan.base.domain.SystemDictionaryItem;
import com.eloan.base.query.PageResult;
import com.eloan.base.query.SystemDictionaryQueryObject;
import com.eloan.base.service.ISystemDictionaryService;

@Controller
public class SystemDictionaryController extends BaseController {

	@Autowired
	private ISystemDictionaryService systemDictionaryService;

	/**
	 * 数据字典列表
	 * @param qo
	 * @param model
	 * @return
	 */
	@RequestMapping("systemDictionary_list")
	public String systemDictionaryList(
			@ModelAttribute("qo") SystemDictionaryQueryObject qo, Model model) {
		PageResult result = this.systemDictionaryService.queryDic(qo);
		model.addAttribute("pageResult", result);
		return "systemdic/systemDictionary_list";
	}

	/**
	 * 数据字典大类页面更改
	 * @param sd
	 * @return
	 */
	@RequestMapping("systemDictionary_update")
	public String systemDictionaryUpdate(SystemDictionary sd) {
		this.systemDictionaryService.saveOrUpdate(sd);
		return "redirect:systemDictionary_list.do";
	}

	/**
	 * 字典详情页面
	 * @param qo
	 * @param model
	 * @return
	 */
	@RequestMapping("systemDictionaryItem_list")
	public String systemDictionaryItemList(
			@ModelAttribute("qo") SystemDictionaryQueryObject qo, Model model) {
		PageResult result = this.systemDictionaryService.queryDicItem(qo);
		model.addAttribute("pageResult", result);
		//查询所有分类
		model.addAttribute("systemDictionaryGroups", this.systemDictionaryService.listDics());
		return "systemdic/systemDictionaryItem_list";
	}
	
	@RequestMapping("systemDictionaryItem_update")
	public String systemDictionaryItemUpdate(SystemDictionaryItem item) {
		this.systemDictionaryService.saveOrUpdateItem(item);
		return "redirect:systemDictionaryItem_list.do?parentId="+item.getParentId();
	}
	
	

}
