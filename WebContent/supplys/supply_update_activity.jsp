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
<title>修改活动信息</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
</head>
<body>
	<a href="<%=path %>/supply/supply_activity_page">返回</a>
	<div class="container">
		<div class="form">
			<c:choose>
				<c:when test="${empty(requestScope.succeed) }">
					<h3>修改活动信息：</h3>
					<form action="<%=path %>/supply/supply_update_activity?id=${requestScope.activity.id }" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="${requestScope.activity.id }" /> 
						<br /><br /> 
						<em>活动名称：</em> 
						<input id="name" type="text" name="name" value="${requestScope.activity.name }" /> 
						<br /><br /> 
						<em>活动图片：</em> 
						<input id="image" type="file" name="image" value="${requestScope.activity.image }" /> 
						<br /><br /> 
						<em>活动描述：</em> 
						<textarea rows="5" cols="30" id="des" name="des">${requestScope.activity.des }</textarea>
						<br /><br /> 
						<em>创建时间：</em> 
						<input id="created_time" type="date" name="created_time" value="${requestScope.activity.created_time }" /> 
						<br /><br />
						<input type="submit" value="提交" class="btn" style="margin-left: 100px;" /> 
					</form>
				</c:when>
				<c:otherwise>
					<h3>${requestScope.succeed }</h3>
				</c:otherwise>
			</c:choose>
			
		</div>
	</div>
</body>
</html>