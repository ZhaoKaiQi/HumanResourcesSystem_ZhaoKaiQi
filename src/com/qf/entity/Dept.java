package com.qf.entity;

/**
 * @ClassName: Dept
 * @Description: 部门表
 * @author 赵凯琦
 * @date 2017-7-10 上午3:00:44
 */
public class Dept {
	private int deptId;// 序号
	private String deptName;// 名称
	private String deptRemark;// 说明

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptRemark() {
		return deptRemark;
	}

	public void setDeptRemark(String deptRemark) {
		this.deptRemark = deptRemark;
	}

	public Dept(int deptId, String deptName, String deptRemark) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.deptRemark = deptRemark;
	}

	public Dept() {
		super();
	}

	@Override
	public String toString() {
		return "部门编号:" + deptId + ", 部门名称:" + deptName + ", 说明:"
				+ deptRemark;
	}

}
