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
<title>设计师详情</title>
<link rel="shortcut icon" href="<%=path %>/images/favicon.png">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="<%=path %>/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="<%=path %>/css/style1.css" rel='stylesheet' type='text/css' />
<link href='<%=path %>/css/fonts.css' rel='stylesheet' type='text/css'>
<link href='<%=path %>/css/jh_style.css' rel='stylesheet' type='text/css'>
<script src="<%=path %>/js/jquery-1.11.0.min.js"></script>
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
</head>
<body>
	<!----头部开始---->
	<div class="header" id="home">
		<jsp:include page="head_common.jsp" ></jsp:include>
	</div>	
	<!----头部结束---->
	<!-- about -->
	<div class="about" style="margin-top: 100px;">
		<div class="container">
			<div class="about-top">
				<div class="col-md-5 about-top-left">
					<img src="<%=path %>/${requestScope.designer.head_icon }" class="img-responsive" style="width: 250px; height: 250px; border-radius: 50%;" alt=" " />
				</div>
				<div class="col-md-7 about-top-right" style="margin-left: -120px; margin-top: 30px;">
					<h3>${requestScope.designer.name }</h3>
					
					<ul class="des_ul">
						<li>
							<h5>手机：<em>${requestScope.designer.phone }</em></h5>
							
						</li>
						<li>
							<h5>地址：<em>${requestScope.designer.address }</em></h5>
							
						</li>
						<li>
							<h5>工作经验：<em>${requestScope.designer.experience }</em></h5>
							
						</li>
						<li>
							<h5>擅长风格：<em>${requestScope.designer.style }</em></h5>
							
						</li>					
						<li>
							<h5>个人介绍：</h5>
							<p>
								${requestScope.designer.des }
							</p>
						</li>
						<li class="o-btn">
							<c:choose>
								<c:when test="${requestScope.designer.collected }">
									<span class="col">已收藏</span>
								</c:when>
								<c:otherwise>
									<a href="<%=path %>/page/designer_col?id=${requestScope.designer.id }">收藏设计师</a>
								</c:otherwise>
							</c:choose>
						</li>
					</ul>
					
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
	<div class="about-banner">
		<div class="container">
<!-- Slider-starts-Here -->
				<script src="js/responsiveslides.min.js"></script>
				 <script>
				    // You can also use "$(window).load(function() {"
				    $(function () {
				      // Slideshow 4
				      $("#slider3").responsiveSlides({
				        auto: true,
				        pager: true,
				        nav: false,
				        speed: 500,
				        namespace: "callbacks",
				        before: function () {
				          $('.events').append("<li>before event fired.</li>");
				        },
				        after: function () {
				          $('.events').append("<li>after event fired.</li>");
				        }
				      });
				
				    });
				  </script>
			<!--//End-slider-script -->
		</div>
	</div>
	
<!-- //about -->
	<!--设计师案例开始-->
	<div class="offer">
		<div class="container">
			<div class="offer-top heading">
				<h2>个人作品<a href="<%=path %>/page/query_desgienr_case_page?id=${requestScope.designer.id }" style="font-size: 14px; margin-left: 900px;">更多></a></h2>
			</div>
			<div class="offer-bottom">
				<c:choose>
					<c:when test="${empty(requestScope.designerCases) }">
						<h3 style="color: red;">该设计师暂时没有上传案例</h3>
					</c:when>
					<c:otherwise>
						<c:forEach items="${requestScope.designerCases }" var="designerCase">
							<div class="col-md-3 offer-left">
								<a href="<%=path %>/page/designer_case_particular?id=${designerCase.id }"><img src="<%=path %>/${designerCase.image1 }" alt="${designerCase.name }" /></a>
								<h4><a href="<%=path %>/page/designer_case_particular?id=${designerCase.id }">${designerCase.name }</a></h4>
								<p>${designerCase.des }</p>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				
				
				
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--设计师案例结束--> 

	<jsp:include page="bottom_common.jsp"></jsp:include>
</body>
</html>