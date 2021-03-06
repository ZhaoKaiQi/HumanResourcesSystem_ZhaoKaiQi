<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>人事管理系统 ——员工管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${pageContext.request.contextPath}/css/css.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
	<link href="${pageContext.request.contextPath}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-migrate-1.2.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
	<script src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerResizable.jss" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/pager.css" type="text/css" rel="stylesheet" />

	<script type="text/javascript">
	       $(function(){
	    	   /** 获取上一次选中的部门数据 */
	    	   var boxs  = $("input[type='checkbox'][id^='box_']");
	    	   
	    	   /** 给全选按钮绑定点击事件  */
		    	$("#checkAll").click(function(){
		    		// this是checkAll  this.checked是true
		    		// 所有数据行的选中状态与全选的状态一致
		    		boxs.attr("checked",this.checked);
		    	})
		    	
	    	   /** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
		    	$("tr[id^='data_']").hover(function(){
		    		$(this).css("backgroundColor","#eeccff");
		    	},function(){
		    		$(this).css("backgroundColor","#ffffff");
		    	})
		    	
		    	
	    	   /** 删除员工绑定点击事件 */
	    	   $("#delete").click(function(){
	    		   /** 获取到用户选中的复选框  */
	    		   var checkedBoxs = boxs.filter(":checked");
	    		   if(checkedBoxs.length < 1){
	    			   $.ligerDialog.error("请选择一个需要删除的员工！");
	    		   }else{
	    			   /** 得到用户选中的所有的需要删除的ids */
	    			   var ids = checkedBoxs.map(function(){
	    				   return this.value;
	    			   })
	    			   
	    			   $.ligerDialog.confirm("确认要删除吗?","删除员工",function(r){
	    				   if(r){
	    					   // alert("删除："+ids.get());
	    					   // 发送请求
	    					   window.location = "${pageContext.request.contextPath}/removeEmployee?ids=" + ids.get();
	    				   }
	    			   });
	    		   }
	    	   })
	       })
	</script>
</head>
<body>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr><td height="10"></td></tr>
	  <tr>
	    <td width="15" height="32"><img src="${pageContext.request.contextPath}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${pageContext.request.contextPath}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：员工管理 &gt; 员工查询</td>
		<td width="15" height="32"><img src="${pageContext.request.contextPath}/images/main_locright.gif" width="15" height="32"></td>
	  </tr>
	</table>
	
	<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
	  <!-- 查询区  -->
	  <tr valign="top">
	    <td height="30">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr>
			  <td class="fftd">
			  	<form name="empform" method="post" id="empform" action="selectEmployee">
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="font3">
					    	职位：
							    <select name="posId" style="width:143px;">
					    			<option value="0">--请选择职位--</option>
					    			<c:forEach items="${positions}" var="position">
					    				<option value="${position.posId}">${position.posName }</option>
					    			</c:forEach>
					    		</select>
					    	姓名：<input type="text" name="empName">
					    	身份证号码：<input type="text" name="empCardId" maxlength="18">
					    </td>
					  </tr>
					  <tr>
					    <td class="font3">
					    	性别：
					    		<select name="empSex" style="width:143px;">
					    			<option value="0">--请选择性别--</option>
					    			<option value="1">男</option>
					    			<option value="2">女</option>
					    		</select>
					    	手机：<input type="text" name="empTel">
					    	所属部门：<select  name="deptId" style="width:100px;">
								   <option value="0">--部门选择--</option>
								   <c:forEach items="${depts}" var="dept">
					    				<option value="${dept.deptId }">${dept.deptName }</option>
					    			</c:forEach>
							</select>&nbsp;
					    	<input type="submit" value="搜索"/>
					    	<input id="delete" type="button" value="删除"/>
					    </td>
					  </tr>
					</table>
				</form>
			  </td>
			</tr>
		  </table>
		</td>
	  </tr>
	  
	  <!-- 数据展示区 -->
	  <tr valign="top">
	    <td height="20">
		  <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
		    <tr class="main_trbg_tit" align="center">
			  <td><input type="checkbox" name="checkAll" id="checkAll"></td>
			  <td>姓名</td>
			  <td>性别</td>
			  <td>手机号码</td>
			  <td>邮箱</td>
			  <td>职位</td>
			  <td>学历</td>
			  <td>身份证号码</td>
			  <td>部门</td>
			  <td>联系地址</td>
			  <td>建档日期</td>
			  <td align="center">操作</td>
			</tr>
			 <!-- 分页部分开始1 -->
						<pg:pager url="${pageContext.request.contextPath}/selectEmployee" index="center" maxPageItems="10" maxIndexPages="10" isOffset="<%=false%>" export="pageOffset,currentPageNumber=pageNumber" scope="request">
							<pg:param name="m" value="findSysparams" />
			<c:forEach items="${employees}" var="employee" varStatus="stat">
				<pg:item>
									<c:if test="${1 == stat.count % 2 }">
										<tr class="tab_list_tr2" onmouseover=this.className = 'tab_list_tr_hover' ; onmouseOut=this.className = 'tab_list_tr2' ;>
									</c:if>

									<c:if test="${0 == stat.count % 2 }">
										<tr class="tab_list_tr" onmouseover=this.className = 'tab_list_tr_hover' ; onmouseOut=this.className = 'tab_list_tr' ;>
									</c:if>
				
				
				
				<tr id="data_${stat.index}" class="main_trbg" align="center">
					<td><input type="checkbox" id="box_${stat.index}" value="${employee.empId}"></td>
					 <td>${employee.empName }</td>
					  <td>
					        <c:choose>
					        	<c:when test="${employee.empSex == 1 }">男</c:when>
					        	<c:otherwise>女</c:otherwise>
					        </c:choose>
					  </td>
					  <td>${employee.empTel }</td>
					  <td>${employee.empEmail }</td>
					  <td>${employee.position.posName }</td>
					  <td>${employee.empEducation }</td>
					  <td>${employee.empCardId }</td>
					  <td>${employee.dept.deptName }</td>
					  <td>${employee.empAddress }</td>
					  <td>${employee.empCreateDate}</td>
					  <td align="center" width="40px;"><a href="showUpdateEmployee?flag=1&empId=${employee.empId}">
							<img title="修改" src="${pageContext.request.contextPath}/images/update.gif"/></a>
					  </td>
				</tr></pg:item>
			</c:forEach>
		  </table>
		  <div style="margin-top: 30px;font-size: 20px;vertical-align:middle;text-align: center;color: #000000;">
						<pg:index>
							<pg:first>
								<a href="<%=pageUrl%>"><img src="${pageContext.request.contextPath}/images/firstPage.png" border="0"></a>
							</pg:first>
							<pg:prev>
								<a href="<%=pageUrl%>"><img src="${pageContext.request.contextPath}/images/upPage.png" border="0"></a>
							</pg:prev>

							<pg:pages>
								<c:choose>
									<c:when test="${pageNumber eq currentPageNumber}">
										<big><b><font style="vertical-align:top;"><%=pageNumber%></font></b></big>
									</c:when>
									<c:otherwise>
										<big><b><font style="vertical-align:10;"><a href="<%=pageUrl%>"><%=pageNumber%></a></font></b></big>
									</c:otherwise>
								</c:choose>

							</pg:pages>
							<pg:next>
								<a href="<%=pageUrl%>"><img src="${pageContext.request.contextPath}/images/downPage.png" border="0"></a>
							</pg:next>
							<pg:last>
								<a href="<%=pageUrl%>"><img src="${pageContext.request.contextPath}/images/lastPage.png" border="0"></a>
							</pg:last>
							<font style="vertical-align:10;font-size: 17px;font-style:italic">一共${employees.size()}条记录</font>
						</pg:index>
					</div>
					</pg:pager>
		  
		  
		</td>
	  </tr>
	  <!-- 分页标签 -->
	  <tr valign="top"><td align="center" class="font3">
	  	 <fkjava:pager
	  	        pageIndex="${requestScope.pageModel.pageIndex}" 
	  	        pageSize="${requestScope.pageModel.pageSize}" 
	  	        recordCount="${requestScope.pageModel.recordCount}" 
	  	        style="digg"
	  	        submitUrl="${pageContext.request.contextPath}/selectEmployee?pageIndex={0}"/>
	  </td></tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>