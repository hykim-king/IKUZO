<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOOKBOOK도서관 - 비밀번호재설정</title>
<link rel="stylesheet" href="/project22/css/bookbook.css">
<style>
/*section*/
section{
  width : 100%;
  height:auto;
}
#container{
padding : 100px;
}
 .content-wrapper {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 80vh; /* 화면 높이의 80%를 차지하도록 설정 */
        }

        .content {
            width: 900px;
            border-top: 2px solid #000;
            border-left: 1px solid #e5e5e5;
            border-right: 1px solid #e5e5e5;
            border-bottom: 1px solid #000;
            padding: 20px 0; /* 상하 간격을 조정하여 content가 정중앙에 위치하도록 함 */
            position: relative; /* h2와 h3의 위치를 content에 상대적으로 조정 */
            text-align: center; /* 입력 폼 중앙 정렬 */
        }

          .content h2, .content h3 {   
            line-height: 1.15;
            letter-spacing: -0.03em;
            text-align: left; /* 텍스트를 왼쪽 정렬 */
            position: absolute;
            left: 0px;
            
        }

        .content h2 {
            font-size: 40px;
            
            top: -122px;
            color:  #27403d; 
             
        }

        .content h3 {
            font-size: 16px;
            top: -52px;
            font-weight: normal;
           
        }

        .content form {
            margin-top: 20px; /* 입력 폼 상단 간격 조정 */
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .content form input[type="text"],
        .content form input[type="email"] {
            width: 300px;
            padding: 10px;
            margin-bottom: 0px; /* 간격을 조정하여 입력 폼들을 가깝게 배치 */
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            background-color: #fff; /* 배경색 변경 */
            box-sizing: border-box;
        }

        .content form input[type="submit"] {
            width: 150px;
            padding: 10px;
            background-color: #27403d; /* 배경색 변경 */
            color: #fff; /* 글씨 색상 변경 */
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            font-weight: bold;
        }

        .content form input[type="submit"]:hover {
            background-color: #D88F2E;  /* 호버 효과 */
        }
</style>
</head>

<body>

	<!-- header 시작  -->  
	<%@ include file="header.jsp" %>
	<!-- header 끝  -->
	
<section>
<div class="content-wrapper">
    <div class="content">
        <h2>비밀번호 초기화</h2>
        <h3>본인확인 완료시 비밀번호를 초기화하실 수 있습니다.</h3>
        <form>
            <input type="text" placeholder="아이디를 입력하세요"><br>
            <input type="text" placeholder="이름을 입력하세요"><br>
            <input type="text" placeholder="전화번호를 입력하세요"><br>
            <input type="submit" value="비밀번호 초기화">
        </form>
    </div>
</div>
</section>

	<!-- footer 시작  -->
	<%@ include file="footer.jsp" %>
	<!-- footer 끝  -->

</body>
</html>