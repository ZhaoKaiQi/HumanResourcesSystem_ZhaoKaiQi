    <%@ taglib uri="/WEB-INF/page.tld" prefix="c"%>  
<%--     <%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt"%>   --%>
    <%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>  
    <head>  
    <style><!--分页样式-->  
    .pager { font: 12px Arial, Helvetica, sans-serif;}  
    .pager a {padding: 1px 6px; border: solid 1px #ddd; background: #fff; text-decoration: none;margin-right:2px;line-height:30px;vertical-align:middle;}  
    .pager .active a{color:red;border:none;}  
    .pager a:visited {padding: 1px 6px; border: solid 1px #ddd; background: #fff; text-decoration: none;}  
    .pager a:hover {color: #fff; background: #ffa501;border-color:#ffa501;text-decoration: none;}  
    .pager .input_li{padding: 1px 6px;}  
    </style>  
    <script><!--分页跳转脚本-->  
    function gotoPage(pageIndex){  
        var queryForm = document.getElementById("queryForm");  
        var action = queryForm.action;  
        var pageSize = document.getElementById("p_pageSizeSelect").value;  
        action += "?pageIndex=" + pageIndex + "&pageSize=" + pageSize;  
        //alert(action);  
        queryForm.action = action;  
        queryForm.submit();  
    }  
      
    function gotoPageByBtn(){  
        var pageIndex = document.getElementById("p_pageIndex").value;  
        var pageIndexInt = parseInt(pageIndex);  
        var totalPage = ${totalPage};  
          
        if(pageIndexInt>0 && pageIndexInt<totalPage){  
            gotoPage(pageIndex);  
        }  
        else{  
            alert("输入页数超出范围!");  
        }  
    }  
    </script>  
    </head>  
    <body>  
    <form id="queryForm" action="${basePath}/log/list" method="post">  
    <table>  
        <tr>  
        <td>用户名:</td>  
        <td><input type="text" name="userName" value="<c:out value="${userName}"/>"/> </td>  
        <td><input type="submit" text="查询"/></td>  
        </tr>  
    </table>  
    </form>  
    <tags:pager pagerRange="10" pageSize="${pageSize}" totalPage="${totalPage}" curIndex="${pageIndex}" formId="queryForm"></tags:pager>  
    <table class="border">  
        <thead>  
            <tr>  
                <th width="100">用户名称</th>  
                <th width="500">操作内容</th>  
                <th width="200">操作时间</th>  
            </tr>  
        </thead>  
        <tbody>  
        <c:forEach items="${logList}" var="log">  
            <tr>  
                <td>${log.userName}</td>  
                <td>${log.result}</td>  
                <td>  
                <fmt:formatDate value="${log.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>  
                </td>  
            </tr>  
        </c:forEach>  
        </tbody>  
    </table>  
    <tags:pager pagerRange="10" pageSize="${pageSize}" totalPage="${totalPage}" curIndex="${pageIndex}" formId="queryForm"></tags:pager>  
    </body>  