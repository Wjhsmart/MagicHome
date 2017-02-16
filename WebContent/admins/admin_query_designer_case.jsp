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
<title>查看设计师案例</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
<script type="text/javascript" src="<%=path %>/js/jh_javascript.js"></script>
</head>
<body>
	<c:choose>
		<c:when test="${empty(sessionScope.admin) }">
			<h3 style="color: red;">当前账号已失效，请重新登入</h3>
		</c:when>
		<c:when test="${empty(requestScope.designerCases) }">
			<h3 class="h3_red">暂无案例信息</h3>
		</c:when>
		<c:otherwise>
			<div>
				<h3>根据条件查找：</h3>
				<form action="<%=path %>/admin/condition_query_designer_case" method="post">
					<em>设计师名称：</em>
					<input type="text" name="designerName" placeholder="请输入设计师名称查找" />
					<em>案例名称：</em>
					<input type="text" name="caseName" placeholder="请输入案例名称查找" />
					<input type="submit" value="查找" />
				</form>
				<br />
				<table class="table">
					<tr class="title">
						<td>编号</td>
						<td>设计师名称</td>
						<td>案例名称</td>
						<td>小区名称</td>
						<td>装修风格</td>
						<td>装修效果图1</td>
						<td>装修效果图2</td>
						<td>装修效果图3</td>
						<td>装修效果图4</td>
						<td>装修效果图5</td>
						<td>案例描述</td>
						<td>创建时间</td>
					</tr>
					<c:forEach items="${requestScope.designerCases }" var="designerCase" varStatus="s">
						<tr>
							<td>${s.index + 1 }</td>
							<td>${designerCase.designer.name }</td>
							<td>${designerCase.name }</td>
							<td>${designerCase.plot_name }</td>
							<td>${designerCase.style }</td>
							<td class="case_img">
								<a href="javascript:;" onclick="checkImg('<%=path %>/${designerCase.image1 }')"><img src="<%=path %>/${designerCase.image1 }" /></a>
							</td>
							<td class="case_img">
								<a href="javascript:;" onclick="checkImg('<%=path %>/${designerCase.image2 }')"><img src="<%=path %>/${designerCase.image2 }" /></a>
							</td>
							<td class="case_img">
								<a href="javascript:;" onclick="checkImg('<%=path %>/${designerCase.image3 }')"><img src="<%=path %>/${designerCase.image3 }" /></a>
							</td>
							<td class="case_img">
								<a href="javascript:;" onclick="checkImg('<%=path %>/${designerCase.image4 }')"><img src="<%=path %>/${designerCase.image4 }" /></a>
							</td>
							<td class="case_img">
								<a href="javascript:;" onclick="checkImg('<%=path %>/${designerCase.image5 }')"><img src="<%=path %>/${designerCase.image5 }" /></a>
							</td>
							<td>${designerCase.des }</td>
							<td>${designerCase.created_time }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div style="text-align: right; padding: 60px;">
				<a href="<%=path %>/admin/query_designer_case_page?pageSize=${requestScope.currentPageSize }">首页</a>
				<a href="<%=path %>/admin/query_designer_case_page?pageNo=${requestScope.currentPage - 1 }&pageSize=${requestScope.currentPageSize }">上一页</a>
				<a href="<%=path %>/admin/query_designer_case_page?pageNo=${requestScope.currentPage + 1 }&pageSize=${requestScope.currentPageSize }">下一页</a>
				<a href="<%=path %>/admin/query_designer_case_page?pageNo=${requestScope.totalPage }&pageSize=${requestScope.currentPageSize }">尾页</a>
				<span>${requestScope.currentPage }</span>/<span>${requestScope.totalPage }</span>
				<span>每页显示数据</span>
				<input type="text" name="pageSize" style="width: 50px;" value="${requestScope.currentPageSize }" form="skip" />
				<input type="submit" value="跳转" form="skip" />
				<input type="text" name="pageNo" style="width: 30px;" form="skip" />
				<span>页</span>
				<form id="skip" action="<%=path %>/admin/query_designer_case_page" method="post"></form>
			</div>
		</c:otherwise>
	</c:choose>
	<div id="check_img" class="check_img" onclick="cancel()"></div>
</body>
</html>