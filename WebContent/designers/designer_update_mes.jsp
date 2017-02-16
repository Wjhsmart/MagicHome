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
				span1.innerHTML = "手机号必须为11位数字";
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
					<h3>${sessionScope.designer.e_mail }:</h3>
					<form onsubmit="return formCheck( )" action="<%=path %>/designer/designer_update_mes" method="post" enctype="multipart/form-data">
						<span id="span"></span>
						<em>设计师名称：</em> 
						<input id="name" type="text" name="name" value="${sessionScope.designer.name }" /> 
						<br /><br /> 
						<em>头像：</em> 
						<input id="logo" type="file" name="head_icon" value="${sessionScope.designer.head_icon }" /> 
						<span>${requestScope.errorMese }</span>
						<br /><br /> 
						<em>手机号：</em> 
						<input id="phone" type="text" name="phone" value="${sessionScope.designer.phone }" /> 
						<br /><br /> 
						<em>地址：</em> 
						<input id="address" type="text" name="address" value="${sessionScope.designer.address }" /> 
						<br /><br /> 
						<em>工作经验：</em> 
						<select name="experience">
							<c:choose>
								<c:when test="${sessionScope.designer.experience == '1-3' }">
									<option value="1-3">1-3年</option>
									<option value="3-5">3-5年</option>
									<option value="5-8">5-8年</option>
									<option value="8">8年以上</option>
								</c:when>
								<c:when test="${sessionScope.designer.experience == '3-5' }">
									<option value="3-5">3-5年</option>
									<option value="1-3">1-3年</option>
									<option value="5-8">5-8年</option>
									<option value="8">8年以上</option>
								</c:when>
								<c:when test="${sessionScope.designer.experience == '5-8' }">
									<option value="5-8">5-8年</option>
									<option value="3-5">3-5年</option>
									<option value="1-3">1-3年</option>
									<option value="8">8年以上</option>
								</c:when>
								<c:otherwise>
									<option value="8">8年以上</option>
									<option value="5-8">5-8年</option>
									<option value="3-5">3-5年</option>
									<option value="1-3">1-3年</option>
								</c:otherwise>
							</c:choose>
						</select>
						<br /><br /> 
						<em>擅长风格：</em> 
						<input id="style" type="text" name="style" value="${sessionScope.designer.style }" /> 
						<br /><br /> 
						<em>设计师描述：</em> 
						<textarea rows="5" cols="30" id="des" name="des">${sessionScope.designer.des }</textarea>
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