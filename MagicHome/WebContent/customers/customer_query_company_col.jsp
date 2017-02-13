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
<title>已收藏的装修公司</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/jh_admin_style.css">
</head>
<body>
	<c:choose>
		<c:when test="${empty(sessionScope.customer) }">
			<h3 style="color: red;">当前账号已失效，请重新登入</h3>
		</c:when>
		<c:when test="${empty(requestScope.companyCols) }">
			<h3 class="h3_red">暂无已收藏的装修公司</h3>
		</c:when>
		<c:otherwise>
			<div>
				<h3>已收藏的装修公司：</h3>
				<c:forEach items="${requestScope.companyCols }" var="companyCol" varStatus="s">
					<div class="company" style="margin-top: 60px;">
						<img src="<%=path %>/${companyCol.company.logo }" style="height: 200px; width: 380px;" />
						<div class="company_right">
							<h3 style="margin-left: 60px;">${companyCol.company.name }</h3>
							<ul>
								<li>
									<span>编号：</span>
									<em>${s.index + 1 }</em>
								</li>
								<li>
									<span>负责人：</span>
									<em>${companyCol.company.principal }</em>
								</li>
								<li>
									<span>手机：</span>
									<em>${companyCol.company.phone }</em>
								</li>
								<li>
									<span>固定电话：</span>
									<em>${companyCol.company.tel }</em>
								</li>
								<li>
									<span>公司成立时间：</span>
									<em>${companyCol.company.open_date }</em>
								</li>
								<li>
									<span>收藏时间：</span>
									<em>${companyCol.created_time }</em>
								</li>
								<li>
									<span>公司描述：</span>
									<span>
										${companyCol.company.des }
									</span>
								</li>
								<li>
									<a href="<%=path %>/customer/delete_company_col?id=${companyCol.id }">取消收藏</a>
								</li>
							</ul>
						</div>
						<p class="clear"></p>
					</div>
				</c:forEach>
				
				
			</div>
			<div style="text-align: right; padding: 60px;">
				<a href="<%=path %>/customer/query_company_col_page?pageSize=${requestScope.currentPageSize }">首页</a>
				<a href="<%=path %>/customer/query_company_col_page?pageNo=${requestScope.currentPage - 1 }&pageSize=${requestScope.currentPageSize }">上一页</a>
				<a href="<%=path %>/customer/query_company_col_page?pageNo=${requestScope.currentPage + 1 }&pageSize=${requestScope.currentPageSize }">下一页</a>
				<a href="<%=path %>/customer/query_company_col_page?pageNo=${requestScope.totalPage }&pageSize=${requestScope.currentPageSize }">尾页</a>
				<span>${requestScope.currentPage }</span>/<span>${requestScope.totalPage }</span>
				<span>每页显示数据</span>
				<input type="text" name="pageSize" style="width: 50px;" value="${requestScope.currentPageSize }" form="skip" />
				<input type="submit" value="跳转" form="skip" />
				<input type="text" name="pageNo" style="width: 30px;" form="skip" />
				<span>页</span>
				<form id="skip" action="<%=path %>/customer/query_company_col_page" method="post"></form>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>