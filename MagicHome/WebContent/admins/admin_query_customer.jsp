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
<title>查询所有用户信息</title>
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
			<a href="<%=path %>/admin/query_admin_page">返回</a>
		</c:when>
		<c:when test="${empty(requestScope.customers) }">
			<h3 class="h3_red">暂无用户信息</h3>
		</c:when>
		<c:otherwise>
			<div class="container" style="text-align: left;">
				<h3>根据条件查找：</h3>
				<form action="<%=path %>/admin/condition_query_customer" method="post">
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
						<td>小区名称</td>
						<td>地址</td>
						<td>创建时间</td>
						<td>最近登入时间</td>
						<td>登入次数</td>
						<td>是否可用</td>
						<td>操作</td>
					</tr>
					<c:forEach items="${requestScope.customers }" var="customer" varStatus="s">
						<tr>
							<td>${s.index + 1 }</td>
							<td>${customer.e_mail }</td>
							<td>${customer.name }</td>
							<td>${customer.phone }</td>
							<td>${customer.plot_name }</td>
							<td>${customer.address }</td>
							<td>${customer.created_time }</td>
							<td>${customer.last_login_time }</td>
							<td>${customer.login_count }</td>
							<td>
								<c:choose>
									<c:when test="${customer.status == 'Y'}">
										是
									</c:when>
									<c:when test="${customer.status == 'N'}">
										否
									</c:when>
									<c:otherwise>
									
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${customer.status == 'Y'}">
										<a href="<%=path %>/admin/forbid_customer?email=${customer.e_mail }">冻结</a>
									</c:when>
									<c:when test="${customer.status == 'N'}">
										<a href="<%=path %>/admin/start_customer?email=${customer.e_mail }">解冻</a>
									</c:when>
									<c:otherwise>
									
									</c:otherwise>
								</c:choose>
								&nbsp;&nbsp;
								<a href="<%=path %>/admin/update_customer_mes_page?email=${customer.e_mail }">修改</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div style="text-align: right; padding: 60px;">
				<a href="<%=path %>/admin/query_customer_all_page?pageSize=${requestScope.currentPageSize }">首页</a>
				<a href="<%=path %>/admin/query_customer_all_page?pageNo=${requestScope.currentPage - 1 }&pageSize=${requestScope.currentPageSize }">上一页</a>
				<a href="<%=path %>/admin/query_customer_all_page?pageNo=${requestScope.currentPage + 1 }&pageSize=${requestScope.currentPageSize }">下一页</a>
				<a href="<%=path %>/admin/query_customer_all_page?pageNo=${requestScope.totalPage }&pageSize=${requestScope.currentPageSize }">尾页</a>
				<span>${requestScope.currentPage }</span>/<span>${requestScope.totalPage }</span>
				<span>每页显示数据</span>
				<input type="text" name="pageSize" style="width: 50px;" value="${requestScope.currentPageSize }" form="skip" />
				<input type="submit" value="跳转" form="skip" />
				<input type="text" name="pageNo" style="width: 30px;" form="skip" />
				<span>页</span>
				<form id="skip" action="<%=path %>/admin/query_customer_all_page" method="post"></form>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>