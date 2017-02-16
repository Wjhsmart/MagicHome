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
	<a href="<%=path %>/designer/designer_case_page">返回</a>
	<div class="container">
		<div class="form">
			<c:choose>
				<c:when test="${empty(requestScope.succeed) }">
					<h3>修改案例信息：</h3>
					<form action="<%=path %>/designer/designer_update_case?id=${requestScope.designerCase.id }" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="${requestScope.designerCase.id }" /> 
						<br /><br /> 
						<em>案例名称：</em> 
						<input id="name" type="text" name="name" value="${requestScope.designerCase.name }" /> 
						<br /><br /> 
						<em>小区名称：</em> 
						<input id="price" type="text" name="plot_name" value="${requestScope.designerCase.plot_name }" /> 
						<br /><br /> 
						<em>装修风格：</em> 
						<select name="style">
							<c:choose>
								<c:when test="${requestScope.designerCase.style == '简约' }">
									<option value="简约">简约</option>
			  						<option value="现代">现代</option>
			  						<option value="地中海">地中海</option>
			  						<option value="乡村田园">乡村田园</option>
			  						<option value="欧式">欧式</option>
			  						<option value="中式">中式风格</option>
			  						<option value="混搭">混搭风格</option>
								</c:when>
								<c:when test="${requestScope.designerCase.style == '现代' }">
									<option value="现代">现代</option>
									<option value="简约">简约</option>
			  						<option value="地中海">地中海</option>
			  						<option value="乡村田园">乡村田园</option>
			  						<option value="欧式">欧式</option>
			  						<option value="中式">中式风格</option>
			  						<option value="混搭">混搭风格</option>
								</c:when>
								<c:when test="${requestScope.designerCase.style == '地中海' }">
									<option value="地中海">地中海</option>
									<option value="简约">简约</option>
			  						<option value="现代">现代</option>
			  						<option value="乡村田园">乡村田园</option>
			  						<option value="欧式">欧式</option>
			  						<option value="中式">中式风格</option>
			  						<option value="混搭">混搭风格</option>
								</c:when>
								<c:when test="${requestScope.designerCase.style == '乡村田园' }">
									<option value="乡村田园">乡村田园</option>
									<option value="简约">简约</option>
			  						<option value="现代">现代</option>
			  						<option value="地中海">地中海</option>
			  						<option value="欧式">欧式</option>
			  						<option value="中式">中式风格</option>
			  						<option value="混搭">混搭风格</option>
								</c:when>
								<c:when test="${requestScope.designerCase.style == '欧式' }">
									<option value="欧式">欧式</option>
									<option value="简约">简约</option>
			  						<option value="现代">现代</option>
			  						<option value="地中海">地中海</option>
			  						<option value="乡村田园">乡村田园</option>
			  						<option value="中式">中式风格</option>
			  						<option value="混搭">混搭风格</option>
								</c:when>
								<c:when test="${requestScope.designerCase.style == '中式风格' }">
									<option value="中式">中式风格</option>
									<option value="简约">简约</option>
			  						<option value="现代">现代</option>
			  						<option value="地中海">地中海</option>
			  						<option value="乡村田园">乡村田园</option>
			  						<option value="欧式">欧式</option>
			  						<option value="混搭">混搭风格</option>
								</c:when>
								<c:when test="${requestScope.designerCase.style == '混搭风格' }">
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
						<input id="image" type="file" name="image1" value="${requestScope.designerCase.image1 }" /> 
						<br /><br /> 
						<em>商品图片2：</em> 
						<input id="image" type="file" name="image2" value="${requestScope.designerCase.image2}" /> 
						<br /><br /> 
						<em>商品图片3：</em> 
						<input id="image" type="file" name="image3" value="${requestScope.designerCase.image3 }" /> 
						<br /><br /> 
						<em>商品图片4：</em> 
						<input id="image" type="file" name="image4" value="${requestScope.designerCase.image4 }" /> 
						<br /><br /> 
						<em>商品图片5：</em> 
						<input id="image" type="file" name="image5" value="${requestScope.designerCase.image5 }" /> 
						<br /><br /> 
						<em>商品描述：</em> 
						<textarea rows="5" cols="30" id="des" name="des">${requestScope.designerCase.des }</textarea>
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