package com.qf.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qf.entity.Dept;
import com.qf.service.DeptService;

/**
 * @ClassName: DeptController
 * @Description: 处理部门的控制层
 * @author 赵凯琦
 * @date 2017-7-11 下午10:35:27
 */
@Controller
public class DeptController {

	// 注入deptService
	@Autowired
	private DeptService deptService;

	/**
	 * @Title: showAddDept
	 * @Description: 下面是部门的添加跳转
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/showAddDept")
	public String showAddDept() {
		return "dept/showAddDept";
	}

	/**
	 * @Title: showUpdateDept
	 * @Description: 下面是部门的查询跳转
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/showUpdateDept")
	public String showUpdateDept(Model model, Dept dept,
			HttpServletRequest request) {
		String dId = request.getParameter("deptId");
		int deptId = Integer.parseInt(dId);
		Dept dept2 = deptService.findDeptById(deptId);
		request.getSession().setAttribute("dept2", dept2);
		return "dept/showUpdateDept";
	}

	/**
	 * @Title: selectDept
	 * @Description: 下面是查询部门
	 * @param @param model
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/selectDept")
	public String selectDept(Model model, HttpServletRequest request) {
		String deptName = request.getParameter("deptName");
		System.out.println("======================");
		System.out.println("查询部门的方法体执行进来了");
		System.out.println("======================");
		// 通过用户名查找用户的方法
		if (deptName != null && !"".equals(deptName)) {
			List<Dept> depts = deptService.findDeptByName(deptName);
			model.addAttribute("depts", depts);
			return "dept/dept";
		} else {
			List<Dept> depts = deptService.queryAll();
			model.addAttribute("depts", depts);
			return "dept/dept";
		}
	}

	/**
	 * @Title: removeDept
	 * @Description: 下面是删除部门的方法
	 * @param @param dept
	 * @param @param model
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/removeDept")
	public String removeDept(Dept dept, Model model, HttpServletRequest request) {
		String ids = request.getParameter("ids");
		System.out.println(ids);
		String[] strs = ids.split(",");
		int[] nums = new int[strs.length];
		for (int i = 0; i < strs.length; i++) {
			if (strs[i] != null) {
				nums[i] = Integer.parseInt(strs[i].trim());
				deptService.deleteDeptById(nums[i]);
			}
		}
		List<Dept> depts = deptService.queryAll();
		// 将用户对象存储到session中
		model.addAttribute("depts", depts);
		return "dept/dept";
	}

	/**
	 * @Title: updateDept
	 * @Description: 下面是修改部门的方法
	 * @param @param dept
	 * @param @param model
	 * @param @param session
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/updateDept")
	public String updateDept(Dept dept, Model model, HttpSession session,
			HttpServletRequest request) {
		if (deptService.updateDept(dept)) {
			System.out.println("控制层成功添加的方法实现了");
			// session.invalidate();
			List<Dept> depts = deptService.queryAll();
			// 将用户对象存储到session中
			model.addAttribute("depts", depts);
			// model.addAttribute("msg3", "恭喜你！修改成功！请重新登录！");
			return "dept/dept";
		} else {
			model.addAttribute("msg2", "对不起，修改有误，请核对信息！");
			return "dept/showUpdateDept";
		}
	}

	/**
	 * @Title: save
	 * @Description: 下面是添加部门的方法
	 * @param @param dept
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/addDept")
	public String save(Dept dept, Model model) {
		System.out.println(dept);
		if (deptService.add(dept)) {
			System.out.println("控制层成功添加的方法实现了");
			List<Dept> depts = deptService.queryAll();
			// 将用户对象存储到session中
			model.addAttribute("depts", depts);
			return "dept/dept";
		} else {
			model.addAttribute("msg2", "添加用户失败，请核正信息！");
			return "dept/showAddDept";
		}
	}
}
