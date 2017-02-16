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
<title>MagicHome</title>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link rel="shortcut icon" href="<%=path %>/images/favicon.png">
<link href="<%=path %>/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="<%=path %>/css/style1.css" rel='stylesheet' type='text/css' />
<link href='<%=path %>/css/fonts.css' rel='stylesheet' type='text/css'>
<link href="<%=path %>/css/jh_style.css" rel="stylesheet" type="text/css" />
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
	<!--设计师开始-->
	<div class="banner" id="home">
		<div class="container">
			<section class="slider">
				<div class="flexslider">
				</div>
			</section>
		</div>
	</div>
	<!--设计师结束--> 
	<!--script开始-->
	<link rel="stylesheet" href="<%=path %>/css/flexslider.css" type="text/css" media="screen" />
	<script defer src="<%=path %>/js/jquery.flexslider.js"></script>
	<script type="text/javascript">
    $(window).load(function(){
      $('.flexslider').flexslider({
        animation: "slide",
        start: function(slider){
          $('body').removeClass('loading');
        }
      });
    });
  </script>
			<!--script结束-->
            <div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >免费网站模板</a></div>
	<!--网站简介和案例开始--> 
	<div class="welcome">
		<div class="container">
			<div class="welcome-top">
				<h1>欢迎来到MagicHome家装平台</h1>
				<p>
					把你家放心交给我们，我们将还你一个Magic的家
					<br />
					我们有最优秀的装修公司，最优秀的设计师团队，更有最先进的建材商品，选择我们就对了
				</p>
			</div>
			<div class="welcome-bottom">
				<div class="col-md-6 welcome-left">
					<h3>设计师案例</h3>
					<p>
						下面是本站设计师团队们的优秀作品，提供大家欣赏
						<a href="<%=path %>/page/designer_case">更多></a>
					</p>
					<div class="welcome-one">
						<div class="col-md-6 welcome-one-left">
							<a href="<%=path %>/page/designer_case_particular?id=${requestScope.designerCase.id }"><img src="<%=path %>/${requestScope.designerCase.image1 }" alt="${requestScope.designerCase.name }" style="height: 400px;" /></a>
						</div>
						<div class="col-md-6 welcome-one-right">
							<a href="<%=path %>/page/designer_case_particular?id=${requestScope.designerCase.id }"><img src="<%=path %>/${requestScope.designerCase.image2 }" alt="${requestScope.designerCase.name }" style="height: 200px;" /></a>
							<a href="<%=path %>/page/designer_case_particular?id=${requestScope.designerCase.id }" class="one-top"><img src="<%=path %>/${requestScope.designerCase.image3 }" alt="${requestScope.designerCase.name }" style="height: 185px; width: 240px;" /></a>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="col-md-6 welcome-left">
					<h3>装修公司案例</h3>
					<p>
						下面提供装修公司案例，仅供参考
						<a href="<%=path %>/page/company_case">更多></a>
					</p>
					<div class="welcome-one">
						<a href="<%=path %>/page/company_case_particular?id=${requestScope.companyCase.id }"><img src="<%=path %>/${requestScope.companyCase.image1 }" alt="" style="height: 400px;"/></a>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--网站简介和案例结束--> 
	<!--设计师开始-->
	<div class="offer">
		<div class="container">
			<div class="offer-top heading">
				<h2>设计师<a href="<%=path %>/page/designer" style="font-size: 14px; margin-left: 900px;">更多></a></h2>
			</div>
			<div class="offer-bottom product_img">
				<c:forEach items="${requestScope.designers }" var="designer">
					<div class="col-md-3 offer-left">
						<a href="<%=path %>/page/designer_particular?id=${designer.id }"><img src="<%=path %>/${designer.head_icon}" alt="${designer.name }" style="border-radius: 50%;"/></a>
						<h4><a href="<%=path %>/page/designer_particular">${designer.name }</a></h4>
						<p>${designer.des }</p>
						<div class="o-btn" style="margin-top: 20px;">
							<c:choose>
								<c:when test="${designer.collected }">
									<span class="col">已收藏</span>
								</c:when>
								<c:otherwise>
									<a href="<%=path %>/page/designer_col_index?id=${designer.id }">收藏设计师</a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</c:forEach>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--设计师结束--> 
	<!--建材商品开始-->
	<div class="offer">
		<div class="container">
			<div class="offer-top heading">
				<h2>建材商品<a href="<%=path %>/page/product" style="font-size: 14px; margin-left: 900px;">更多></a></h2>
			</div>
			<div class="offer-bottom product_img">
				<c:forEach items="${requestScope.products }" var="product">
					<div class="col-md-3 offer-left">
						<a href="#"><img src="<%=path %>/${product.image }" alt="${product.name }" /> </a>
						<h6>￥${product.sale_price }</h6>
						<h5>原价：<del>${product.price }</del></h5>
						<h4><a href="#">${product.name }</a></h4>
						<p>${product.des }</p>
						<div class="o-btn">
							<c:choose>
								<c:when test="${product.collected }">
									<span class="col">已收藏</span>
								</c:when>
								<c:otherwise>
									<a href="<%=path %>/page/product_col_index?id=${product.id }">收藏商品</a>
								</c:otherwise>
							</c:choose>
							
						</div>
					</div>
				</c:forEach>
				
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--建材商品结束--> 
	<!--装修公司开始-->
	<div class="offer">
		<div class="container">
			<div class="offer-top heading">
				<h2>装修公司<a href="<%=path %>/page/company" style="font-size: 14px; margin-left: 900px;">更多></a></h2>
			</div>
			<div class="offer-bottom product_img">
				<c:forEach items="${requestScope.companys }" var="company">
					<div class="col-md-3 offer-left">
						<a href="<%=path %>/customer/company_particular?id=${company.id }"><img src="<%=path %>/${company.logo }" alt="${company.name }" style="height: 100px;"/></a>
						<h4><a href="<%=path %>/customer/company_particular?id=${company.id }">${company.name }</a></h4>
						<p>${company.des }</p>
						<div class="o-btn">
							<c:choose>
								<c:when test="${company.collected }">
									<span class="col">已收藏</span>
								</c:when>
								<c:otherwise>
									<a href="<%=path %>/page/company_col_index?id=${company.id }">收藏公司</a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</c:forEach>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--装修公司结束--> 
	<!--nature-starts--> 
	<div class="nature">
		<div class="container">
		</div>
	</div>
	<!--nature-ends--> 
	
	<!--advantages-starts--> 
	<div class="advantages about">
		<div class="container">
			<div class="col-md-6 advantages-left heading">
				<h2>我们的优势</h2>
				<div class="advn-one">
						<div class="ad-mian">
							<div class="ad-left">
								<p>1</p>
							</div>
							<div class="ad-right">
								<h4><a href="single.html">装修效率提高用户成本下降</a></h4>
								<p>
									Magic是怎么做到“效率提升、成本下降”的?很多人都想知道这个答案。
									以前大家找装修公司可能要挨门走户，然后货比三家。
									另外传统装修公司在做设计图的时候，需要较长的时间周期，
									而Magic通过整合各项资源，高效把控时间，能够极大地提高装修公司的效率。
									在装修公司效率提高和成本下降的情况下，他们非常乐意给Magic平台的业主提供一个优惠的价格，
									换而言之，选择Magic平台的业主在装修方面，成本在下降。
								</p>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="ad-mian">
							<div class="ad-left">
								<p>2</p>
							</div>
							<div class="ad-right">
								<h4><a href="#">提前预约，免受影响</a></h4>
								<p>
									在装修的整个链条上，Magic对时间的管理效率极高，它提出了预约服务，
									用户可以通过填写用户信息预约来预约装修公司。当用户发出预约时，最多5个装修公司
									可以查看用户的预约信息，这样既提升用户的效率，又不会打扰到用户。
								</p>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="ad-mian">
							<div class="ad-left">
								<p>3</p>
							</div>
							<div class="ad-right">
								<h4><a href="#">提供收藏，帮助记忆</a></h4>
								<p>
									我们提供装修公司收藏、设计师收藏、建材商品收藏功能，方便用户收藏自己
									喜欢的公司、设计师和建材商品，无需用户去记忆这些东西	
								</p>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
			</div>
			<div class="col-md-6 advantages-left heading">
				<h3>装修小常识</h3>
			<div class="advn-two">
						<h4>3分钟教你学会装修</h4>
						<p>
							装修也算是一件人生大事，没经验的朋友装修后常常会发现中了好多“设计坑”，
							但那时后悔已晚！本站就让您3分钟学会“避坑”！
						</p>
						<ul>
							<li><a href="#">装修完全手册</a></li>
							<li><a href="#">装修计算器大全</a></li>
							<li><a href="#">装修合同下载</a></li>
							<li><a href="#">装修价格估算</a></li>
							<li><a href="#">装修设计</a></li>
						</ul>
			</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<!--advantages-end--> 
	<jsp:include page="bottom_common.jsp"></jsp:include>
</body>
</html>