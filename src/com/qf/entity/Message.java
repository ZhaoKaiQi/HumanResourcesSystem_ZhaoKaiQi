package com.qf.entity;

import java.util.Date;

/**
 * @ClassName: Message
 * @Description: 通知表
 * @author 赵凯琦
 * @date 2017-7-10 上午2:59:13
 */
public class Message {
	private int msgId;// 序号
	private String msgTitle;// 标题
	private String msgContent;// 内容
	//@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date msgCreateDate;// 时间
	private User user;// 维护用户

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public Date getMsgCreateDate() {
		return msgCreateDate;
	}

	public void setMsgCreateDate(Date msgCreateDate) {
		this.msgCreateDate = msgCreateDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Message(int msgId, String msgTitle, String msgContent,
			Date msgCreateDate, User user) {
		super();
		this.msgId = msgId;
		this.msgTitle = msgTitle;
		this.msgContent = msgContent;
		this.msgCreateDate = msgCreateDate;
		this.user = user;
	}

	public Message() {
		super();
	}

	@Override
	public String toString() {
		return "Message [编号=" + msgId + ", 通知标题=" + msgTitle + ", 通知内容="
				+ msgContent + ", 创建时间=" + msgCreateDate + ", 用户=" + user + "]";
	}
}
