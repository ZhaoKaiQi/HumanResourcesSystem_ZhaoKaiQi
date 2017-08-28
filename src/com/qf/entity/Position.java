package com.qf.entity;

/**
 * @ClassName: Position
 * @Description: 职位表
 * @author 赵凯琦
 * @date 2017-7-10 上午3:00:04
 */
public class Position {
	private int posId;// 序号
	private String posName;// 名称
	private String posRemark;// 说明

	public int getPosId() {
		return posId;
	}

	public void setPosId(int posId) {
		this.posId = posId;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getPosRemark() {
		return posRemark;
	}

	public void setPosRemark(String posRemark) {
		this.posRemark = posRemark;
	}

	public Position(int posId, String posName, String posRemark) {
		super();
		this.posId = posId;
		this.posName = posName;
		this.posRemark = posRemark;
	}

	public Position() {
		super();
	}

	@Override
	public String toString() {
		return "编号:" + posId + ", 职位名:" + posName + ", 说明:"
				+ posRemark;
	}

}
