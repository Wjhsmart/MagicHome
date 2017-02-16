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
<title>未审核的建材商</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
<script type="text/javascript" src="<%=path %>/js/jh_javascript.js"></script>
</head>
<body>
	<c:choose>
		<c:when test="${empty(sessionScope.admin) }">
			<h3 style="color: red;">当前账号已失效，请重新登入</h3>
		</c:when>
		<c:when test="${empty(requestScope.supplys) }">
			<h3 class="h3_red">暂无未审核的建材商</h3>
		</c:when>
		<c:otherwise>
			<div>
				<h3>根据条件查找：</h3>
				<form action="<%=path %>/admin/condition_query_not_audit_supply" method="post">
					<em>邮箱：</em>
					<input type="email" name="email" placeholder="请输入邮箱查找" />
					<em>建材商名称：</em>
					<input type="text" name="name" placeholder="请输入名称查找" />
					<em>负责人：</em>
					<input type="text" name="principal" placeholder="请输入负责人名称查找" />
					<input type="submit" value="查找" />
				</form>
				<br />
				<table class="table">
					<tr class="title">
						<td>编号</td>
						<td>Email</td>
						<td>建材商名称</td>
						<td>建材商负责人</td>
						<td>建材商地址</td>
						<td>建材商电话</td>
						<td>建材商固定电话</td>
						<td>建材商成立日期</td>
						<td>建材商描述</td>
						<td>建材商创建时间</td>
						<td>是否通过审核</td>
						<td>最近登入时间</td>
						<td>登入次数</td>
						<td>是否可用</td>
						<td style="width: 150px;">操作</td>
					</tr>
					<c:forEach items="${requestScope.supplys }" var="supply" varStatus="s">
						<tr>
							<td>${s.index + 1 }</td>
							<td>${supply.e_mail }</td>
							<td>${supply.name }</td>
							<td>${supply.principal }</td>
							<td>${supply.address }</td>
							<td>${supply.phone }</td>
							<td>${supply.tel }</td>
							<td>${supply.open_date }</td>
							<td>${supply.des }</td>
							<td>${supply.created_time }</td>
							<td>
								<c:choose>
									<c:when test="${supply.checked == 'Y'}">
										是
									</c:when>
									<c:when test="${supply.checked == 'N'}">
										否
									</c:when>
									<c:otherwise>
									
									</c:otherwise>
								</c:choose>
							</td>
							<td>${supply.last_login_time }</td>
							<td>${supply.login_count }</td>
							<td>
								<c:choose>
									<c:when test="${supply.status == 'Y'}">
										是
									</c:when>
									<c:when test="${supply.status == 'N'}">
										否
									</c:when>
									<c:otherwise>
									
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<a href="<%=path %>/admin/pass_audit_supply?email=${supply.e_mail }">通过审核</a>
								&nbsp;&nbsp;
								<a href="<%=path %>/admin/delete_audit_supply?email=${supply.e_mail }" onclick="return affirmDelete('${supply.name }')">删除</a>
								
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div style="text-align: right; padding: 60px;">
				<a href="<%=path %>/admin/query_not_audit_supply_page?pageSize=${requestScope.currentPageSize }">首页</a>
				<a href="<%=path %>/admin/query_not_audit_supply_page?pageNo=${requestScope.currentPage - 1 }&pageSize=${requestScope.currentPageSize }">上一页</a>
				<a href="<%=path %>/admin/query_not_audit_supply_page?pageNo=${requestScope.currentPage + 1 }&pageSize=${requestScope.currentPageSize }">下一页</a>
				<a href="<%=path %>/admin/query_not_audit_supply_page?pageNo=${requestScope.totalPage }&pageSize=${requestScope.currentPageSize }">尾页</a>
				<span>${requestScope.currentPage }</span>/<span>${requestScope.totalPage }</span>
				<span>每页显示数据</span>
				<input type="text" name="pageSize" style="width: 50px;" value="${requestScope.currentPageSize }" form="skip" />
				<input type="submit" value="跳转" form="skip" />
				<input type="text" name="pageNo" style="width: 30px;" form="skip" />
				<span>页</span>
				<form id="skip" action="<%=path %>/admin/query_not_audit_supply_page" method="post"></form>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>