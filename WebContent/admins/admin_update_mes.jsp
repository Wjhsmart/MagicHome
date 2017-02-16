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
<title>修改自己的信息</title>
<link rel="shortcut icon" href="<%=path %>/images/favicon.png">
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
	<a href="<%=path %>/admin/query_personal_mes_page">返回</a>
	<div class="container">
		<div class="form">
			<c:choose>
				<c:when test="${empty(requestScope.succeed) }">
					<h3>${sessionScope.admin.e_mail }:</h3>
					<form onsubmit="return formCheck( )" action="<%=path %>/admin/update_mes" method="post">
						<em>名称：</em> 
						<input id="name" type="text" name="name" value="${sessionScope.admin.name }" /> 
						<span id="span"></span> 
						<br /><br /> 
						<em>手机号：</em> 
						<input id="phone" type="text" name="phone" value="${sessionScope.admin.phone }" /> 
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