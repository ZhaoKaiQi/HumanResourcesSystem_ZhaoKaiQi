package com.qf.utils;

import java.io.IOException;

import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PagerTag extends SimpleTagSupport {
	private int pageIndex;// 表示分页的第几页
	private int pageSize;// 表示分页标签 ，每页显示多少条数据
	private int recordCount;// 记录分页的总数
	private String submitUrl;
	private String style;

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	StringWriter sw = new StringWriter();
	PagerTag pagerTag = new PagerTag();

	public void doTag() throws JspException, IOException {
		// pageIndex,pageSize,recordCount,submitUrl,style
		if (submitUrl != null && style != null && pageIndex >= 0
				&& pageSize >= 0 && recordCount >= 0) {
			//获取PageContext对象，若重写了setJspContext方法，必须把PageContext赋于成员变量jspContext
			PageContext pageContext = (PageContext) this.getJspContext();
		      pageContext.getOut().print("这里是10086<br>");
		      //得到代表jsp标签体的JspFragment，若这个类重写了setJspBody方法，必须赋于成员变量jspFragment，所以不需要调用getJspBody方法
		     JspFragment jspFragment=this.getJspBody();
		     //将标签体的内容输出到浏览器
		      jspFragment.invoke(null);
		     pageContext.getOut().print("<br>");
		     /*
		     * 或者把标签条输入缓冲流中，然后对数据进行修改
		     */
		   jspFragment.invoke(sw);
		     String str=sw.getBuffer().toString();
		  str="####"+str+"####";
		    pageContext.getOut().print(str+"<br>");
		    //使用标签属性
		} else {
			/* 从内容体中使用消息 */
			getJspBody().invoke(sw);
			getJspContext().getOut().println(sw.toString());
		}

	}

}
