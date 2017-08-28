package com.qf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.qf.entity.Attendance;

/**
 * @ClassName: IAttendanceMapper
 * @Description: 签到
 * @author 赵凯琦
 * @date 2017-7-15 下午9:13:05
 */
public interface IAttendanceMapper {

	/**
	 * @Title: findAttendanceByTime
	 * @Description: 查询大于atdCreateTimeStart1签到的所有数据
	 * @param @param atdCreateTimeStart1
	 * @param @return
	 * @return List<Attendance>
	 * @throws
	 */
	@Select("select * from attendance AS a JOIN USER AS b ON a.userId=b.userId where atdCreateTime>=#{param1} ORDER BY atdCreateTime")
	@Results({
		//对应用户
		@Result(property="user.userId",column="userId"),
		@Result(property="user.userName",column="userName"),
		@Result(property="user.loginName",column="loginName"),
		@Result(property="user.userPassWord",column="userPassWord"),
		@Result(property="user.status",column="status"),
		@Result(property="user.createDate",column="createDate"),
	})
	List<Attendance> findAttendanceByTime(String atdCreateTimeStart);

	/**
	 * @Title: findAttendanceByEndTime
	 * @Description: 查询小于atdCreateTimeStart2签到的所有数据
	 * @param @param atdCreateTimeStart2
	 * @param @return
	 * @return List<Attendance>
	 * @throws
	 */
	@Select("select * from attendance AS a JOIN USER AS b ON a.userId=b.userId where atdCreateTime<#{param1} ORDER BY atdCreateTime")
	@Results({
		//对应用户
		@Result(property="user.userId",column="userId"),
		@Result(property="user.userName",column="userName"),
		@Result(property="user.loginName",column="loginName"),
		@Result(property="user.userPassWord",column="userPassWord"),
		@Result(property="user.status",column="status"),
		@Result(property="user.createDate",column="createDate"),
	})
	List<Attendance> findAttendanceByEndTime(String atdCreateTimeEnd);

	/**
	 * @Title: findAttendanceByMiddleTime
	 * @Description: 查询时间段内签到的所有数据
	 * @param @param atdCreateTimeStart1
	 * @param @param atdCreateTimeStart2
	 * @param @return
	 * @return List<Attendance>
	 * @throws
	 */
	@Select("select * from attendance AS a JOIN USER AS b ON a.userId=b.userId where atdCreateTime>#{param1} and atdCreateTime<#{param2} ORDER BY atdCreateTime")
	@Results({
		//对应用户
		@Result(property="user.userId",column="userId"),
		@Result(property="user.userName",column="userName"),
		@Result(property="user.loginName",column="loginName"),
		@Result(property="user.userPassWord",column="userPassWord"),
		@Result(property="user.status",column="status"),
		@Result(property="user.createDate",column="createDate"),
	})
	List<Attendance> findAttendanceByMiddleTime(String atdCreateTimeStart,
			String atdCreateTimeEnd);

	/**
	 * @Title: queryAll
	 * @Description: 查询全部
	 * @param @return
	 * @return List<Attendance>
	 * @throws
	 */
	@Select("select * from attendance AS a JOIN USER AS b ON a.userId=b.userId ORDER BY atdCreateTime")
	@Results({
		//对应用户
		@Result(property="user.userId",column="userId"),
		@Result(property="user.userName",column="userName"),
		@Result(property="user.loginName",column="loginName"),
		@Result(property="user.userPassWord",column="userPassWord"),
		@Result(property="user.status",column="status"),
		@Result(property="user.createDate",column="createDate"),
	})
	List<Attendance> queryAll();

	/**
	 * @Title: addAtt
	 * @Description: 添加签到记录
	 * @param @param attendance
	 * @return void
	 * @throws
	 */
	@Insert("insert into attendance(atdCreateTime,atdFlag,userId) values(#{atdCreateTime},#{atdFlag},#{user.userId})")
	void addAtt(Attendance attendance);
	
	/**
	 * @Title: findAttendanceById 
	 * @Description: 通过Id查找出席记录
	 * @param @param i
	 * @param @return 
	 * @return Attendance
	 * @throws
	 */
	@Select("SELECT atdCreateTime FROM attendance WHERE atdId=#{param1}")
	String findAttendanceById(int atdId);

	/**
	 * @Title: findAttendanceBytime
	 * @Description: 查询时间段内签到的所有数据
	 * @param @param atdCreateTimeStart1
	 * @param @param atdCreateTimeStart2
	 * @param @return
	 * @return List<Attendance>
	 * @throws
	 */
	@Select("select atdCreateTime from attendance where atdCreateTime>=#{param1}")
	List<String> findAttendanceBytime(String atdDate);

}
