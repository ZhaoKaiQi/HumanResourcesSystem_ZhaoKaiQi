package com.qf.controller;

import java.util.Date;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qf.entity.User;
import com.qf.service.UserService;

/**
 * @ClassName: UserController
 * @Description: 用户界面处理的控制层
 * @author 赵凯琦
 * @date 2017-7-10 下午11:04:22
 */
@Controller
public class UserController {

	// 注入userService
	@Autowired
	private UserService service;

	/**
	 * @Title: hello
	 * @Description: 动态获取路径
	 * @param @param jn
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/{jn}")
	public String hello(@PathVariable String jn) {
		return jn;
	}

	/**
	 * @Title: checkLoginNameAndPwd
	 * @Description: 处理登录
	 * @param @param model
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/login")
	public String checkLoginNameAndPwd(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		// 从前端界面获取输入的登录名和密码
		String loginName = request.getParameter("loginname");
		String userPassWord = request.getParameter("password");
		System.out.println("获取到了前端页面传来的值，分别为：" + loginName + userPassWord);
		// 校验输入的登录名和密码是否正确
		int queryResult = service.findUserByNameAndPwd(loginName, userPassWord);
		System.out.println("判断结果为：" + queryResult);
		if (queryResult == 0) {
			// 如果不正确，打印提示信息，并返回登录界面
			model.addAttribute("msg", "用户名或密码有误，请重新输入！");
			return "loginForm";
		} else {
			// 如果正确，则通过登录名查找对应的用户对象
			User user = service.queryUserByLoginName(loginName);
			// 将用户对象存储到session中
			request.getSession().setAttribute("user", user);
			// 测试session中是否存入了查询出来的用户
			System.out.println(request.getSession().getAttribute("user"));
			// 通过重定向的方法跳转到主页面（main）
			return "redirect:redirect";
		}
	}

	/**
	 * @Title: loginSucess
	 * @Description: 重定向跳转到主页面的方法
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/redirect")
	public String loginSucess() {
		return "main";
	}

	@RequestMapping("/rush")
	public String rush(HttpServletRequest request, HttpSession session) {
		User user1 = (User) request.getSession().getAttribute("user");
		if (user1 == null || "".equals(user1)) {
			return "redirect:redirect";
		} else {
			int userId = user1.getUserId();
			System.out.println("获取到了userId的值是：" + userId);
			session.removeAttribute("user");
			// int userId=Integer.parseInt();
			User user = service.findUserById(userId);
			request.getSession().setAttribute("user", user);
			System.out.println("最新的user的信息是(这句执行说明新数据存到session了)："
					+ request.getSession().getAttribute("user2"));
			return "redirect:redirect";
		}
	}

	/**
	 * @Title: go
	 * @Description: 跳转到登录页面的方法
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/index")
	public String go() {
		return "redirect:loginForm";
	}

	/**
	 * @Title: distroy
	 * @Description: 注销session的方法
	 * @param @param session
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/logout")
	public String distroy(HttpSession session) {
		// 销毁session
		session.invalidate();
		return "redirect:loginForm";
	}

	/**
	 * @Title: showUpdateUser
	 * @Description: 下面是用户的查询跳转
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/showUpdateUser")
	public String showUpdateUser(Model model, User user,
			HttpServletRequest request) {
		String uId = request.getParameter("userId");
		int userId = Integer.parseInt(uId);
		User user2 = service.findUserById(userId);
		request.getSession().setAttribute("user2", user2);
		return "user/showUpdateUser";
	}

	/**
	 * @Title: showAddUser
	 * @Description: 下面是用户的添加跳转
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/showAddUser")
	public String showAddUser() {
		return "user/showAddUser";
	}

	/**
	 * @Title: save
	 * @Description: 添加用户的方法
	 * @param @param user
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/addUser")
	public String save(User user, Model model) {
		user.setCreateDate(new Date(System.currentTimeMillis()));
		System.out.println(user);
		if (service.add(user)) {
			System.out.println("控制层成功添加的方法实现了");
			List<User> users = service.queryAll();
			// 将用户对象存储到session中
			model.addAttribute("users", users);
			return "user/user";
		} else {
			model.addAttribute("msg2", "添加用户失败，请核正信息！");
			return "user/showAddUser";
		}
	}

	/**
	 * @Title: removeUser
	 * @Description: 下面是删除用户的方法
	 * @param @param user
	 * @param @param model
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/removeUser")
	public String removeUser(User user, Model model, HttpServletRequest request) {
		String ids = request.getParameter("ids");
		System.out.println(ids);
		String[] strs = ids.split(",");
		int[] nums = new int[strs.length];
		for (int i = 0; i < strs.length; i++) {
			if (strs[i] != null) {
				nums[i] = Integer.parseInt(strs[i].trim());
				service.deleteUserById(nums[i]);
			}
		}
		List<User> users = service.queryAll();
		// 将用户对象存储到session中
		model.addAttribute("users", users);
		return "user/user";
	}

	/**
	 * @Title: selectUser
	 * @Description: 下面是查询用户
	 * @param @param model
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/selectUser")
	public String selectUser(Model model, HttpServletRequest request) {
		String userName = request.getParameter("username");
		String sta = request.getParameter("status");
		System.out.println("======================");
		System.out.println("查询用户的方法体执行进来了");
		System.out.println("======================");
		// 通过用户名查找用户的方法
		if ((userName != null && !"".equals(userName))
				&& (sta == null || "".equals(sta))) {
			List<User> users = service.findUserByName(userName);
			model.addAttribute("users", users);
			return "user/user";
			// 通过用户状态查找用户的方法
		} else if ((sta != null && !"".equals(sta))
				&& (userName == null || "".equals(userName))) {
			int status = Integer.parseInt(sta);
			List<User> users = service.findUserByStatus(status);
			model.addAttribute("users", users);
			return "user/user";
			// 通过用户名和用户状态查找用户方法
		} else if ((sta != null && !"".equals(sta))
				&& (userName != null && !"".equals(userName))) {
			int status = Integer.parseInt(sta);
			List<User> users = service.findUserByStaAndName(userName, status);
			model.addAttribute("users", users);
			return "user/user";
		} else {
			List<User> users = service.queryAll();
			// 将用户对象存储到session中
			model.addAttribute("users", users);
			return "user/user";
		}
	}

	/**
	 * @Title: updateUser
	 * @Description: 下面是修改用户
	 * @param @param user
	 * @param @param model
	 * @param @param session
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/updateUser")
	public String updateUser(User user, Model model, HttpSession session,
			HttpServletRequest request) {
		if (service.updateUser(user)) {
			System.out.println("控制层成功添加的方法实现了");
			// session.invalidate();
			List<User> users = service.queryAll();
			// 将用户对象存储到session中
			model.addAttribute("users", users);
			// model.addAttribute("msg3", "恭喜你！修改成功！请重新登录！");
			return "user/user";
		} else {
			model.addAttribute("msg2", "对不起，修改有误，请核对信息！");
			return "user/showUpdateUser";
		}
	}
}
