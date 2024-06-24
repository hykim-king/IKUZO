<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.pcwk.ehr.board.BoardDTO" %>
<%@ include file="/jsp/common.jsp" %> 

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 상세보기</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bookbook.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/book_board.css">
    <script src="${pageContext.request.contextPath}/asset/js/jquery_3_7_1.js"></script>
</head>
<style>
table{ border-collapse : collapse; width:100%}  /*이중선 제거*/
 th,td{
      width: 100px;
      height: 50px;
      text-align: center;
      border: 1px solid #000;
      
      vertical-align: top;  /* 위 */
      vertical-align: bottom;   /* 아래 */
      vertical-align: middle;   /* 가운데 */
      background:antiquewhite;
    }
</style>
<script>
$(document).ready(function() {
    $('.edit-button').click(function(event) {
        event.preventDefault(); // 기본 링크 동작을 막음
        var seq = $(this).data('seq'); // 클릭한 게시글의 seq를 가져옴

        $.ajax({
            type: "POST",
            url: "/IKUZO/ikuzo/board.ikuzo",
            async: true,
            dataType: "html",
            data: {
                "work_div": "viewBoardDetail",
                "seq": seq // 클릭한 게시글의 seq를 전달
            },
            success: function(data) {
                console.log("Retrieved data: ", data); // 데이터 확인 로그
                localStorage.setItem("boardData", JSON.stringify(data));
                window.location.href = "board_mng.jsp";
            },
            error: function(xhr, status, error) {
                console.log('데이터를 불러오는 중 에러가 발생했습니다.');
                console.log("상태: " + status);
                console.log("에러: " + error);
                console.log("응답 텍스트: " + xhr.responseText);
            }
        });
    });
});
</script>
<body>
<!-- header 시작  -->  
<%@ include file="header.jsp" %>
<!-- header 끝  -->

<!-- container -->
<section class="container">
    <div class="inner-container">
        <div class="page-title-group">
            <h2 class="page-title">게시글 상세보기</h2>
        </div>

        <!-- 세션에서 BoardDTO 객체 가져오기 -->
<% 
    BoardDTO board = (BoardDTO) request.getAttribute("board");
   
    if (board == null) {
        board = (BoardDTO) session.getAttribute("boardDetail");
    }

    if (board != null) {
%>
        <div id="detail_container">
          <table>
              <colgroup>
                  <col style="width: 10%">
                  <col style="width: 15%">
                  <col style="width: 10%">
                  <col style="width: 15%">
                  <col style="width: 10%">
                  <col style="width: 15%">
                  <col style="width: 10%">
                  <col style="width: 15%">
                  <col style="width: 10%">
                  <col style="width: 15%">
              </colgroup>
              <thead>
                  <tr>
                      <th>제목</th>
                      <td><%= board.getTitle() %></td>
                      <th>작성자</th>
                      <td><%= board.getRegId() %></td>
                      <th>작성일</th>
                      <td><%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(board.getRegDt()) %></td>
                      <th>조회수</th>
                      <td><%= board.getReadCnt() %></td>
                      <th>개시글 고유번호</th>
                      <td><%= board.getSeq()%></td>
                  </tr>
              </thead> 
              <tbody>
                  <tr>
                      <th>내용</td>
                      <td colspan="9" style="text-align: left; padding-left: 10px;"><%= board.getContents() %></td>
                  </tr>
              </tbody>
          </table>  
          <div class="btn-group">
             <a href="board01.jsp" class="btn">목록으로</a>
              <a href="#" class="edit-button" data-seq="<%= board.getSeq() %>">편집</a>
          </div>
        </div>
        <% } else { %>
            <div>게시글 데이터를 불러올 수 없습니다.</div>
        <% } %>
    </div>
</section>
<!-- //container -->

<!-- footer 시작  -->
<%@ include file="footer.jsp" %>
<!-- footer 끝  -->

</body>
</html>
