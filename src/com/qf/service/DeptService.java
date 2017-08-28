package com.qf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qf.entity.Dept;
import com.qf.mapper.IDeptMapper;

/**
 * @ClassName: DeptService
 * @Description: 部门的业务逻辑层
 * @author 赵凯琦
 * @date 2017-7-11 下午10:39:51
 */
@Service
public class DeptService {
	// 注入deptMapper
	@Autowired
	private IDeptMapper mapper;

	public List<Dept> findDeptByName(String deptName) {
		return mapper.findDeptByName(deptName);
	}

	public List<Dept> queryAll() {
		return mapper.queryAll();
	}

	public void deleteDeptById(int deptId) {
		if (deptId <= 0 || "".equals(deptId)) {
			throw new RuntimeException("所输入数据有问题...");
		} else {
			System.out.println("业务逻辑层执行到了" + deptId);
			// User user=
			mapper.deleteDeptById(deptId);
		}
	}

	public Dept findDeptById(int deptId) {
		return mapper.findDeptById(deptId);
	}

	public boolean updateDept(Dept dept) {
		return mapper.updateDept(dept) > 0 ? true : false;
	}

	public boolean add(Dept dept) {
		return mapper.add(dept) > 0 ? true : false;
	}

}
