package com.pcwk.ehr.book;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.JView;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.StringUtil;

/**
 * Servlet implementation class BookController
 */

public class BookController  extends HttpServlet implements ControllerV , PLog{
	private static final long seriaVersionUID = 1L;
	
	BookService service;

	public JView doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("---------------------");
		log.debug("doUpdate()");
		log.debug("---------------------");
		
		BookDTO inVO = new BookDTO();
		String bookCode  = StringUtil.nvl(request.getParameter("bookCode"),"");
		String bookGenre = StringUtil.nvl(request.getParameter("bookGenre"), "");
		String bookName  = StringUtil.nvl(request.getParameter("bookName"),"");
		String isbn      = StringUtil.nvl(request.getParameter("isbn"), "");
		String bookPubDate = StringUtil.nvl(request.getParameter("bookPubDate"),"");
		String publisher   = StringUtil.nvl(request.getParameter("publisher"), "");
		String author      = StringUtil.nvl(request.getParameter("author"), "");
		String bookInfo    = StringUtil.nvl(request.getParameter("bookInfo"), "");
		String modId       = StringUtil.nvl(request.getParameter("modId"), "");
		
		log.debug("bookCode:" + bookCode);
		log.debug("bookGenre:" + bookGenre);
		log.debug("bookName:" + bookName);
		log.debug("isbn:" + isbn);
		log.debug("bookPubDate:" + bookPubDate);
		log.debug("publisher:" + publisher);
		log.debug("author:" + author);
		log.debug("bookInfo:" + bookInfo);
		log.debug("modId:" + modId);
		
		inVO.setBookCode(Integer.parseInt(bookCode));
		inVO.setBookGenre(Integer.parseInt(bookGenre));
		inVO.setBookName(bookName);
		inVO.setIsbn(Integer.parseInt(isbn));
		inVO.setBookPubDate(bookPubDate);
		inVO.setPublisher(publisher);
		inVO.setAuthor(author);
		inVO.setBookInfo(bookInfo);
		inVO.setModId(modId);
		
		log.debug("inVO:" + inVO);
		
		int flag = service.doUpdate(inVO);
		String message = "";
		log.debug("flag:" + flag);
		if(1 == flag) {
			message = "수정되었습니다.";
		}else{
			message = "수정실패";
		}
		
		MessageVO messageVO = new MessageVO();
		messageVO.setMessageId(String.valueOf(flag));
		messageVO.setMsgContents(message);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(messageVO);
		log.debug("jsonString:" + jsonString);

		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.print(jsonString);
		
		return null;
	}
	// doDelete는 상속 받은 HttpServlet에 메서드로 return이 void이므로 이름 변경
	public JView doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("---------------------");
		log.debug("doDel()");
		log.debug("---------------------");
		
		BookDTO inVO = new BookDTO();
		String bookCode = StringUtil.nvl(request.getParameter("bookCode"),"");
		
		inVO.setBookCode(Integer.parseInt(bookCode));
		log.debug("inVO:" + inVO);
		
		int flag = service.doDelete(inVO);
		log.debug("flag:{}" + flag);
		
		String message = "";
		if (1 == flag) {
			message = "삭제 성공";
		} else {
			message = "삭제 실패";
		}

		MessageVO messageVO = new MessageVO();
		messageVO.setMessageId(String.valueOf(flag));
		messageVO.setMsgContents(message);
		log.debug("messageVO:{}", messageVO);

		Gson gson = new Gson();
		String jsonString = gson.toJson(messageVO);
		log.debug("jsonString:{}", jsonString);

		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.print(jsonString);
		
		return null;
	}
	
	public JView doSelectOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("---------------------");
		log.debug("doSelectOne()");
		log.debug("---------------------");
		BookDTO inVO = new BookDTO();
		String seq = StringUtil.nvl(request.getParameter("bookCode"),"0");
		
		inVO.setBookCode(Integer.parseInt(seq));
		log.debug("inVO:" + inVO);
		
		BookDTO outVO = this.service.doSelectOne(inVO);
		log.debug("outVO:" + outVO);
		
		// UI 데이터 전달
		request.setAttribute("outVO", outVO);
		
		return new JView("/1_project02/book_list_info.jsp");
	}
	@Override
	public JView doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("---------------------");
		log.debug("doWork()");
		log.debug("---------------------");
		
		JView viewName = null;
		
		String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
		log.debug("work_div : {}",workDiv);
		
		switch(workDiv) {
		case "doUpdate":
			viewName = doUpdate(request, response);
			break;
		case "doDelete":
			viewName = doDel(request,response);
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
