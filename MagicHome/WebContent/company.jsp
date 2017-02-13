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
<title>装修公司</title>
<link rel="shortcut icon" href="<%=path %>/images/favicon.png">
<meta name="keywords" content="Majestic Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
	Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="<%=path %>/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="<%=path %>/css/style1.css" rel='stylesheet' type='text/css' />
<link href="<%=path %>/css/jh_style.css" rel="stylesheet" type="text/css" />
<link href='<%=path %>/css/fonts.css' rel='stylesheet' type='text/css'>
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
	<!----start-header---->
	<div class="header" id="home">
		<jsp:include page="head_common.jsp"></jsp:include>
	</div>	
	<!----end-header---->
	<!--blog-->
	<div class="blog">
		<div class="container">
			<div class="blog-head heading">
				<h1>装修公司</h1>
			</div>	
			<div class="blog-top">
				<div class="col-md-9 blog-left">
					<div class="blog-main">
						<a href="" class="bg">本站入驻了全赣州最优秀的装修公司</a>
						<p>预约热线：4006-900-288,已有191016人咨询</p>
					</div>
					<c:forEach items="${requestScope.companys }" var="company">
						<div class="blog-main-one company_case">
							<div class="blog-one">
								<div class="col-md-5 blog-one-left">
									<a href="<%=path %>/page/company_particular?id=${company.id }"><img src="<%=path %>/${company.logo }" alt="${company.name }" /></a>
								</div>
								<div class="col-md-7 blog-one-left">
									<h3>
										${company.name }
									</h3>
									<br /><br />
									<p style="text-align: left; height: 140px;">
										&nbsp;&nbsp;&nbsp;${company.des }
									</p>
									<div class="b-btn">
										<a href="<%=path %>/customer/company_particular?id=${company.id }">查看详情</a>
									</div>
								</div>
								<div class="clearfix"></div>
							</div>	
							<div class="blog-comments">
									<ul>
										<li><span class="glyphicon glyphicon-bookmark" aria-hidden="true"></span>
											<c:choose>
												<c:when test="${company.collected }">
													<span>已收藏</span>
												</c:when>
												<c:otherwise>
													<a href="<%=path %>/page/company_col?id=${company.id }">收藏公司</a>
												</c:otherwise>
											</c:choose>
										</li>
										<li><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span><p>${company.open_date }</p></li>
										<li><span class="glyphicon glyphicon-user" aria-hidden="true"></span><a href="#">${company.principal}</a></li>
									</ul>
									<div class="clearfix"></div>
							</div>	
						</div>
					</c:forEach>
				</div>
				<div class="col-md-3 blog-right">
					<div class="categories">
						<h3>赣州新闻</h3>
						<ul>
							<li><a href="#">赣州建材公司大搜罗</a></li>
							<li><a href="#">论赣州大户型装修的设计理念</a></li>
							<li><a href="#">赣州厨房装修也要融会贯通</a></li>
							<li><a href="#">赣州装修公司谈设计精髓</a></li>
							<li><a href="#">赣州装修公司推荐：宏图 装修公司</a></li>
						</ul>
					</div>
					<div class="categories">
						<h3>热点新闻</h3>
						<ul>
							<li><a href="#">美的首款YunOS冰箱正式发布亮相</a></li>
							<li><a href="#">家电巨头扎堆扫地机器人</a></li>
							<li><a href="#">中国家电业背后不堪内在病根</a></li>
							<li><a href="#">武大新生樱花城堡</a></li>
							<li><a href="#">古朴民国建筑惹网友羡慕 </a></li>
						</ul>
					</div>
					<div class="categories">
						<h3>档案室</h3>
						<ul>
							<li><a href="#">March 2016</a></li>
							<li><a href="#">May 2016</a></li>
							<li><a href="#">August 2016</a></li>
							<li><a href="#">October 2016</a></li>
						</ul>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="pagination">
				<nav>
  					<ul class="pager">
    					<li><a href="<%=path %>/page/company?pageNo=${requestScope.currentPage - 1 }&pageSize=${requestScope.currentPageSize }">上一页</a></li>
    					<li><a href="<%=path %>/page/company?pageNo=${requestScope.currentPage + 1 }&pageSize=${requestScope.currentPageSize }">下一页</a></li>
  						<span>${requestScope.currentPage }</span>/<span>${requestScope.totalPage }</span>
  					</ul>
				</nav>
			</div>
		</div>
	</div>
	<!--blog-->
	
	<jsp:include page="bottom_common.jsp"></jsp:include>
</body>
</html>