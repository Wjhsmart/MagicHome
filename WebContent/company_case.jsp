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
<title>装修公司案例</title>
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
	<!--about-starts--> 
	<div class="about">
		<div class="container">
			<div class="about-top heading">
				<h1 style="margin-top: 10px;">装修公司案例</h1>
				<h4>下面是所有装修公司的效果图</h4>
				<p>
					这里集结了全赣州最好的装修公司，请放下把您的房子交给我们，下面案例仅供参考
				</p>
				<div class="about-bottom company_case">
					<c:forEach items="${requestScope.companyCases }" var="companyCase">
						<div class="col-md-4 about-left">
							<a href="<%=path %>/page/company_case_particular?id=${companyCase.id }"><img src="<%=path %>/${companyCase.image1 }" alt="${companyCase.name }" /></a>
							<h5><a href="<%=path %>/page/show_company_case_particular">${companyCase.name }</a></h5>
							<p>${companyCase.des }</p>
							<div class="o-btn" style="text-align: center;">
								<c:choose>
									<c:when test="${companyCase.collected }">
										<span class="col" style="margin: 0px 120px;">已收藏</span>
									</c:when>
									<c:otherwise>
										<a href="<%=path %>/page/company_case_col?id=${companyCase.id }" class="col">收藏案例</a>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</c:forEach>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<!-- 分页开始 -->
		<div style="text-align: right; padding: 60px;">
			<a href="<%=path %>/page/company_case?pageSize=${requestScope.currentPageSize }" style="color: #000;">首页</a>
			<a href="<%=path %>/page/company_case?pageNo=${requestScope.currentPage - 1 }&pageSize=${requestScope.currentPageSize }" style="color: #000;">上一页</a>
			<a href="<%=path %>/page/company_case?pageNo=${requestScope.currentPage + 1 }&pageSize=${requestScope.currentPageSize }" style="color: #000;">下一页</a>
			<a href="<%=path %>/page/company_case?pageNo=${requestScope.totalPage }&pageSize=${requestScope.currentPageSize }" style="color: #000;">尾页</a>
			<span>${requestScope.currentPage }</span>/<span>${requestScope.totalPage }</span>
			<span>每页显示数据</span>
			<input type="text" name="pageSize" style="width: 50px;" value="${requestScope.currentPageSize }" form="skip" />
			<input type="submit" value="跳转" form="skip" />
			<input type="text" name="pageNo" style="width: 30px;" form="skip" />
			<span>页</span>
			<form id="skip" action="<%=path %>/page/company_case" method="post"></form>
		</div>
		<!-- 分页结束 -->
	</div>
	<!----about-end---->
	
	<jsp:include page="bottom_common.jsp"></jsp:include>
</body>
</html>