<%@ page language="java" contentType="text/jsp; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%
		String path = request.getContextPath();
	%>
<!DOCTYPE jsp>
<html>
<head>
<meta http-equiv="Content-Type" content="text/jsp; charset=UTF-8">
<title>头部共同的部分</title>
<link href='<%=path %>/css/common.css' rel='stylesheet' type='text/css'>
<link href="<%=path %>/css/style1.css" rel='stylesheet' type='text/css' />
</head>
<body>
	<div class="container">
		<div class="logo">
			<a href="<%=path %>/page/index"><img src="<%=path%>/images/logo.png"
				alt="返回首页" style="margin-left: -10px;"></a>
		</div>
		<div class="navigation">
			<span class="menu"></span>
			<ul class="navig">
				<li>
					<span style="color: white; font-weight: bold;">你好，</span>
					<c:choose>
						<c:when test="${!empty(sessionScope.customer) }">
							<span style="color: white; font-weight: bold;" >${sessionScope.customer.name }</span>
							<a href="<%=path %>/customer/customer_index_page" class="active" >个人中心</a>
							<a href="<%=path %>/customer/exit">退出</a>
						</c:when>
						<c:when test="${!empty(sessionScope.supply) }">
							<span style="color: white; font-weight: bold;" >${sessionScope.supply.name }</span>
							<a href="<%=path %>/supply/return_index_page" class="active" >后台管理</a>
							<a href="<%=path %>/supply/exit">退出</a>
						</c:when>
						<c:when test="${!empty(sessionScope.company) }">
							<span style="color: white; font-weight: bold;" >${sessionScope.company.name }</span>
							<a href="<%=path %>/company/return_index_page" class="active" >后台管理</a>
							<a href="<%=path %>/company/exit">退出</a>
						</c:when>
						<c:when test="${!empty(sessionScope.designer) }">
							<span style="color: white; font-weight: bold;" >${sessionScope.designer.name }</span>
							<a href="<%=path %>/designer/return_index_page" class="active" >后台管理</a>
							<a href="<%=path %>/designer/exit">退出</a>
						</c:when>
						<c:otherwise>
							<a href="<%=path %>/customer/login_page">登录</a>
							<a href="<%=path%>/page/register_page">免费注册</a>
						</c:otherwise>
					</c:choose>
				</li>
				<li><a href="<%=path%>/page/index">首页</a><span> </span></li>
				<li><a href="<%=path%>/page/company_case">装修案例</a><span> </span></li>
				<li><a href="<%=path%>/page/designer_case">设计案例</a><span> </span></li>
				<li><a href="<%=path%>/page/company">装修公司</a><span> </span></li>
				<li><a href="<%=path%>/page/designer">设计师</a><span> </span></li>
				<li><a href="<%=path%>/page/product">建材商城</a><span> </span></li>
			</ul>
		</div>
		<!-- script-for-menu -->
		<script>
			$("span.menu").click(function() {
				$(" ul.navig").slideToggle("slow", function() {
				});
			});
		</script>
		<!-- script-for-menu -->
	</div>
</body>
</html>