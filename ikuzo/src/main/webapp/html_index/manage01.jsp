<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="bookbook.css">
<link rel="stylesheet" href="book_board.css">
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
            <div class="active">
                <a href="manage01.jsp">회원 관리</a>
            </div>
            <div>
                <a href="manage02.jsp">도서 관리</a>
            </div>
        </div>    
    </div>

<div class="category-box">
    <div class="category-wrap">
        <div class="redAlert">
            <a href="#">삭제</a>
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
                <option value="post_title" >제목</option>
                <option value="post_content" >내용</option>
                <option value="post_author" >작성자</option>
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
            <th class="user_id">아이디</th>
            <th class="user_name">이름</th>
            <th class="user_group">그룹</th>
            <th class="rent_date1">대출일자</th>
            <th class="rent_date2">반납일자</th>
            <th class="now_rent_yn">미납도서 유무</th>
            <th class="count">연체금액</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td class="checkbox"><input type="checkbox" class="chk"></td>
            <td class="user_id">아이디1</td>
            <td class="user_name">이름1</td>
            <td class="user_group">회원</td>
            <td class="rent_date1">YYYY/MM/DD</td>
            <td class="rent_date2">YYYY/MM/DD</td>
            <td class="now_rent_yn">N</td>
            <td class="count">0</td>
        </tr>                           
        <tr>
            <td class="checkbox"><input type="checkbox" class="chk"></td>
            <td class="user_id">아이디2</td>
            <td class="user_name">이름2</td>
            <td class="user_group">회원</td>
            <td class="rent_date1">YYYY/MM/DD</td>
            <td class="rent_date2">YYYY/MM/DD</td>
            <td class="now_rent_yn">N</td>
            <td class="count">0</td>
        </tr>                           
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