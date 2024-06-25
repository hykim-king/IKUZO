<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.pcwk.ehr.board.BoardDao" %>
<%@ page import="com.pcwk.ehr.board.BoardDTO" %>
<%@ include file="/jsp/common.jsp" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 편집</title>
    <script src="/IKUZO/assest/js/jquery_3_7_1.js"></script>
    <script src="${pageContext.request.contextPath}/asset/js/jquery_3_7_1.js"></script>
  <link rel="stylesheet" href="/IKUZO/assest/css/bookbook.css">
    <link rel="stylesheet" href="/IKUZO/assest/css/book_board.css">
<<<<<<< HEAD
=======
    <script src="/IKUZO/assest/js/jquery_3_7_1.js"></script>
>>>>>>> 1373482124dba5b256277eb4d08859657af9d57e
</head>
<script>
$(document).ready(function() {
    // 수정 버튼 클릭 시 처리
    $('#doUpdate').click(function(event) {
        var updatedTitle = $('#title').val();
        var updatedContents = $('#contents').val();
        doUpdate(updatedTitle, updatedContents); // 수정 함수 호출
    });

    // 삭제 버튼 클릭 시 처리
    $('#doDelete').click(function(event) {
        doDelete(); // 삭제 함수 호출
    });

    // 목록 버튼 클릭 시 처리
    $('#moveToList').click(function(event) {
        moveToList(); // 목록으로 이동 함수 호출
    });
});

function moveToList() {
    console.log('moveToList()');
    alert("게시 목록으로 이동 합니다.");
    window.location.href = "/IKUZO/jsp/board01.jsp";
}

function doUpdate(updatedTitle, updatedContents) {
    console.log('doUpdate()');

    var seq = $('#seq').text().trim(); // seq 값을 가져옵니다.

    // 필수 입력값 체크
    if (seq.length === 0) {
        alert('Seq를 확인 하세요.');
        return;
    }
    if (updatedTitle.trim() === '') {
        alert('제목을 확인 하세요.');
        return;
    }
    if (updatedContents.trim() === '') {
        alert('내용을 확인 하세요.');
        return;
    }

    $.ajax({
        type: "POST",
        url: "/IKUZO/ikuzo/board.ikuzo",
        async: true,
        dataType: "html",
        data: {
            "work_div": "doUpdate",
            "seq": seq,
            "title": updatedTitle,
            "contents": updatedContents
        },
        success: function(response) {
            console.log("success data:" + response);

            // 서버에서 받은 응답을 처리
            if (response) {
                try {
                    const messageVO = JSON.parse(response);
                    console.log("messageVO.messageId:" + messageVO.messageId);
                    console.log("messageVO.msgContents:" + messageVO.msgContents);

                    if (messageVO.messageId === "1") {
                        alert(messageVO.msgContents);
                        window.location.href = "/IKUZO/jsp/board01.jsp";
                    } else {
                        alert(messageVO.msgContents);
                    }

                } catch (e) {
                    console.error("JSON 파싱 에러:", e);
                }
            } else {
                console.warn("response가 null 혹은 undefined.");
                alert("서버로부터 응답을 받지 못했습니다.");
            }
        },
        error: function(data) {
            console.error("error:", data);
            alert("서버와의 통신 중 에러가 발생했습니다.");
        }
    });
}

function doDelete() {
    console.log('doDelete()');
    var seq = $('#seq').text().trim(); // seq 값을 가져옵니다.

    // seq 값 체크
    if (seq.length === 0) {
        alert('Seq를 확인 하세요.');
        return;
    }

    if (!confirm('삭제 하시겠습니까?')) {
        return;
    }

    $.ajax({
        type: "GET",
        url: "/IKUZO/ikuzo/board.ikuzo",
        async: true,
        dataType: "html",
        data: {
            "work_div": "doDelete",
            "seq": seq
        },
        success: function(response) {
            console.log("success response:" + response);
            const messageVO = JSON.parse(response);
            console.log("messageVO.messageId:" + messageVO.messageId);
            console.log("messageVO.msgContents:" + messageVO.msgContents);

            if (messageVO.messageId === "1") {
                alert(messageVO.msgContents);
                window.location.href = "/IKUZO/jsp/board01.jsp";
            } else {
                alert(messageVO.msgContents);
            }
        },
        error: function(data) {
            console.log("error:" + data);
            alert("서버와의 통신 중 에러가 발생했습니다.");
        }
    });
}
</script>
<body>
<!-- container -->
<div class="container">
  
  <!-- 제목 -->
  <div class="page-header  mb-4">
    <h2>게시글 편집</h2>
  </div>
  <!--// 제목 end ------------------------------------------------------------->
  <!-- 버튼 -->
  <div class="mb-2 d-grid gap-2 d-md-flex justify-content-md-end">
      <input type="button" value="목록" class="btn btn-primary" id="moveToList">
      <input type="button" value="수정" class="btn btn-primary" id="doUpdate">
      <input type="button" value="삭제" class="btn btn-primary" id="doDelete">
  </div>
  <!--// 버튼 ----------------------------------------------------------------->
  <% 
    BoardDTO board = (BoardDTO) request.getAttribute("board");
  if(board == null) {
      board = (BoardDTO) session.getAttribute("boardDetail");
  }
  if(board  != null){
    String title = board.getTitle();
    String contents = board.getContents();
    int seq = board.getSeq();
 %>
  <!-- form -->
     <form id="boardForm">
    <div class="mb-3">
        <label for="title" class="form-label">제목:</label>
        <input type="text" class="form-control" id="title" name="title" value="<%= title %>">
    </div>
    <div class="mb-3">
        <label for="contents" class="form-label">내용:</label>
        <textarea class="form-control" id="contents" name="contents" rows="5"><%= contents %></textarea>
    </div>
    <div class="mb-3">
        <label for="seq" class="form-label">고유번호:</label>
        <span id="seq"><%= seq %></span>
    </div>
  </form>
  <%} %>
  
  <!--// form end -->
</div>
<!-- //container -->
<!-- footer 시작  -->
<%@ include file="footer.jsp" %>
<!-- footer 끝  -->

</body>
</html>