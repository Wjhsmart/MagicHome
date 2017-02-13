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
<title>修改商品信息</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
<script>
	function formCheck() {
		var name = document.getElementById("name").value;
		if (name != '' && price != '') {
			return true;
		}
		span.innerHTML = "商品名称、价格不能为空";
		return false;
	}
</script>
</head>
<body>
	<div class="container">
		<div class="form">
			<c:choose>
				<c:when test="${empty(requestScope.succeed) }">
					<h3>修改商品信息：</h3>
					<span id="span">${requestScope.errorMes }</span> 
					<form action="<%=path %>/supply/supply_update_product?id=${requestScope.product.id }" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="${requestScope.product.id }" /> 
						<br /><br /> 
						<em>商品名称：</em> 
						<input id="name" type="text" name="name" value="${requestScope.product.name }" /> 
						<br /><br /> 
						<em>商品价格：</em> 
						<input id="price" type="text" name="price" value="${requestScope.product.price }" /> 
						<br /><br /> 
						<em>商品折后价：</em> 
						<input id="sale_price" type="text" name="sale_price" value="${requestScope.product.sale_price }" /> 
						<br /><br /> 
						<em>商品图片：</em> 
						<input id="image" type="file" name="image" value="${requestScope.product.image }" /> 
						<br /><br /> 
						<em>商品描述：</em> 
						<textarea rows="5" cols="30" id="des" name="des">${requestScope.product.des }</textarea>
						<br /><br /> 
						<em>创建时间：</em> 
						<input id="created_time" type="date" name="created_time" value="${requestScope.product.created_time }" /> 
						<br /><br />
						<em>状态：</em> 
						<select name="status">
							<option value="Y">上架
							<option value="N">下架
						</select>
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