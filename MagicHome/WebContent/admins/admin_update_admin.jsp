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
<title>修改管理员信息</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
<script>
	function formCheck() {
		var name = document.getElementById("name").value;
		var phone = document.getElementById("phone").value;
		var span = document.getElementById("span");
		var span1 = document.getElementById("span1");
		
		if (name != '' && phone != '') {
			if (phone.length != 11) {
				span1.innerHTML = "手机号格式错误";
				return false;
			}
			return true;
		} else {
			if (name == '') {
				span.innerHTML = "名称不能为空";
				return false;
			} else if (phone == '') {
				span1.innerHTML = "手机不能为空";
				return false;
			}
		}
		return false;
	}
</script>
</head>
<body>
	<a href="<%=path %>/admin/query_admin_all_page">返回上一页</a>
	<div class="container">
		<div class="form">
			<c:choose>
				<c:when test="${empty(sessionScope.admin) }">
					<h3 style="color: red;">当前账号已失效，请重新登入</h3>
				</c:when>
				<c:when test="${sessionScope.admin.role == 'normal' }">
					<h3 style="color: red; text-align: left;">抱歉，您没有相应的权限</h3>
				</c:when>
				<c:when test="${empty(requestScope.succeed) }">
					<h3>${requestScope.admin.e_mail }:</h3>
					<form onsubmit="return formCheck( )" action="<%=path %>/admin/update_admin_mes?email=${requestScope.admin.e_mail }" method="post">
						<em>密码：</em> 
						<a href="<%=path %>/admin/reset_admin_pwd?email=${requestScope.admin.e_mail }">点击重置密码</a>
						<br /><br /> 
						<em>名称：</em> 
						<input id="name" type="text" name="name" value="${requestScope.admin.name }" /> 
						<span id="span"></span> 
						<br /><br /> 
						<em>手机号：</em> 
						<input id="phone" type="text" name="phone" value="${requestScope.admin.phone }" /> 
						<span id="span1"></span>
						<br /><br /> 
						<input type="submit" value="提交" class="btn" style="margin-left: 100px;" /> 
					</form>
				</c:when>
				<c:otherwise>
					<h3>${requestScope.succeed }</h3>
				</c:otherwise>
			</c:choose>
			
		</div>
	</div>
</body>
</html>