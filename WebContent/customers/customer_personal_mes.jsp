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
<title>显示个人信息</title>
<link rel="shortcut icon" href="<%=path %>/images/favicon.png">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
</head>
<body>
	<h3 class="title">用户信息:</h3>
	<div class="container">
		<table>
			<tr>
				<td>Email:</td> 
				<td><em>${sessionScope.customer.e_mail }</em></td>
			</tr>
			<tr>
				<td>昵称:</td>
				<td><em>${sessionScope.customer.name }</em></td>
			</tr>
			<tr>
				<td>电话:</td> 
				<td><em>${sessionScope.customer.phone }</em></td>
			</tr>
			<tr>
				<td>小区名称:</td>
				<td><em>${sessionScope.customer.plot_name }</em></td>
			</tr>
			<tr>
				<td>地址:</td> 
				<td><em>${sessionScope.customer.address }</em></td>
			</tr>
			<tr>
				<td>创建时间:</td>
				<td><em>${sessionScope.customer.created_time }</em></td>
			</tr>
			<tr>
				<td>最近登入时间:</td>
				<td><em>${requestScope.lastLoginTime }</em></td>
			</tr>
			<tr>
				<td>登入次数:</td>
				<td><em>${sessionScope.customer.login_count }</em></td>
			</tr>
			<tr>
				<td>账号是否可用:</td>
				<td><em> 
					<c:choose>
						<c:when test="${sessionScope.customer.status == 'Y'}">
									是
								</c:when>
						<c:when test="${sessionScope.customer.status == 'N'}">
									否
								</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>
				</em></td>
			</tr>
			<tr class="update" style="text-align: center">
				<td><a href="<%=path %>/customer/update_personal_mes_page">修改信息</a></td>
			</tr>
		</table>
	</div>


</body>
</html>