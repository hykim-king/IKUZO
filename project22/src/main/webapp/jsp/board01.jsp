<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.pcwk.ehr.board.BoardDAO" %>
<%@ page import="com.pcwk.ehr.board.BoardDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link rel="stylesheet" href="/project22/css/bookbook.css">
<link rel="stylesheet" href="/project22/css/book_board.css">
</head>
<body>

  <!-- header 시작  -->  
  <%@ include file="header.jsp" %>
  <!-- header 끝  -->
  
<!-- container -->
<section class="container">
    <div class="inner-container">
       <div class="page-title-group">
          <h2 class="page-title">공지 &amp; 행사</h2>
       </div>

    <div class="page-list-group">
        <div class="page-list-inner">
            <div class="active">
                <a href="board01.jsp">공지 &amp; 행사</a>
            </div>
            <div>
                <a href="board02.jsp">소통마당</a>
            </div>
        </div>    
    </div>

<div class="category-box">
    <div class="category-wrap">
        <div class="active">
            <a href="#">전체</a>
        </div>
        <div class="tab-list-cell">
            <a href="#">공지사항</a>
        </div>
        <div class="tab-list-cell">
            <a href="#">도서관 소식</a>
        </div>
    </div>

    <div class="category-wrap category-wrap2">
        <form action="#">
            <select>
                <option value="" selected="selected">전체</option>
                <option value="post_title">제목</option>
                <option value="post_content">내용</option>
                <option value="post_author">작성자</option>
            </select>
            <input type="search" name="board_search" placeholder="검색어를 입력해주세요" value="">
            <button type="submit" class="btn-control">
                <span class="icon"></span>
            </button>
        </form>
    </div>
</div>

<table class="notice-board">
    <thead>
        <tr>
            <th class="num">No.</th>
            <th class="category">제목</th>
            <th class="subject">내용</th>
            <th class="writer">작성자</th>
            <th class="date">작성일</th>
            <th class="count">조회수</th>
        </tr>
    </thead>
    <tbody>
<%
    BoardDAO dao = new BoardDAO();
    List<BoardDTO> boardList = dao.getBoardList();

    for (BoardDTO board : boardList) {
%>
    <tr>
        <td class="num"><%= board.getSeq() %></td>
        <td class="category"><%= board.getTitle() %></td>
        <td class="text-left subject"><%= board.getContents() %></td>
        <td class="writer"><%= board.getRegId() %></td>
        <td class="date"><%= board.getRegDt() %></td>
        <td class="count"><%= board.getReadCnt() %></td>
    </tr>
<%
    }
%>
    </tbody>
</table>

<div class="paging-wrap">
    <div class="pagination">
        <a href="" class="page-item current">1</a>
        <a href="" class="page-item">2</a>
        <a href="" class="page-item">3</a>
        <a href="" class="page-item">&hellip;</a>
        <a href="" class="page-item">6</a>
        <a href="" class="next page-item">다음</a>                 
    </div>
</div>

</div>
</section>
<!-- //container -->

  <!-- footer 시작  -->
  <%@ include file="footer.jsp" %>
  <!-- footer 끝  -->
</body>
</html>
