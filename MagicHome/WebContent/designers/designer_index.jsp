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
<title>家装平台管理-装修公司后台管理</title>
<link rel="shortcut icon" href="<%=path %>/images/favicon.png">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/easyui.css" id="swicth-style">
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery.easyui.min.js"></script>
<style type="text/css">
body {
	font: 12px/20px "微软雅黑", "宋体", Arial, sans-serif, Verdana, Tahoma;
	padding: 0;
	margin: 0;
}

a:link {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

a:active {
	text-decoration: none;
}

.cs-north {
	height: 60px;
}

.cs-north-bg {
	width: 100%;
	height: 100%;
}

.cs-north-logo {
	height: 40px;
	margin: 15px 0px 0px 5px;
	display: inline-block;
	color: #000000;
	font-size: 22px;
	font-weight: bold;
	text-decoration: none
}

.cs-west {
	width: 200px;
	padding: 0px;
}

.cs-south {
	height: 25px;
	background:
		url('themes/pepper-grinder/images/ui-bg_fine-grain_15_ffffff_60x60.png')
		repeat-x;
	padding: 0px;
	text-align: center;
}

.cs-navi-tab {
	padding: 5px;
}

.cs-tab-menu {
	width: 120px;
}

.cs-home-remark {
	padding: 10px;
}

.wrapper {
	float: right;
	height: 30px;
	margin-left: 10px;
}

.ui-skin-nav {
	float: right;
	padding: 0;
	margin-right: 10px;
	list-style: none outside none;
	height: 30px;
}

.ui-skin-nav .li-skinitem {
	float: left;
	font-size: 12px;
	line-height: 30px;
	margin-left: 10px;
	text-align: center;
}

.ui-skin-nav .li-skinitem span {
	cursor: pointer;
	width: 10px;
	height: 10px;
	display: inline-block;
}

.ui-skin-nav .li-skinitem span.cs-skin-on {
	border: 1px solid #FFFFFF;
}

.ui-skin-nav .li-skinitem span.gray {
	background-color: gray;
}

.ui-skin-nav .li-skinitem span.pepper-grinder {
	background-color: #BC3604;
}

.ui-skin-nav .li-skinitem span.blue {
	background-color: blue;
}

.ui-skin-nav .li-skinitem span.cupertino {
	background-color: #D7EBF9;
}

.ui-skin-nav .li-skinitem span.dark-hive {
	background-color: black;
}

.ui-skin-nav .li-skinitem span.sunny {
	background-color: #FFE57E;
}
</style>
<script type="text/javascript">
	function addTab(title, url) {
		if ($('#tabs').tabs('exists', title)) {
			$('#tabs').tabs('select', title);//选中并刷新
			var currTab = $('#tabs').tabs('getSelected');
			var url = $(currTab.panel('options').content).attr('src');
			if (url != undefined && currTab.panel('options').title != '主页') {
				$('#tabs').tabs('update', {
					tab : currTab,
					options : {
						content : createFrame(url)
					}
				})
			}
		} else {
			var content = createFrame(url);
			$('#tabs').tabs('add', {
				title : title,
				content : content,
				closable : true
			});
		}
		tabClose();
	}
	function createFrame(url) {
		var s = '<iframe scrolling="auto" frameborder="0"  src="' + url
				+ '" style="width:100%;height:100%;"></iframe>';
		return s;
	}

	function tabClose() {
		/*双击关闭TAB选项卡*/
		$(".tabs-inner").dblclick(function() {
			var subtitle = $(this).children(".tabs-closable").text();
			$('#tabs').tabs('close', subtitle);
		})
		/*为选项卡绑定右键*/
		$(".tabs-inner").bind('contextmenu', function(e) {
			$('#mm').menu('show', {
				left : e.pageX,
				top : e.pageY
			});

			var subtitle = $(this).children(".tabs-closable").text();

			$('#mm').data("currtab", subtitle);
			$('#tabs').tabs('select', subtitle);
			return false;
		});
	}
	//绑定右键菜单事件
	function tabCloseEven() {
		//刷新
		$('#mm-tabupdate').click(function() {
			var currTab = $('#tabs').tabs('getSelected');
			var url = $(currTab.panel('options').content).attr('src');
			if (url != undefined && currTab.panel('options').title != '主页') {
				$('#tabs').tabs('update', {
					tab : currTab,
					options : {
						content : createFrame(url)
					}
				})
			}
		})
		//关闭当前
		$('#mm-tabclose').click(function() {
			var currtab_title = $('#mm').data("currtab");
			$('#tabs').tabs('close', currtab_title);
		})
		//全部关闭
		$('#mm-tabcloseall').click(function() {
			$('.tabs-inner span').each(function(i, n) {
				var t = $(n).text();
				if (t != '主页') {
					$('#tabs').tabs('close', t);
				}
			});
		});
		//关闭除当前之外的TAB
		$('#mm-tabcloseother').click(function() {
			var prevall = $('.tabs-selected').prevAll();
			var nextall = $('.tabs-selected').nextAll();
			if (prevall.length > 0) {
				prevall.each(function(i, n) {
					var t = $('a:eq(0) span', $(n)).text();
					if (t != '主页') {
						$('#tabs').tabs('close', t);
					}
				});
			}
			if (nextall.length > 0) {
				nextall.each(function(i, n) {
					var t = $('a:eq(0) span', $(n)).text();
					if (t != '主页') {
						$('#tabs').tabs('close', t);
					}
				});
			}
			return false;
		});
		//关闭当前右侧的TAB
		$('#mm-tabcloseright').click(function() {
			var nextall = $('.tabs-selected').nextAll();
			if (nextall.length == 0) {
				//msgShow('系统提示','后边没有啦~~','error');
				alert('后边没有啦~~');
				return false;
			}
			nextall.each(function(i, n) {
				var t = $('a:eq(0) span', $(n)).text();
				$('#tabs').tabs('close', t);
			});
			return false;
		});
		//关闭当前左侧的TAB
		$('#mm-tabcloseleft').click(function() {
			var prevall = $('.tabs-selected').prevAll();
			if (prevall.length == 0) {
				alert('到头了，前边没有啦~~');
				return false;
			}
			prevall.each(function(i, n) {
				var t = $('a:eq(0) span', $(n)).text();
				$('#tabs').tabs('close', t);
			});
			return false;
		});

		//退出
		$("#mm-exit").click(function() {
			$('#mm').menu('hide');
		})
	}

	$(function() {
		tabCloseEven();

		$('.cs-navi-tab').click(function() {
			var $this = $(this);
			var href = $this.attr('src');
			var title = $this.text();
			addTab(title, href);
		});

		var themes = {
			'gray' : '<%=path %>/themes/gray/easyui.css',
			'pepper-grinder' : '<%=path %>/themes/pepper-grinder/easyui.css',
			'blue' : '<%=path %>/themes/default/easyui.css',
			'cupertino' : '<%=path %>/themes/cupertino/easyui.css',
			'dark-hive' : '<%=path %>/themes/dark-hive/easyui.css',
			'sunny' : '<%=path %>/themes/sunny/easyui.css'
		};

		var skins = $('.li-skinitem span').click(
				function() {
					var $this = $(this);
					if ($this.hasClass('cs-skin-on'))
						return;
					skins.removeClass('cs-skin-on');
					$this.addClass('cs-skin-on');
					var skin = $this.attr('rel');
					$('#swicth-style').attr('href', themes[skin]);
					setCookie('cs-skin', skin);
					skin == 'dark-hive' ? $('.cs-north-logo').css('color',
							'#FFFFFF') : $('.cs-north-logo').css('color',
							'#000000');
				});

		if (getCookie('cs-skin')) {
			var skin = getCookie('cs-skin');
			$('#swicth-style').attr('href', themes[skin]);
			$this = $('.li-skinitem span[rel=' + skin + ']');
			$this.addClass('cs-skin-on');
			skin == 'dark-hive' ? $('.cs-north-logo').css('color', '#FFFFFF')
					: $('.cs-north-logo').css('color', '#000000');
		}
	});

	function setCookie(name, value) {//两个参数，一个是cookie的名子，一个是值
		var Days = 30; //此 cookie 将被保存 30 天
		var exp = new Date(); //new Date("December 31, 9998");
		exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
		document.cookie = name + "=" + escape(value) + ";expires="
				+ exp.toGMTString();
	}

	function getCookie(name) {//取cookies函数        
		var arr = document.cookie.match(new RegExp("(^| )" + name
				+ "=([^;]*)(;|$)"));
		if (arr != null)
			return unescape(arr[2]);
		return null;
	}
	function refresh() {
		window.location.href="<%=path %>/designer/return_index_page";
	}
</script>
<style type="css/text">

</style>
</head>
<body class="easyui-layout">
	<div region="north" border="true" class="cs-north">
		<div class="cs-north-bg">
			<div class="cs-north-logo" style="position: relative; margin-left: 30px;">
				<img src="<%=path %>/images/favicon.png" style="position: absolute; top: -7px; left: -30px;" />
				<a href="<%=path%>/page/index">设计师后台管理</a>
				<span style="margin-left: 40px; font-size: 16px;">
					欢迎您，
					<c:if test="${!empty(sessionScope.designer) }">
						${sessionScope.designer.name }
					</c:if>
				</span>
				
			</div>
			<ul class="ui-skin-nav">
				<li style="float: left; margin: 3px 30px;">
					<a href="javascript:;" onclick="refresh()">刷新</a>
					&nbsp;
					<a href="<%=path %>/page/exit">退出系统</a>
				</li>
				<li style="width: 120px; float: left; margin-top: 3px;">
					<span>点击可以切换主题--></span>
				</li>
				<li class="li-skinitem" title="gray">
					<span class="gray" rel="gray"></span>
				</li>
				<li class="li-skinitem" title="pepper-grinder">
					<span class="pepper-grinder" rel="pepper-grinder"></span>
				</li>
				<li class="li-skinitem" title="blue">
					<span class="blue" rel="blue"></span>
				</li>
				<li class="li-skinitem" title="cupertino">
					<span class="cupertino" rel="cupertino"></span>
				</li>
				<li class="li-skinitem" title="dark-hive">
				<span class="dark-hive" rel="dark-hive"></span>
				</li>
				<li class="li-skinitem" title="sunny">
					<span class="sunny" rel="sunny"></span>
				</li>
			</ul>
		</div>
	</div>
	<div region="west" border="true" split="true" title="导航" class="cs-west">
		<div class="easyui-accordion" fit="true" border="false">
			<div title="个人中心">
				<a href="javascript:void(0);" src="<%=path %>/designer/designer_personal_page"
					class="cs-navi-tab">查看个人信息</a>
				<p></p>
				<a href="javascript:void(0);" src="<%=path %>/designer/designer_update_pwd_page"
					class="cs-navi-tab">修改密码</a>
				<p></p>
			</div>
			<div title="案例管理">
				<a href="javascript:void(0);" src="<%=path %>/designer/designer_add_case_page"
					class="cs-navi-tab">添加案例</a>
				<p></p>
				<a href="javascript:void(0);" src="<%=path %>/designer/designer_case_page"
					class="cs-navi-tab">查看所有案例</a>
				<p></p>
			</div>
		</div>
	</div>
	<div id="mainPanle" region="center" border="true" border="false">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="主页">
				<div class="cs-home-remark">
					<h1 style="text-align: center;">欢迎使用家装平台管理系统</h1>
					
				</div>
			</div>
		</div>
	</div>

	<div region="south" border="false" id="south">
		<center>672630243@qq.com</center>
	</div>

	<div id="mm" class="easyui-menu cs-tab-menu">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseother">关闭其他</div>
		<div id="mm-tabcloseall">关闭全部</div>
	</div>
</body>
</html>