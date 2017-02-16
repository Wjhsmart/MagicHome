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
<title>添加案例</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
<script>
	function formCheck() {
		var name = document.getElementById("name").value;
		var plot_name = document.getElementById("plot_name").value;
		var style = document.getElementById("style").value;
		if (name != '' && plot_name != '' && style != '') {
			return true;
		}
		span.innerHTML = "案例名称、小区名称、装修风格不能为空";
		return false;
	}
</script>
</head>
<body>
	<c:choose>
		<c:when test="${empty(sessionScope.designer) }">
			<h3 style="color: red;">当前账号已失效，请重新登入</h3>
		</c:when>
		<c:when test="${!empty(requestScope.succeed) }">
			<h3>${requestScope.succeed }</h3>
		</c:when>
		<c:otherwise>
			<div class="container">
				<div class="form">
					<h3>添加案例：</h3>
					<span id="span">${requestScope.errorMes }</span> 
					<form onsubmit="return formCheck()" action="<%=path %>/designer/designer_add_case?id=${sessionScope.designer.id }" method="post" enctype="multipart/form-data">
						<input type="hidden" name="designer_id" value="${sessionScope.designer.id }" />
						<em>案例名称：</em> 
						<input type="text" id="name" name="name" placeholder="请输入案例名称" /> 
						<br /><br /> 
						<em>小区名称：</em> 
						<input type="text" id="plot_name" name="plot_name" placeholder="请输入小区名称" /> 
						<br /><br /> 
						<em>装修风格：</em> 
						<select name="style">
  							<option value="">装修风格</option>
  							<option value="简约">简约</option>
  							<option value="现代">现代</option>
  							<option value="地中海">地中海</option>
  							<option value="乡村田园">乡村田园</option>
  							<option value="欧式">欧式</option>
  							<option value="中式">中式风格</option>
  							<option value="混搭">混搭风格</option>
						</select>
						<br /><br /> 
						<em>装修图片1：</em> 
						<input type="file" id="image" name="image1" placeholder="请选择案例图片" /> 
						<br /><br /> 
						<em>装修图片2：</em> 
						<input type="file" id="image" name="image2" placeholder="请选择案例图片" /> 
						<br /><br /> 
						<em>装修图片3：</em> 
						<input type="file" id="image" name="image3" placeholder="请选择案例图片" /> 
						<br /><br /> 
						<em>装修图片4：</em> 
						<input type="file" id="image" name="image4" placeholder="请选择案例图片" /> 
						<br /><br /> 
						<em>装修图片5：</em> 
						<input type="file" id="image" name="image5" placeholder="请选择案例图片" /> 
						<br /><br /> 
						<em>装修描述：</em> 
						<textarea rows="5" cols="30" id="des" name="des"></textarea>
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