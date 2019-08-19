package com.eloan.mgrtool.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.eloan.base.service.ILogininfoService;

@Component
public class CreateDefaultAdminListener implements
		ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private ILogininfoService logininfoService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(!logininfoService.hasAdmin()){
			logininfoService.createDefaultAdmin();
		}
	}

}
