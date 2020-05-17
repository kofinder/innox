package com.finder.ecoshop;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.finder.ecoshop.utils.ApplicationContextUtil;


public class ApplicationContextProvider implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {

		ApplicationContextUtil.setCtx(ctx);
	}
}
