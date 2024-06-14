package com.pcwk.ehr.libuser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.JView;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.StringUtil;

/**
 * Servlet implementation class BoardController
 */
//@WebServlet(description = "게시판 컨트롤러", urlPatterns = { "/board/board.do" })
public class BoardController extends HttpServlet implements ControllerV, PLog{
	private static final long serialVersionUID = 1L;
    
	BoardService service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
		log.debug("-----------------");
    	log.debug("BoardController()");
    	log.debug("-----------------");
    	
    	service = new BoardService();
    }
    
	public JView doRetrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("-----------------");
    	log.debug("doRetrieve()");
    	log.debug("-----------------");
    	
    	// JSP viewName 저장(경로)
    	JView viewName = null;
    	
    	HttpSession session = request.getSession();
    	
    	SearchDTO inVO = new SearchDTO();
    	//http://localhost:8080/WEB02/board/board.do?work_div=doRetrieve&page_no=2&page_size=20
    	//page_no
    	//page_size
    	String pageNo = StringUtil.nvl(request.getParameter("page_no"), "1");
    	String pageSize = StringUtil.nvl(request.getParameter("page_size"), "10");
    	
    	String searchWord = StringUtil.nvl(request.getParameter("search_word"), "");    	
    	String searchDiv = StringUtil.nvl(request.getParameter("search_div"), "");    	
    	
    	log.debug("pageNo : {}", pageNo);
    	log.debug("pageSize : {}", pageSize);
    	log.debug("searchWord : {}", searchWord);
    	log.debug("searchDiv : {}", searchDiv);
    	
    	inVO.setPageNo(Integer.parseInt(pageNo));
    	inVO.setPageSize(Integer.parseInt(pageSize));
    	inVO.setSearchDiv(searchDiv);
    	inVO.setSearchWord(searchWord);

    	log.debug("inVO : {}", inVO);
    	
    	// service call
    	List<BoardDTO> list = service.doRetrieve(inVO);
    	
    	// reutrn 데이터 확인
    	int i = 0;
    	for (BoardDTO vo : list) {
    		log.debug("i : {}, vo : {}", ++i, vo);			
		}
    	
    	// UI 데이터 전달
    	request.setAttribute("list", list);
    	
    	// 검색조건 UI로 전달
    	request.setAttribute("vo", inVO);    	
		
		// RequestDispatcher dispacher = request.getRequestDispatcher("/board/board_list.jsp");
		// dispacher.forward(request, response);		     
    	
    	return viewName = new JView("/board/manange01.jsp");
	}
	
	private JView moveToReg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("moveToReg()");
		log.debug("-----------------");
		
		return new JView("/board/board_reg.jsp");
	}
	
	private JView doSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("doSave()");
		log.debug("-----------------");
		BoardDTO inVO = new BoardDTO();
		String title = StringUtil.nvl(request.getParameter("title"), "");
		String regId = StringUtil.nvl(request.getParameter("regId"), "");
		String content = StringUtil.nvl(request.getParameter("contents"), "");
		
		log.debug("title : ", title);
		log.debug("regId : ", regId);
		log.debug("content : " ,content);
		
		/*
		 * inVO.setTitle(title); inVO.setContents(content); inVO.setRegId(regId);
		 * inVO.setModId(regId)
		 */;
		
		int flag = this.service.doSave(inVO);
		log.debug("flag : {}", flag);
		
		if (1 == flag) {
			return new JView("/board/board.do?work_div=doRetrieve");
		}
		
		return null;
	}
	
	public JView ajaxdoSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		log.debug("-----------------");
		log.debug("ajaxDoSave()");
		log.debug("-----------------");
		BoardDTO inVO = new BoardDTO();
		String title = StringUtil.nvl(request.getParameter("title"), "");
		String regId = StringUtil.nvl(request.getParameter("regId"), "");
		String content = StringUtil.nvl(request.getParameter("contents"), "");
		
		log.debug(title);
		log.debug(regId);
		log.debug(content);
		
		/*
		 * inVO.setTitle(title); inVO.setContents(content); inVO.setRegId(regId);
		 * inVO.setModId(regId);
		 */
		
		int flag = this.service.doSave(inVO);
		log.debug("flag : {}", flag);
		
		String message = "";
		if(flag == 1) {
			message = "등록성공";
		}else {
			message = "등록실패";			
		}
		MessageVO messageVO = new MessageVO();
		messageVO.setMessageId(String.valueOf(flag));
		messageVO.setMsgContents(message);
		
		log.debug(message);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(messageVO);
		
		log.debug("jsonString : {}", jsonString);
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		
		return null;
	}
	
	public JView doSelectOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		log.debug("-----------------");
    	log.debug("doSelectOne()");
    	log.debug("-----------------");
    	BoardDTO inVO = new BoardDTO();
    	String seq = StringUtil.nvl(request.getParameter("seq"), "0");
    	
		/* inVO.setSeq(Integer.parseInt(seq)); */
    	log.debug("inVO" + inVO);
    	
    	BoardDTO outVO = this.service.selectOneReadCnt(inVO);
    	log.debug("outVO" + outVO);
    	
    	// UI 데이터 전달
    	request.setAttribute("outVO", outVO);
    	
		return new JView("/board/board_mng.jsp");
	}
	
	@Override
	public JView doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("-----------------");
    	log.debug("doWork()");
    	log.debug("-----------------");
    	
    	JView viewName = null;
    	
    	String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
    	
    	log.debug("workDiv : {}", workDiv);
    	
    	switch (workDiv) {
    	case "doSelectOne":
    		viewName = doSelectOne(request, response);
    		break;
    	case "ajaxdoSave":
    		viewName = ajaxdoSave(request, response);
    		break;
    	case "doSave":
    		viewName = doSave(request, response);
    		break;    	
    	case "moveToReg":
    		viewName = moveToReg(request, response);
    		break;
		case "doRetrieve":
			viewName = doRetrieve(request, response);
			break;

		default:
			log.debug("작업구분을 확인하세요. workDiv : {}", workDiv);
			break;
		}
    	
    	return viewName;
	}

} // CLASS
