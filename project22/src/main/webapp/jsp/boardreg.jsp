<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>게시글 등록</title>
</head>
<body>
    <form action="BoardRegProcess.jsp" method="post">
        제목: <input type="text" name="title"><br>
        내용: <textarea name="contents"></textarea><br>
        <input type="submit" value="등록">
    </form>
</body>
</html>
