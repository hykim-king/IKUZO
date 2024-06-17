<%@page import="com.pcwk.ehr.mainpage.MainBoardDTO"%>
<%@page import="com.pcwk.ehr.mainpage.SearchDTO"%>
<%@page import="com.pcwk.ehr.mainpage.MainBoardDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/common.jsp" %>
<%
MainBoardDao dao = new MainBoardDao();
   SearchDTO searchVO = new SearchDTO();
   searchVO.setPageNo(1);
   searchVO.setPageSize(10);

   List<MainBoardDTO> listY = dao.doRetrieveAdimY(searchVO);
   for(MainBoardDTO voY :listY) {
                System.out.println(voY);
            }
   List<MainBoardDTO> listN = dao.doRetrieveAdimN(searchVO);
   for(MainBoardDTO voN :listN) {
                System.out.println(voN);
            }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/bookbook.css">
<link rel="stylesheet" href="../css/index.css">
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
                        <!-- 배너 이미지 목록 -->
                    </ul>
                </div>
                <div id="sectionLoginDiv">
                    <h2>나만의 도서관</h2>
                    <button>
                        <a href="http://localhost:8080/project2/jsp/login.jsp">로그인하러 가기</a>
                    </button>
                </div>
            </div>
            
            <div class="boardGroup">
                <div id="noticeBoard1" class="listWrap">
                    <h2>공지사항</h2>
                    <ul>
                    <%
                    for(MainBoardDTO voY :listY) {
                    %>
                        <li>
                            <a><%=voY.getTitle()%></a>
                            <span class="date"><%=voY.getModDt()%></span>
                        </li>
                    <%
                    }
                    %>  
                      
                    </ul>
                    <p class="more">
                        <a href="http://localhost:8080/project2/jsp/board01.jsp">
                            <img src="../img/iconMore.png">
                        </a>
                    </p>
                </div>
                
                <div id="noticeBoard2" class="listWrap">
                    <h2>소통마당</h2>
                    <ul>
                    <%
                    for(MainBoardDTO voN :listN) {
                    %>
                        <li>
                            <a><%=voN.getTitle()%></a>
                            <span class="date"><%=voN.getModDt()%></span>
                        </li>
                    <% } %>  
                    </ul>
                    <p class="more2">
                        <a href="http://localhost:8080/project2/jsp/board01.jsp">
                            <img src="../img/iconMore.png">
                        </a>
                    </p>
                </div>
            </div>
            
            <div class="bookListWrap">
                <div id="bookShowList" >
                    <ul>
                        <li>
                            <a href="" class="cover">
                                <img src="" alt="" onerror="javascript:errorImageFile(this);">
                            </a>
                            <strong class="tit"></strong>
                        </li>
                        <li>
                            <a href="" class="cover">
                                <img src="" alt="" onerror="javascript:errorImageFile(this);">
                            </a>
                            <strong class="tit"></strong>
                        </li>
                        <li>
                            <a href="" class="cover">
                                <img src="" alt="" onerror="javascript:errorImageFile(this);">
                            </a>
                            <strong class="tit"></strong>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </section>

    <!-- footer 시작  -->
    <%@ include file="footer.jsp" %>
    <!-- footer 끝  -->

</body>
</html>
