package com.qf.entity;

import java.util.Date;

/**
 * @ClassName: User
 * @Description: 用户表
 * @author 赵凯琦
 * @date 2017-7-10 上午3:01:18
 */
public class User {
	private int userId;// 序号
	private String userName;// 用户名
	private String loginName;// 登录名
	private String userPassWord;// 密码
	private int status;// 状态
	//@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDate;// 建档日期

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserPassWord() {
		return userPassWord;
	}

	public void setUserPassWord(String userPassWord) {
		this.userPassWord = userPassWord;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date empCreateDate) {
		this.createDate = empCreateDate;
	}

	public User(String userName, String loginName, String userPassWord,
			int status, Date createDate) {
		super();
		this.userName = userName;
		this.loginName = loginName;
		this.userPassWord = userPassWord;
		this.status = status;
		this.createDate = createDate;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [用户编号=" + userId + ", 用户名=" + userName + ", 登录名=" + loginName + ", 用户密码="
				+ userPassWord + ", 状态=" + status + ", 创建时间=" + createDate
				+ "]";
	}
}
