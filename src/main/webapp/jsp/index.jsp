<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.pcwk.ehr.login.LoginDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>북북 도서관</title>
<link rel="stylesheet" href="/IKUZO/assest/css/bookbook.css">
<link rel="stylesheet" href="/IKUZO/assest/css/index.css">
<script>
    function loginAndUpdateGreeting() {
        $.ajax({
            url: '<%= request.getContextPath() %>/login.do',
            type: 'POST',
            data: {
                work_div: 'login',
                user_id: $('#user_id').val(),
                user_pw: $('#user_pw').val()
            },
            success: function(response) {
                if(response === "성공") {
                    $.get('<%= request.getContextPath() %>/getUserName.do', function(data) {
                        $('#headerMenu').append('<li><p>' + data + '님 환영합니다</p></li>');
                    });
                } else {
                    alert(response);
                }
            },
            error: function(xhr, status, error) {
                console.error('로그인 실패:', error);
                alert('로그인 중 오류가 발생했습니다.');
            }
        });
    }
</script>

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
                    <%
                        LoginDTO user = (LoginDTO) session.getAttribute("user");
                        if (user != null) {
                    %>
                        <p id = "usernamePost"><%= user.getUserName() %>님 환영합니다</p>
                    <%
                        } else {
                    %>
                        <p id ="usernamePost" ><a href = "login.jsp">로그인이 필요합니다</a></p>
                    <%
                        }
                    %>
                </div>
            </div>
            
            <div class="boardGroup">
                <div id="noticeBoard1" class="listWrap">
                    <h2>공지사항</h2>
                    <ul>
                        <li>
                            <a>공지사항 1</a>
                            <span class="date">2022.02.22</span>
                        </li>
                        <li>
                            <a>공지사항 2</a>
                            <span class="date">2022.02.22</span>
                        </li>
                        <li>
                            <a>공지사항 3</a>
                            <span class="date">2022.02.22</span>
                        </li>
                    </ul>
                    <p class="more">
                        <a href="board01.jsp">
                            <img src="/IKUZO/assest/img/iconMore.png">
                        </a>
                    </p>
                </div>
                
                <div id="noticeBoard2" class="listWrap">
                    <h2>소통마당</h2>
                    <ul>
                        <li>
                            <a>공지사항 1</a>
                            <span class="date">2022.02.22</span>
                        </li>
                        <li>
                            <a>공지사항 2</a>
                            <span class="date">2022.02.22</span>
                        </li>
                        <li>
                            <a>공지사항 3</a>
                            <span class="date">2022.02.22</span>
                        </li>
                    </ul>
                    <p class="more2">
                        <a href="board01.jsp">
                            <img src="/IKUZO/assest/img/iconMore.png">
                        </a>
                    </p>
                </div>
            </div>
            
            <div class="bookListWrap">
   <!--              <div id="bookShowList" >
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
                </div> -->
            </div>
        </div>
    </section>

    <!-- footer 시작  -->
    <%@ include file="footer.jsp" %>
    <!-- footer 끝  -->

</body>
</html>
