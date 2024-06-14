<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="com.pcwk.ehr.board.BoardDAO" %>
<%@ page import="com.pcwk.ehr.board.BoardDTO" %>
<%
    String title = request.getParameter("title");
    String contents = request.getParameter("contents");
    String regId = "사용자ID"; // 실제 사용자 ID를 여기에 넣어야 함
    String modId = "사용자ID"; // 실제 사용자 ID를 여기에 넣어야 함
    String isAdmin = "N"; // 기본값 설정

    BoardDTO board = new BoardDTO();
    board.setTitle(title);
    board.setContents(contents);
    board.setRegId(regId);
    board.setModId(modId);
    board.setIsAdmin(isAdmin);
    board.setRegDt(new Date()); // 현재 시간을 등록일로 설정
    board.setModDt(new Date()); // 현재 시간을 수정일로 설정

    BoardDAO dao = new BoardDAO();
    int result = dao.insertBoard(board);

    if (result > 0) {
        response.sendRedirect("board01.jsp"); // 등록 성공 시 목록 페이지로 이동
    } else {
        out.println("게시글 등록 실패"); // 실패 시 메시지 출력
    }
%>
