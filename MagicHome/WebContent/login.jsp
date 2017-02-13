<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <% 
    	String  path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/cj_style.css"/>
<link rel="shortcut icon" href="<%=path %>/images/favicon.png">
<script>
	function showDiv(show, hide, hide1, obj, obj1, obj2) {
		var showDiv = document.getElementById(show);
		var hideDiv = document.getElementById(hide);
		var hideDiv1 = document.getElementById(hide1);
		var obj = document.getElementById(obj);
		var obj1 = document.getElementById(obj1);
		var obj2 = document.getElementById(obj2);
		showDiv.style = "display: block;";
		hideDiv.style = "display: none;";
		hideDiv1.style = "display: none;";
		obj.style = "border-bottom:2px solid #00b161; color:#00b161;";
		obj1.style = "border-bottom:none; color:black;";
		obj2.style = "border-bottom:none; color:black;";
	}
	function showUser(user, enterprise, designer, name, qy, project) {
		showDiv(user, enterprise, designer, name, qy, project);
	}
	
	function customerCheckForm() {
		var customer_email = document.getElementById("customer_email").value;
		var customer_pwd = document.getElementById("customer_pwd").value;
		var error_span = document.getElementById("error_span");
		if (customer_email != '' && customer_pwd != '') {
			return true;
		}
		error_span.innerHTML = "邮箱密码不能为空";
		return false;
	}
	
	function setAColor(obj, obj1, obj2) {
		var user = document.getElementById("name");
		var enterprise = document.getElementById("qy");
		var designer = document.getElementById("des");
		user.style = obj
		enterprise.style = obj1;
		designer.style = obj2;
	}

	function motionLogin() {
		var val = document.getElementById("motion");
		var remember = document.getElementById("remember");
		if (val.value == 'n') {
			val.value = "y";
			remember.style = "color: red; font-size: 12px; display: block;";
		} else {
			val.value = "n";
			remember.style = "display: none;";
		}
	}
</script>
</head>
<body>
	<%
		String email = "";
		String pwd = "";
		String motion = "n";
		String checked = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie c : cookies) {
				String cookieName = c.getName();
				if (cookieName.equals("customer_email")) {
					email = c.getValue();
				}
				if (cookieName.equals("customer_pwd")) {
					pwd = c.getValue();
				}
			}
		}
		if (email != null && !email.equals("") && pwd != null && !pwd.equals("")) {
			motion = "y";
			checked = "checked='checked'";
		} else {
			motion = "n";
			checked = "";
		}
	%>
	<div class="head_div">
		<div class="top_cj">
			<div class="logo_container">
				<div class="logo"><a href="<%=path %>/page/index" ><img src="<%=path %>/images/logo3.png"/></a></div>
				<div class="hello_left"><h2>欢迎登录</h2></div>
			</div>
		</div>
		<div class="bj_img"><img src="<%=path %>/images/111.jpg" class="img_radius"/>
			<p class="clear"></p>
		</div>
		<div class="form">
			<div class="nav_text">
				<a href="javascript:;" onclick="showUser('user', 'enterprise', 'designer', 'name', 'qy', 'des')" id="name">个人用户</a>
				<a href="javascript:;" onclick="showUser('enterprise', 'user', 'designer', 'qy', 'name', 'des')" id="qy">企业用户</a>
				<a href="javascript:;" onclick="showUser('designer', 'user', 'enterprise', 'des', 'qy', 'name')" id="des">设计师</a>
			</div>
			<c:choose>
				<c:when test="${requestScope.user == 'block'}">
					<script>
						setAColor('border-bottom:2px solid #00b161; color:#00b161;', 'border-bottom: none; color: #000000;', 'border-bottom: none; color: #000000;');
					</script>
				</c:when>
				<c:when test="${requestScope.enterprise == 'block'}">
					<script>
						setAColor('border-bottom: none; color: #000000;', 'border-bottom:2px solid #00b161; color:#00b161;', 'border-bottom: none; color: #000000;');
					</script>
				</c:when>
				<c:when test="${requestScope.designer == 'block'}">
					<script>
						setAColor('border-bottom: none; color: #000000;', 'border-bottom: none; color: #000000;', 'border-bottom:2px solid #00b161; color:#00b161;');
					</script>
				</c:when>
					
			</c:choose>
			<div id="user" class="input" style="display:${requestScope.user };">
				<form onsubmit="return customerCheckForm()" action="<%=path %>/customer/login" method="post">
					<div class="input_type">
						<span style="display: none;" id="remember">公共场合不建议记住密码</span>
						<span style="font-size: 12px; color: green;">${requestScope.succeed }</span>
						<span style="color: red; font-size: 12px; margin-left: 60px;" id="error_span">${requestScope.errorMes }</span>
						<input type="email" name="email" id="customer_email" value="<%=email %>" placeholder="邮箱"/>
						
					</div>
					<div class="input_type">
						<input type="password" name="pwd" id="customer_pwd" value="<%=pwd %>" placeholder="密码"/>
						
					</div>
					<div class="text_input_checkbox"><input type="checkbox" <%=checked %> name="selfMotion" value="<%=motion %>" id="motion" onclick="motionLogin()"/>
						<label>记住密码</label>
						<a href="#">忘记密码?</a>
					</div>
					<div class="input_submit"><input type="submit" value="立即登录"/></div>
					<div class="text_qq_weibo">
						<a href="#"><img src=" <%=path %>/images/weibo.png"/>微博登录</a> 
						<a href="#"><img src=" <%=path %>/images/qq.png" class="qq"/>QQ登录</a>
						<a href="<%=path %>/page/register_page" class="free">免费注册</a>
					</div>
				</form>
			</div>
			<div id="enterprise" class="input" style="display:${requestScope.enterprise };" >
				<form action="<%=path %>/supply/login" method="post" >
					<div class="radio">
						<label><input type="radio" ${requestScope.company } name="radio" value="1"/>装修公司</label> 
						<label><input type="radio" ${requestScope.supply }  name="radio" value="2"/>建材商</label>
					</div>
					<span style="font-size: 13px; color: red; margin-left: 40px;">${requestScope.enterpriseErrorMes }</span>
					<div class="input_type"><input type="email" name="email" placeholder="邮箱"/></div>
					<div class="input_type"><input type="password" name="pwd" placeholder="密码"/></div>
					<div class="input_submit"><input type="submit" value="立即登录"/></div>
				</form>
			</div>
			<div id="designer" class="input" style="display:${requestScope.designer }">
				<form action="<%=path %>/designer/login" method="post" >
					<div class="input_type">
						<span style="font-size: 10px; color: red;">${requestScope.designerErrorMes }</span>
						<input type="email" name="email" placeholder="邮箱"/>
					</div>
					<div class="input_type"><input type="password" name="pwd" placeholder="密码"/></div>
					<div class="input_submit"><input type="submit" value="立即登录"/></div>
				</form>
			</div>
			<p class="clear"></p>
		</div>
		<div class="bottom_nav">
			<jsp:include page="bottom_common.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>