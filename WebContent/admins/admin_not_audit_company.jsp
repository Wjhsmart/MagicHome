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
<title>未审核的装修公司</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
<script type="text/javascript" src="<%=path %>/js/jh_javascript.js"></script>
</head>
<body>
	<c:choose>
		<c:when test="${empty(sessionScope.admin) }">
			<h3 style="color: red;">当前账号已失效，请重新登入</h3>
		</c:when>
		<c:when test="${empty(requestScope.companys) }">
			<h3 class="h3_red">暂无未审核的装修公司</h3>
		</c:when>
		<c:otherwise>
			<div>
				<h3>根据条件查找：</h3>
				<form action="<%=path %>/admin/condition_query_not_audit_company" method="post">
					<em>邮箱：</em>
					<input type="email" name="email" placeholder="请输入邮箱查找" />
					<em>公司名称：</em>
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
						<td>公司名称</td>
						<td>公司负责人</td>
						<td>公司地址</td>
						<td>公司电话</td>
						<td>公司固定电话</td>
						<td>公司成立日期</td>
						<td>公司描述</td>
						<td>公司创建时间</td>
						<td>是否通过审核</td>
						<td>最近登入时间</td>
						<td>登入次数</td>
						<td>是否可用</td>
						<td style="width: 150px;">操作</td>
					</tr>
					<c:forEach items="${requestScope.companys }" var="company" varStatus="s">
						<tr>
							<td>${s.index + 1 }</td>
							<td>${company.e_mail }</td>
							<td>${company.name }</td>
							<td>${company.principal }</td>
							<td>${company.address }</td>
							<td>${company.phone }</td>
							<td>${company.tel }</td>
							<td>${company.open_date }</td>
							<td>${company.des }</td>
							<td>${company.created_time }</td>
							<td>
								<c:choose>
									<c:when test="${company.checked == 'Y'}">
										是
									</c:when>
									<c:when test="${company.checked == 'N'}">
										否
									</c:when>
									<c:otherwise>
									
									</c:otherwise>
								</c:choose>
							</td>
							<td>${company.last_login_time }</td>
							<td>${company.login_count }</td>
							<td>
								<c:choose>
									<c:when test="${company.status == 'Y'}">
										是
									</c:when>
									<c:when test="${company.status == 'N'}">
										否
									</c:when>
									<c:otherwise>
									
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<a href="<%=path %>/admin/pass_audit_company?email=${company.e_mail }">通过审核</a>
								&nbsp;&nbsp;
								<a href="<%=path %>/admin/delete_audit_company?email=${company.e_mail }" onclick="return affirmDelete('${company.name }')">删除</a>
								
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div style="text-align: right; padding: 60px;">
				<a href="<%=path %>/admin/query_not_audit_company_page?pageSize=${requestScope.currentPageSize }">首页</a>
				<a href="<%=path %>/admin/query_not_audit_company_page?pageNo=${requestScope.currentPage - 1 }&pageSize=${requestScope.currentPageSize }">上一页</a>
				<a href="<%=path %>/admin/query_not_audit_company_page?pageNo=${requestScope.currentPage + 1 }&pageSize=${requestScope.currentPageSize }">下一页</a>
				<a href="<%=path %>/admin/query_not_audit_company_page?pageNo=${requestScope.totalPage }&pageSize=${requestScope.currentPageSize }">尾页</a>
				<span>${requestScope.currentPage }</span>/<span>${requestScope.totalPage }</span>
				<span>每页显示数据</span>
				<input type="text" name="pageSize" style="width: 50px;" value="${requestScope.currentPageSize }" form="skip" />
				<input type="submit" value="跳转" form="skip" />
				<input type="text" name="pageNo" style="width: 30px;" form="skip" />
				<span>页</span>
				<form id="skip" action="<%=path %>/admin/query_not_audit_company_page" method="post"></form>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>