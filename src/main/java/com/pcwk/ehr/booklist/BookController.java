package com.pcwk.ehr.booklist;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pcwk.ehr.brent.RentDTO;
import com.pcwk.ehr.brent.RentService;
import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.JView;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.cmn.StringUtil;

/**
 * Servlet implementation class BookController
 */

public class BookController extends HttpServlet implements ControllerV, PLog {
	private static final long seriaVersionUID = 1L;

	BookService service;
	RentService serviceR;
	
	public BookController() {
		log.debug("---------------------");
		log.debug("BookController()");
		log.debug("---------------------");

		service = new BookService();
		serviceR = new RentService();
	}

	//doSelectOne
	public JView doSelectOne(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("---------------------");
		log.debug("doSelectOne()");
		log.debug("---------------------");
		
		 BookDTO inVO = new BookDTO(); 
		 String bookCode = StringUtil.nvl(request.getParameter("bookcode"),"0");
		 
		 inVO.setBookCode(Integer.parseInt(bookCode));
		 log.debug("inVO:" + inVO);
		 
		 BookDTO outVO = this.service.doSelectOne(inVO);
		 log.debug("outVO:" + outVO);
		  
		 // UI 데이터 전달 request.setAttribute("outVO", outVO);
		 request.setAttribute("outVO", outVO);

		 return new JView("/jsp/book_list_info.jsp");
	}

	//doRetrieve2
	public JView doRetrieve2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("---------------------");
		log.debug("doRetrieve2()");
		log.debug("---------------------");

		// JSP viewName저장
		JView viewName = null;

		SearchDTO inVO = new SearchDTO();

		// page_no
		// page_size
		String pageNo = StringUtil.nvl(request.getParameter("page_no"), "1");
		String pageSize = StringUtil.nvl(request.getParameter("page_size"), "10");

		log.debug("pageNo:{}", pageNo);
		log.debug("pageSize:{}", pageSize);

		inVO.setPageNo(Integer.parseInt(pageNo));
		inVO.setPageSize(Integer.parseInt(pageSize));

		log.debug("inVO:{}", inVO);

		// service call
		List<BookDTO> list = service.doRetrieve2(inVO);

		int i = 0;
		for (BookDTO vo : list) {
			log.debug("i : {} ,vo : {}", ++i, vo);
		}

		// UI 데이터 전달
		request.setAttribute("list", list);

		// 검색 조건 UI로 전달
		request.setAttribute("vo", inVO);
		
		log.debug("inVO: {}", inVO);
		return new JView("/jsp/book_list.jsp");
	}
	
	//moveToReg
	public JView moveToReg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("---------------------");
		log.debug("moveToReg()");
		log.debug("---------------------");
		return new JView("/jsp/book_list_info.jsp");
	}
	
	//ajaxDoSave
	public JView ajaxDoSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("---------------------");
		log.debug("ajaxDoSave()");
		log.debug("---------------------");
		
		RentDTO rentVO = new RentDTO();
		
		String bookCode =	StringUtil.nvl(request.getParameter("bookCode"),"");
		
		
		return null;
	}
	
	//doRsntSave
	public JView doRentSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("---------------------");
		log.debug("doRentSave()");
		log.debug("---------------------");
		
		RentDTO inVO = new RentDTO();
		
		String userId = StringUtil.nvl(request.getParameter("userId"),"");
		int bookCode = Integer.parseInt(StringUtil.nvl(request.getParameter("book_code"),"0"));
		
		log.debug("userId:{}",userId);
		
		inVO.setUserId(userId);
		inVO.setBookCode(bookCode);
		
		int flag = this.serviceR.doSave(inVO);
		log.debug("flag:{}",flag);
		
		
		String message = "";
		if(flag == 1) {
			message = "등록성공";
		}else {
			message = "등록실패";
		}
		
		MessageVO messageVO = new MessageVO();
		messageVO.setMessageId(String.valueOf(flag));
		messageVO.setMsgContents(message);
		
		log.debug("messageVO:{}",messageVO);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(messageVO);
		
		log.debug("jsonStirng:{}", jsonString);
		
		//한글 깨짐 설정
		response.setContentType("text/html; charset=UTF-8");
		
		//sussess data 화면에 출력
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		
		return null;
	}
	
	
	@Override
	public JView doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("---------------------");
		log.debug("doWork()");
		log.debug("---------------------");

		JView viewName = null;

		String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
		log.debug("work_div : {}", workDiv);

		switch(workDiv) {
		case "doSave":
			viewName = doRentSave(request, response);
			break;
		case "ajaxDoSave":
			viewName = ajaxDoSave(request, response);
			break;
		case "moveToReg":
			viewName = moveToReg(request, response);
			break;
		case "doRetrieve":
			viewName = doRetrieve2(request, response);
			break;
		case "doSelectOne":
			viewName = doSelectOne(request, response);
			break;
		default:
			log.debug("작업구분을 확인 하세요. workDiv : {}", workDiv);
			break;
		}

		return viewName;
	}

}
