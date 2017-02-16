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
<title>修改个人密码</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
<script>
	function formCheck() {
		var oldPwd = document.getElementById("oldPwd").value;
		var newPwd = document.getElementById("newPwd").value;
		var affPwd = document.getElementById("affPwd").value;
		var oldPwd_span = document.getElementById("oldPwd_span");
		var newPwd_span = document.getElementById("newPwd_span");
		var span = document.getElementById("span");
		if (oldPwd != '' && newPwd != '' && affPwd != '') {
			if (newPwd.length < 6) {
				span.innerHTML = "密码长度不能小于6位";
				return false;
			} else if (newPwd != affPwd) {
				span.innerHTML = "两次密码不一致"
				return false;
			}
			return true;
		} else {
			if (oldPwd == '') {
				oldPwd_span.innerHTML = "旧密码不能为空";
				return false;
			} else if (newPwd == '') {
				newPwd_span.innerHTML = "请输入新密码";
				return false;
			} else if (affPwd == '') {
				span.innerHTML = "请确认密码";
				return false;
			}
		}
		return false;
	}
	
</script>
</head>
<body>
	<div class="container">
		<c:choose>
			<c:when test="${empty(requestScope.succeed) }">
				<h3>修改用户密码</h3>
				<div class="form">
					<form onsubmit="return formCheck( )" action="<%=path %>/customer/update_personal_pwd" method="post">
						<em>旧密码：</em>
						<input id="oldPwd" type="password" name="oldPwd" placeholder="请输入旧密码" />
						<span id="oldPwd_span">${requestScope.errorMes }</span>
						<br /><br />
						<em>新密码：</em>
						<input id="newPwd" type="password" name="pwd" placeholder="请输入新密码" />
						<span id="newPwd_span"></span>
						<br /><br />
						<em>确认密码：</em>
						<input id="affPwd" type="password" placeholder="确认密码" />
						<span id="span"></span>
						<br /><br />
						<input type="submit" value="提交" class="btn" />
						<input type="reset" value="重置" class="btn" />
					</form>
				</div>
			</c:when>
			<c:otherwise>
				<h3>${requestScope.succeed }</h3>
			</c:otherwise>
		</c:choose>
		
	</div>
</body>
</html>