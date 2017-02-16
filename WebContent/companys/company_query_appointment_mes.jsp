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
<title>查看预约详细信息</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
</head>
<body>
	<c:choose>
		<c:when test="${empty(sessionScope.company) }">
			<h3 style="color: red;">当前账号已失效，请重新登入</h3>
		</c:when>
		<c:otherwise>
			<a href="<%=path %>/company/query_not_audit_appointment">返回</a>
			<div>
				<h3>查看详细预约信息：</h3>
				<table class="table">
					<tr class="title">
						<td>用户名称</td>
						<td>用户手机</td>
						<td>小区名称</td>
						<td>建筑面积</td>
						<td>装修方式</td>
						<td>装修预算</td>
						<td>预约日期</td>
					</tr>
					<tr>
						<td>${requestScope.appointment.name }</td>
						<td>${requestScope.appointment.phone }</td>
						<td>${requestScope.appointment.plot_name }</td>
						<td>${requestScope.appointment.area }</td>
						<td>
							<c:choose>
								<c:when test="${requestScope.appointment.way == 'whole'}">
									全包
								</c:when>
								<c:when test="${requestScope.appointment.way == 'half'}">
									半包
								</c:when>
							</c:choose>
						</td>
						<td>${requestScope.appointment.budget }</td>
						<td>${requestScope.appointment.created_time }</td>
					</tr>
				</table>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>