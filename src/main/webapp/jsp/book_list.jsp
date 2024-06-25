<%@page import="com.pcwk.ehr.booklist.BookDTO"%>
<%@page import="java.util.List" %>
<%@page import="com.pcwk.ehr.cmn.SearchDTO"%>
<%@page import="com.pcwk.ehr.cmn.StringUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/common.jsp" %>    
<%
  List<BookDTO> list  = (List<BookDTO>)request.getAttribute("list");
  SearchDTO searchCon = (SearchDTO)request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="/WEB02/assets/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="/IKUZO/assest/css/bootstrap.css">
<title>Book List</title>
<link rel="stylesheet" href="/IKUZO/assest/css/bookbook.css">
<link rel="stylesheet" href="/IKUZO/assest/css/book_list.css">
<script>
document.addEventListener("DOMContentLoaded", function(){
	console.log("DOMContentLoaded()");
	
    //도서 한권 선택
	const books = document.querySelectorAll(".book");
    
  function doRetrieve() {
	  console.log("doRetrieve()");
	  
	  let row = document.getElementById("row_book");
	  
	  row.work_div.value = "doRetrieve";
	  row.page_no.value = "1";
	  
	  console.log("row.search_div.value:"+row.search_div.value);
	  console.log("row.page_size.value:"+row.page_size.value);
	  
	  
	  
	  row.submit();
  }
	
	books.forEach(function(book){
        //double click
        book.addEventListener('click',function(){
          console.log('book click');

        let book_code= this.querySelector('.book_db .book_code').textContent.trim();
          console.log('코드 : '+book_code);
        window.location.replace("/IKUZO/ikuzo/book.ikuzo?work_div=doSelectOne&bookcode="+book_code);
         });
        
    }); 
	
    
});

	function pageRetrieve(url,pageNo){
		 console.log("url:"+url);
		 console.log("pageNo:"+pageNo);
		
		let row = document.getElementById("book_frm");
		row.work_div.value = "doRetrieve";
		
		row.page_no.value = pageNo;
		
		row.action = url;
		
		row.submit();
	}
	
</script>
</head>
<body>

  <!-- header 시작  -->  
  <%@ include file="header.jsp" %>
  <!-- header 끝  -->
  
<section class="main">


<div class="genre">
 <nav class="b_nav">
    <button type="button" class="btn btn-success" onclick="location.href='#'">전체</button>
    <button type="button" class="btn btn-success" onclick="location.href='#'">소설</button>
    <button type="button" class="btn btn-success" onclick="location.href='#'">시/에세이</button>
    <button type="button" class="btn btn-success" onclick="location.href='#'">가정/육아</button>
    <button type="button" class="btn btn-success" onclick="location.href='#'">건강</button>
    <button type="button" class="btn btn-success" onclick="location.href='#'">자기계발</button>
    <button type="button" class="btn btn-success" onclick="location.href='#'">역사/문화</button>
    <button type="button" class="btn btn-success" onclick="location.href='#'">과학</button>
    <button type="button" class="btn btn-success" onclick="location.href='#'">외국어</button>
    <button type="button" class="btn btn-success" onclick="location.href='#'">만화</button>
    <button type="button" class="btn btn-success" onclick="location.href='#'">여행</button>
</nav>
</div>

  <div class="row_book">
  <form action="#" id="book_frm">
  <input type="hidden" name="work_div" id="work_div" placeholder="작업구분">
  <input type="hidden" name="page_no" id="page_no"   placeholder="페이지번호">
<% 
     if(null != list && list.size() > 0){
     for(BookDTO vo : list){ 
     %>
    <div class="book" id="book_list">
      <div class = "book_db"  style = "display : none">
      <p class="book_code"><%=vo.getBookCode() %></p>
      
      </div>
      <img class="image" alt="책" src="/IKUZO/assest/img/book.png">
      <div class="genre"><%=vo.getGenreName()%></div>
      <div class="pub-date"><%=vo.getBookPubDate() %></div>
      <div class="author">작가:<%=vo.getAuthor() %></div>
      <h2 class="book-name">
      <a class="name" ><%=vo.getBookName() %></a>
      </h2>
    </div>
   <% } //for
       }//if       
   %> 
   </form>      
  </div>
  
  <nav aria-label ="Page navigation examole">
     <%
      //총글수
      SearchDTO pageingVO = (SearchDTO)request.getAttribute("vo");
      int totalCnt = pageingVO.getTotalCnt();
		   
      //바닥 글수
      int bottomCnt = pageingVO.getBottomCount();
      
      //페이지 사이즈
      int pageSize = pageingVO.getPageSize();
      
      //페이지 번호
      int pageNo = pageingVO.getPageNo();
      
      //pageRetrieve(url,2);
      out.print(StringUtil.renderingPaging(totalCnt, pageNo, pageSize, bottomCnt, "/IKUZO/ikuzo/book.ikuzo", "pageRetrieve"));
    %>
  </nav>
</section>
  
  <!-- footer 시작  -->
  <%@ include file="footer.jsp" %>
  <!-- footer 끝  -->
  
 <script src="/IKUZO/assest/js/bootstrap.bundle.min.js"></script> 

</body>
</html>