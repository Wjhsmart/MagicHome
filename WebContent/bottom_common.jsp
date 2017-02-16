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
<title>共同的底部</title>
<link href='<%=path %>/css/common.css' rel='stylesheet' type='text/css'>
</head>
<body>
	<!--footer-starts--> 
	<div class="footer">
		<div class="container">
			<div class="footer-top">
				<p>
					<a href="#">关于我们 </a>&nbsp;|&nbsp; 
					<a href="#">联系我们</a>&nbsp;|&nbsp;
					<a href="#">友情链接</a>&nbsp;|&nbsp;
					<a href="#">帮助中心</a>&nbsp;|&nbsp;
					<a href="#">意见反馈</a>&nbsp;|&nbsp;
					<a href="#">高薪聘请</a>&nbsp;|&nbsp;
					<a href="#">法律声明</a>
				</p>
				<p id="p_text">免责声明：本网站部分内容由用户自行上传，如权利人发现存在误传其作品情形，请及时与本站联系。</p>
				<p id="p_text">© 2016 magic.com MagicHame装修网和设计师社区 保留所有权利</p>
				<p id="p_text">中国装修网 赣ICP备08125550号</p>
				<script type="text/javascript">
									$(document).ready(function() {
										/*
										var defaults = {
								  			containerID: 'toTop', // fading element id
											containerHoverID: 'toTopHover', // fading element hover id
											scrollSpeed: 1200,
											easingType: 'linear' 
								 		};
										*/
										
										$().UItoTop({ easingType: 'easeOutQuart' });
										
									});
								</script>
		<a href="#home" id="toTop" class="scroll" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span><
			</div>
		</div>
	</div>
	<!--footer-ends--> 
</body>
</html>