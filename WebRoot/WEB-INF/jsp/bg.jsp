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
		
	</head>

	<body>
		<!--Step:1 为ECharts准备一个具备大小（宽高）的Dom-->
		<div id="main" style="height: 500px; border: 1px solid #ccc; padding: 10px;">

		</div>
		<script type="text/javascript">
			// Step:3 conifg ECharts's path, link to echarts.js from current page.
			// Step:3 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
			
			window.onload=function(){
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
								//$("#getDateForm").click(function() {
									//var atdCreate = $("#dateint").val();
									$.ajax({
										type: "get",
										async: false,
										url: "signCharts",
										dataType: "json",
										data: {
											
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
								//})
							})
						}
		</script>
	</body>

</html>