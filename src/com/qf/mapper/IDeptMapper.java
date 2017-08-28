package com.qf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qf.entity.Dept;

/**
 * @ClassName: IDeptMapper
 * @Description: Dept的映射文件接口
 * @author 赵凯琦
 * @date 2017-7-11 下午10:38:10
 */
public interface IDeptMapper {

	@Select("select * from dept where deptName=#{param1}")
	@ResultType(Dept.class)
	List<Dept> findDeptByName(String deptName);

	@Select("select * from dept order by deptId DESC")
	@ResultType(Dept.class)
	List<Dept> queryAll();

	@Delete("delete from dept where deptId=#{param1}")
	void deleteDeptById(int deptId);

	@Select("select * from dept where deptId=#{param1}")
	Dept findDeptById(int deptId);

	@Update("update dept set deptName=#{deptName},deptRemark=#{deptRemark} where deptId=#{deptId}")
	int updateDept(Dept dept);

	@Insert("insert into dept(deptName,deptRemark) values(#{deptName},#{deptRemark})")
	/* @Options(useGeneratedKeys = true, keyProperty = "id") */
	int add(Dept dept);
}
