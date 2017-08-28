package com.qf.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.qf.entity.Employee;
import com.qf.provider.IEmployeeProvider;

/**
 * @ClassName: IEmployeeMapper
 * @Description: Employee的映射文件接口
 * @author 赵凯琦
 * @date 2017-7-11 下午10:38:10
 */
public interface IEmployeeMapper {

	@Select("select * from employee where empName=#{param1}")
	@ResultType(Employee.class)
	List<Employee> findEmployeeByName(String empName);

	@Select("select * from employee AS a JOIN USER AS b ON a.userId=b.userId JOIN dept AS c ON a.deptId=c.deptId JOIN POSITION AS d ON a.posId=d.posId where empId=#{param1}")
	@Results({
		//对应用户
		@Result(property="user.userId",column="userId"),
		@Result(property="user.userName",column="userName"),
		@Result(property="user.loginName",column="loginName"),
		@Result(property="user.userPassWord",column="userPassWord"),
		@Result(property="user.status",column="status"),
		@Result(property="user.createDate",column="createDate"),
		//对应职位表
		@Result(property="position.posId",column="posId"),
		@Result(property="position.posName",column="posName"),
		@Result(property="position.posRemark",column="posRemark"),
		//对应部门
		@Result(property="dept.deptId",column="deptId"),
		@Result(property="dept.deptName",column="deptName"),
		@Result(property="dept.deptRemark",column="deptRemark"),
	})
	Employee findEmployeeById(int empId);
	
	@Select("SELECT * FROM employee AS a JOIN USER AS b ON a.userId=b.userId JOIN dept AS c ON a.deptId=c.deptId JOIN POSITION AS d ON a.posId=d.posId")
	@Results({
		//对应用户
		@Result(property="user.userId",column="userId"),
		@Result(property="user.userName",column="userName"),
		@Result(property="user.loginName",column="loginName"),
		@Result(property="user.userPassWord",column="userPassWord"),
		@Result(property="user.status",column="status"),
		@Result(property="user.createDate",column="createDate"),
		//对应职位表
		@Result(property="position.posId",column="posId"),
		@Result(property="position.posName",column="posName"),
		@Result(property="position.posRemark",column="posRemark"),
		//对应部门
		@Result(property="dept.deptId",column="deptId"),
		@Result(property="dept.deptName",column="deptName"),
		@Result(property="dept.deptRemark",column="deptRemark"),
	})
	//@ResultMap("com.qf.mapper.IEmployeeMapper.gsonetomany")
	//@ResultType(Employee.class)
	List<Employee> queryAll();
	
	@Delete("delete from employee where empId=#{param1}")
	void deleteEmployeeById(int empId);

	@Update("update employee set empName=#{empName},empCardId=#{empCardId},empSex=#{empSex},empEducation=#{empEducation},empEmail=#{empEmail},empTel=#{empTel},empPhone=#{empPhone},empParty=#{empParty},empQQNum=#{empQQNum},empAddress=#{empAddress},empPostCode=#{empPostCode},empBirthday=#{empBirthday},empRace=#{empRace},empSpeciality=#{empSpeciality},empHobby=#{empHobby},empRemark=#{empRemark},userId=#{user.userId},posId=#{position.posId},deptId=#{dept.deptId} where empId=#{empId}")
	int updateEmployee(Employee employee);

	@Insert("insert into employee(empName,empCardId,empSex,posId,empEducation,empEmail,empTel,empPhone,empParty,empQQNum,empAddress,empPostCode,empBirthday,empRace,empSpeciality,empHobby,empRemark,deptId,empCreateDate,userId) values(#{empName},#{empCardId},#{empSex},#{position.posId},#{empEducation},#{empEmail},#{empTel},#{empPhone},#{empParty},#{empQQNum},#{empAddress},#{empPostCode},#{empBirthday},#{empRace},#{empSpeciality},#{empHobby},#{empRemark},#{dept.deptId},#{empCreateDate},#{user.userId})")
	/* @Options(useGeneratedKeys = true, keyProperty = "id") */
	int add(Employee employee);

	@SelectProvider(type=IEmployeeProvider.class,method="select")
	@Results({
		//对应用户
		@Result(property="user.userId",column="userId"),
		@Result(property="user.userName",column="userName"),
		@Result(property="user.loginName",column="loginName"),
		@Result(property="user.userPassWord",column="userPassWord"),
		@Result(property="user.status",column="status"),
		@Result(property="user.createDate",column="createDate"),
		//对应职位表
		@Result(property="position.posId",column="posId"),
		@Result(property="position.posName",column="posName"),
		@Result(property="position.posRemark",column="posRemark"),
		//对应部门
		@Result(property="dept.deptId",column="deptId"),
		@Result(property="dept.deptName",column="deptName"),
		@Result(property="dept.deptRemark",column="deptRemark"),
	})
	List<Employee> select(Map<String, Object> map);
}
