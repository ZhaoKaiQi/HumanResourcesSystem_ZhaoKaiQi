package com.qf.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qf.entity.Dept;
import com.qf.entity.Employee;
import com.qf.entity.Position;
import com.qf.entity.User;
import com.qf.mapper.IEmployeeMapper;
import com.qf.service.DeptService;
import com.qf.service.EmployeeService;
import com.qf.service.PositionService;
import com.qf.service.UserService;
import com.qf.utils.MyBatisUtils;

/**
 * @ClassName: EmployeeController
 * @Description: 处理员工的控制层
 * @author 赵凯琦
 * @date 2017-7-11 下午10:35:27
 */
@Controller
public class EmployeeController {

	// 注入EmployeeService
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DeptService deptService;

	@Autowired
	private PositionService positionService;

	@Autowired
	private UserService userService;

	/**
	 * @Title: showAddEmployee
	 * @Description: 下面是员工的添加跳转
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/showAddEmployee")
	public String showAddEmployee(Model model, HttpServletRequest request) {
		List<Position> positions = positionService.queryAll();
		System.out.println("position为：" + positions);
		System.out.println("=========showAddEmployee执行了的=========");
		model.addAttribute("positions", positions);
		List<Dept> depts = deptService.queryAll();
		model.addAttribute("depts", depts);
		return "employee/showAddEmployee";
	}

	/**
	 * @Title: showUpdateEmployee
	 * @Description: 下面是员工的查询跳转
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/showUpdateEmployee")
	public String showUpdateEmployee(Model model, Employee employee,
			HttpServletRequest request) {
		// 查询出所有职位信息发送到前端
		List<Position> positions = positionService.queryAll();
		model.addAttribute("positions", positions);
		// 查询出所有部门信息发送到前端
		List<Dept> depts = deptService.queryAll();
		model.addAttribute("depts", depts);

		String eId = request.getParameter("empId");
		int empId = Integer.parseInt(eId);
		System.out.println("这个员工的跳转showUpdateEmployee方法" + empId);
		Employee employee2 = employeeService.findEmployeeById(empId);
		System.out.println("为发送到修改页面而查出来的员工结果为：" + employee2);
		request.getSession().setAttribute("employee2", employee2);
		return "employee/showUpdateEmployee";
	}

	/**
	 * @Title: selectEmployee
	 * @Description: 下面是查询员工
	 * @param @param model
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/selectEmployee")
	public String selectEmployee(Employee employee, Model model,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		// 获取姓名
		String empName = request.getParameter("empName");
		// 获取身份证号码
		String empCardId = request.getParameter("empCardId");
		// 获取性别
		String empSex = request.getParameter("empSex");
		// 获取position对象
		String posId = request.getParameter("posId");
		// 获取dept对象
		String deptId = request.getParameter("deptId");
		// 获取电话
		String empTel = request.getParameter("empTel");

		System.out.println("======================");
		System.out.println("查询员工的方法体执行进来了【" + "电话：" + empTel + "，员工名："
				+ empName + "，性别：" + empSex + "，部门Id：" + deptId + "，职位Id："
				+ posId + "，身份证：" + empCardId + "】");
		System.out.println("======================");
		if ((empName != null && !"".equals(empName))
				|| (empCardId != null && !"".equals(empCardId))
				|| (empTel != null && !"".equals(empTel))
				|| (empSex != null && !"".equals(empSex))
				|| (posId != null && !"".equals(posId))
				|| (deptId != null && !"".equals(deptId))) {
			// 添加属性
			map.put("empName", empName);
			map.put("empTel", empTel);
			map.put("empSex", empSex);
			map.put("deptId", deptId);
			map.put("empCardId", empCardId);
			map.put("posId", posId);
			// System.out.println("里面的方法执行了");
			IEmployeeMapper mapper = MyBatisUtils.getSession().getMapper(
					IEmployeeMapper.class);
			List<Employee> employees = employeeService.findAllByWhat(map);
			System.out.println("查询结果：" + employees);
			model.addAttribute("employees", employees);
			System.out.println("外面的方法执行了");
			List<Position> positions = positionService.queryAll();
			System.out.println("position为：" + positions);
			model.addAttribute("positions", positions);
			List<Dept> depts = deptService.queryAll();
			model.addAttribute("depts", depts);
			MyBatisUtils.commit();
			MyBatisUtils.close();
			return "employee/employee";
		} else {
			System.out.println("外面的方法执行了");
			List<Position> positions = positionService.queryAll();
			System.out.println("position为：" + positions);
			model.addAttribute("positions", positions);
			List<Dept> depts = deptService.queryAll();
			model.addAttribute("depts", depts);

			List<Employee> employees = employeeService.queryAll();
			System.out.println("employee:" + employees);
			model.addAttribute("employees", employees);
			return "employee/employee";
		}
	}

	/**
	 * @Title: removeEmployee
	 * @Description: 下面是删除员工的方法
	 * @param @param employee
	 * @param @param model
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/removeEmployee")
	public String removeEmployee(Employee employee, Model model,
			HttpServletRequest request) {
		String ids = request.getParameter("ids");
		System.out.println(ids);
		String[] strs = ids.split(",");
		int[] nums = new int[strs.length];
		for (int i = 0; i < strs.length; i++) {
			if (strs[i] != null) {
				nums[i] = Integer.parseInt(strs[i].trim());
				employeeService.deleteEmployeeById(nums[i]);
			}
		}
		List<Employee> employees = employeeService.queryAll();
		// 将用户对象存储到session中
		model.addAttribute("employees", employees);
		return "employee/employee";
	}

	/**
	 * @throws ParseException
	 * @Title: updateEmployee
	 * @Description: 下面是修改员工的方法
	 * @param @param employee
	 * @param @param model
	 * @param @param session
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/updateEmployee")
	public String updateEmployee(Employee employee, Model model,
			HttpSession session, HttpServletRequest request)
			throws ParseException {
		System.out.println("从页面获取的employee对象为：" + employee);
		// 获取前端页面对应的empId
		String ei = request.getParameter("empId");
		int empId = Integer.parseInt(ei);
		employee.setEmpId(empId);
		// 获取性别
		String es = request.getParameter("empSex");
		int empSex = Integer.parseInt(es);
		employee.setEmpSex(empSex);
		// 获取userId
		String ui = request.getParameter("userId");
		int userId = Integer.parseInt(ui);
		System.out.println("userId为：" + userId);
		User user = userService.findUserById(userId);
		employee.setUser(user);
		// 获取posId
		String ps = request.getParameter("posId");
		int posId = Integer.parseInt(ps);
		System.out.println("posId为：" + posId);
		Position position = positionService.findPositionById(posId);
		employee.setPosition(position);
		// 获取deptId
		String dt = request.getParameter("deptId");
		int deptId = Integer.parseInt(dt);
		System.out.println("deptId为：" + deptId);
		Dept dept = deptService.findDeptById(deptId);
		employee.setDept(dept);
		// 执行修改
		System.out.println("修改方法中改变数据格式后的员工链表为：" + employee);
		if (employeeService.updateEmployee(employee)) {
			System.out.println("控制层成功添加的方法实现了");
			List<Employee> employees = employeeService.queryAll();
			// 将用户对象存储到session中
			model.addAttribute("employees", employees);
			List<Position> positions = positionService.queryAll();
			model.addAttribute("positions", positions);
			List<Dept> depts = deptService.queryAll();
			model.addAttribute("depts", depts);
			// model.addAttribute("msg3", "恭喜你！修改成功！请重新登录！");
			return "employee/employee";
		} else {
			model.addAttribute("msg2", "对不起，修改有误，请核对信息！");
			return "employee/showUpdateEmployee";
		}
	}

	/**
	 * @Title: save
	 * @Description: 下面是添加员工的方法
	 * @param @param employee
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/addEmployee")
	public String save(Employee employee, Model model,
			HttpServletRequest request) {
		// 设置创建员工的建档时间
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String empCreateDate = dateFormat.format(new Date());
		employee.setEmpCreateDate(empCreateDate);
		// 替当前员工创建用户
		User user = new User();
		// 设置员工账户的初始信息，员工在以后拿到用户账号以后，可以自行更改相关信息（这个账户是公司分配的，不能注册）
		user.setUserName(employee.getEmpEmail());
		user.setLoginName(employee.getEmpTel());
		Date eDate;
		try {
			eDate = dateFormat.parse(empCreateDate);
			user.setCreateDate(eDate);
		} catch (ParseException e) {
			System.out.println("设置用户创建时间出现异常：" + e.getMessage());
		}
		user.setStatus(0);
		user.setUserPassWord(employee.getEmpTel());
		System.out.println("看一下创建出来的user信息：" + user);
		userService.add(user);
		// 获取部门Id
		String dId = request.getParameter("deptId");
		int deptId = Integer.parseInt(dId);
		// 获取职位Id
		String pId = request.getParameter("posId");
		int posId = Integer.parseInt(pId);
		// 将职位和部门的对象通过外键存入员工表
		employee.setUser(userService.queryUserByLoginName(employee.getEmpTel()));
		// 将user添加到employee列表中
		System.out.println("每个新员工的默认账户为："
				+ userService.queryUserByLoginName(employee.getEmpTel()));
		employee.setDept(deptService.findDeptById(deptId));
		employee.setPosition(positionService.findPositionById(posId));
		System.out.println(employee);
		if (employeeService.add(employee)) {
			List<Position> positions = positionService.queryAll();
			System.out.println("position为：" + positions);
			model.addAttribute("positions", positions);
			List<Dept> depts = deptService.queryAll();
			model.addAttribute("depts", depts);
			System.out.println("控制层成功添加的方法实现了");
			List<Employee> employees = employeeService.queryAll();
			// 将用户对象存储到session中
			model.addAttribute("employees", employees);
			return "employee/employee";
		} else {
			model.addAttribute("msg2", "添加用户失败，请核正信息！");
			return "employee/showAddEmployee";
		}
	}
}
