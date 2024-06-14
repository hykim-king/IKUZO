<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/project22/css/bookbook.css">
<link rel="stylesheet" href="/project22/css/index.css">
<script src="/project2/js/index.js"></script>
</head>
<body>

	<!-- header 시작  -->  
	<%@ include file="header.jsp" %>
	<!-- header 끝  -->
	
	
	
  <section id="section">
  <div id="container">
  
  
    <div id="sectionDiv">
      <div id="sectionBannerDiv">
        <ul>
         
          <li><a><img alt="배너1" src="/project22/img/banner01.png" id = "banner001"></a></li>
          <li><a><img alt="배너2" src="/project22/img/banner02.png" id = "banner002"></a></li>
          <li><a><img alt="배너3" src="/project22/img/banner03.png" id = "banner003"></a></li>
          <li><a><img alt="배너4" src="/project22/img/banner04.png" id = "banner004"></a></li>
          <li><a><img alt="배너5" src="/project22/img/banner05.png"  id = "banner005"></a></li>
        </ul>
      </div>
      <div id="sectionLoginDiv">
        <h2>나의 도서관</h2>
        <button>
          <a href="http://localhost:8080/project22/jsp/login.jsp">로그인하러 가기</a>
        </button>
      </div>
    </div>
    
    
    
    <div id= "board">
	    <div id="noticeBoard1" class = "noticeBoard">
	      <h2>공지사항</h2>
	      <ul>
	        <li>공지사항 1</li>
	        <li>공지사항 2</li>
	        <li>공지사항 3</li>
	      </ul>
	    </div>
	    <div  id="noticeBoard2" class = "noticeBoard">
        <h2>공지사항 2</h2>
        <ul>
          <li>공지사항 2-1</li>
          <li>공지사항 2-2</li>
          <li>공지사항 2-3</li>
        </ul>
      </div>
    
      </div>
      </div>
      
      <div id= "section3">
        <div id ="showBooks">
          추천도서
        
        
        
        
        </div>
      
      
      
      
      </div>
      
      
      
      
  </section>


	<!-- footer 시작  -->
	<%@ include file="footer.jsp" %>
	<!-- footer 끝  -->

</body>
</html>