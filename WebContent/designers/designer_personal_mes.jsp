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
<title>显示设计师信息</title>
<link rel="shortcut icon" href="<%=path %>/images/favicon.png">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
</head>
<body>
	<h3 class="title">设计师信息:</h3>
	<div class="container">
		<table>
			<tr>
				<td class="logo_li">
					<span>头像:</span>
					<img class="logo" src="<%=path %>/${sessionScope.designer.head_icon }" />
				</td>
			</tr>
			<tr>
				<td><span>Email:</span></td>
				<td><em>${sessionScope.designer.e_mail }</em></td>
			</tr>
			<tr>
				<td><span>设计师名称:</span></td>
				<td><em>${sessionScope.designer.name }</em></td>
			</tr>
			<tr>
				<td><span>电话:</span></td>
				<td><em>${sessionScope.designer.phone }</em></td>
			</tr>
			<tr>
				<td><span>地址:</span></td>
				<td><em>${sessionScope.designer.address }</em></td>
			</tr>
			<tr>
				<td><span>工作经验:</span></td>
				<td><em>${sessionScope.designer.experience }</em></td>
			</tr>
			<tr>
				<td><span>擅长风格:</span></td>
				<td><em>${sessionScope.designer.style }</em></td>
			</tr>
			<tr>
				<td><span>设计师描述:</span></td>
				<td><em>${sessionScope.designer.des }</em></td>
			</tr>
			<tr>
				<td><span>最近登入时间:</span></td>
				<td><em>${requestScope.lastLoginTime }</em></td>
			</tr>
			<tr>
				<td><span>登入次数:</span></td>
				<td><em>${sessionScope.designer.login_count }</em></td>
			</tr>
			<tr>
				<td><span>账号是否可用:</span></td>
				<td><em> 
					<c:choose>
						<c:when test="${sessionScope.designer.status == 'Y'}">
									<span>是</span>
								</c:when>
						<c:when test="${sessionScope.designer.status == 'N'}">
									<span>否</span>
								</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>
				</em></td>
			</tr>
			<tr class="update" style="text-align: center">
				<td><a href="<%=path %>/designer/designer_update_mes_page">修改信息</a></td>
			</tr>
		</table>
	</div>


</body>
</html>