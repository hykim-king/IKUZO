<%@page import="com.pcwk.ehr.managebook.manageBookDTO"%>
<%@page import="com.pcwk.ehr.managebook.manageBookDao"%>
<%@page import="com.pcwk.ehr.managebook.SearchDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/common.jsp" %>       
<%
manageBookDao dao = new manageBookDao();
SearchDTO searchVO = new SearchDTO();
searchVO.setPageNo(1);
searchVO.setPageSize(20);

List<manageBookDTO> list = dao.doRetrieve(searchVO);

for(manageBookDTO vo :list) {
    System.out.println(vo);
}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/WEB02/assest/css/bookbook.css">
<link rel="stylesheet" href="/WEB02/assest/css/book_board.css">
</head>
<body>
<!-- header 시작  -->  
<%@ include file="header.jsp" %>
<!-- header 끝  -->
  
<!-- container -->
<section class="container">
    <div class="inner-container">
       <div class="page-title-group">
          <h2 class="page-title">관리자 페이지</h2>
       </div>

        
    <div class="page-list-group">
        <div class="page-list-inner">
            <div>
                <a href="manage01.jsp">회원 관리</a>
            </div>
            <div class="active">
                <a href="manage02.jsp">도서 관리</a>
            </div>
        </div>    
    </div>

<div class="category-box">
    <div class="category-wrap">
        <div class="redAlert">
            <a href="#">삭제</a>
        </div>
        <div class="active">
            <a href="#">추가</a>
        </div>
    </div>

    <div class="category-wrap category-wrap2">
        <form action="#">
            <select>
                <option value="" selected="selected">전체</option>
                <option value="10">도서제목</option>
                <option value="20">장르</option>
                <option value="30">작가</option>
                <option value="40">출판사</option>
            </select>
            <input type="search" name="board_search" placeholder="검색어를 입력해주세요" value="">
        </form>
        <button type="submit" class="btn-control">
            <span class="icon"></span>
        </button>   
    </div>
</div>

<table class="notice-board">
    <thead>
        <tr>
            <th class="checkbox"><input id="checkAll" type="checkbox"></th>
            <th class="book_name">도서제목</th>
            <th class="book_genre">장르</th>
            <th class="author">작가</th>
            <th class="company">출판사</th>
            <th class="rent_date">대출일</th>
            <th class="due_date">반납예정일</th>
            <th class="returned_date">반납일</th>
            <th class="rent_yn">대출가능여부</th>
            <th class="profile_edit">정보수정</th>
        </tr>
    </thead>
    <tbody>
    <%
       for(manageBookDTO vo :list) {
       %>
        <tr>
            <td class="checkbox"><input type="checkbox" class="chk"></td>
            <td class="book_name"><%=vo.getBookName()%></td>
            <td class="book_genre"><%=vo.getGenre()%></td>
            <td class="author"><%=vo.getAuthor()%></td>
            <td class="publisher"><%=vo.getPublisher()%></td>
            <td class="rent_date"><%=vo.getRentDate()%></td>
            <td class="due_date"><%=vo.getDueDate()%></td>
            <td class="returned_date"><%=vo.getRetunredDate()%></td>
            <td class="rent_yn"><%=vo.getRentYn()%></td>
            <td class="profile_edit">
                <a href="" class="active">수정</a>
            </td>
        </tr> 
    <% } %>                                 
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
<script src="/WEB02/assest/js/check.js"></script>
</body>
</html>