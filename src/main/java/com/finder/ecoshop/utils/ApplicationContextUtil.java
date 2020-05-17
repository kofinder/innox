package com.finder.ecoshop.utils;

import org.springframework.context.ApplicationContext;

public class ApplicationContextUtil {

	private static ApplicationContext ctx;

	public static ApplicationContext getCtx() {
		return ctx;
	}

	public static void setCtx(ApplicationContext ctx) {
		ApplicationContextUtil.ctx = ctx;
	}
}
