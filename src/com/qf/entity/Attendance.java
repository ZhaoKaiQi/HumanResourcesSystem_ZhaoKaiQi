package com.qf.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * @ClassName: Attendance
 * @Description: 签到表
 * @author 赵凯琦
 * @date 2017-7-10 上午2:53:58
 */
public class Attendance {
	private int atdId;// 序号
	//@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private String atdCreateTime;// 时间
	private int atdFlag;// 是否有效：1有效2无效
	private User user;// 外键

	public int getAtdId() {
		return atdId;
	}

	public void setAtdId(int atdId) {
		this.atdId = atdId;
	}

	public String getAtdCreateTime() {
		return atdCreateTime;
	}

	public void setAtdCreateTime(String atdCreateTime) {
		this.atdCreateTime = atdCreateTime;
	}

	public int getAtdFlag() {
		return atdFlag;
	}

	public void setAtdFlag(int atdFlag) {
		this.atdFlag = atdFlag;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Attendance(int atdId, String atdCreateTime, int atdFlag, User user) {
		super();
		this.atdId = atdId;
		this.atdCreateTime = atdCreateTime;
		this.atdFlag = atdFlag;
		this.user = user;
	}

	public Attendance() {
		super();
	}

	@Override
	public String toString() {
		return "Attendance [序号=" + atdId + ", 签到时间=" + atdCreateTime
				+ ", 是否有效=" + atdFlag + ", user=" + user + "]";
	}

}
