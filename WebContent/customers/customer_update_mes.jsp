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
		var plot_name = document.getElementById("plot_name").value;
		var address = document.getElementById("address").value;
		var span = document.getElementById("span");
		if (name != '' && phone != '' && plot_name != '' && address != '') {
			if (phone.length != 11) {
				span1.innerHTML = "手机号格式错误";
				return false;
			}
			return true;
		} else {
			span.innerHTML = "所有都为必填字段";
			return false;
		}
		return false;
	}
	
</script>
</head>
<body>
	<div class="container">
		<div class="form">
			<c:choose>
				<c:when test="${empty(requestScope.succeed) }">
					<h3>${sessionScope.customer.e_mail }:</h3>
					<form onsubmit="return formCheck( )" action="<%=path %>/customer/update_personal_mes" method="post">
						<span id="span"></span>
						<em>名称：</em> 
						<input id="name" type="text" name="name" value="${sessionScope.customer.name }" /> 
						<br /><br /> 
						<em>手机号：</em> 
						<input id="phone" type="text" name="phone" value="${sessionScope.customer.phone }" /> 
						<br /><br /> 
						<em>小区名称：</em> 
						<input id="plot_name" type="text" name="plot_name" value="${sessionScope.customer.plot_name }" /> 
						<br /><br /> 
						<em>地址：</em> 
						<input id="address" type="text" name="address" value="${sessionScope.customer.address }" /> 
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