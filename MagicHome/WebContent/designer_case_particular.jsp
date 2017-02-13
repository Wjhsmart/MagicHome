<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<% 
		String path = request.getContextPath(); 
	%>
<!DOCTYPE html>
<html>
<head>
<title>设计师案例详情</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Majestic Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link rel="shortcut icon" href="<%=path %>/images/favicon.png">
<link href="<%=path %>/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="<%=path %>/css/style1.css" rel='stylesheet' type='text/css' />
<link href='<%=path %>/css/fonts.css' rel='stylesheet' type='text/css'>
<link href="<%=path %>/css/jh_style.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.11.0.min.js"></script>
<!---- start-smoth-scrolling---->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<link type="text/css" rel="stylesheet" href="<%=path %>/css/lanrenzhijia.css"></link>
<!---- start-smoth-scrolling---->

</head>
<body>
	<!----start-header---->
	<div class="header" id="home">
		<jsp:include page="head_common.jsp"></jsp:include>
	</div>	
	
	<!----end-header---->
	<!--blog-->
	<div class="blog">
		<div class="container">
			<div class="blog-head heading">
			
			</div>	
			<h1 style="margin-top: 20px; margin-left: 200px;">设计师案例详情</h1>
			<div style="text-align: right; margin-right: 250px;">
				<a href="<%=path %>/page/designer_case">浏览更多设计师案例》</a>
			</div>
			<div class="blog-top">
				<!-- 代码 开始 -->
				<div id="content">
					<div class="mygallery clearfix">
						<div class="tn3 album">
							<ol>
								<li>
									<h4 style="font-size: 16px;">${requestScope.designerCase.name }</h4>
									<div class="tn3 description">${requestScope.designerCase.des }</div> 
									<a href="<%=path %>/${requestScope.designerCase.image1 }">
										<img src="<%=path %>/${requestScope.designerCase.image1 }" />
									</a>
								</li>
								<li>
									<h4 style="font-size: 16px;">${requestScope.designerCase.name }</h4>
									<div class="tn3 description">${requestScope.designerCase.des }</div> 
									<a href="<%=path %>/${requestScope.designerCase.image2 }">
										<img src="<%=path %>/${requestScope.designerCase.image2 }" />
									</a>
								</li>
								<li>
									<h4 style="font-size: 16px;">${requestScope.designerCase.name }</h4>
									<div class="tn3 description">${requestScope.designerCase.des }</div> 
									<a href="<%=path %>/${requestScope.designerCase.image3 }">
										<img src="<%=path %>/${requestScope.designerCase.image3 }" />
									</a>
								</li>
								<li>
									<h4 style="font-size: 16px;">${requestScope.designerCase.name }</h4>
									<div class="tn3 description">${requestScope.designerCase.des }</div> 
									<a href="<%=path %>/${requestScope.designerCase.image4 }">
										<img src="<%=path %>/${requestScope.designerCase.image4 }" />
									</a>
								</li>
								<li>
									<h4 style="font-size: 16px;">${requestScope.designerCase.name }</h4>
									<div class="tn3 description">${requestScope.designerCase.des }</div> 
									<a href="<%=path %>/${requestScope.designerCase.image5 }">
										<img src="<%=path %>/${requestScope.designerCase.image5 }" />
									</a>
								</li>
							</ol>
						</div>
					</div>
				</div>
				<script src="<%=path %>/js/js/jquery_min.js"></script>
				<script src="<%=path %>/js/js/jquery.tn3lite.min.js"></script>
				<script>
					$(document).ready(function() {
						var tn1 = $('.mygallery').tn3({
							skinDir : "skins",
							imageClick : "fullscreen",
							image : {
								maxZoom : 1.5,
								crop : true,
								clickEvent : "dblclick",
								transitions : [ {
									type : "blinds"
								}, {
									type : "grid"
								}, {
									type : "grid",
									duration : 460,
									easing : "easeInQuad",
									gridX : 1,
									gridY : 8,
									// flat, diagonal, circle, random
									sort : "random",
									sortReverse : false,
									diagonalStart : "bl",
									// fade, scale
									method : "scale",
									partDuration : 360,
									partEasing : "easeOutSine",
									partDirection : "left"
								} ]
							}
						});
					});
				</script>
				<!-- 代码 结束 -->

			</div>
		</div>
	</div>
	<!--blog-->
	<!--footer-starts--> 
			<jsp:include page="bottom_common.jsp"></jsp:include>
	<!--footer-ends--> 
</body>
</html>