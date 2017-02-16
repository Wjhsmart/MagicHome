<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>500</title>
<style type="text/css">
	.return_index {
		width: 248px;
		height: 60px;
		border-radius: 5%;
		position: absolute;
		top: 354px;
		right: 250px;
		z-index: 100;
		cursor: pointer;
	}
</style>
<script type="text/javascript">
	function returnIndex() {
		window.location.href="<%=path %>/page/index";
	}
</script>
</head>
<body>
<img style="margin:auto;" src="<%=path %>/images/500.gif" />
<div class="return_index" onclick="returnIndex()"></div>
</body>
</html>