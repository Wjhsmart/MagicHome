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
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="Majestic Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
	Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<title>设计师个人案例</title>
<link rel="shortcut icon" href="<%=path %>/images/favicon.png">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="<%=path %>/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="<%=path %>/css/style1.css" rel='stylesheet' type='text/css' />
<link href='<%=path %>/css/fonts.css' type='text/css' />
<link href="<%=path %>/css/jh_style.css" type="text/css" rel="stylesheet" />
<script src="js/jquery-1.11.0.min.js"></script>
<!---- start-smoth-scrolling---->
<script type="text/javascript" src="<%=path %>/js/move-top.js"></script>
<script type="text/javascript" src="<%=path %>/js/easing.js"></script>
<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
				});
			});
		</script>
<!---- start-smoth-scrolling---->
<link rel="stylesheet" type="text/css" href="<%=path %>/css/common.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/css/style4.css" />
<script type="text/javascript" src="<%=path %>/js/modernizr.custom.79639.js"></script>
</head>
<body>
	<!----start-header---->
	<div class="header" id="home">
		<jsp:include page="head_common.jsp"></jsp:include>
	</div>	
	<!----end-header---->
	<!--中间部分开始--> 
	<div class="about">
		<div class="container">
			<div class="about-top heading">
				<h1 style="margin-top: 10px;"><span style="color: green;">${requestScope.designer.name }</span>的设计案例</h1>
				<h4>下面是<span style="color: green;">${requestScope.designer.name }</span>的效果图</h4>
				<c:choose>
					<c:when test="${empty(requestScope.designerCases) }">
						<h4 style="color: red;">该设计师暂时没有上传案例</h4>
					</c:when>
					<c:otherwise>
						<div class="about-bottom company_case">
							<c:forEach items="${requestScope.designerCases }" var="designerCase">
								<div class="col-md-4 about-left">
									<a href="<%=path %>/page/designer_case_particular?id=${designerCase.id }"><img src="<%=path %>/${designerCase.image1 }" alt="${designerCase.name }" /></a>
									<h5><a href="<%=path %>/page/designer_case_particular?id=${designerCase.id }">${designerCase.name }</a></h5>
									<p>${designerCase.des }</p>
								</div>
							</c:forEach>
							
							<div class="clearfix"></div>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<!-- 分页开始 -->
		<div style="text-align: right; padding: 60px;">
			<a href="<%=path %>/page/query_desgienr_case_page?id=${requestScope.designer.id }&pageSize=${requestScope.currentPageSize }" style="color: #000;">首页</a>
			<a href="<%=path %>/page/query_desgienr_case_page?pageNo=${requestScope.currentPage - 1 }&id=${requestScope.designer.id }&pageSize=${requestScope.currentPageSize }" style="color: #000;">上一页</a>
			<a href="<%=path %>/page/query_desgienr_case_page?pageNo=${requestScope.currentPage + 1 }&id=${requestScope.designer.id }&pageSize=${requestScope.currentPageSize }" style="color: #000;">下一页</a>
			<a href="<%=path %>/page/query_desgienr_case_page?pageNo=${requestScope.totalPage }&id=${requestScope.designer.id }&pageSize=${requestScope.currentPageSize }" style="color: #000;">尾页</a>
			<span>${requestScope.currentPage }</span>/<span>${requestScope.totalPage }</span>
			<span>每页显示数据</span>
			<input type="text" name="pageSize" style="width: 50px;" value="${requestScope.currentPageSize }" form="skip" />
			<input type="submit" value="跳转" form="skip" />
			<input type="text" name="pageNo" style="width: 30px;" form="skip" />
			<span>页</span>
			<form id="skip" action="<%=path %>/page/query_desgienr_case_page?id=${requestScope.designer.id }" method="post"></form>
		</div>
		<!-- 分页结束 -->
	</div>
	<!-- 中间部分结束 -->
	
	<jsp:include page="bottom_common.jsp"></jsp:include>
</body>
</html>