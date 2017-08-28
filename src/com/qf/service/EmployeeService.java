package com.qf.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qf.entity.Employee;
import com.qf.mapper.IEmployeeMapper;

/**
 * @ClassName: EmployeeService
 * @Description: 员工的业务逻辑层
 * @author 赵凯琦
 * @date 2017-7-11 下午10:39:51
 */
@Service
public class EmployeeService {
	// 注入IEmployeeMapper
	@Autowired
	private IEmployeeMapper mapper;

	public List<Employee> findEmployeeByName(String empName) {
		return mapper.findEmployeeByName(empName);
	}

	public List<Employee> queryAll() {
		return mapper.queryAll();
	}

	public void deleteEmployeeById(int empId) {
		if (empId <= 0 || "".equals(empId)) {
			throw new RuntimeException("所输入数据有问题...");
		} else {
			System.out.println("业务逻辑层执行到了" + empId);
			// User user=
			mapper.deleteEmployeeById(empId);
		}
	}

	public Employee findEmployeeById(int empId) {
		return mapper.findEmployeeById(empId);
	}

	public boolean updateEmployee(Employee employee) {
		return mapper.updateEmployee(employee) > 0 ? true : false;
	}

	public boolean add(Employee employee) {
		return mapper.add(employee) > 0 ? true : false;
	}

	public List<Employee> findAllByWhat(Map<String, Object> map) {
		return mapper.select(map);
	}

}
