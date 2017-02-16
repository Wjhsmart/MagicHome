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
<title>查看装修公司活动</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
<script type="text/javascript" src="<%=path %>/js/jh_javascript.js"></script>
</head>
<body>
	<c:choose>
		<c:when test="${empty(sessionScope.admin) }">
			<h3 style="color: red;">当前账号已失效，请重新登入</h3>
		</c:when>
		<c:when test="${empty(requestScope.companyActivitys) }">
			<h3 class="h3_red">暂无活动信息</h3>
		</c:when>
		<c:otherwise>
			<div>
				<h3>根据条件查找：</h3>
				<form action="<%=path %>/admin/condition_query_company_activity" method="post">
					<em>公司名称：</em>
					<input type="text" name="companyName" placeholder="请输入公司名称查找" />
					<em>活动名称：</em>
					<input type="text" name="activityName" placeholder="请输入活动名称查找" />
					<input type="submit" value="查找" />
				</form>
				<br />
				<table class="table">
					<tr class="title">
						<td>编号</td>
						<td>公司名称</td>
						<td>活动名称</td>
						<td>活动图片</td>
						<td>活动描述</td>
						<td>创建时间</td>
						<td style="width: 150px;">操作</td>
					</tr>
					<c:forEach items="${requestScope.companyActivitys }" var="companyActivity" varStatus="s">
						<tr>
							<td>${s.index + 1 }</td>
							<td>${companyActivity.company.name }</td>
							<td>${companyActivity.name }</td>
							<td class="case_img">
								<a href="javascript:;" onclick="checkImg('<%=path %>/${companyActivity.image }')"><img src="<%=path %>/${companyActivity.image }" /></a>
							</td>
							<td>${companyActivity.des }</td>
							<td>${companyActivity.created_time }</td>
							<td>
								<a href="<%=path %>/admin/delete_company_activity?id=${companyActivity.id }" onclick="return affirmDelete('${companyActivity.name }')">删除活动</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div style="text-align: right; padding: 60px;">
				<a href="<%=path %>/admin/query_company_activity_page?pageSize=${requestScope.currentPageSize }">首页</a>
				<a href="<%=path %>/admin/query_company_activity_page?pageNo=${requestScope.currentPage - 1 }&pageSize=${requestScope.currentPageSize }">上一页</a>
				<a href="<%=path %>/admin/query_company_activity_page?pageNo=${requestScope.currentPage + 1 }&pageSize=${requestScope.currentPageSize }">下一页</a>
				<a href="<%=path %>/admin/query_company_activity_page?pageNo=${requestScope.totalPage }&pageSize=${requestScope.currentPageSize }">尾页</a>
				<span>${requestScope.currentPage }</span>/<span>${requestScope.totalPage }</span>
				<span>每页显示数据</span>
				<input type="text" name="pageSize" style="width: 50px;" value="${requestScope.currentPageSize }" form="skip" />
				<input type="submit" value="跳转" form="skip" />
				<input type="text" name="pageNo" style="width: 30px;" form="skip" />
				<span>页</span>
				<form id="skip" action="<%=path %>/admin/query_company_activity_page" method="post"></form>
			</div>
		</c:otherwise>
	</c:choose>
	<div id="check_img" class="check_img" onclick="cancel()"></div>
</body>
</html>