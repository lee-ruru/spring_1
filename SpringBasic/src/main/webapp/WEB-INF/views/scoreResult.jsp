<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>성적 결과!</h1>
	<p>과목별 성적</p>
	수학 : ${math }<br/>
	영어 : ${english }<br/>
	언어 : ${language }<br/>
	사회 : ${social }<br/>
	컴퓨터 : ${computer }<br/>
	<p>총점과 평균</p>
	총점 : ${total}<br/>
	평균점수 : ${avg}
</body>
</html>