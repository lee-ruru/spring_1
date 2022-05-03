<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/userInfo" method="post">
		유저 번호 : <input type ="number" name="userNum" placeholder="유저번호"/><br/>
		유저 아이디 : <input type ="text" name="userId" placeholder="유저아이디"/><br/>
		유저 비밀번호 : <input type ="text" name="userPw" placeholder="유저비밀번호"/><br/>
		유저 이름 : <input type ="text" name="userName" placeholder="유저이름"/><br/>
		유저 나이 : <input type ="number" name="userAge" placeholder="유저나이"/><br/>
		
		<input type ="submit" value="확인"/>
	</form>
</body>
</html>