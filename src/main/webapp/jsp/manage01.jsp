<%@page import="com.pcwk.ehr.manageuser.SearchDTO"%>
<%@page import="com.pcwk.ehr.manageuser.ManageUserDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/common.jsp" %>       
<%
List<ManageUserDTO> list =(List<ManageUserDTO>)request.getAttribute("list");

SearchDTO searchCon =(SearchDTO)request.getAttribute("vo");
%> 
searchCon : <%=searchCon%>
cPath:<%=cPath%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet" href="/IKUZO/assest/css/bookbook.css">
<link rel="stylesheet" href="/IKUZO/assest/css/book_manage.css">
<script>
document.addEventListener("DOMContentLoaded", function(){
	  // isEmpty 함수 정의
    function isEmpty(value) {
        return (value == null || value.length === 0);
    }
	
    // 작업 구분
    const workDiv = document.querySelector("#work_div");
	
	  // 회원 관련 변수
	  const userId = document.querySelector("#seq");

    // 페이지 이동 버튼
    const manageUserbtn = document.querySelector("#manageUserbtn");	
    const manageBookbtn = document.querySelector("#manageBookbtn");	

    // 삭제 버튼
    const deletebtn = document.querySelector("#deletebtn");	
    
    // 이벤트 핸들러 시작
    manageUserbtn.addEventListener('click', function(event){ // 회원관리 페이지 이동 버튼 클릭
       moveToMuser();
    }); // click
    manageBookbtn.addEventListener('click', function(event){ // 도서관리 페이지 이동 버튼 클릭
       moveToMbook();
    }); // click
    deletebtn.addEventListener('click', function(event){ // 회원삭제 버튼 클릭
    	doDeleteUser(); 
    }); // click    
    // 이벤트 핸들러 끝
    
    // 함수 시작
    function moveToMuser(){ // 회원관리페이지이동 함수
    	console.log("userbtn");
      window.location.href = "/IKUZO/ikuzo/manage01.ikuzo?work_div=doRetrieve";
    }    
    function moveToMbook(){ // 도서관리페이지이동 함수
    	console.log("bookbtn");
      window.location.href = "/IKUZO/ikuzo/manage02.ikuzo?work_div=doRetrieve";
    }    
    
    // 회원 삭제 함수 시작
    function doDeleteUser(){        
        console.log('doDeleteUser');
        workDiv.value = 'doDelete';
        
        // tbody 내의 모든 행을 가져옵니다
        const rows = document.querySelectorAll(".notice-board tbody tr");
        let userId = "";
        const userIds = [];

        // 각 행을 반복 처리합니다
        rows.forEach(function(row) {
            // 현재 행의 체크박스를 찾습니다
            const checkbox = row.querySelector("td.checkbox input.chk");

            // 체크박스가 체크되어 있는지 확인합니다
            if (checkbox.checked) {
                // 현재 행의 user_id를 찾습니다
                userId = row.querySelector("td.user_id").innerText;
                userIds.push(userId);
            }
        });

        // userIds를 출력합니다 (필요에 따라 이 배열을 사용할 수 있습니다)
        console.log(userId);        
        
        // userIds 체크 여부
        if(isEmpty(userIds) == true){
            alert('체크된 회원이 존재하지 않습니다. 잘못된 경로!');
        }else if(false == confirm('삭제 하시겠습니까?')){
        	  return;
        }  
        
        // ajax start
        $.ajax({
            type: "GET", 
            url:"/IKUZO/ikuzo/manage01.ikuzo",
            asyn:"true",
            dataType:"html",
            data:{
                "work_div":"doDelete",
                "userId": userId
            },
            success:function(response){//통신 성공
                console.log("success response:"+response);
                const messageVO = JSON.parse(response);
                console.log("messageVO.messageId:"+ messageVO.messageId);             
                console.log("messageVO.msgContents:"+ messageVO.msgContents);  
                
                if(isEmpty(messageVO) == false && "1" === messageVO.messageId){
                  alert(messageVO.msgContents);
                  window.location.href = "/IKUZO/ikuzo/manage01.ikuzo?work_div=doRetrieve";
                }else{
                  alert(messageVO.msgContents);
                }
            },
            error:function(data){//실패시 처리
                    console.log("error:"+data);
            }
        });    
      }
    // 회원 삭제 함수 끝
    // 함수 끝
});
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
          <h2 class="page-title">관리자 페이지</h2>
       </div>

        
    <div class="page-list-group">
        <div class="page-list-inner">
            <div class="active">
                <a href="#" id ="manageUserbtn">회원 관리</a>
            </div>
            <div>
                <a href="#" id ="manageBookbtn">도서 관리</a>
            </div>
        </div>    
    </div>

<div class="category-box">
    <div class="category-wrap">
        <div class="redAlert">
            <a href="#" id ="deletebtn">삭제</a>
        </div>
        <div class="active">
            <a href="#">반납</a>
        </div>
        <div>
            <a href="#">반납 취소</a>
        </div>
    </div>

    <div class="category-wrap category-wrap2">
        <form action="#">
        <input type = "hidden" name = "work_div" id = "work_div">
            <select>
                <option value="" selected="selected">전체</option>
                <option value="post_title" >아이디</option>
                <option value="post_content" >이름</option>
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
            <th class="rnum">번호</th>
            <th class="user_id">아이디</th>
            <th class="user_name">이름</th>
            <th class="user_group">그룹</th>
            <th class="now_rent_yn">미납도서 유무</th>
            <th class="count">연체금액</th>
        </tr>
    </thead>
    <tbody>
    <%
    for(ManageUserDTO vo :list) {
    %>
        <tr>
          <td class="checkbox"><input type="checkbox" class="chk"></td>
          <td class="rnum"><%=vo.getRnum()%></td>
          <td class="user_id"><%=vo.getUserId()%></td>
          <td class="user_name"><%=vo.getUserName()%></td>
          <td class="user_group"><%=vo.getIsAdmin()%></td>
          <td class="now_rent_yn"><%=vo.getRentBookYn()%></td>
          <td class="count"><%=vo.getExtraSum()%></td>
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
<script src="/IKUZO/assest/js/check.js"></script>
</body>
</html>