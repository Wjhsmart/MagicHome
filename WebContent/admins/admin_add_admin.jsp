<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加管理员</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
<script>
	function formCheck() {
		var email = document.getElementById("email").value;
		var password = document.getElementById("password").value;
		var name = document.getElementById("name").value;
		var phone = document.getElementById("phone").value;
		var email_span = document.getElementById("email_span");
		var password_span = document.getElementById("password_span");
		var name_span = document.getElementById("name_span");
		var phone_span = document.getElementById("phone_span");
		if (email != '' && password != '' && name != '' && phone != '') {
			if (phone.length == 11) {
				if (password.length < 6) {
					password_span.innerHTML = "密码长度不能小于6位数";
					return false;
				}
				return true;
				
			} else {
				phone_span.innerHTML = "手机号必须是11位的数字";
				return false;
			}
		} else {
			if (email == '') {
				email_span.innerHTML = "邮箱不能为空";
				return false;
			} else if (password == '') {
				password_span.innerHTML = "密码不能为空";
				return false;
			} else if (name == '') {
				name_span.innerHTML = "名称不能为空";
				return false;
			} else if (phone == '') {
				phone_span.innerHTML = "手机号不能为空";
				return false;
			}
		}
		return false;
	}
</script>
</head>
<body>
	<c:choose>
		<c:when test="${empty(sessionScope.admin) }">
			<h3 style="color: red;">当前账号已失效，请重新登入</h3>
		</c:when>
		<c:when test="${sessionScope.admin.role == 'normal' }">
			<h3 style="color: red;">抱歉，您没有相应的权限</h3>
		</c:when>
		<c:when test="${!empty(requestScope.succeed) }">
			<h3>${requestScope.succeed }</h3>
		</c:when>
		<c:otherwise>
			<div class="container">
				<div class="form">
					<h3>添加管理员：</h3>
					<form onsubmit="return formCheck()" action="<%=path %>/admin/add_admin" method="post">
						<em>邮箱：</em> 
						<input type="email" id="email" name="email" placeholder="请输入管理员登入邮箱" /> 
						<span id="email_span">${requestScope.errorMes }</span> 
						<br /><br /> 
						<em>密码：</em> 
						<input type="password" id="password" name="password" placeholder="请输入管理员登入密码" /> 
						<span id="password_span"></span> 
						<br /><br /> 
						<em>名称：</em> 
						<input type="text" id="name" name="name" placeholder="请输入管理员名称" /> 
						<span id="name_span"></span> 
						<br /><br /> 
						<em>手机号：</em> 
						<input type="text" id="phone" name="phone" placeholder="请输入管理员手机号" /> 
						<span id="phone_span"></span> 
						<br /><br /> 
						<em>是否可用：</em> 
						<select name="status">
							<option value="Y">是
							<option value="N">否
						</select>
						<br /><br /> 
						<input type="submit" value="提交" class="btn" />
						<input type="reset" value="重置" class="btn" />
					</form>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	
</body>
</html>