<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ include file="/jsp/common.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<script src="/IKUZO/assest/js/jquery_3_7_1.js"></script>

<script>
document.addEventListener("DOMContentLoaded", function(){
    console.log('DOMContentLoaded ');
    
    const doSaveBtn = document.querySelector("#doSave");
    const moveToListBtn = document.querySelector("#moveToList");
    const workDiv = document.querySelector("#work_div"); // 작업구분
    const title = document.querySelector("#title"); // 제목
    const isAdmin = document.querySelector("#isAdmin"); // 관리자 여부
    const contents = document.querySelector("#contents"); // 내용
    
    doSaveBtn.addEventListener("click", function(){
        $.ajax({
            type: "POST", 
            url: "/IKUZO/ikuzo/board.ikuzo",
            async: true, // 비동기 통신
            dataType: "html",
            data: {
                "work_div": "ajaxDoSave",
                "title": title.value,
                "isAdmin": "N",
                "contents": contents.value
            },
            success: function(data){ // 통신 성공
                console.log("success data:" + data);
                const messageVO = JSON.parse(data);
                console.log("messageId:" + messageVO.messageId);
                console.log("msgContents:" + messageVO.msgContents);
                
                if(messageVO.messageId === "1"){
                    alert(messageVO.msgContents);
                    // 등록 성공 시 목록 페이지로 이동
                    window.location.href = "/IKUZO/jsp/board02.jsp";
                } else {
                    alert(messageVO.msgContents);
                }
            },
            error: function(data){ // 실패 시 처리
                console.log("error:" + data);
            }
        }); // ajax end
    });

    moveToListBtn.addEventListener("click", function(){
        // 목록 버튼 클릭 시 boardlist_01.jsp 페이지로 이동
        window.location.href = "/IKUZO/jsp/board01.jsp";
    });
});   
</script>
</head>
<body>
<div>
    <!-- 제목 -->
    <div>
      <h2>게시-등록</h2>
      <hr/>
    </div>
    <!--//제목 --------------------------------------------------------------->
    
    <!-- 버튼 -->
    <div>
      <input type="button" value="목록"  id="moveToList">
      <input type="button" value="등록"  id="doSave">
    </div>
    <!--//버튼 --------------------------------------------------------------->
    
    <!-- form -->
    <form action="#" name="regForm" id="regForm" method="get">
       <input type="hidden"  name="work_div" id="work_div">
       <div>
        <label for="title">제목</label>
        <input type="text" name="title" id="title"  maxlength="75" required="required">
       </div>
       
       <div>

       </div>
       
       <div>
        <label for="contents">내용</label>
        <textarea rows="7" cols="10" id="contents" name="contents"></textarea>
       </div>              
    </form>
    <!--//form --------------------------------------------------------------->
</div>    
</body>
</html>
