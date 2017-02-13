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
<title>修改案例信息</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
</head>
<body>
	<div class="container">
		<div class="form">
			<c:choose>
				<c:when test="${empty(requestScope.succeed) }">
					<h3>修改案例信息：</h3>
					<form action="<%=path %>/company/company_update_case?id=${requestScope.companyCase.id }" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="${requestScope.companyCase.id }" /> 
						<br /><br /> 
						<em>案例名称：</em> 
						<input id="name" type="text" name="name" value="${requestScope.companyCase.name }" /> 
						<br /><br /> 
						<em>小区名称：</em> 
						<input id="price" type="text" name="plot_name" value="${requestScope.companyCase.plot_name }" /> 
						<br /><br /> 
						<em>装修风格：</em> 
						<select name="style">
							<c:choose>
								<c:when test="${requestScope.companyCase.style == '简约' }">
									<option value="简约">简约</option>
		  							<option value="现代">现代</option>
		  							<option value="地中海">地中海</option>
		  							<option value="乡村田园">乡村田园</option>
		  							<option value="欧式">欧式</option>
		  							<option value="中式">中式风格</option>
		  							<option value="混搭">混搭风格</option>
								</c:when>
								<c:when test="${requestScope.companyCase.style == '现代' }">
									<option value="现代">现代</option>
									<option value="简约">简约</option>
		  							<option value="地中海">地中海</option>
		  							<option value="乡村田园">乡村田园</option>
		  							<option value="欧式">欧式</option>
		  							<option value="中式">中式风格</option>
		  							<option value="混搭">混搭风格</option>
								</c:when>
								<c:when test="${requestScope.companyCase.style == '地中海' }">
									<option value="地中海">地中海</option>
									<option value="简约">简约</option>
		  							<option value="现代">现代</option>
		  							<option value="乡村田园">乡村田园</option>
		  							<option value="欧式">欧式</option>
		  							<option value="中式">中式风格</option>
		  							<option value="混搭">混搭风格</option>
								</c:when>
								<c:when test="${requestScope.companyCase.style == '乡村田园' }">
									<option value="乡村田园">乡村田园</option>
									<option value="简约">简约</option>
		  							<option value="现代">现代</option>
		  							<option value="地中海">地中海</option>
		  							<option value="欧式">欧式</option>
		  							<option value="中式">中式风格</option>
		  							<option value="混搭">混搭风格</option>
								</c:when>
								<c:when test="${requestScope.companyCase.style == '欧式' }">
									<option value="欧式">欧式</option>
									<option value="简约">简约</option>
		  							<option value="现代">现代</option>
		  							<option value="地中海">地中海</option>
		  							<option value="乡村田园">乡村田园</option>
		  							<option value="中式">中式风格</option>
		  							<option value="混搭">混搭风格</option>
								</c:when>
								<c:when test="${requestScope.companyCase.style == '中式风格' }">
									<option value="中式">中式风格</option>
									<option value="简约">简约</option>
		  							<option value="现代">现代</option>
		  							<option value="地中海">地中海</option>
		  							<option value="乡村田园">乡村田园</option>
		  							<option value="欧式">欧式</option>
		  							<option value="混搭">混搭风格</option>
								</c:when>
								<c:when test="${requestScope.companyCase.style == '混搭风格' }">
									<option value="混搭">混搭风格</option>
									<option value="简约">简约</option>
		  							<option value="现代">现代</option>
		  							<option value="地中海">地中海</option>
		  							<option value="乡村田园">乡村田园</option>
		  							<option value="欧式">欧式</option>
		  							<option value="中式">中式风格</option>
								</c:when>
							</c:choose>
						</select>
						<br /><br /> 
						<em>商品图片1：</em> 
						<input id="image" type="file" name="image1" value="${requestScope.companyCase.image1 }" /> 
						<br /><br /> 
						<em>商品图片2：</em> 
						<input id="image" type="file" name="image2" value="${requestScope.companyCase.image2}" /> 
						<br /><br /> 
						<em>商品图片3：</em> 
						<input id="image" type="file" name="image3" value="${requestScope.companyCase.image3 }" /> 
						<br /><br /> 
						<em>商品图片4：</em> 
						<input id="image" type="file" name="image4" value="${requestScope.companyCase.image4 }" /> 
						<br /><br /> 
						<em>商品图片5：</em> 
						<input id="image" type="file" name="image5" value="${requestScope.companyCase.image5 }" /> 
						<br /><br /> 
						<em>商品描述：</em> 
						<input id="des" type="text" name="des" value="${requestScope.companyCase.des }" /> 
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