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
<title>添加商品</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
<script>
	function formCheck() {
		var name = document.getElementById("name").value;
		var price = document.getElementById("price").value;
		var sale_price = document.getElementById("sale_price").value;
		var span = document.getElementById("spanMes");
		if (name != '' && price != '') {
			return true;
		} else {
			if (name == '') {
				span.innerHTML = "名称不能为空";
				return false;
			} else if (price == '') {
				span.innerHTML = "价格不能为空";
				return false;
			}
		} 
		return false;
	}
</script>
</head>
<body>
	<c:choose>
		<c:when test="${empty(sessionScope.supply) }">
			<h3 style="color: red;">当前账号已失效，请重新登入</h3>
		</c:when>
		<c:when test="${!empty(requestScope.succeed) }">
			<h3>${requestScope.succeed }</h3>
		</c:when>
		<c:otherwise>
			<div class="container">
				<div class="form">
					<h3>添加商品：</h3>
					<span id="spanMes" style="margin-left: 130px;">${requestScope.errorMes }</span> 
					<form onsubmit="return formCheck()" action="<%=path %>/supply/supply_add_product?id=${sessionScope.supply.id }" method="post" enctype="multipart/form-data">
						<input type="hidden" name="supply_id" value="${sessionScope.supply.id }" />
						<em>商品名称：</em> 
						<input type="text" id="name" name="name" placeholder="请输入商品名称" /> 
						<br /><br /> 
						<em>商品价格：</em> 
						<input type="text" id="price" name="price" placeholder="请输入商品价格" /> 
						<br /><br /> 
						<em>商品折后价：</em> 
						<input type="text" id="sale_price" name="sale_price" placeholder="请输入商品折后价" /> 
						<br /><br /> 
						<em>商品图片：</em> 
						<input type="file" id="image" name="image" placeholder="请选择商品图片" /> 
						<br /><br /> 
						<em>商品描述：</em> 
						<textarea rows="5" cols="30" id="des" name="des"></textarea>
						<br /><br /> 
						<em>状态：</em> 
						<select name="status">
							<option value="Y">上架
							<option value="N">下架
						</select>
						<br /><br /> 
						<input type="submit" value="提交" class="btn" />
						<input type="reset" value="重置" class="btn" />
					</form>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	
</body>
</html>