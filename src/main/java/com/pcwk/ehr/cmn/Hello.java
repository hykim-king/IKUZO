package com.pcwk.ehr.cmn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hello
 */
@WebServlet(description = "firstServlet", urlPatterns = { "/hello_cmn.do" })
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hello() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String userId = req.getParameter("user_id");
    	String passwd = req.getParameter("passwd");
    	
    	System.out.println("userId : " + userId);
    	System.out.println("passwd : " + passwd);
    	
    	// 화면으로 데이터 전달
    	// 처리 결과 저장
    	req.setAttribute("user_id", userId + "_Server");
    	req.setAttribute("passwd", passwd + "_Server");
    	// h03/h09_form_login.jsp : 전달받을 화면
    	// req.setAttribute("key", 데이터);
    	
    	RequestDispatcher dispatcher = req.getRequestDispatcher("/h03/h09_form_login.jsp");
    	dispatcher.forward(req, resp);
    	
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><title></title></head>");
		out.println("<body>");
		out.println("<h2>Hello, world!</h2>");
		out.println("What time is it now? : " + new Date());
		out.println("</body>");
		out.println("</html>");
	}


}
