<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
    		prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${array }<hr/>
	<%--위 array를 c:forEach를 이용해 하나하나 나열해주세요. --%>
	<c:forEach var="item" items="${array}" >
		${item }<br/>
	</c:forEach>
</body>
</html>
<!-- http://localhost:8181/getList?array=하고싶은말&array=하고싶은말 이렇게들어가면됨 -->