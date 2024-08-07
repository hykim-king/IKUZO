package com.pcwk.ehr.login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.JView;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.StringUtil;

//@WebServlet("/login.do") FrontControllerV에서 작동
public class LoginController extends HttpServlet implements PLog, ControllerV {

    private static final long serialVersionUID = 1L;
    private LoginService service;

    public LoginController() {
        log.debug("-----------------");
        log.debug("LoginController()");
        log.debug("-----------------");
        service = new LoginService();
    }
    
    public JView login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("---------------------");
        log.debug("login()");
        log.debug("---------------------");

        String userId = StringUtil.nvl(request.getParameter("user_id"), "");
        String userPw = StringUtil.nvl(request.getParameter("user_pw"), "");
        JView viewName = null;
        
        LoginDTO inVO = new LoginDTO();
        inVO.setUserId(userId);

        LoginDTO outVO = service.doSelectOne(inVO);

        String message;
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (outVO != null && userId.equals(outVO.getUserId()) && userPw.equals(outVO.getUserPw())) {
            HttpSession session = request.getSession();
            log.debug("session : {}", session);
            outVO.setFlag(1); // 로그인 성공
            session.setAttribute("user", outVO);
            session.setMaxInactiveInterval(-1);
            log.debug("세션 생성");
            message = "성공";
        } else {
            message = "아이디와 비밀번호를 확인하세요."; // 실패 메시지 수정
        }

        out.print(message);
        return viewName;
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        log.debug("doGet 메서드 진입");
//        doWork(request, response);
//        log.debug("doGet 메서드 종료");
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        log.debug("doPost 메서드 진입");
//        doWork(request, response);
//        log.debug("doPost 메서드 종료");
//    }

    public JView processLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("-------------------");
		log.debug("logout()");
		log.debug("-------------------");
		JView viewName= new JView ("/jsp/login.jsp");
        HttpSession session = request.getSession(false); // 세션이 존재하면 반환, 존재하지 않으면 null 반환
        log.debug("session()" + session);
        
        // 로그아웃 후 index.jsp로 이동
        if (session != null) {
            session.invalidate(); // 세션 무효화
            log.debug("세션 삭제");
        } else {
        	
        }
        
        return viewName;
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
    	case "login":
    		viewName = login(request, response);
    		break;
    	case "logout":
    		viewName = processLogout(request, response); // 로그아웃 로직 추가
    		break;
    	case "toLogin" :
    		viewName = new JView("/jsp/login.jsp");
    		break;
    	default:
    		log.debug("작업구분을 확인 하세요. workDiv : {}", workDiv);
    		break;
    	}
    	
    	return viewName;
    }
}
