<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header id="header">

    <div>
        <h1 id="logo">
            <a href="index.jsp">
                <img alt="logoImg" src="/IKUZO/assest/img/logoImg.png" id="logoImg">
            </a>
        </h1>
    </div>

    <div id="headerMenu">
        <ul>
            <% if (session.getAttribute("user") == null) { %>
                <li>
                    <a href="login.jsp">로그인</a>
                </li>
                <li>
                    <a href="join.jsp">회원가입</a>
                </li>
            <% } else { %>
                <li>
                    <a href="myPage01.jsp">마이페이지</a>
                </li>
                <li>
                    <a href="myPageUpdate.jsp">회원정보수정</a>
                </li>
                
                <% if (session.getAttribute("isAdmin") != null && ((String)session.getAttribute("isAdmin")).equals("Y")) { %>
						    <li>
						        <a href="manage01.jsp">관리자페이지</a>
						    </li>
						    <% } %>
                <li>
                   <a id="gnb_logout_button" href="#" onclick="logoutAndRedirect()">로그아웃</a>
                </li>
            <% } %>
        </ul>
    </div>

    <form id="headerSearchForm" action="검색할 서블릿 주소" method="GET"> <!-- 검색할 서블릿 주소를 설정하세요 -->
        <legend>검색</legend>
        <select id="searchDiv" name="searchDiv">
            <option value="10" selected>제목</option>
            <option value="20">저자</option>
            <option value="30">출판사</option>
        </select>
        <input type="text" id="searchInput" name="keyword">
        <button type="submit"><p>검색하기</p></button>
    </form>
</header>

<nav>
    <ul>
        <li><a href="#">자료검색</a></li>
        <li><a href="#">베스트셀러</a></li>
        <li>
            <a href="#">커뮤니티</a>
            <ul id="navSubMenu3">
                <li><a href="board01.jsp">공지사항</a></li>
                <li><a href="board02.jsp">소통마당</a></li>
            </ul>
        </li>
    </ul>
</nav>

<script>
    function logoutAndRedirect() {
        $.ajax({
            url: '<%= request.getContextPath() %>/login.do',
            type: 'POST',
            data: { work_div: 'logout' },
            success: function(response) {
                // 로그아웃 성공 후 메인 페이지로 리다이렉트
                window.location.href = '<%= request.getContextPath() %>/index.jsp';
            },
            error: function(xhr, status, error) {
                console.error('로그아웃 실패:', error);
                alert('로그아웃 중 오류가 발생했습니다.');
            }
        });
    }
</script>
