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
<title>设计师</title>
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
<script type="text/javascript">
	function designerParticular(obj) {
		window.location.href=obj;
	}
</script>
</head>
<body>
	<!----start-header---->
	<div class="header" id="home">
		<jsp:include page="head_common.jsp"></jsp:include>
	</div>	
	<!----end-header---->
	<!--team-starts--> 
	<div class="team">
		<div class="container">
			<div class="team-top heading">
				<h3>设计师</h3>
			</div>
			<div class="team-bottom">
				<ul class="ch-grid">
					<c:forEach items="${requestScope.designers }" var="designer">
						<li>
							<div class="ch-item" style="background-image: url('<%=path %>/${designer.head_icon }'); cursor: pointer;" onclick="designerParticular('<%=path %>/page/designer_particular?id=${designer.id }')">				
								<div class="ch-info-wrap">
									<div class="ch-info">
										<div class="ch-info-front" style="background-image: url('<%=path %>/${designer.head_icon }');"></div>
										<div class="ch-info-back">
											<h3>${designer.name }</h3>
											<p>${designer.des }</p>
										</div>	
									</div>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="pagination" style="margin-left: 600px;">
			<nav>
  				<ul class="pager">
    				<li><a href="<%=path %>/page/designer?pageNo=${requestScope.currentPage - 1 }&pageSize=${requestScope.currentPageSize }">上一页</a></li>
    				<li><a href="<%=path %>/page/designer?pageNo=${requestScope.currentPage + 1 }&pageSize=${requestScope.currentPageSize }">下一页</a></li>
  					<span>${requestScope.currentPage }</span>/<span>${requestScope.totalPage }</span>
  				</ul>
			</nav>
		</div>
	</div>
	<!--team-end--> 
	
	<jsp:include page="bottom_common.jsp"></jsp:include>
</body>
</html>