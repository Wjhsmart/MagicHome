<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证码</title>
</head>
<body>
	<img src="getcaptcha.sl" alt="验证码" name="checkImg" id="checkImg" onClick="document.getElementById('checkImg').src='getcaptcha.sl?temp='+ (new Date().getTime().toString(36)); return false"/>
	The current time is [insertTimeOnServer].
</body>
</html>