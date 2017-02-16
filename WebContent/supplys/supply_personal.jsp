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
<title>建材商个人信息</title>
<link rel="shortcut icon" href="<%=path %>/images/favicon.png">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
</head>
<body>
	<h3 class="title">建材商信息:</h3>
	<div class="container">
		<table>
			<tr>
				<td>Email:</td>
				<td><em>${sessionScope.supply.e_mail }</em></td>
			</tr>
			<tr>
				<td>建材商名称:</td>
				<td><em>${sessionScope.supply.name }</em></td>
			</tr>
			<tr>
				<td>负责人:</td>
				<td><em>${sessionScope.supply.principal }</em></td>
			</tr>
			<tr>
				<td>地址:</td> 
				<td><em>${sessionScope.supply.address }</em></td>
			</tr>
			<tr>
				<td>手机:</td> 
				<td><em>${sessionScope.supply.phone}</em></td>
			</tr>
			<tr>
				<td>固定电话:</td>
				<td><em>${sessionScope.supply.tel }</em></td>
			</tr>
			<tr>
				<td>成立时间:</td>
				<td><em>${sessionScope.supply.open_date }</em></td>
			</tr>
			<tr>
				<td>经度:</td>
				<td><em>${sessionScope.supply.longitude }</em></td>
			</tr>
			<tr>
				<td>纬度:</td> 
				<td><em>${sessionScope.supply.latitude }</em></td>
			</tr>
			<tr>
				<td>描述:</td>
				<td><em>${sessionScope.supply.des }</em></td>
			</tr>
			<tr>
				<td>是否审核:</td>
				<td><em> 
					<c:choose>
						<c:when test="${sessionScope.supply.checked == 'Y'}">
									是
								</c:when>
						<c:when test="${sessionScope.supply.checked == 'N'}">
									否
								</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>
				</em></td>
			</tr>
			<tr>
				<td>审核时间</td>
				<td><em>${sessionScope.supply.checked_time }</em></td>
			</tr>
			<tr>
				<td>创建时间:</td> 
				<td><em>${sessionScope.supply.created_time }</em></td>
			</tr>
			<tr>
				<td>最近登入时间:</td>
				<td><em>${requestScope.last_login_time }</em></td>
			</tr>
			<tr>
				<td>登入次数:</td>
				<td><em>${sessionScope.supply.login_count }</em></td>
			</tr>
			<tr>
				<td>账号是否可用:</td> 
				<td><em> 
					<c:choose>
						<c:when test="${sessionScope.supply.status == 'Y'}">
									是
								</c:when>
						<c:when test="${sessionScope.supply.status == 'N'}">
									否
								</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>
				</em></td>
			</tr>
			<tr class="update" style="text-align: center">
				<td><a href="<%=path %>/supply/supply_update_mes_page" >修改信息</a></td>
			</tr>
		</table>
	</div>


</body>
</html>