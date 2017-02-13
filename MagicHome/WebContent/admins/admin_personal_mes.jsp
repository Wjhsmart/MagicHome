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
	<h3>管理员信息:</h3>
	<div>
		<ul class="admin_mes">
			<li>
				<span>Email:</span> 
				<em>${sessionScope.admin.e_mail }</em>
			</li>
			<li>
				<span>名称:</span> 
				<em>${sessionScope.admin.name }</em>
			</li>
			<li>
				<span>电话:</span> 
				<em>${sessionScope.admin.phone }</em>
			</li>
			<li>
				<span>身份:</span> 
				<em> 
					<c:choose>
						<c:when test="${sessionScope.admin.role == 'super'}">
									<span>超级管理员</span>
								</c:when>
						<c:when test="${sessionScope.admin.role == 'normal'}">
									<span>普通管理员</span>
								</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>
				</em>
			</li>
			<li>
				<span>创建时间:</span> 
				<em>${sessionScope.admin.created_time }</em>
			</li>
			<li>
				<span>最近登入时间:</span> 
				<em>${requestScope.lastLoginTime }</em>
			</li>
			<li>
				<span>登入次数:</span> 
				<em>${sessionScope.admin.login_count }</em>
			</li>
			<li>
				<span>账号是否可用:</span> 
				<em> 
					<c:choose>
						<c:when test="${sessionScope.admin.status == 'Y'}">
									<span>是</span>
								</c:when>
						<c:when test="${sessionScope.admin.status == 'N'}">
									<span>否</span>
								</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>
				</em>
			</li>
			<li class="update" style="text-align: center">
				<a href="<%=path %>/admin/update_personal_mes_page">修改信息</a>
			</li>
		</ul>
	</div>


</body>
</html>