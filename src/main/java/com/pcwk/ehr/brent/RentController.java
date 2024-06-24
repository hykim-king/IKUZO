package com.pcwk.ehr.brent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.JView;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.StringUtil;

public class RentController implements ControllerV, PLog {
	
	public RentController() {
		log.debug("---------------------");
    	log.debug("RentController()");
    	log.debug("---------------------");
	}
	
	
	public JView rentcheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("---------------------");
    	log.debug("rentcheck()");
    	log.debug("---------------------");
    	
    	return null;
	}
		
	@Override
	public JView doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("---------------------");
    	log.debug("doWork()");
    	log.debug("---------------------");
		
    	JView viewName = null;
    	
    	String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
    	log.debug("workDiv : {}",workDiv);
    	
    	switch(workDiv) {
    	case "rentcheck":
    		viewName = rentcheck(request,response);
    		break;
    	}
		
		return null;
	}

}
