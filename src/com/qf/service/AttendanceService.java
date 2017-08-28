package com.qf.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qf.entity.Attendance;
import com.qf.mapper.IAttendanceMapper;

/**
 * @ClassName: AttendanceService
 * @Description: 签到
 * @author 赵凯琦
 * @date 2017-7-15 下午9:13:53
 */
@Service
public class AttendanceService {
	// 注入IAttendanceMapper
	@Autowired
	private IAttendanceMapper mapper;

	/**
	 * @Title: findAttendanceByTime
	 * @Description: 查询atdCreateTimeStart1以后的所有数据
	 * @param @param atdCreateTimeStart1
	 * @param @return
	 * @return List<Attendance>
	 * @throws
	 */
	public List<Attendance> findAttendanceByTime(String atdCreateTimeStart) {
		return mapper.findAttendanceByTime(atdCreateTimeStart);
	}

	/**
	 * @Title: findAttendanceByEndTime
	 * @Description: 查询小于atdCreateTimeStart2签到的所有数据
	 * @param @param atdCreateTimeStart2
	 * @param @return
	 * @return List<Attendance>
	 * @throws
	 */
	public List<Attendance> findAttendanceByEndTime(String atdCreateTimeEnd) {
		return mapper.findAttendanceByEndTime(atdCreateTimeEnd);
	}

	/**
	 * @Title: findAttendanceByMiddleTime
	 * @Description: 查询时间段内签到的所有数据
	 * @param @param atdCreateTimeStart1
	 * @param @param atdCreateTimeStart2
	 * @param @return
	 * @return List<Attendance>
	 * @throws
	 */
	public List<Attendance> findAttendanceByMiddleTime(
			String atdCreateTimeStart, String atdCreateTimeEnd) {
		return mapper.findAttendanceByMiddleTime(atdCreateTimeStart,
				atdCreateTimeEnd);
	}

	/**
	 * @Title: queryAll
	 * @Description: 查询全部
	 * @param @return
	 * @return List<Attendance>
	 * @throws
	 */
	public List<Attendance> queryAll() {
		return mapper.queryAll();
	}
/**
 * @Title: addAtt 
 * @Description: 添加签到记录
 * @param @param attendance 
 * @return void
 * @throws
 */
	public void addAtt(Attendance attendance) {
mapper.addAtt(attendance);	
	}
/**
 * @Title: findAttendanceById 
 * @Description: 通过Id查找出席记录
 * @param @param i
 * @param @return 
 * @return Attendance
 * @throws
 */
public String findAttendanceById(int atdId) {
	return mapper.findAttendanceById(atdId);
}

/**
 * @Title: findAttendanceBytime 
 * @Description: TODO
 * @param @param atdDate
 * @param @return 
 * @return String
 * @throws
 */
public List<String> findAttendanceBytime(String atdDate) {
	return mapper.findAttendanceBytime( atdDate);
}

	
}
