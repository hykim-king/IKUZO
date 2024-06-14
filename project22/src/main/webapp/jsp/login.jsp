<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOOKBOOK도서관 - 로그인 하면 다양한 서비스를 이용할 수 있어요!</title>
<link rel="stylesheet" href="/project22/css/bookbook.css">
<style>
    body {
        font-family: Arial, sans-serif;
    }
    #container{
    margin-top:40px;
		padding : 120px;
		}
    .content-wrapper {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 80vh;
    }
    .content {
        text-align: center;
        padding: 0px 0px;
    }
    .login-box {
        padding: 20px;
        width: 300px;
        margin: 0 auto;
    }
    .login-box input[type="text"], .login-box input[type="password"] {
        width: calc(100% - 20px);
        padding: 10px;
        margin: 10px 0;
    }
    .login-box input[type="submit"] {
        width: 100%;
        padding: 10px;
        background-color: #27403d;
        color: white;
        border: none;
        cursor: pointer;
    }
    .options {
        margin-top: 20px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        position: relative;
        background-color: #f1f1f1;
        padding: 40px;
    }
    .option-box {
        text-align: center;
        width: 48%;
        background-color: transparent;
    }
    .option-box button, .option-box a {
        padding: 7px;
        border: 1px solid #ccc;
        cursor: pointer;
        width: 47%;
        box-sizing: border-box;
        background-color: white;
        color: #4e4e4e;
        text-decoration: none;
        display: inline-block;
        text-align: center;
    }
    .inline-buttons {
        display: flex;
        justify-content: space-between;
        width: 100%;
    }
    .options::before {
        content: '';
        position: absolute;
        left: 50%;
        top: 0;
        bottom: 0;
        width: 1px;
        background-color: #ccc;
        transform: translateX(-50%);
    }
    .highlight {
        font-size: 20px;
        color: green;
        font-weight: bold;
    }
    .content h2 {
        font-size: 40px;
        position: relative;
        top: -329px;
        color: #27403d;
    }
    .content h3 {
        font-size: 18px;
        position: relative;
        top: -329px;
        color: #777;
    }
    .options{
    background-color: F3E4C2;
    width: 800px;
    }
    
    .highlight{
    color:#27403d;
    }
</style>
</head>
<body>

  <!-- header 시작  -->  
  <%@ include file="header.jsp" %>
  <!-- header 끝  -->
<section id = "container">
<div class="content-wrapper">
    <div class="content">
        <div class="login-box">            
            <form>
                <input type="text" placeholder="아이디를 입력하세요"><br>
                <input type="password" placeholder="비밀번호를 입력하세요"><br>
                <input type="submit" value="로그인">
            </form>
        </div>
        <h2>BOOKBOOK도서관에 오신 것을 환영합니다!</h2>
        <h3>로그인하시면 다양한 서비스를 이용할 수 있습니다.</h3>
        <div class="options">
            <div class="option-box">
                <p class="highlight">아이디, 비밀번호를 잊어 버렸나요?</p>
                <p style="font-size: 16px; color: #979797;">지금 빠른 처리를 원하시면 <br/> BOOKBOOK 고객센터 02-2231-6412로 전화주세요!</p>
                <div class="inline-buttons">
                    <a href="http://localhost:8080/project22/jsp/username-recovery.jsp">아이디 찾기</a>
                    <a href="http://localhost:8080/project22/jsp/password-reset.jsp">비밀번호 재설정</a>
                </div>
            </div>
            <div class="option-box">
                <p class="highlight">아직 회원이 아니신가요?</p>
                <p style="font-size: 16px; color: #979797;">회원가입을 통해 도서관을 경험할 수 있습니다.</p>
                <a href="http://localhost:8080/project22/jsp/join.jsp " class="join-button">가입하기</a>
            </div>
        </div>
    </div>
</div>
</section>
<!-- footer 시작  -->
<%@ include file="footer.jsp" %>
<!-- footer 끝  -->

</body>
</html>
