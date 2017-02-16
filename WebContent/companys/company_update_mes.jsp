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
<title>修改自己的信息</title>
<link rel="shortcut icon" href="<%=path %>/images/favicon.png">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
<script>
	function formCheck() {
		var name = document.getElementById("name").value;
		var principal = document.getElementById("principal").value;
		var phone = document.getElementById("phone").value;
		var tel = document.getElementById("tel").value;
		var address = document.getElementById("address").value;
		var longitude = document.getElementById("longitude").value;
		var latitude = document.getElementById("latitude").value;
		var des = document.getElementById("des").value;
		var span = document.getElementById("span");
		if (name != '' && phone != '' && principal != '' && tel != '' && address != '' && longitude != '' && latitude != '' && des != '') {
			if (phone.length != 11) {
				span1.innerHTML = "手机号格式错误";
				return false;
			}
			return true;
		} else {
			span.innerHTML = "都不能为空";
		}
		return false;
	}
	
</script>
</head>
<body>
	<div class="container">
		<div class="form">
			<c:choose>
				<c:when test="${empty(requestScope.succeed) }">
					<h3>${sessionScope.company.e_mail }:</h3>
					<form onsubmit="return formCheck( )" action="<%=path %>/company/update_mes" method="post" enctype="multipart/form-data">
						<span id="span"></span>
						<em>公司名称：</em> 
						<input id="name" type="text" name="name" value="${sessionScope.company.name }" /> 
						<br /><br /> 
						<em>公司负责人：</em> 
						<input id="principal" type="text" name="principal" value="${sessionScope.company.principal }" /> 
						<br /><br /> 
						<em>公司LOGO：</em> 
						<input id="logo" type="file" name="logo" value="${sessionScope.company.logo }" /> 
						<span>${requestScope.errorMese }</span>
						<br /><br /> 
						<em>手机号：</em> 
						<input id="phone" type="text" name="phone" value="${sessionScope.company.phone }" /> 
						<br /><br /> 
						<em>固定电话：</em> 
						<input id="tel" type="text" name="tel" value="${sessionScope.company.tel }" /> 
						<br /><br /> 
						<em>公司地址：</em> 
						<input id="address" type="text" name="address" value="${sessionScope.company.address }" /> 
						<br /><br /> 
						<em>经度：</em> 
						<input id="longitude" type="text" name="longitude" value="${sessionScope.company.longitude }" /> 
						<br /><br /> 
						<em>纬度：</em> 
						<input id="latitude" type="text" name="latitude" value="${sessionScope.company.latitude }" /> 
						<br /><br /> 
						<em>公司描述：</em> 
						<textarea rows="5" cols="30" id="des" name="des">${sessionScope.company.des }</textarea>
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