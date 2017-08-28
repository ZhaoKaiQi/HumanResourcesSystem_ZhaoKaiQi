    <%@ tag language="java" pageEncoding="UTF-8"%>  
    <%@ taglib uri="/WEB-INF/page.tld" prefix="c"%>  
    <%@ attribute name="curIndex" type="java.lang.Long" required="true"%>  
    <%@ attribute name="pageSize" type="java.lang.Long" required="true"%>  
    <%@ attribute name="pagerRange" type="java.lang.Long" required="true"%>  
    <%@ attribute name="totalPage" type="java.lang.Long" required="true"%>  
    <%@ attribute name="formId" type="java.lang.String" required="true"%>  
    <%  
        long begin = Math.max(1, curIndex - pagerRange/2);  
        long end = Math.min(begin + (pagerRange-1),totalPage);  
          
        request.setAttribute("p_begin", begin);  
        request.setAttribute("p_end", end);  
    %>  
        <table class="pager">  
        <tr>  
             <% if (curIndex!=1){%>  
                    <td><a href="javascript:gotoPage(1)">首页</a></td>  
                    <td><a href="javascript:gotoPage(<%=curIndex-1%>)">上一页</a></td>  
             <%}else{%>  
                    <td class="disabled"><a href="#">首页</a></td>  
                    <td class="disabled"><a href="#">上一页</a></td>  
             <%}%>  
       
            <c:forEach var="i" begin="${p_begin}" end="${p_end}">  
                <c:choose>  
                    <c:when test="${i == curIndex}">  
                        <td class="active"><a href="#">${i}</a></td>  
                    </c:when>  
                    <c:otherwise>  
                        <td><a href="javascript:gotoPage(${i})">${i}</a></td>  
                    </c:otherwise>  
                </c:choose>  
            </c:forEach>  
      
             <% if (curIndex!=totalPage){%>  
                    <td><a href="#">下一页</a></td>  
                    <td><a href="#">末页</a></td>  
             <%}else{%>  
                    <td class="disabled"><a href="javascript:gotoPage(<%=curIndex+1%>)">下一页</a></td>  
                    <td class="disabled"><a href="javascript:gotoPage(<%=totalPage%>)">末页</a></td>  
             <%}%>  
             <td><a>共${totalPage}页</a></td>  
             <td class="input_li">跳转到:<input type="text" id="p_pageIndex" size="2" value="<c:out value="${pageIndex}"/>"/>页 <input type="button" id="gotoBtn" onclick="gotoPageByBtn()" value="GO"/></td>  
             <td class="input_li"> 每页:  
             <select id="p_pageSizeSelect" onchange="gotoPage(<%=curIndex%>)">  
                <option value="10" <c:if test="${pageSize==10}">selected</c:if>>10条</option>  
                <option value="20" <c:if test="${pageSize==20}">selected</c:if>>20条</option>  
                <option value="50" <c:if test="${pageSize==50}">selected</c:if>>50条</option>  
             </select>  
             </td>  
        </tr>  
        </table>  