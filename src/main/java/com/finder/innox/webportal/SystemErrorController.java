package com.finder.innox.webportal;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SystemErrorController implements ErrorController{
	
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		logger.info("handleError() >> " + status.toString());
        return "error";
    }

	@Override
	public String getErrorPath() {
		return "error";
	}

}
