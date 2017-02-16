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
<title>已收藏的设计师案例</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
<script type="text/javascript" src="<%=path %>/js/jh_javascript.js"></script>
</head>
<body>
	<c:choose>
		<c:when test="${empty(sessionScope.customer) }">
			<h3 style="ColCaseor: red;">当前账号已失效，请重新登入</h3>
		</c:when>
		<c:when test="${empty(requestScope.designerCaseCols) }">
			<h3 class="h3_red">暂无已收藏的设计师案例</h3>
		</c:when>
		<c:otherwise>
			<div>
				<h3>已收藏的设计师案例：</h3>
				<c:forEach items="${requestScope.designerCaseCols }" var="designerCaseCol" varStatus="s">
					<div class="company_case">
						<div class="company_container">
							<div class="container_1">
								<a href="javascript:;" onclick="checkImg('<%=path %>/${designerCaseCol.designerCase.image1 }')">
									<img src="<%=path %>/${designerCaseCol.designerCase.image1 }" />
								</a>
							</div>
							<div class="container_2">
								<a href="javascript:;" onclick="checkImg('<%=path %>/${designerCaseCol.designerCase.image2 }')">
									<img src="<%=path %>/${designerCaseCol.designerCase.image2 }" />
								</a>
							</div>
							<div class="container_2">
								<a href="javascript:;" onclick="checkImg('<%=path %>/${designerCaseCol.designerCase.image3 }')">
									<img src="<%=path %>/${designerCaseCol.designerCase.image3 }" />
								</a>
							</div>
							<div class="container_2">
								<a href="javascript:;" onclick="checkImg('<%=path %>/${designerCaseCol.designerCase.image4 }')">
									<img src="<%=path %>/${designerCaseCol.designerCase.image4 }" />
								</a>
							</div>
							<div class="container_2">
								<a href="javascript:;" onclick="checkImg('<%=path %>/${designerCaseCol.designerCase.image5 }')">
									<img src="<%=path %>/${designerCaseCol.designerCase.image5 }" />
								</a>
							</div>
						</div>
						<div class="case_des">
							<h3>${designerCaseCol.designerCase.name }</h3>
							<ul>
								<li>
									<span>编号：</span>
									<em>${s.index + 1 }</em>
								</li>
								<li>
									<span>所属设计师：</span>
									<em>${designerCaseCol.designerCase.designer.name }</em>
								</li>
								<li>
									<span>小区名称:</span>
									<em>${designerCaseCol.designerCase.plot_name }</em>
								</li>
								<li>
									<span>装修风格:</span>
									<em>${designerCaseCol.designerCase.style }</em>
								</li>
								<li>
									<span>收藏时间:</span>
									<em>${designerCaseCol.created_time}</em>
								</li>
								<li>
									<span>描述:</span>
									<span>
										${designerCaseCol.designerCase.des }
									</span>
								</li>
								<li>
									<a href="<%=path %>/customer/delete_designer_col_case?id=${designerCaseCol.id }">取消收藏</a>
								</li>
							</ul>
						</div>
						<p class="clear"></p>
					</div>
				</c:forEach>
			</div>
			<div style="text-align: right; padding: 60px;">
				<a href="<%=path %>/customer/query_designer_col_case_page?pageSize=${requestScope.currentPageSize }">首页</a>
				<a href="<%=path %>/customer/query_designer_col_case_page?pageNo=${requestScope.currentPage - 1 }&pageSize=${requestScope.currentPageSize }">上一页</a>
				<a href="<%=path %>/customer/query_designer_col_case_page?pageNo=${requestScope.currentPage + 1 }&pageSize=${requestScope.currentPageSize }">下一页</a>
				<a href="<%=path %>/customer/query_designer_col_case_page?pageNo=${requestScope.totalPage }&pageSize=${requestScope.currentPageSize }">尾页</a>
				<span>${requestScope.currentPage }</span>/<span>${requestScope.totalPage }</span>
				<span>每页显示数据</span>
				<input type="text" name="pageSize" style="width: 50px;" value="${requestScope.currentPageSize }" form="skip" />
				<input type="submit" value="跳转" form="skip" />
				<input type="text" name="pageNo" style="width: 30px;" form="skip" />
				<span>页</span>
				<form id="skip" action="<%=path %>/customer/query_designer_col_case_page" method="post"></form>
			</div>
		</c:otherwise>
	</c:choose>
	<div id="check_img" class="check_img" onclick="cancel()"></div>
</body>
</html>