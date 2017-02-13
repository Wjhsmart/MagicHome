<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <% String  path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/cj_style.css"/>
<link rel="shortcut icon" href="<%=path %>/images/favicon.png">
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<style type="text/css"> 
/*密码强度*/
.pw-strength {clear: both;position: relative;top: 8px; left: -310px;; width: 180px;}
.pw-bar{background: url("../images/pwd-1.png") no-repeat;height: 14px;overflow: hidden;width: 179px;}
.pw-bar-on{background:  url("../images/pwd-2.png") no-repeat; width:0px; height:14px;position: absolute;top: 1px;left: 2px;transition: width .5s ease-in;-moz-transition: width .5s ease-in;-webkit-transition: width .5s ease-in;-o-transition: width .5s ease-in;}
.pw-weak .pw-defule{ width:0px;}
.pw-weak .pw-bar-on {width: 60px;}
.pw-medium .pw-bar-on {width: 120px;}
.pw-strong .pw-bar-on {width: 179px;}
.pw-txt {padding-top: 2px;width: 180px;overflow: hidden;}
.pw-txt span {color: #707070;float: left;font-size: 12px;text-align: center;width: 58px;}</style>
<script>
	function showDiv(show, hide, hide1, obj, obj1, obj2) {
		var showDiv = document.getElementById(show);
		var hideDiv = document.getElementById(hide);
		var hideDiv1 = document.getElementById(hide1);
		var obj = document.getElementById(obj);
		var obj1 = document.getElementById(obj1);
		var obj2 = document.getElementById(obj2);
		showDiv.style = "display: block;";
		hideDiv.style = "display: none;";
		hideDiv1.style = "display: none;";
		obj.style = "border-bottom:2px solid #00b161; color:#00b161;";
		obj1.style = "border-bottom:none; color:black;";
		obj2.style = "border-bottom:none; color:black;";
	}
	function showUser(user, enterprise, designer, name, qy, project) {
		showDiv(user, enterprise, designer, name, qy, project);
	}
	var agree = "n";
	function customerCheckForm() {
		var customer_email = document.getElementById("customer_email").value;
		var customer_pwd = document.getElementById("pass1").value;
		var customer_phone = document.getElementById("customer_phone").value;
		var customer_name = document.getElementById("customer_name").value;
		var error = document.getElementById("error");
		if (customer_email != '' && customer_pwd != '' && customer_phone != '' && customer_name != '' && agree == 'y') {
			return true;
		}
		error.innerHTML = "所有都为必填";
		return false;
	}
	function consent(obj) {
		agree = obj.value;
		var sub = document.getElementById("sub");
		if (agree == "n") {
			agree = "y";
			sub.style = "width: 300px; height: 30px; color: white; background-color: red;"
		} else if (agree == "y") {
			agree = "n";
			sub.style = "width: 300px; height: 30px; color: white; background-color: silver;"
		}
		obj.value = agree;
	}
	
</script>
<script type="text/javascript"> 
function checkPwd(index) { 
	var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g"); 
	var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g"); 
	var enoughRegex = new RegExp("(?=.{6,}).*", "g"); 

	if (false == enoughRegex.test($('#pass' + index).val())) { 
		$('#level' + index).removeClass('pw-weak'); 
		$('#level' + index ).removeClass('pw-medium'); 
		$('#level' + index).removeClass('pw-strong'); 
		$('#level' + index).addClass(' pw-defule'); 
		 //密码小于六位的时候，密码强度图片都为灰色 
	} 
	else if (strongRegex.test($('#pass' + index).val())) { 
		$('#level' + index).removeClass('pw-weak'); 
		$('#level' + index).removeClass('pw-medium'); 
		$('#level' + index).removeClass('pw-strong'); 
		$('#level' + index).addClass(' pw-strong'); 
		 //密码为八位及以上并且字母数字特殊字符三项都包括,强度最强 
	} 
	else if (mediumRegex.test($('#pass' + index).val())) { 
		$('#level' + index).removeClass('pw-weak'); 
		$('#level' + index).removeClass('pw-medium'); 
		$('#level' + index).removeClass('pw-strong'); 
		$('#level' + index).addClass(' pw-medium'); 
		 //密码为七位及以上并且字母、数字、特殊字符三项中有两项，强度是中等 
	} 
	else { 
		$('#level' + index).removeClass('pw-weak'); 
		$('#level' + index).removeClass('pw-medium'); 
		$('#level' + index).removeClass('pw-strong'); 
		$('#level' + index).addClass('pw-weak'); 
		 //如果密码为6为及以下，就算字母、数字、特殊字符三项都包括，强度也是弱的 
	} 
	return true; 
}
$(function(){ 
	$('#pass1').keyup(function() {
		checkPwd(1);
	}); 
	$('#pass2').keyup(function() {
		checkPwd(2);
	}); 
	$('#pass3').keyup(function() {
		checkPwd(3);
	}); 
}) 

	function setAColor(obj, obj1, obj2) {
		var user = document.getElementById("name");
		var enterprise = document.getElementById("qy");
		var designer = document.getElementById("des");
		user.style = obj
		enterprise.style = obj1;
		designer.style = obj2;
	}

</script>
</head>
<body>
	<div class="head_div">
		<div class="top_cj">
			<div class="logo_container">
				<div class="logo"><a href="<%=path %>/page/index"><img src="<%=path %>/images/logo3.png"/></a></div>
				<div class="hello_left"><h2>欢迎注册</h2></div>
				<p class="clear"></p>
			</div>
		</div>
		<div class="bj_img"><img src="<%=path %>/images/111.jpg" class="img_radius"/>
			<p class="clear"></p>
		</div>
		<h4 class="have">
			<span>已有账号?</span><a href="<%=path %>/customer/login_page">立即登录</a>
		</h4>
		<div class="form">
			<div class="nav_text">
				<a href="javascript:;" onclick="showUser('user', 'enterprise', 'designer', 'name', 'qy', 'des')" id="name">个人用户</a>
				<a href="javascript:;" onclick="showUser('enterprise', 'user', 'designer', 'qy', 'name', 'des')" id="qy">企业用户</a>
				<a href="javascript:;" onclick="showUser('designer', 'user', 'enterprise', 'des', 'qy', 'name')" id="des">设计师</a>
			</div>
			<c:choose>
				<c:when test="${requestScope.user == 'block'}">
					<script>
						setAColor('border-bottom:2px solid #00b161; color:#00b161;', 'border-bottom: none; color: #000000;', 'border-bottom: none; color: #000000;');
					</script>
				</c:when>
				<c:when test="${requestScope.enterprise == 'block'}">
					<script>
						setAColor('border-bottom: none; color: #000000;', 'border-bottom:2px solid #00b161; color:#00b161;', 'border-bottom: none; color: #000000;');
					</script>
				</c:when>
				<c:when test="${requestScope.designer == 'block'}">
					<script>
						setAColor('border-bottom: none; color: #000000;', 'border-bottom: none; color: #000000;', 'border-bottom:2px solid #00b161; color:#00b161;');
					</script>
				</c:when>
					
			</c:choose>
			<div id="user" class="input" style="display:${requestScope.user };">
				<form onsubmit="return customerCheckForm()" action="<%=path %>/customer/register" method="post">
					<div class="input_type">
						<span id="error" style="color:red">${requestScope.errorMes }</span>
						<input type="email" placeholder="邮箱" name="email" id="customer_email"/>
					</div>
					<div class="input_type">
						<table style="width: 320px; margin: 20px auto;">
							<tr>
								<td>
									<span class="tbl-txt">
										<input id="pass1" class="input-style" size="30" maxlength="30" name="pwd" type="password" placeholder="请输入6位以上的密码" />
									</span>
								</td>
							</tr>
							<tr>
								<th></th>
								<td id="level1" class="pw-strength">
									<div class="pw-bar"></div>
									<div class="pw-bar-on"></div>
									<div class="pw-txt">
										<span>弱</span> <span>中</span> <span>强</span>
									</div>
								</td>
							</tr>
						</table>
					</div>
					<div class="input_type"><input type="text" placeholder="请输入手机号，手机号码为11位数字" name="phone" id="customer_phone"/></div>
					<div class="input_type"><input type="text" placeholder="用户昵称" name="name" id="customer_name"/></div>
					<div class="input_checkbox"><input type="checkbox" onclick="consent(this)" id="customer_agree" value="n"/><label>我已阅读并接受<a href="#">《Magic用户协议》</a></label></div>
					<div class="input_submit"><input id="sub" type="submit" style="background: silver;" value="立即注册"/></div>
				</form>
			</div>
			<div id="enterprise" class="input" style="display:${requestScope.enterprise };" >
				<form action="<%=path %>/supply/register" method="post" >
					<div class="radio">
						<label><input type="radio" ${requestScope.company } name="radio" value="1"/>装修公司</label> 
						<label><input type="radio" ${requestScope.supply } name="radio" value="2"/>建材商</label>
					</div>
					<div class="input_type">
						<span style="font-size: 13px; color: red;">${requestScope.errorMes }</span>
						<span style="font-size: 13px; color: green;">${requestScope.succeed }</span>
						<input type="text" name="name" placeholder="名称"/>
					</div>
					<div class="input_type"><input type="text" name="principal" placeholder="负责人"/></div>
					<div class="input_type"><input type="email" name="email" placeholder="邮箱"/></div>
					<div class="input_type">
						<table style="width: 320px; margin: 20px auto;">
							<tr>
								<td>
									<span class="tbl-txt">
										<input id="pass2" class="input-style" size="30" maxlength="30" name="pwd" type="password" placeholder="请输入6位以上的密码" />
									</span>
								</td>
							</tr>
							<tr>
								<th></th>
								<td id="level2" class="pw-strength">
									<div class="pw-bar"></div>
									<div class="pw-bar-on"></div>
									<div class="pw-txt">
										<span>弱</span> <span>中</span> <span>强</span>
									</div>
								</td>
							</tr>
						</table>
					</div>
					<div class="input_type"><input type="text" name="phone" placeholder="请输入手机号，手机号码为11位数字"/></div>
					<div class="input_submit"><input type="submit" value="申请入驻"/></div>
				</form>
			</div>
			<div id="designer" class="input" style="display:${requestScope.designer }">
				<form action="<%=path %>/designer/register" method="post" >
					<span style="font-size: 13px; color: red; margin-left: 40px;">${requestScope.designerErrorMes }</span>
					<span style="font-size: 13px; color: green; margin-left: 40px;">${requestScope.designerSucceed }</span>
					<div class="input_type">
						<span style="font-size: 10px; color: red;">${requestScope.errorMes }</span>
						<input type="text" name="name" placeholder="名称"/>
					</div>
					<div class="input_type"><input type="email" name="email" placeholder="邮箱"/></div>
					<div class="input_type">
						<table style="width: 320px; margin: 20px auto;">
							<tr>
								<td>
									<span class="tbl-txt">
										<input id="pass3" class="input-style" size="30" maxlength="30" name="pwd" type="password" placeholder="请输入6位以上的密码" />
									</span>
								</td>
							</tr>
							<tr>
								<th></th>
								<td id="level3" class="pw-strength">
									<div class="pw-bar"></div>
									<div class="pw-bar-on"></div>
									<div class="pw-txt">
										<span>弱</span> <span>中</span> <span>强</span>
									</div>
								</td>
							</tr>
						</table>
					</div>
					<div class="input_type"><input type="text" name="phone" placeholder="请输入手机号，手机号码为11位数字"/></div>
					<div class="experience">
						<select name="option">
  							<option value="">工作经验</option>
  							<option value="1-3">1~3年</option>
  							<option value="3-5">3~5年</option>
  							<option value="5-8">5~8年</option>
  							<option value="8">8年以上</option>
						</select>
					</div>
					<div class="input_submit"><input type="submit" value="申请入驻"/></div>
				</form>
			</div>
			<p class="clear"></p>
		</div>
		<div class="bottom_nav">
			<jsp:include page="bottom_common.jsp"></jsp:include>
		</div>
	</div>
	
</body>
</html>