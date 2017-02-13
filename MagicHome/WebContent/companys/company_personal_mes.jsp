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
<title>显示装修公司信息</title>
<link rel="shortcut icon" href="<%=path %>/images/favicon.png">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
</head>
<body>
	<h3 class="title">装修公司信息:</h3>
	<div class="container">
	<table>
		<tr>
			<td class="logo_li">
				<span>公司LOGO:</span> 
				<img class="logo" src="<%=path %>/${sessionScope.company.logo }" />
			</td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><em>${sessionScope.company.e_mail }</em></td>
		</tr>
		<tr>
			<td>公司名称:</td>
			<td><em>${sessionScope.company.name }</em></td>
		</tr>
		<tr>
			<td>公司负责人:</td>
			<td><em>${sessionScope.company.principal }</em></td>
		</tr>
		<tr>
			<td>公司地址:</td>
			<td><em>${sessionScope.company.address }</em></td>
		</tr>
		<tr>
			<td>电话:</td>
			<td><em>${sessionScope.company.phone }</em></td>
		</tr>
		<tr>
			<td>固定电话:</td>
			<td><em>${sessionScope.company.tel }</em></td>
		</tr>
		<tr>
			<td>公司成立日期:</td>
			<td><em>${sessionScope.company.open_date }</em></td>
		</tr>
		<tr>
			<td>最近登入时间:</td>
			<td><em>${requestScope.lastLoginTime }</em></td>
		</tr>
		<tr>
			<td>登入次数:</td>
			<td><em>${sessionScope.company.login_count }</em></td>
		</tr>
		<tr>
			<td>账号是否可用:</td>
			<td><em> 
					<c:choose>
						<c:when test="${sessionScope.company.status == 'Y'}">
									<span>是</span>
								</c:when>
						<c:when test="${sessionScope.company.status == 'N'}">
									<span>否</span>
								</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>
			</em></td>
		</tr>
		<tr>
			<td>公司介绍:</td>
			<td><em>${sessionScope.company.des }</em></td>
		</tr>
		<tr class="update"  style="text-align: center">
			<td><a href="<%=path %>/company/update_personal_mes_page">修改信息</a></td>
		</tr>
	</table>
	</div>


</body>
</html>