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
<title>查询所有用户的预约</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
</head>
<body>
	<c:choose>
		<c:when test="${empty(sessionScope.company) }">
			<h3 style="color: red;">当前账号已失效，请重新登入</h3>
		</c:when>
		<c:when test="${!empty(requestScope.errorMes) }">
			<h3 class="h3_red">${requestScope.errorMes }</h3>
		</c:when>
		<c:when test="${empty(requestScope.appointments) }">
			<h3 class="h3_red">暂无预约信息</h3>
		</c:when>
		<c:otherwise>
			<div>
				<h3>查看所有用户的预约：</h3>
				<table class="table">
					<tr class="title">
						<td>编号</td>
						<td>用户名称</td>
						<td>用户手机</td>
						<td>小区名称</td>
						<td>建筑面积</td>
						<td>装修方式</td>
						<td>装修预算</td>
						<td>预约日期</td>
						<td style="width: 150px;">操作</td>
					</tr>
					<c:forEach items="${requestScope.appointments }" var="appointment" varStatus="s">
						<tr>
							<td>${s.index + 1 }</td>
							<td>${appointment.name }</td>
							<td>***********</td>
							<td>${appointment.plot_name }</td>
							<td>${appointment.area }</td>
							<td>
								<c:choose>
									<c:when test="${appointment.way == 'whole'}">
										全包
									</c:when>
									<c:when test="${appointment.way == 'half'}">
										半包
									</c:when>
								</c:choose>
							</td>
							<td>${appointment.budget }</td>
							<td>${appointment.created_time }</td>
							<td>
								<a href="<%=path %>/company/query_appointment?app_id=${appointment.id }">接受预约</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div style="text-align: right; padding: 60px;">
				<a href="<%=path %>/company/query_not_audit_appointment?pageSize=${requestScope.currentPageSize }">首页</a>
				<a href="<%=path %>/company/query_not_audit_appointment?pageNo=${requestScope.currentPage - 1 }&pageSize=${requestScope.currentPageSize }">上一页</a>
				<a href="<%=path %>/company/query_not_audit_appointment?pageNo=${requestScope.currentPage + 1 }&pageSize=${requestScope.currentPageSize }">下一页</a>
				<a href="<%=path %>/company/query_not_audit_appointment?pageNo=${requestScope.totalPage }&pageSize=${requestScope.currentPageSize }">尾页</a>
				<span>${requestScope.currentPage }</span>/<span>${requestScope.totalPage }</span>
				<span>每页显示数据</span>
				<input type="text" name="pageSize" style="width: 50px;" value="${requestScope.currentPageSize }" form="skip" />
				<input type="submit" value="跳转" form="skip" />
				<input type="text" name="pageNo" style="width: 30px;" form="skip" />
				<span>页</span>
				<form id="skip" action="<%=path %>/company/query_not_audit_appointment" method="post"></form>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>