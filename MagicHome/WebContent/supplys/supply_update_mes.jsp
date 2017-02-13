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
		var phone = document.getElementById("phone").value;
		var span = document.getElementById("span");
		
		if (name != '' && phone != '') {
			if (phone.length != 11) {
				span.innerHTML = "手机号为11为数字";
				return false;
			}
			return true;
		} else {
			if (name == '') {
				span.innerHTML = "名称不能为空";
				return false;
			} else if (phone == '') {
				span.innerHTML = "手机不能为空";
				return false;
			}
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
					<h3>${sessionScope.supply.e_mail }:</h3>
					<span id="span"></span> 
					<form onsubmit="return formCheck( )" action="<%=path %>/supply/supply_update_mes" method="post">
						<em>建材商名称：</em> 
						<input id="name" type="text" name="name" value="${sessionScope.supply.name }" /> 
						<br /><br /> 
						<em>负责人：</em> 
						<input id="principal" type="text" name="principal" value="${sessionScope.supply.principal }" /> 
						<br /><br /> 
						<em>地址：</em> 
						<input id="address" type="text" name="address" value="${sessionScope.supply.address }" /> 
						<br /><br /> 
						<em>手机号：</em> 
						<input id="phone" type="text" name="phone" value="${sessionScope.supply.phone }" /> 
						<br /><br /> 
						<em>固定电话：</em> 
						<input id="tel" type="text" name="tel" value="${sessionScope.supply.tel }" /> 
						<br /><br /> 
						<em>成立时间：</em> 
						<input id="open_date" type="date" name="open_date" value="${sessionScope.supply.open_date }" /> 
						<br /><br /> 
						<em>经度：</em> 
						<input id="longitude" type="text" name="longitude" value="${sessionScope.supply.longitude }" /> 
						<br /><br /> 
						<em>纬度：</em> 
						<input id="latitude" type="text" name="latitude" value="${sessionScope.supply.latitude }" /> 
						<br /><br /> 
						<em>描述：</em> 
						<textarea rows="5" cols="30" id="des" name="des">${sessionScope.supply.des }</textarea>
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