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
<title>查看所有管理员信息</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
<script type="text/javascript" src="<%=path %>/js/jh_javascript.js"></script>
</head>
<body>
	<c:choose>
		<c:when test="${empty(sessionScope.admin) }">
			<h3 style="color: red;">当前账号已失效，请重新登入</h3>
		</c:when>
		<c:when test="${!empty(requestScope.errorMes) }">
			<h3 style="color: red;">${requestScope.errorMes }</h3>
			<a href="<%=path %>/admin/query_admin_all_page">返回</a>
		</c:when>
		<c:otherwise>
			<div>
				<h3>根据条件查找：</h3>
				<form action="<%=path %>/admin/condition_query_admin" method="post">
					<em>邮箱：</em>
					<input type="email" name="email" placeholder="请输入邮箱查找" />
					<em>名称：</em>
					<input type="text" name="name" placeholder="请输入名称查找" />
					<input type="submit" value="查找" />
				</form>
				<br />
				<table class="table">
					<tr class="title">
						<td>编号</td>
						<td>Email</td>
						<td>名称</td>
						<td>电话</td>
						<td>身份</td>
						<td>创建时间</td>
						<td>最近登入时间</td>
						<td>登入次数</td>
						<td>是否可用</td>
						<td style="width: 200px;">操作</td>
					</tr>
					<c:forEach items="${requestScope.admins }" var="admin" varStatus="s">
						<tr>
							<td>${s.index + 1 }</td>
							<td>${admin.e_mail }</td>
							<td>${admin.name }</td>
							<td>${admin.phone }</td>
							<td>
								<c:choose>
									<c:when test="${admin.role == 'super'}">
										超级管理员
									</c:when>
									<c:when test="${admin.role == 'normal'}">
										普通管理员
									</c:when>
									<c:otherwise>
									
									</c:otherwise>
								</c:choose>
							</td>
							<td>${admin.created_time }</td>
							<td>${admin.last_login_time }</td>
							<td>${admin.login_count }</td>
							<td>
								<c:choose>
									<c:when test="${admin.status == 'Y'}">
										是
									</c:when>
									<c:when test="${admin.status == 'N'}">
										否
									</c:when>
									<c:otherwise>
									
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${admin.status == 'Y'}">
										<a href="<%=path %>/admin/forbid_admin?email=${admin.e_mail }">禁用管理员</a>
									</c:when>
									<c:when test="${admin.status == 'N'}">
										<a href="<%=path %>/admin/start_admin?email=${admin.e_mail }">启用管理员</a>
									</c:when>
								</c:choose>
								&nbsp;&nbsp;
								<a href="<%=path %>/admin/update_admin_mes_page?email=${admin.e_mail }">修改</a>
								&nbsp;&nbsp;
								<c:choose>
									<c:when test="${sessionScope.admin.role == 'super' }">
										<a href="<%=path %>/admin/delete_admin_mes?email=${admin.e_mail }" onclick="return affirmDelete('${admin.name }')">删除</a>
									</c:when>
									<c:otherwise>
										<a href="<%=path %>/admin/delete_admin_mes_page">删除</a>
									</c:otherwise>
								</c:choose>
								
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div style="text-align: right; padding: 60px;">
				<a href="<%=path %>/admin/query_admin_all_page?pageSize=${requestScope.currentPageSize }">首页</a>
				<a href="<%=path %>/admin/query_admin_all_page?pageNo=${requestScope.currentPage - 1 }&pageSize=${requestScope.currentPageSize }">上一页</a>
				<a href="<%=path %>/admin/query_admin_all_page?pageNo=${requestScope.currentPage + 1 }&pageSize=${requestScope.currentPageSize }">下一页</a>
				<a href="<%=path %>/admin/query_admin_all_page?pageNo=${requestScope.totalPage }&pageSize=${requestScope.currentPageSize }">尾页</a>
				<span>${requestScope.currentPage }</span>/<span>${requestScope.totalPage }</span>
				<span>每页显示数据</span>
				<input type="text" name="pageSize" style="width: 50px;" value="${requestScope.currentPageSize }" form="skip" />
				<input type="submit" value="跳转" form="skip" />
				<input type="text" name="pageNo" style="width: 30px;" form="skip" />
				<span>页</span>
				<form id="skip" action="<%=path %>/admin/query_admin_all_page" method="post"></form>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>