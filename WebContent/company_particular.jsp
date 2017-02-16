<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<% 
		String path = request.getContextPath(); 
	%>
<!DOCTYPE html>
<html>
<head>
<title>装修公司详情</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Majestic Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link rel="shortcut icon" href="<%=path %>/images/favicon.png">
<link href="<%=path %>/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="<%=path %>/css/style1.css" rel='stylesheet' type='text/css' />
<link href="<%=path %>/css/common.css" rel="stylesheet" type="text/css" />
<link href='http://fonts.useso.com/css?family=Titillium+Web:400,200,200italic,300,300italic,400italic,600,600italic,700,700italic,900' rel='stylesheet' type='text/css'>
<script src="js/jquery-1.11.0.min.js"></script>
<!---- start-smoth-scrolling---->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
				});
			});
			function tishi(obj) {
				alert(obj);
			}
			function checkComment() {
				var comment = document.getElementById("comment_area").value;
				var comment_span = document.getElementById("comment_span");
				if (comment != '') {
					if (comment.length <  5) {
						comment_span.innerHTML = "字数不能小于5个";
						return false;
					} else {
						return true;
					}
				} else {
					comment_span.innerHTML = "内容不能为空";
					return false;
				}
				return false;
			}
		</script>
<!---- start-smoth-scrolling---->
<style>
	.input_span span {
		font-weight: bold;
	}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=5tDF3rtkaq2L8orMMWscWuDkltPPWjPX"></script>
</head>
<body>
	<c:if test="${!empty(requestScope.errorMes) }">
		<script>
			tishi("${requestScope.errorMes }");
		</script>		
	</c:if>
	<!----start-header---->
	<div class="header" id="home">
		<jsp:include page="head_common.jsp"></jsp:include>
	</div>	
	<!----end-header---->
	<!--blog-->
	<div class="blog">
		<div class="container">
			<div class="blog-head heading">
			
			</div>	
			<div class="blog-top">
				<div class="col-md-9 blog-left">
					<div class="blog-main">
						<a href="#" class="bg">${requestScope.company.name }</a>
						<p>预约热线：${requestScope.company.tel },已有191016人咨询</p>
					</div>
					<div class="blog-main-one">
						<div class="blog-one">
							<img src="<%=path %>/${requestScope.company.logo }" alt="${requestScope.company.name }" />
							<p>${reqeustScope.company.des }</p>
						</div>	
						<div class="blog-comments">
							<ul>
								<li><span class="glyphicon glyphicon-bookmark" aria-hidden="true">
									<c:choose>
										<c:when test="${requestScope.company.collected }">
											<span>已收藏</span>
										</c:when>
										<c:otherwise>
											<a href="<%=path %>/customer/company_col_particular?id=${requestScope.company.id }">收藏公司</a>
										</c:otherwise>
									</c:choose>
								</li>
								<li><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span><p>${requestScope.company.open_date }</p></li>
								<li><span class="glyphicon glyphicon-user" aria-hidden="true"></span><a href="#">${requestScope.company.principal }</a></li>
							</ul>
							<div class="clearfix"></div>
						</div>	
					</div>
					
					<div class="related heading">
							<h3>装修案例<a href="<%=path %>/page/company_particular_case?id=${requestScope.company.id }" style="font-size: 14px; margin-left: 600px;">更多></a></h3>
							<div class="related-bottom">
								<c:choose>
									<c:when test="${empty(requestScope.companyCases) }">
										<h4 style="color: red;">该公司暂时没有上传案例</h4>
									</c:when>
									<c:otherwise>
										<c:forEach items="${requestScope.companyCases }" var="companyCase">
											<div class="col-md-3 related-left">
												<a href="<%=path %>/page/company_case_particular?id=${companyCase.id }"><img src="<%=path %>/${companyCase.image1 }" alt="${companyCase.name }" style="height: 150px;" /></a>
												<h4><a href="<%=path %>/page/company_case_particular?id=${companyCase.id }">${companyCase.name }</a></h4>
											</div>
										</c:forEach>
									</c:otherwise>
								</c:choose>
								<div class="clearfix"></div>
							</div>
						</div>
					
						<div class="comments">
							<div class="comments-top heading">
								<h3>公司位置</h3>
							</div>
							<br />
							<div id="allmap" style="width: 800px; height: 500px;">
								
							</div>
						</div>
					
						<div class="comments">
							<div class="comments-top heading">
								<h3>热门评论</h3>
							</div>
							<div class="comments-bottom">
								<c:forEach items="${requestScope.comments }" var="comment">
									<div class="media">
										<div class="media-left">
											<a href="#">
											<img class="media-object" src="<%=path %>/images/co.png" alt="">
											</a>
										</div>
										<div class="media-body">
											<h4 class="media-heading" style="margin-top: 20px;"><a href="#">${comment.customer.name }</a></h4>
											<p>${comment.content }</p>
										</div>
									</div>
								</c:forEach>
									
							</div>
						</div>
					
						<!--代码部分begin-->
						<style>
						*{margin:0;padding:0;list-style-type:none;}
						a,img{border:0;}
						body{font:12px/180% Arial, Helvetica, sans-serif;}
						/*quiz style*/
						.quiz{border:solid 1px #ccc;height:270px;width:800px; margin:0 auto;}
						.quiz h3{font-size:14px;line-height:35px;height:35px;border-bottom:solid 1px #e8e8e8;padding-left:20px;background:#f8f8f8;color:#666;position:relative;}
						.quiz_content{padding-top:10px;padding-left:20px;position:relative;height:205px;}
						.quiz_content .btm{border:none;width:100px;height:33px;background:url(../images/image/bt.png) no-repeat;margin:10px 0 0 64px;display:inline;cursor:pointer;}
						.quiz_content li.full-comment{position:relative;z-index:99;height:41px;}
						.quiz_content li.cate_l{height:24px;line-height:24px;padding-bottom:10px;}
						.quiz_content li.cate_l dl dt{float:left;}
						.quiz_content li.cate_l dl dd{float:left;padding-right:15px;}
						.quiz_content li.cate_l dl dd label{cursor:pointer;}
						.quiz_content .l_text{height:120px;position:relative;padding-left:18px;}
						.quiz_content .l_text .m_flo{float:left;width:80px;}
						.quiz_content .l_text .text{width:634px;height:109px;border:solid 1px #ccc;}
						.quiz_content .l_text .tr{position:absolute;bottom:-18px;right:40px;}
						/*goods-comm-stars style*/
						.goods-comm{height:41px;position:relative;z-index:7;}
						.goods-comm-stars{line-height:25px;padding-left:12px;height:41px;position:absolute;top:0px;left:0;width:400px;}
						.goods-comm-stars .star_l{float:left;display:inline-block;margin-right:5px;display:inline;}
						.goods-comm-stars .star_choose{float:left;display:inline-block;}
						/* rater star */
						.rater-star{position:relative;list-style:none;margin:0;padding:0;background-repeat:repeat-x;background-position:left top;float:left;}
						.rater-star-item, .rater-star-item-current, .rater-star-item-hover{position:absolute;top:0;left:0;background-repeat:repeat-x;}
						.rater-star-item{background-position: -100% -100%;}
						.rater-star-item-hover{background-position:0 -48px;cursor:pointer;}
						.rater-star-item-current{background-position:0 -48px;cursor:pointer;}
						.rater-star-item-current.rater-star-happy{background-position:0 -25px;}
						.rater-star-item-hover.rater-star-happy{background-position:0 -25px;}
						.rater-star-item-current.rater-star-full{background-position:0 -72px;}
						/* popinfo */
						.popinfo{display:none;position:absolute;top:30px;background:url(../images/image/infobox-bg.gif) no-repeat;padding-top:8px;width:192px;margin-left:-14px;}
						.popinfo .info-box{border:1px solid #f00;border-top:0;padding:0 5px;color:#F60;background:#FFF;}
						.popinfo .info-box div{color:#333;}
						.rater-click-tips{font:12px/25px;color:#333;margin-left:10px;background:url(../images/image/infobox-bg-l.gif) no-repeat 0 0;width:125px;height:34px;padding-left:16px;overflow:hidden;}
						.rater-click-tips span{display:block;background:#FFF9DD url(../images/image/infobox-bg-l-r.gif) no-repeat 100% 0;height:34px;line-height:34px;padding-right:5px;}
						.rater-star-item-tips{background:url(../images/image/star-tips.gif) no-repeat 0 0;height:41px;overflow:hidden;}
						.cur.rater-star-item-tips{display:block;}	
						.rater-star-result{color:#FF6600;font-weight:bold;padding-left:10px;float:left;}
						</style>
						<div class="quiz">
							<h3>我要评论</h3>
							<div class="quiz_content">
								<form onsubmit="return checkComment()" action="<%=path %>/customer/comment?company_id=${requestScope.company.id }" method="post">
									<div class="goods-comm">
										<div class="goods-comm-stars">
											<span class="star_l">满意度：</span>
											<div id="rate-comm-1" class="rate-comm"></div>
										</div>
									</div>
					
									<div class="l_text">
										<label class="m_flo">内  容：</label>
										<textarea name="content" id="comment_area" class="text"></textarea>
										<br />
										<span style="margin-left: 80px; color: red;" id="comment_span"></span>
										<span class="tr">字数限制为5-200个</span>
									</div>
									<button class="btm" type="submit" style="margin-left: 630px; margin-top: 20px;"></button>
								</form>
							</div><!--quiz_content end-->
						</div><!--quiz end-->
						<script src="<%=path %>/js/jquery-1.js"></script>
						<script src="<%=path %>/js/comment.js"></script>
						<!--代码部分end-->
					
						<div class="reply heading">
					 		<h3>我要预约</h3>
					 		<div class="contact-form">
								<form class="input_span" action="<%=path %>/page/apply_appointment?id=${requestScope.company.id }" method="post">
									<input type="text" name="name" placeholder="您的称呼" required/>
									<input type="text" name="plot_name" placeholder="小区名称" required/>
									<input type="text" name="phone" placeholder="填写手机号码，方便我们与您联系" required/>
									<input type="text" name="area" placeholder="请输入您需要装修的面积(㎡)" required/>
									<br />
									<span>装修预算：</span>
									<input type="radio" name="budget" value="1-3w" checked="checked">1-3万
									<input style="margin-left: 80px;" type="radio" name="budget" value="4-8w">4-8万
									<input style="margin-left: 80px;" type="radio" name="budget" value="8w">8万以上
									<br />
									<span>装修方式：</span>
									<input type="radio" name="way" value="whole" checked="checked">全包
									<input style="margin-left: 80px;" type="radio" name="way" value="half">半包
									<br /> 
									<input type="submit" value="马上预约"/>
				   				</form>
				   			</div>	
						</div>
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
						<h3>活动</h3>
						<ul>	
							<c:forEach items="${requestScope.companyActivitys }" var="companyActivity">
								<li><a href="#">${companyActivity.name }</a></li>
								<li>&nbsp;--- ${companyActivity.des }</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--blog-->
	<!--footer-starts--> 
			<jsp:include page="bottom_common.jsp"></jsp:include>
	<!--footer-ends--> 
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");    // 创建Map实例
	map.centerAndZoom(new BMap.Point(114.79, 25.79), 11);  // 初始化地图,设置中心点坐标和地图级别
	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	map.setCurrentCity("赣州");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
</script>
