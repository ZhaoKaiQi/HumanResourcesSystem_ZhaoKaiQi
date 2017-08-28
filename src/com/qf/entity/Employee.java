package com.qf.entity;

/**
 * @ClassName: Employee
 * @Description: 员工表
 * @author 赵凯琦
 * @date 2017-7-10 上午2:55:00
 */
public class Employee {
	private int empId;// 序号
	private String empName;// 姓名
	private String empCardId;// 身份证
	private String empAddress;// 住址
	private String empPostCode;// 邮编
	private String empTel;// 手机号
	private String empPhone;//电话号
	private String empQQNum;// QQ号
	private String empEmail;// 邮箱
	private int empSex;// 性别
	private String empParty;// 政治面貌
	private String empBirthday;// 生日
	private String empRace;// 民族
	private String empEducation;// 学历
	private String empSpeciality;// 专业
	private String empHobby;// 爱好
	private String empRemark;// 备注
	private String empCreateDate;// 创建时间
	private User user;// 用户表主键
	private Dept dept;// 部门表主键
	private Position position;// 职位表主键

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpCardId() {
		return empCardId;
	}

	public void setEmpCardId(String empCardId) {
		this.empCardId = empCardId;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	public String getEmpPostCode() {
		return empPostCode;
	}

	public void setEmpPostCode(String empPostCode) {
		this.empPostCode = empPostCode;
	}

	public String getEmpTel() {
		return empTel;
	}

	public void setEmpTel(String empTel) {
		this.empTel = empTel;
	}

	public String getEmpQQNum() {
		return empQQNum;
	}

	public void setEmpQQNum(String empQQNum) {
		this.empQQNum = empQQNum;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public int getEmpSex() {
		return empSex;
	}

	public void setEmpSex(int empSex) {
		this.empSex = empSex;
	}

	public String getEmpParty() {
		return empParty;
	}

	public void setEmpParty(String empParty) {
		this.empParty = empParty;
	}

	public String getEmpBirthday() {
		return empBirthday;
	}

	public void setEmpBirthday(String empBirthday) {
		//DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//this.empBirthday = dateFormat.format(empBirthday);
		this.empBirthday=empBirthday;
	}

	public String getEmpRace() {
		return empRace;
	}

	public void setEmpRace(String empRace) {
		this.empRace = empRace;
	}

	public String getEmpEducation() {
		return empEducation;
	}

	public void setEmpEducation(String empEducation) {
		this.empEducation = empEducation;
	}

	public String getEmpSpeciality() {
		return empSpeciality;
	}

	public void setEmpSpeciality(String empSpeciality) {
		this.empSpeciality = empSpeciality;
	}

	public String getEmpHobby() {
		return empHobby;
	}

	public void setEmpHobby(String empHobby) {
		this.empHobby = empHobby;
	}

	public String getEmpRemark() {
		return empRemark;
	}

	public void setEmpRemark(String empRemark) {
		this.empRemark = empRemark;
	}

	public String getEmpCreateDate() {
		return empCreateDate;
	}

	public void setEmpCreateDate(String empCreateDate) {
		this.empCreateDate = empCreateDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public Employee(int empId, String empName, String empCardId,
			String empAddress, String empPostCode, String empTel,String empPhone,
			String empQQNum, String empEmail, int empSex, String empParty,
			String empBirthday, String empRace, String empEducation,
			String empSpeciality, String empHobby, String empRemark,
			String empCreateDate, User user, Dept dept, Position position) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empCardId = empCardId;
		this.empAddress = empAddress;
		this.empPostCode = empPostCode;
		this.empTel = empTel;
		this.empQQNum = empQQNum;
		this.empEmail = empEmail;
		this.empSex = empSex;
		this.empParty = empParty;
		this.empBirthday = empBirthday;
		this.empRace = empRace;
		this.empEducation = empEducation;
		this.empSpeciality = empSpeciality;
		this.empHobby = empHobby;
		this.empRemark = empRemark;
		this.empCreateDate = empCreateDate;
		this.user = user;
		this.dept = dept;
		this.position = position;
		this.empPhone=empPhone;
	}

	public Employee() {
		super();
	}

	@Override
	public String toString() {
		return "Employee [员工编号=" + empId + ", 姓名=" + empName + ", 身份证="
				+ empCardId + ", 住址=" + empAddress + ", 邮编=" + empPostCode
				+ ", 手机号码=" + empTel +", 电话号码=" + empPhone + ", QQ号=" + empQQNum + ", 邮箱=" + empEmail
				+ ", 性别=" + empSex + ", 政治面貌=" + empParty + ", 生日="
				+ empBirthday + ", 民族=" + empRace + ", 学历=" + empEducation
				+ ", 专业=" + empSpeciality + ", 兴趣爱好=" + empHobby + ", 备注="
				+ empRemark + ", 创建时间=" + empCreateDate + ", 用户=" + user
				+ ", 部门=" + dept + ", 职位=" + position + "]";
	}

}
