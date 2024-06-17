<%@page import="com.pcwk.ehr.mypage.LoanListDTO"%>
<%@page import="com.pcwk.ehr.mypage.SearchDTO"%>
<%@page import="com.pcwk.ehr.mypage.LoanListDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ include file="/jsp/common.jsp" %>
<%
LoanListDao dao = new LoanListDao();
      SearchDTO searchVO = new SearchDTO();
      searchVO.setPageNo(1);
      searchVO.setPageSize(10);


      List<LoanListDTO> list = dao.doRetrieve(searchVO);

      for(LoanListDTO vo :list) {
          System.out.println(vo);
      }
%>  
<%-- <%
  List<LoanListDTO> list = (List<LoanListDTO>)request.getAttribute("list");
    SearchDTO searchCon = (SearchDTO)request.getAttribute("vo");
  %> --%>
<%-- searchCon:<%=searchCon%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
//<meta name="viewport" content="width=device-width, initial-scale=1">
//<link rel="stylesheet" href="/WEB02/assets/css/bootstrap.css">
<title>북북 도서관</title>
<link rel="stylesheet" href="bookbook.css">
<link rel="stylesheet" href="book_board.css">
<script src="/IKUZO2/assets/js/jquery_3_7_1.js"></script>

<!-- 호출 -->  
<script>
document.addEventListener("DOMContentLoaded", function(){
	//조회버튼 객체생성
	const doRetrieveBtn = document.querySelector("#doRetrieve");
	
	//html객체를 자바스크립트에서 생성
	const searchWord = document.querySelector("#search_word");
	
	//이벤트 핸들러
	
});//--addEventListener "DOMContentLoaded" end


</script>
</head>

<body>
<!-- header 시작  -->  
<%@ include file="header.jsp" %>
<!-- header 끝  -->
	
<!-- container -->
<section class="container">
    <div class="inner-container">
       <div class="page-title-group">
          <h2 class="page-title">마이 페이지</h2>
       </div>

        
    <div class="page-list-group">
        <div class="page-list-inner">
            <div class="active">
                <a href="myPage01.jsp">대출 목록</a>
            </div>
            <div>
                <a href="myPage02.jsp">즐겨 찾기</a>
            </div>
        </div>    
    </div>

<div class="category-box">
    <div class="category-wrap">
        <div class="active">
            <a href="#">회원정보 수정</a>
        </div>
        <!-- <div class="tab-list-cell">
            <a href="#">공지사항</a>
        </div>
        <div class="tab-list-cell">
            <a href="#">도서관 소식</a>
        </div> -->
    </div>

    <div class="category-wrap category-wrap2">
        <form action="#">
            <select>
                <option value="" selected="selected">전체</option>
                <option value="post_title">도서제목</option>
                <option value="post_content">장르</option>
                <option value="post_author">작가</option>
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
            <th class="rent_num">no</th>
            <th class="rent_book">도서제목</th>
            <th class="rent_genre">장르</th>
            <th class="rent_author">작가</th>
        </tr>
    </thead>
    <tbody>
  
     <%
       for(LoanListDTO vo :list) {
       %>
        <tr>
            <td class="rent_num"><%=vo.getNum()%></td>  
            <td class="user_id"><%=vo.getBookName()%></td>
            <td class="user_name"><%=vo.getGenreName()%></td>
            <td class="rent_author"><%=vo.getAuthor()%></td>
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
<script src="check.js"></script>
</body>
</html>