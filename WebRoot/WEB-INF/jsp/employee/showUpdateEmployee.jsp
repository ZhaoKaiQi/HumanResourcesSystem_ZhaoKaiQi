<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>人事管理系统——修改员工</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<link href="${pageContext.request.contextPath}/css/css.css"
	type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css" />
<link
	href="${pageContext.request.contextPath}/js/ligerUI/skins/ligerui-icons.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-migrate-1.2.1.js"></script>
<script
	src="${pageContext.request.contextPath}/js/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerDrag.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerResizable.jss"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/pager.css"
	type="text/css" rel="stylesheet" />
<script language="javascript" type="text/javascript"
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function() {
		// 控制文档加载完成以后 选中性别 
		$("#sex").val("${employee.empSex}");

		/** 员工表单提交 */
		$("#employeeForm").submit(function() {
			//alert(1);
			var name = $("#name");
			var cardId = $("#cardId");
			var education = $("#education");
			var email = $("#email");
			var phone = $("#phone");
			var tel = $("#tel");
			var party = $("#party");
			var qqNum = $("#qqNum");
			var address = $("#address");
			var postCode = $("#postCode");
			var birthday = $("#birthday");
			var race = $("#race");
			var speciality = $("#speciality");
			var hobby = $("#hobby");
			var msg = "";
			if ($.trim(name.val()) == "") {
				msg = "姓名不能为空！";
				name.focus();
			} else if ($.trim(cardId.val()) == "") {
				msg = "身份证号码不能为空！";
				cardId.focus();
			} else if (!/^[1-9]\d{16}[0-9A-Za-z]$/.test($.trim(cardId.val()))) {
				msg = "身份证号码格式不正确！";
				cardId.focus();
			} else if ($.trim(education.val()) == "") {
				msg = "学历不能为空！";
				education.focus();
			} else if ($.trim(email.val()) == "") {
				msg = "邮箱不能为空！";
				email.focus();
			} else if (!/^\w+@\w{2,3}\.\w{2,6}$/.test($.trim(email.val()))) {
				msg = "邮箱格式不正确！";
				email.focus();
			} else if ($.trim(phone.val()) == "") {
				msg = "手机号码不能为空！";
				phone.focus();
			} else if (!/^1[3|5|8]\d{9}$/.test($.trim(phone.val()))) {
				msg = "手机号码格式不正确！";
				phone.focus();
			} else if ($.trim(tel.val()) == "") {
				msg = "电话号码不能为空！";
				tel.focus();
			} else if (!/^0\d{2,3}-?\d{7,8}$/.test($.trim(tel.val()))) {
				msg = "电话号码格式不正确！";
				tel.focus();
			} else if ($.trim(party.val()) == "") {
				msg = "政治面貌不能为空！";
				party.focus();
			} else if ($.trim(qqNum.val()) == "") {
				msg = "QQ号码不能为空！";
				qqNum.focus();
			} else if (!/^\d{6,}$/.test($.trim(qqNum.val()))) {
				msg = "QQ号码格式不正确！";
				qqNum.focus();
			} else if ($.trim(address.val()) == "") {
				msg = "地址不能为空！";
				address.focus();
			} else if ($.trim(postCode.val()) == "") {
				msg = "邮政编码不能为空！";
				postCode.focus();
			} else if (!/^[1-9]\d{5}$/.test($.trim(postCode.val()))) {
				msg = "邮政编码格式不正确！";
				postCode.focus();
			} else if ($.trim(birthday.val()) == "") {
				msg = "出生日期不能为空！";
				birthday.focus();
			} else if (!birthday.val()) {
				//					!/^\d{4}-\d{2}-\d{2}$/.test($.trim(birthday.val()))
				msg = "出生日期格式不正确！";
				birthday.focus();
			} else if ($.trim(race.val()) == "") {
				msg = "民族不能为空！";
				race.focus();
			} else if ($.trim(speciality.val()) == "") {
				msg = "专业不能为空！";
				speciality.focus();
			} else if ($.trim(hobby.val()) == "") {
				msg = "爱好不能为空！";
				hobby.focus();
			}
			if (msg != "") {
				$.ligerDialog.error(msg);
				return false;
			} else {
				return true;
			}
			$("#employeeForm").submit();
		});
		$("#change").click(function() {
			alert("恭喜你！修改成功！");
			//parent.location = "${pageContext.request.contextPath}/logout.action";
		})
	});
</script>
</head>

<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="10"></td>
		</tr>
		<tr>
			<td width="15" height="32"><img
				src="${pageContext.request.contextPath}/images/main_locleft.gif"
				width="15" height="32"></td>
			<td class="main_locbg font2"><img
				src="${pageContext.request.contextPath}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：员工管理
				&gt; 修改员工</td>
			<td width="15" height="32"><img
				src="${pageContext.request.contextPath}/images/main_locright.gif"
				width="15" height="32"></td>
		</tr>
	</table>
	<table width="100%" height="90%" border="0" cellpadding="5"
		cellspacing="0" class="main_tabbor">
		<tr valign="top">
			<td>
				<form action="updateEmployee" id="employeeForm" method="post">
					<!-- 隐藏表单，flag表示添加标记 -->
					<input type="hidden" name="flag" value="2"> <input
						type="hidden" name="empId" value="${employee.empId }">

					<table width="100%" border="0" cellpadding="0" cellspacing="10"
						class="main_tab">
						<tr>
							<td class="font3 fftd">
								<table>
									<tr>
										<td class="font3 fftd">姓名：<input type="text"
											name="empName" id="name" size="20"
											value="${employee2.empName }" /></td>
										<td class="font3 fftd">身份证号码：<input type="text"
											name="empCardId" id="cardId" size="20"
											value="${employee2.empCardId }" /></td>
									</tr>
									<tr>
										<td class="font3 fftd">性别： <select name="empSex"
											style="width:143px;">
												<option value="${employee2.empSex}">
													<c:choose>
														<c:when test="${employee2.empSex == 1 }">男</c:when>
														<c:otherwise>女</c:otherwise>
													</c:choose>
												</option>
												<option value="1">男</option>
												<option value="2">女</option>
										</select></td>

										<td class="font3 fftd">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：
											<select name="posId" style="width:143px;">

												<option value="${employee2.position.posId }">${employee2.position.posName}</option>
												<c:forEach items="${positions }" var="position">
													<c:choose>
														<c:when
															test="${employee2.position.posId==position.posId }"></c:when>
														<c:otherwise>
															<option value="${position.posId }">${position.posName}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
										</select></td>
									</tr>
									<tr>
										<td class="font3 fftd">学历：<input name="empEducation"
											id="education" size="20" value="${employee2.empEducation }" />
										</td>
										<td class="font3 fftd">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：<input
											name="empEmail" id="email" size="20"
											value="${employee2.empEmail }" /></td>
									</tr>
									<tr>
										<td class="font3 fftd">手机：<input name="empTel" id="phone"
											size="20" value="${employee2.empTel}" /></td>
										<td class="font3 fftd">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：<input
											name="empPhone" id="tel" size="20"
											value="${employee2.empPhone }" /></td>
										<td><input type="hidden" name="empCreateDate"
											value="${employee2.empCreateDate}"> <input
											type="hidden" name="userId" value="${employee2.user.userId}">
										</td>
									</tr>

								</table></td>
						</tr>
						<tr>
							<td class="main_tdbor"></td>
						</tr>

						<tr>
							<td class="font3 fftd">政治面貌：<input name="empParty"
								id="party" size="40" value="${employee2.empParty }" />&nbsp;&nbsp;
								QQ&nbsp;&nbsp;号码： <input name="empQQNum" id="qqNum" size="20"
								value="${employee2.empQQNum }" />
							</td>
						</tr>
						<tr>
							<td class="main_tdbor"></td>
						</tr>

						<tr>
							<td class="font3 fftd">联系地址：<input name="empAddress"
								id="address" size="40" value="${employee2.empAddress }" />&nbsp;&nbsp;
								邮政编码： <input name="empPostCode" id="postCode" size="20"
								value="${employee2.empPostCode }" />
							</td>
						</tr>
						<tr>
							<td class="main_tdbor"></td>
						</tr>

						<tr>
							<td class="font3 fftd">出生日期：<input cssClass="Wdate"
								onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});"
								name="empBirthday" id="birthday" size="40"
								value="${employee2.empBirthday}" />&nbsp;&nbsp;
								民&nbsp;&nbsp;&nbsp;&nbsp;族： <input name="empRace" id="race"
								size="20" value="${employee2.empRace }" />
							</td>
							<%-- <f:formatDate value="${employee2.empBirthday}" pattern="yyyy-MM-dd"/> --%>
						</tr>
						<tr>
							<td class="main_tdbor"></td>
						</tr>

						<tr>
							<td class="font3 fftd">所学专业：<input name="empSpeciality"
								id="speciality" size="40" value="${employee2.empSpeciality }" />&nbsp;&nbsp;
								爱&nbsp;&nbsp;&nbsp;&nbsp;好： <input name="empHobby" id="hobby"
								size="20" value="${employee2.empHobby }" />
							</td>
						</tr>
						<tr>
							<td class="main_tdbor"></td>
						</tr>

						<tr>
							<td class="font3 fftd">备&nbsp;&nbsp;&nbsp;&nbsp;注：<input
								name="empRemark" id="remark" size="40"
								value="${employee2.empRemark }" /> &nbsp;&nbsp;所属部门： <select
								name="deptId" style="width:100px;">
									<option value="${employee2.dept.deptId}">${employee2.dept.deptName}</option>
									<c:forEach items="${depts}" var="dept">
										<c:choose>
											<c:when test="${employee2.dept.deptId == dept.deptId }">
												<%-- <option value="${dept.id }" selected="selected">${dept.name }</option> --%>
											</c:when>
											<c:otherwise>
												<option value="${dept.deptId }">${dept.deptName }</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td class="main_tdbor"></td>
						</tr>

						<tr>
							<td align="left" class="fftd"><input type="submit"
								value="修改" id="change">&nbsp;&nbsp;<input type="reset"
								value="取消 ">
								<div style="color: red">${msg2}</div></td>
						</tr>
					</table>
				</form></td>
		</tr>
	</table>
	<div style="height:10px;"></div>
</body>

</html>