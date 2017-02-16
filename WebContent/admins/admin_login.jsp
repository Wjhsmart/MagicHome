<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>管理员登入</title>
<link rel="shortcut icon" href="<%=path %>/images/favicon.png">
<link rel="stylesheet" href="<%=path %>/css/reset.css">
<link rel="stylesheet" href="<%=path %>/css/supersized.css">
<link rel="stylesheet" href="<%=path %>/css/style.css">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	<script src="<%=path %>/js/html5.js"></script>
<![endif]-->
</head>
<body>
	<div class="page-container">
		<h1>管理员登入</h1>
		
		<form onsubmit="return login()" action="<%=path %>/admin/login" method="post">
			<h3 style="color: red; font-size: 16px; margin-left: -200px; margin-bottom: -10px;">${requestScope.errorMes }</h3>
			<input type="email" name="email" class="username" placeholder="请输入您的管理员邮箱！"> 
			<input type="password" name="password" class="password" placeholder="请输入您的管理员密码！"> 
			<h4 id="codeHint" style="text-align: left; margin-bottom: -10px; margin-top: 10px; color: red;"></h4>			
			<input type="text" class="Captcha" name="captcha" id="captcha" placeholder="请输入验证码！">
			<input type="button" class="code_btn" name="code" id="code" value="点击获取验证码" onclick="changeCode(this)" />
			<button type="submit" class="submit_button">登入</button>
			<div class="error">
				<span>+</span>
			</div>
		</form>
	</div>
	
	<script src="<%=path %>/js/jquery-1.8.2.min.js" ></script>
    <script src="<%=path %>/js/supersized.3.2.7.min.js" ></script>
    <script src="<%=path %>/js/supersized-init.js" ></script>
    <script src="<%=path %>/js/scripts.js" ></script>
</body>
</html>