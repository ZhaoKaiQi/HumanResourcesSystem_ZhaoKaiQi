<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML>
<html>

	<head>
		<title>人事管理系统——签到图表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link href="${pageContext.request.contextPath}/css/css.css" type="text/css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css" />
		<link href="${pageContext.request.contextPath}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
		<script type="text/javascript" src="js/echarts.js"></script>
		<script type="text/javascript" src="js/chart/bar.js"></script>
		<script type="text/javascript" src="js/chart/line.js"></script>

		<!-- ECharts单文件引入 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.js"></script>
		<link href="${pageContext.request.contextPath}/css/pager.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript">
			$(function() {
				/** 部门表单提交 */
				$("#deptForm").submit(function() {
					var name = $("#name");
					var remark = $("#remark");
					var msg = "";
					if($.trim(name.val()) == "") {
						msg = "部门名称不能为空！";
						name.focus();
					} else if($.trim(remark.val()) == "") {
						msg = "详细描述不能为空！";
						remark.focus();
					}
					if(msg != "") {
						$.ligerDialog.error(msg);
						return false;
					} else {
						return true;
					}
					$("#deptForm").submit();
				});
			});
		</script>
	</head>

	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="10"></td>
			</tr>
			<tr>
				<td width="15" height="32"><img src="${pageContext.request.contextPath}/images/main_locleft.gif" width="15" height="32"></td>
				<td class="main_locbg font2"><img src="${pageContext.request.contextPath}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：签到管理 &gt; 签到图表</td>
				<td width="15" height="32"><img src="${pageContext.request.contextPath}/images/main_locright.gif" width="15" height="32"></td>
			</tr>
		</table>

		<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
			<tr valign="top">
				<td>
					<form id="deptForm" method="post">
						<!-- action="getPicture?atdCreateTime" -->
						<!-- 隐藏表单，flag表示添加标记 -->
						<input type="hidden" name="flag" value="2">
						<table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
							<tr>
								<td class="font3 fftd">开始日期：<input type="text" name="atdCreateTime" id="dateint" size="20" />
									<input type="button" value="生成图表 " id="getDateForm">
								</td>
							</tr>
							<tr>
								<td class="main_tdbor"></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
		<!--Step:1 为ECharts准备一个具备大小（宽高）的Dom-->
		<div id="main" style="height: 500px; border: 1px solid #ccc; padding: 10px;">

		</div>
		<script type="text/javascript">
			// Step:3 conifg ECharts's path, link to echarts.js from current page.
			// Step:3 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
			$(function() {
						require.config({
							paths: {
								echarts: '/js'
							}
						});
						// Step:4 require echarts and use it in the callback.
						// Step:4 动态加载echarts然后在回调函数中开始使用，注意保持按需加载结构定义图表路径
						require(
							[
								'echarts',
								'echarts/chart/bar',
								'echarts/chart/line'
							],
							function(ec) {
								//--- 折柱 ---
								var myChart = ec.init(document.getElementById('main'));
								//通过Ajax获取数据  
								$("#getDateForm").click(function() {
									var atdCreate = $("#dateint").val();
									$.ajax({
										type: "POST",
										async: false,
										url: "signCharts",
										dataType: "json",
										data: {
											atdCreateTime: atdCreate,
										},
										success: function(json) {
											alert(json);
											var arr = eval(json);
											alert(arr[0]);
											alert(arr[1]);
											alert(arr[2]);
											myChart.setOption({
												tooltip: {
													trigger: 'axis'
												},
												legend: {
													data: ['上班', '下班']
												},
												toolbox: {
													show: true,
													feature: {
														mark: {
															show: true
														},
														dataView: {
															show: true,
															readOnly: false
														},
														magicType: {
															show: true,
															type: ['line', 'bar']
														},
														restore: {
															show: true
														},
														saveAsImage: {
															show: true
														}
													}
												},
												calculable: true,
												xAxis: [{
													type: 'category',
													data: arr[2]
												}],
												yAxis: [{
													type: 'value',
													splitArea: {
														show: true
													}
												}],
												series: [{
														name: '上班',
														type: 'bar',
														data: arr[0]
													},
													{
														name: '下班',
														type: 'bar',
														data: arr[1]
													}
												]
											});
										},
										error: function(errorMsg) {
											alert("请求数据失败");
										}
									})
								})
							})
						})
		</script>
	</body>

</html>