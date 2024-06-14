<%@page import="com.pcwk.ehr.board.SearchDTO"%>
<%@page import="com.pcwk.ehr.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/board/common.jsp" %>    
<%
    List<BoardDTO> list =(List<BoardDTO>)request.getAttribute("list");

    SearchDTO searchCon =(SearchDTO)request.getAttribute("vo");
    %>  
searchCon : <%=searchCon%>
cPath:<%=cPath%>
<%-- <%
BoardDao dao = new BoardDao();
SearchDTO searchVO = new SearchDTO();
searchVO.setPageNo(1);
searchVO.setPageSize(10);

List<BoardDTO> list = dao.doRetrieve(searchVO);

for(BoardDTO vo :list) {
    System.out.println(vo);
}
%>   --%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>I hate title</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="/WEB02/assest/images/favicon.ico" type = "image/x-icon">
<link rel="stylesheet" href="/WEB02/assest/css/bootstrap.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<!-- 버튼 동작 -->  
<script>
document.addEventListener("DOMContentLoaded", function(){
	  // 등록 버튼
	  const moveToRegBtn = document.querySelector("#moveToReg");  
	  
	  // 조회 버튼
	  const doRetrieveBtn = document.querySelector("#doRetrieve");
	  
	  // html 객체를 자바스크립트에서 생성
	  const searchWord = document.querySelector("#search_word");

	  // table 자식 rows(tbody tr) 선택
	  const rows = document.querySelectorAll("#boardList tbody tr");
	  
	  const buttons = document.querySelectorAll(".btn-outline-success");
	  
	  //이벤트 핸들러 
	  buttons.forEach(function(button){
		  button.addEventListener('click', function(){
			  let seqValue = this.getAttribute('td.seq1 data-hidden-info').trim();			 
			  
			  doSelectOne(seqValue);
		  }); // click
	  }); // forEach
	  
	  rows.forEach(function(row){
	    row.addEventListener('click', function(){
	      console.log("row click!");
	      
	      // this(tr) 자식 (td : seq 클래스)
	      let seqValue = this.querySelector('td.seq2').textContent.trim();
	      console.log("seqValue : ", seqValue);	      

	      doSelectOne(seqValue);
	    });
	  }); // forEach 끝
	  
	  /* // jquery 선택
	  $('#boardList > tbody').on('click', 'tr', function(){
	    console.log('row click');
	    
	    let pTr = $(this);
	    let tdArray = pTr.children();
	    let seq = tdArray.eq(5).text();
	    
	    console.log(seq);
	  }); */

	  moveToRegBtn.addEventListener("click", function(event){
	      console.log("moveToRegBtn click");
	      
	      // 폼 요소 선택
	      let frm = document.getElementById("board_frm");

	      frm.work_div.value = "moveToReg";
	      
	      console.log("frm.work_div.value : " + frm.search_div.value);
	        
	      console.log("frm.action : " + "<%=cPath%>" + "/board/board.do");
	      frm.action = "<%=cPath%>" + "/board/board.do";
	      
	      frm.submit();     
	    });
	  
	  doRetrieveBtn.addEventListener("click", function(event){
	    console.log("click yes!");
	    doRetrieve();   
	  });
	  
	  searchWord.addEventListener("keydown", function(event){
	    console.log("keydown", event.key, event.keyCode);
	    
	    if(event.keyCode == 13){
	      console.log(`input.value:${input.value}`);
	      doRetrieve();
	    }
	  });

	});
	
	function doSelectOne(seqValue){
		// 폼 요소 선택
    let frm = document.getElementById("board_frm");
    
    // 폼 데이터 설정
    frm.work_div.value = "doSelectOne";
    
    //seq
    frm.seq.value = seqValue;
    frm.action = "<%=cPath%>" + "/board/board.do";
    
    // 폼 제출
    frm.submit();
	}

	function doRetrieve(){
	  console.log("doRetrieve");
	  
	  // 폼 요소 선택
	  let frm = document.getElementById("board_frm"); 
	  frm.work_div.value = "doRetrieve";
	  frm.page_no.value = "1";
	  
	  console.log("frm.search_div.value : " + frm.search_div.value);
	  console.log("frm.search_word.value : " + frm.search_word.value);
	  console.log("frm.page_size.value : " + frm.page_size.value);
	    
	  console.log("frm.action : " + "<%=cPath%>" + "/board/board.do");
	  frm.action = "<%=cPath%>" + "/board/board.do";
	  
	  frm.submit();
	}
</script>
</head>
<body>
<div class="container">
  <!-- 제목 start -->
  <div class="page-header" style = "padding-top : 10px;"> 
    <h3>게시판_목록</h3>
  </div> <!-- container-lg --> 
  <hr>
  <!-- 제목 end -->
  
  <!-- content -->
  <!-- 행을 만드는 태그는 tr과 셀을 만드는 태그 td, th -->
  <img style = "width : 100%; height : auto;" alt="#top" src="../images/top.jpg">
  
  <!-- 검색 -->
  <div class = "row">
  <p>searchDiv : <%=searchCon.getSearchDiv()%></p>
  <p>page : ${page}</p>
  <form class="form-inline" action="<%=cPath%>/board/board.do" name="board_frm" method="get" id="board_frm">
    <input type="hidden" name="work_div" id="work_div" placeholder="작업구분">
    <input type="hidden" name="page_no" id="page_no" placeholder="페이지 번호">
    <input type="hidden" name="seq" id="seq" placeholder="순번">
    <div class = "form-group">
      <label for = "search_div">구분</label>
      
      <select class = "form-select" style = "margin : 10px 0; cursor : pointer;" name = "search_div" id="search_div">
        <option value="">전체</option>
        <option value="10" <%if(null != searchCon && "10".equals(searchCon.getSearchDiv())){out.print("selected");}%>>제목</option>
        <option value="20" <%if(null != searchCon && "20".equals(searchCon.getSearchDiv())){out.print("selected");}%>>내용</option>
        <option value="30" <%if(null != searchCon && "30".equals(searchCon.getSearchDiv())){out.print("selected");}%>>아이디</option>
        <option value="40" <%if(null != searchCon && "40".equals(searchCon.getSearchDiv())){out.print("selected");}%>>제목+내용</option>
        <option value="50" <%if(null != searchCon && "50".equals(searchCon.getSearchDiv())){out.print("selected");}%>>SEQ</option>
      </select>
      
      <select class = "form-select" style = "margin : 10px 0; cursor : pointer;" name = "page_size" id="page_size">
        <option value="10" <%if(null != searchCon && 10 == searchCon.getPageSize()){out.print("selected");}%>>10페이지</option>
        <option value="20" <%if(null != searchCon && 20 == searchCon.getPageSize()){out.print("selected");}%>>20페이지</option>
        <option value="50" <%if(null != searchCon && 50 == searchCon.getPageSize()){out.print("selected");}%>>50페이지</option>
        <option value="100" <%if(null != searchCon && 100 == searchCon.getPageSize()){out.print("selected");}%>>100페이지</option>
        <option value="200" <%if(null != searchCon && 200 == searchCon.getPageSize()){out.print("selected");}%>>200페이지</option>
      </select>
      
      <input class = "form-control" type = "search" name = "search_word" id="search_word" placeholder = "검색어" value="<%if(null != searchCon){out.print(searchCon.getSearchWord());}%>">
      
    </div>
  </form>
  </div>
  <!-- 검색 끝 -->

  <!-- 버튼 -->
  <div class = "d-flex justify-content-end">
    <button class = "btn btn-light" type = "submit" id="doRetrieve">조회</button>
    <input class = "btn btn-success" type="button" value = "등록" id = "moveToReg">
  </div>
  <!-- 버튼 -->
  
  <div>
  <table id = "boardList" class="table table-hover" style="text-align: center; margin-top : 10px;">
    <thead>
      <tr class = "table-light">
        <th>No</th>
        <th>제목</th>
        <th>내용</th>
        <th>등록자</th>
        <th>등록일</th>
        <th>조회수</th>
        <th>관리</th>
        <th style = "display : none;">SEQ</th>
      </tr>
    </thead>
    <tbody style = "cursor : pointer;">
    <%
    if(null != list && list.size() > 0){ // if
    	  for(BoardDTO vo :list) { // for
    %>
    <tr>
      <td><%=vo.getNum()%></td>
      <td><%=vo.getTitle()%></td>
      <td><%=vo.getContents()%></td>
      <td><%=vo.getModId()%></td>
      <td><%=vo.getModDt()%></td>
      <td><%=vo.getReadCnt()%></td>
      <td class = "seq1 text-center">
        <div>          
          <input type = "button" value = "수정" class = "btn btn-outline-success btn-sm" data-hidden-info = "<%=vo.getSeq()%>">
        </div>
      </td>
      <td class = "seq2" style = "display : none;"><%=vo.getSeq()%></td>
    </tr>
    <% 
       } // for
    } // if
    %>
    </tbody>
  </table>
  <!-- content end -->
  </div>
</div> <!-- container end -->  
<script src = "/WEB02/assest/js/bootstrap.bundle.min.js"></script>  
</body>
</html>