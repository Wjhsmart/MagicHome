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
<title>建材商品</title>
<link rel="shortcut icon" href="<%=path %>/images/favicon.png">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="<%=path %>/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="<%=path %>/css/style1.css" rel='stylesheet' type='text/css' />
<link href='<%=path %>/css/fonts.css' rel='stylesheet' type='text/css'>
<link href='<%=path %>/css/jh_style.css' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="<%=path %>/css/lightbox.css" type="text/css" media="all" />
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
<script type="application/x-javascript"> addEventListener("load", function() {setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--bootstrap-->
			 <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<!--coustom css-->
			<link href="<%=path %>/css/style2.css" rel="stylesheet" type="text/css"/>
		<!--default-js-->
			<script src="<%=path %>/js/jquery-2.1.4.min.js"></script>
		<!--bootstrap-js-->
<!---- start-smoth-scrolling---->
</head>
<body>
	<!----头部开始---->
	<div class="header" id="home">
		<jsp:include page="head_common.jsp" ></jsp:include>
	</div>	
	<!----头部结束---->
	<!--light-box-files -->
		<script src="<%=path %>/js/jquery.chocolat.js"></script>
		<link rel="stylesheet" href="<%=path %>/css/chocolat.css" type="text/css" media="screen" charset="utf-8" />
		<!--light-box-files -->
		<script type="text/javascript" charset="utf-8">
		$(function() {
			$('.gallery-bottom a').Chocolat();
		});
		</script>
	<!--gallery-starts-->
                <div class="gallery">
                    <div class="container">
                        <div class="gallery-text">
                        <h2>建材商品</h2>
                        <p>本站集合了所有最诚信的商家提供的建材商品</p>
                        </div>
                            <div class="grid-gallery offer-left">
                            	<c:forEach items="${requestScope.products }" var="product">
                            		<div class="col-md-4 col-xs-4 gallery-grid" style="margin-bottom: 100px;">
	                                    <a class="example-image-link" href="<%=path %>/${product.image }" data-lightbox="example-set" data-title="${product.des }">
											<img class="example-image" src="<%=path %>/${product.image }" alt="" style="height: 300px; width: 350px;"/>
										</a>
										<h6 style="width: 200px;">￥${product.sale_price }</h6>
										<br /><br />
										<h5 style="color: red;">原价：<del>${product.price }</del></h5>
										<br />
										<h3 style="margin: 0px 120px;">${product.name }</h3>
										<br /><br />
										<c:choose>
											<c:when test="${product.collected }">
												<span class="col" style="margin: 0px 120px;">已收藏</span>
											</c:when>
											<c:otherwise>
												<a href="<%=path %>/page/product_col?id=${product.id }" class="col" style="margin: 0px 120px;">收藏商品</a>
											</c:otherwise>
										</c:choose>
	                                </div>
                            	</c:forEach>
                                
                            <div class="clearfix"></div>
                        </div>
                        <script src="<%=path %>/js/lightbox.js"></script>
                    </div>
                    <div class="pagination" style="margin-left: 600px;">
						<nav>
			  				<ul class="pager">
			    				<li><a href="<%=path %>/page/product?pageNo=${requestScope.currentPage - 1 }&pageSize=${requestScope.currentPageSize }">上一页</a></li>
			    				<li><a href="<%=path %>/page/product?pageNo=${requestScope.currentPage + 1 }&pageSize=${requestScope.currentPageSize }">下一页</a></li>
			  					<span>${requestScope.currentPage }</span>/<span>${requestScope.totalPage }</span>
			  				</ul>
						</nav>
					</div>
            </div>
            <!--gallery-starts-->  
	
	<jsp:include page="bottom_common.jsp"></jsp:include>
</body>
</html>