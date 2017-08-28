package com.qf.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qf.entity.Position;
import com.qf.service.PositionService;

/**
 * @ClassName: PositionController
 * @Description: 处理职位的控制层
 * @author 赵凯琦
 * @date 2017-7-11 下午10:35:27
 */
@Controller
public class PositionController {

	// 注入PositionService
	@Autowired
	private PositionService positionService;

	/**
	 * @Title: showAddJob
	 * @Description: 下面是职位的添加跳转
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/showAddJob")
	public String showAddJob() {
		return "job/showAddJob";
	}

	/**
	 * @Title: showUpdateJob
	 * @Description: 下面是职位的查询跳转
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/showUpdateJob")
	public String showUpdateJob(Model model, Position position,
			HttpServletRequest request) {
		String pId = request.getParameter("posId");
		int posId = Integer.parseInt(pId);
		Position position2 = positionService.findPositionById(posId);
		request.getSession().setAttribute("position2", position2);
		return "job/showUpdateJob";
	}

	/**
	 * @Title: selectPosition
	 * @Description: 下面是查询职位
	 * @param @param model
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/selectPosition")
	public String selectPosition(Model model, HttpServletRequest request) {
		String posName = request.getParameter("posName");
		System.out.println("======================");
		System.out.println("查询职位的方法体执行进来了");
		System.out.println("======================");
		// 通过用户名查找用户的方法
		if (posName != null && !"".equals(posName)) {
			List<Position> positions = positionService
					.findPositionByName(posName);
			model.addAttribute("positions", positions);
			return "job/job";
		} else {
			List<Position> positions = positionService.queryAll();
			model.addAttribute("positions", positions);
			return "job/job";
		}
	}

	/**
	 * @Title: removePosition
	 * @Description: 下面是删除职位的方法
	 * @param @param position
	 * @param @param model
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/removePosition")
	public String removePosition(Position position, Model model,
			HttpServletRequest request) {
		String ids = request.getParameter("ids");
		System.out.println(ids);
		String[] strs = ids.split(",");
		int[] nums = new int[strs.length];
		for (int i = 0; i < strs.length; i++) {
			if (strs[i] != null) {
				nums[i] = Integer.parseInt(strs[i].trim());
				positionService.deletePositionById(nums[i]);
			}
		}
		List<Position> positions = positionService.queryAll();
		// 将用户对象存储到session中
		model.addAttribute("positions", positions);
		return "job/job";
	}

	/**
	 * @Title: updatePosition
	 * @Description: 下面是修改职位的方法
	 * @param @param position
	 * @param @param model
	 * @param @param session
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/updatePosition")
	public String updatePosition(Position position, Model model,
			HttpSession session, HttpServletRequest request) {
		System.out.println(position);
		if (positionService.updatePosition(position)) {
			System.out.println("控制层成功添加的方法实现了");
			// session.invalidate();
			List<Position> positions = positionService.queryAll();
			// 将用户对象存储到session中
			model.addAttribute("positions", positions);
			// model.addAttribute("msg3", "恭喜你！修改成功！请重新登录！");
			return "job/job";
		} else {
			model.addAttribute("msg2", "对不起，修改有误，请核对信息！");
			return "job/showUpdateJob";
		}
	}

	/**
	 * @Title: save
	 * @Description: 下面是添加职位的方法
	 * @param @param position
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/addPosition")
	public String save(Position position, Model model) {
		System.out.println(position);
		if (positionService.add(position)) {
			System.out.println("控制层成功添加的方法实现了");
			List<Position> positions = positionService.queryAll();
			// 将用户对象存储到session中
			model.addAttribute("positions", positions);
			return "job/job";
		} else {
			model.addAttribute("msg2", "添加用户失败，请核正信息！");
			return "job/showAddjob";
		}
	}
}
