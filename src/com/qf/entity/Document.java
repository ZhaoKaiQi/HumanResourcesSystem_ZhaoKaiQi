package com.qf.entity;

import java.util.Date;

/**
 * @ClassName: Document
 * @Description: 文档表
 * @author 赵凯琦
 * @date 2017-7-10 上午2:58:09
 */
public class Document {
	private int docId;// 序号
	private String upFile;// 文件上传路径
	private String docTitle;// 标题
	private String docFileName;// 文件名称
	private String docRemark;// 内容
	private Date docCreateDate;// 时间
	private User user;// 用户表主键

	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}

	public String getDocTitle() {
		return docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public String getDocFileName() {
		return docFileName;
	}

	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}

	public String getDocRemark() {
		return docRemark;
	}

	public void setDocRemark(String docRemark) {
		this.docRemark = docRemark;
	}

	public Date getDocCreateDate() {
		return docCreateDate;
	}

	public void setDocCreateDate(Date docCreateDate) {
		this.docCreateDate = docCreateDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public String getUpFile() {
		return upFile;
	}

	public void setUpFile(String upFile) {
		this.upFile = upFile;
	}

	public Document(int docId, String docTitle, String docFileName,
			String docRemark, Date docCreateDate, User user, String upFile) {
		super();
		this.docId = docId;
		this.docTitle = docTitle;
		this.docFileName = docFileName;
		this.docRemark = docRemark;
		this.docCreateDate = docCreateDate;
		this.user = user;
		this.upFile =upFile;
	}

	public Document() {
		super();
	}

	@Override
	public String toString() {
		return "Document [序号=" + docId + ", 标题=" + docTitle + ", 文件名称="
				+ docFileName + ", 内容=" + docRemark + ", 创建时间=" + docCreateDate
				+ ", 用户=" + user + ", 文件路径=" + upFile + "]";
	}

}
