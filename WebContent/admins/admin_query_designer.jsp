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
<title>已审核的设计师</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
</head>
<body>
	<c:choose>
		<c:when test="${empty(sessionScope.admin) }">
			<h3 style="color: red;">当前账号已失效，请重新登入</h3>
		</c:when>
		<c:when test="${empty(requestScope.designers) }">
			<h3 class="h3_red">暂无已审核的设计师</h3>
		</c:when>
		<c:otherwise>
			<div>
				<h3>根据条件查找：</h3>
				<form action="<%=path %>/admin/condition_query_designer" method="post">
					<em>邮箱：</em>
					<input type="email" name="email" placeholder="请输入邮箱查找" />
					<em>设计师名称：</em>
					<input type="text" name="name" placeholder="请输入名称查找" />
					<input type="submit" value="查找" />
				</form>
				<br />
				<table class="table">
					<tr class="title">
						<td>编号</td>
						<td>Email</td>
						<td>设计师名称</td>
						<td>头像</td>
						<td>地址</td>
						<td>电话</td>
						<td>工作经验</td>
						<td>擅长风格</td>
						<td>描述</td>
						<td>入驻时间</td>
						<td>是否通过审核</td>
						<td>审核时间</td>
						<td>最近登入时间</td>
						<td>登入次数</td>
						<td>是否可用</td>
						<td style="width: 100px;">操作</td>
					</tr>
					<c:forEach items="${requestScope.designers }" var="designer" varStatus="s">
						<tr>
							<td>${s.index + 1 }</td>
							<td>${designer.e_mail }</td>
							<td>${designer.name }</td>
							<td class="case_img">
								<img src="<%=path %>/${designer.head_icon }" style="border-radius: 50%;" />
							</td>
							<td>${designer.address }</td>
							<td>${designer.phone }</td>
							<td>${designer.experience }</td>
							<td>${designer.style }</td>
							<td>${designer.des }</td>
							<td>${designer.created_time }</td>
							
							<td>
								<c:choose>
									<c:when test="${designer.checked == 'Y'}">
										是
									</c:when>
									<c:when test="${designer.checked == 'N'}">
										否
									</c:when>
									<c:otherwise>
									
									</c:otherwise>
								</c:choose>
							</td>
							<td>${designer.checked_time }</td>
							<td>${designer.last_login_time }</td>
							<td>${designer.login_count }</td>
							<td>
								<c:choose>
									<c:when test="${designer.status == 'Y'}">
										是
									</c:when>
									<c:when test="${designer.status == 'N'}">
										否
									</c:when>
									<c:otherwise>
									
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${designer.status == 'Y'}">
										<a href="<%=path %>/admin/forbid_designer?email=${designer.e_mail }">冻结</a>
									</c:when>
									<c:when test="${designer.status == 'N'}">
										<a href="<%=path %>/admin/start_designer?email=${designer.e_mail }">解冻</a>
									</c:when>
									<c:otherwise>
									
									</c:otherwise>
								</c:choose>
								
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div style="text-align: right; padding: 60px;">
				<a href="<%=path %>/admin/query_designer_page?pageSize=${requestScope.currentPageSize }">首页</a>
				<a href="<%=path %>/admin/query_designer_page?pageNo=${requestScope.currentPage - 1 }&pageSize=${requestScope.currentPageSize }">上一页</a>
				<a href="<%=path %>/admin/query_designer_page?pageNo=${requestScope.currentPage + 1 }&pageSize=${requestScope.currentPageSize }">下一页</a>
				<a href="<%=path %>/admin/query_designer_page?pageNo=${requestScope.totalPage }&pageSize=${requestScope.currentPageSize }">尾页</a>
				<span>${requestScope.currentPage }</span>/<span>${requestScope.totalPage }</span>
				<span>每页显示数据</span>
				<input type="text" name="pageSize" style="width: 50px;" value="${requestScope.currentPageSize }" form="skip" />
				<input type="submit" value="跳转" form="skip" />
				<input type="text" name="pageNo" style="width: 30px;" form="skip" />
				<span>页</span>
				<form id="skip" action="<%=path %>/admin/query_designer_page" method="post"></form>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>