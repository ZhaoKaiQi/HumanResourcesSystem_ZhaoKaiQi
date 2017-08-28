package com.qf.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.pdf.BaseFont;
import com.qf.entity.Attendance;
import com.qf.entity.Dept;
import com.qf.entity.Document;
import com.qf.entity.Employee;
import com.qf.entity.Position;
import com.qf.entity.User;
import com.qf.mapper.IEmployeeMapper;
import com.qf.service.AttendanceService;
import com.qf.service.DeptService;
import com.qf.service.DocumentService;
import com.qf.service.EmployeeService;
import com.qf.service.PositionService;
import com.qf.utils.ExportExcel;
import com.qf.utils.GetPDFUtils;
import com.qf.utils.MyBatisUtils;

/**
 * @ClassName: DocumentController
 * @Description: 处理文件的控制层
 * @author 赵凯琦
 * @date 2017-7-11 下午10:35:27
 */
@Controller
public class DocumentController {
	// 注入DocumentService
	@Autowired
	private DocumentService documentService;

	// 注入AttendanceService
	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private DeptService deptService;

	@Autowired
	private PositionService positionService;

	@Autowired
	private EmployeeService employeeService;

	/**
	 * @Title: showAddDocument
	 * @Description: 下面是文件的添加跳转
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/showAddDocument")
	public String showAddDocument() {
		return "document/showAddDocument";
	}

	/**
	 * @Title: selectDocument
	 * @Description: 下面是查询文件
	 * @param @param model
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/selectDocument")
	public String selectDocument(Model model, HttpServletRequest request) {
		// 获取页面传来的标题
		String docTitle = request.getParameter("docTitle");
		System.out.println("======================");
		System.out.println("查询文件的方法体执行进来了");
		System.out.println("======================");
		// 通过标题查找用户的方法
		if ((docTitle != null && !"".equals(docTitle))) {
			List<Document> documents = documentService
					.findDocumentByTitle(docTitle);
			System.out.println("查询结果为：" + documents);
			model.addAttribute("documents", documents);
			return "document/document";

			// 通过标题和内容查找通知的方法（模糊查询）
		} else {
			// 获取全部通知表对象的方法，用来展示查询
			List<Document> documents = documentService.queryAll();
			System.out.println("查询的结果是;" + documents);
			model.addAttribute("documents", documents);
			return "document/document";
		}
	}

	/**
	 * @Title: removeDocument
	 * @Description: 下面是删除文件的方法
	 * @param @param document
	 * @param @param model
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/removeDocument")
	public String removeDocument(Document document, Model model,
			HttpServletRequest request) {
		String ids = request.getParameter("ids");
		System.out.println(ids);
		String[] strs = ids.split(",");
		int[] nums = new int[strs.length];
		for (int i = 0; i < strs.length; i++) {
			if (strs[i] != null) {
				nums[i] = Integer.parseInt(strs[i].trim());
				documentService.deleteDocumentById(nums[i]);
			}
		}
		List<Document> documents = documentService.queryAll();
		// 将用户对象存储到session中
		model.addAttribute("documents", documents);
		return "document/document";
	}

	/**
	 * @Title: showUpdateDocument
	 * @Description: 下面是文件的查询跳转
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/showUpdateDocument")
	public String showUpdateDocument(Model model, Document document,
			HttpServletRequest request) {
		String dId = request.getParameter("docId");
		int docId = Integer.parseInt(dId);
		Document document2 = documentService.findDocumentById(docId);
		System.out.println("文件中：" + document2);
		request.getSession().setAttribute("document2", document2);
		return "document/showUpdateDocument";
	}

	/**
	 * @Title: updateDocument
	 * @Description: 下面是修改文件的方法
	 * @param @param document
	 * @param @param model
	 * @param @param session
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/updateDocument")
	public String updateDocument(Document document2, Model model) {
		System.out.println("updateDocument方法里执行到了，此处的document为：" + document2);
		if (documentService.updateDocument(document2)) {
			System.out.println("======控制层成功添加的方法实现了======");
			List<Document> documents = documentService.queryAll();
			// 将用户对象存储到session中
			System.out.println("======修改过的文件信息如下：======" + documents);
			model.addAttribute("documents", documents);
			// model.addAttribute("msg3", "恭喜你！修改成功！请重新登录！");
			return "document/document";
		} else {
			model.addAttribute("msg2", "对不起，修改有误，请核对信息！");
			return "document/document";
		}
	}

	/**
	 * @Title: downLoad
	 * @Description: 下载
	 * @param @param model
	 * @param @param request
	 * @param @param docId
	 * @param @return
	 * @param @throws Exception
	 * @return ResponseEntity<byte[]>
	 * @throws
	 */
	@RequestMapping("/downLoad")
	public ResponseEntity<byte[]> downLoad(Model model,
			HttpServletRequest request) throws Exception {
		// 通过Id查找文件
		String docId = request.getParameter("docId");
		int docId2 = Integer.parseInt(docId);
		Document document = documentService.findDocumentById(docId2);
		System.out.println("获取到的文件：" + document);
		// 主要获取文件保存的路径
		String upfile = document.getUpFile();
		System.out.println("看看有没有获取到文件路径：" + upfile);
		File file = new File(upfile);
		System.out.println("文件路径如下：" + file);
		// request.getParameter(arg0);
		// request.getParameterValues(arg0)
		HttpHeaders headers = new HttpHeaders();
		// 这个地方划分的时候\代表转义字符，所有要用\\\\来分割
		String[] array = upfile.split("\\\\");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		String docFileName = list.get(array.length - 1);
		System.out.println("array.length是：" + array.length + ";集合是：" + list
				+ ";获取的文件名字为：" + docFileName);
		// 设置要打开的文件名称
		headers.setContentDispositionFormData("attachment", new String(
				docFileName.getBytes("UTF-8"), "ISO-8859-1"));
		// 设置文件下载
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 这是一个流，二进制数据

		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
				headers, HttpStatus.OK);
	}

	/**
	 * @Title: save
	 * @Description: 下面是上传文件的方法
	 * @param @param document
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/addDocument", method = RequestMethod.POST)
	// 下面这一句获取页面上传文件的名字
	public String save(@RequestParam("docFileName") MultipartFile mFile,
			Model model, HttpServletRequest request) {
		System.out.println("方法执行到save了");

		// 下面这个方法是用来创建文件的
		// 判断上传的文件是否为空
		if (!mFile.isEmpty()) {

			System.out.println("!mFile.isEmpty()为" + !mFile.isEmpty());
			// 要存的目录
			File dir = new File(new File(request.getSession()
					.getServletContext().getRealPath("/")).getParent(),// 绝对路径下的HRProSources文件夹
					"HRProSources");
			// 判断目录是否存在
			if (!dir.exists()) {
				dir.mkdirs();// 如果目录没有创建过，就创建目录
			}
			// 获取上传文件的文件名
			String docFileName = mFile.getOriginalFilename();// 获取上传文件的文件名的方法
																// mFile.getOriginalFilename()
			Document document = new Document();
			document.setDocFileName(docFileName);
			// 绝对文件，知道上传到那个文件，上线的时候可以去掉,加个下划线方便以后截取
			File file = new File(dir, System.currentTimeMillis() + "_"
					+ docFileName);
			System.out.println(file.getAbsolutePath());
			try {
				// 创建文件
				mFile.transferTo(file);
				System.out.println("方法执行到创建文件了");
			} catch (Exception e) {
				System.out.println("保存文件失败：" + e);
			}
			// 保存数据到数据库，下面是数据库操作
			// 获取文件题目
			String docTitle = request.getParameter("docTitle");
			document.setDocTitle(docTitle);
			// 获取文件内容
			String docRemark = request.getParameter("docRemark");
			document.setDocRemark(docRemark);
			// 获取发文人信息
			User user = (User) request.getSession().getAttribute("user");
			document.setUser(user);
			// 存入路径
			document.setUpFile(file.toString());
			document.setDocCreateDate(new Date(System.currentTimeMillis()));
			System.out.println("上传的文件信息" + document);
			if (documentService.add(document)) {
				System.out.println("控制层成功添加的方法实现了");
				List<Document> documents = documentService.queryAll();
				// 将用户对象存储到session中
				model.addAttribute("documents", documents);
				return "document/document";
			} else {
				model.addAttribute("msg2", "添加用户失败，请核正信息！");
				return "document/document";
			}

		} else {
			request.setAttribute("msg", "亲，图片太大");
		}

		return "document/document";
	}

	/**
	 * @Title: sign
	 * @Description: 出席表跳转
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/sign")
	public String sign() {
		return "sign/sign";
	}

	/**
	 * @throws ParseException
	 * @Title: selectDocument
	 * @Description: 下面是查询出席状况
	 * @param @param model
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/selectAttendance")
	public String selectAttendance(Attendance attendance, Model model,
			HttpServletRequest request) throws ParseException {
		// 获取页面传来的标题
		String atdCreateTimeStart = request.getParameter("atdCreateTimeStart");
		String atdCreateTimeEnd = request.getParameter("atdCreateTimeEnd");
		System.out.println("开始时间：" + atdCreateTimeStart + ";结束时间："
				+ atdCreateTimeEnd);
		User user = (User) request.getSession().getAttribute("user");
		attendance.setUser(user);
		System.out.println("======================");
		System.out.println("搜索签到的方法体执行进来了");
		System.out.println("======================");
		// 开始时间不为空，结束时间为空
		if ((atdCreateTimeStart != null && !"".equals(atdCreateTimeStart))
				&& (atdCreateTimeEnd == null || "".equals(atdCreateTimeEnd))) {
			System.out.println("只有开始时间");
			List<Attendance> attendances = attendanceService
					.findAttendanceByTime(atdCreateTimeStart);
			System.out.println("查询结果为：" + attendances);
			model.addAttribute("attendances", attendances);
			return "sign/sign";

			// 开始时间为空，结束时间不为空
		} else if ((atdCreateTimeStart == null || "".equals(atdCreateTimeStart))
				&& (atdCreateTimeEnd != null && !"".equals(atdCreateTimeEnd))) {
			System.out.println("只有结束时间");
			List<Attendance> attendances = attendanceService
					.findAttendanceByEndTime(atdCreateTimeEnd);
			System.out.println("查询结果为：" + attendances);
			model.addAttribute("attendances", attendances);
			return "sign/sign";

			// 开始结束时间都不为空
		} else if ((atdCreateTimeStart != null && !""
				.equals(atdCreateTimeStart))
				&& (atdCreateTimeEnd != null && !"".equals(atdCreateTimeEnd))) {
			System.out.println("时间都不空");
			List<Attendance> attendances = attendanceService
					.findAttendanceByMiddleTime(atdCreateTimeStart,
							atdCreateTimeEnd);
			System.out.println("查询结果为：" + attendances);
			model.addAttribute("attendances", attendances);
			return "sign/sign";
		} else {
			// 获取全部通知表对象的方法，用来展示查询
			List<Attendance> attendances = attendanceService.queryAll();
			System.out.println("查询的结果是;" + attendances);
			model.addAttribute("attendances", attendances);
			return "sign/sign";
		}
	}

	/**
	 * @throws ParseException
	 * @Title: newSign
	 * @Description: 点击打卡的方法
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/newSign")
	public String newSign(Model model, HttpServletRequest request)
			throws ParseException {
		System.out.println("打卡的按钮被点击了，方法执行了");
		Attendance attendance = new Attendance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String atdCreateTime = dateFormat.format(new Date());
		attendance.setAtdCreateTime(atdCreateTime);
		User user = (User) request.getSession().getAttribute("user");
		attendance.setUser(user);
		System.out.println("打卡方法执行了：" + atdCreateTime + "===" + atdCreateTime);
		String time = atdCreateTime;
		String[] array = time.split(" ");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		String str = list.get(1);
		System.out.println("list:" + list + ";str:" + str);
		String[] array2 = str.split(":");
		List<String> list2 = new ArrayList<String>();
		for (int i = 0; i < array2.length; i++) {
			list2.add(array2[i]);
		}
		String str2 = list2.get(0);
		System.out.println("list2:" + list2 + ";str2:" + str2);
		int time1 = Integer.parseInt(str2);
		if (9 < time1 && time1 < 20) {
			attendance.setAtdFlag(0);
		} else {
			attendance.setAtdFlag(1);
		}
		System.out.println("attendance为:" + attendanceService.queryAll());
		attendanceService.addAtt(attendance);
		List<Attendance> attendances = attendanceService.queryAll();
		System.out.println("查询的结果是;" + attendances);
		model.addAttribute("attendances", attendances);
		return "sign/sign";
	}

	/**
	 * @Title: jump
	 * @Description: 简单跳转页面的方法，sign里面的，以后可以改功能
	 * @param @param model
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @param @throws IOException
	 * @param @throws JSONException
	 * @return String
	 * @throws
	 */
	@RequestMapping("/jump")
	public String jump(Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException, JSONException {
		return "sign/signCharts";

	}

	/**
	 * @Title: signCharts
	 * @Description: 生成图表
	 * @param @return
	 * @return String
	 * @throws
	 */
	// @ResponseBody
	@RequestMapping("/signCharts")
	public void signCharts(Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException, JSONException {
		String ids = request.getParameter("ids");
		String atdDate = request.getParameter("atdCreateTime");
		System.out.println("ids:" + ids + ";atdDate:" + atdDate);
		System.out.println(attendanceService.findAttendanceById(1));
		// new一个json对象
		JSONArray jArray = new JSONArray();
		// 执行sign.jsp跳转过来的方法
		if ((ids != null && !"".equals(ids))
				&& (atdDate == null || "".equals(atdDate))) {
			String[] strs = ids.split(",");
			int[] nums = new int[strs.length];
			List<String> timeListUp = new ArrayList<String>();
			List<String> timeListDown = new ArrayList<String>();
			Set<String> dateList = new TreeSet<String>();
			for (int i = 0; i < strs.length; i++) {
				if (strs[i] != null) {
					nums[i] = Integer.parseInt(strs[i].trim());
					System.out.println("nums[i]:" + nums[i]);
					String attStr = attendanceService
							.findAttendanceById(nums[i]);
					System.out.println("获取到的ateCreateTime为：" + attStr);
					String[] strs2 = attStr.split(" ");
					String[] str3 = strs2[1].split(":");
					System.out.println("str3[0]:" + str3[0]);
					if (Integer.parseInt(str3[0]) < 12) {
						timeListUp.add(strs2[1].trim());// 上午
					} else {
						timeListDown.add(strs2[1].trim());// 下午
					}
					dateList.add(strs2[0].trim());
				}
			}
			System.out.println("获取到的日期为：" + dateList + "；获取到的时间为：" + timeListUp
					+ "；获取到的时间为：" + timeListDown);
			// 将数据存入json中
			jArray.put(timeListUp);
			jArray.put(timeListDown);
			jArray.put(dateList);
			response.getWriter().write(jArray.toString());
			response.getWriter().flush();
		}
		if ((atdDate != null && !"".equals(atdDate))
				&& (ids == null || "".equals(ids))) {
			List<String> timeListUp = new ArrayList<String>();
			List<String> timeListDown = new ArrayList<String>();
			Set<String> dateList = new TreeSet<String>();
			List<String> attStr = attendanceService
					.findAttendanceBytime(atdDate);
			System.out.println("获取到的ateCreateTime为：" + attStr);
			for (int j = 0; j < attStr.size(); j++) {
				String[] strs2 = attStr.get(j).split(" ");
				String[] str3 = strs2[1].split(":");
				System.out.println("str3[0]:" + str3[0]);
				if (Integer.parseInt(str3[0]) < 12) {
					timeListUp.add(str3[0].trim());// 上午
				} else {
					timeListDown.add(str3[0].trim());// 下午
				}
				dateList.add(strs2[0].trim());
			}
			System.out.println("获取到的日期为：" + dateList + "；获取到的时间为：" + timeListUp
					+ "；获取到的时间为：" + timeListDown);
			// 将数据存入json中
			jArray.put(timeListUp);
			jArray.put(timeListDown);
			jArray.put(dateList);
			response.getWriter().write(jArray.toString());
			response.getWriter().flush();
		}
	}

	/**
	 * @Title: poi
	 * @Description: 报表
	 * @param @param model
	 * @param @param employee
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/poi")
	public String poi(Model model, Employee employee, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		// 获取姓名
		String empName = request.getParameter("empName");
		// 获取身份证号码
		request.getSession().setAttribute("empName", empName);
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
			return "poi/poi";
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
			return "poi/poi";
		}
	}

	/**
	 * @throws Exception
	 * @Title: createBb
	 * @Description: 生成PDF或者Excel
	 * @param @param model
	 * @param @param employee
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/createBb")
	public String createBb(Model model, Employee employee,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String t = request.getParameter("t");
		String ids = request.getParameter("ids");
		System.out.println("t为：" + t + ";ids为：" + ids);
		String empName = (String) request.getSession().getAttribute("empName");
		System.out.println(empName);

		if (Integer.parseInt(t) == 1) {

			if ((empName == null || empName.equals(" "))
					&& (ids != null && !ids.equals(""))) {
				List<List<String>> ls = new ArrayList<List<String>>();

				// 分割IDS
				String[] strs = ids.split(",");
				int[] nums = new int[strs.length];

				for (int i = 0; i < strs.length; i++) {
					if (strs[i] != null) {
						nums[i] = Integer.parseInt(strs[i].trim());
						Employee employee2 = employeeService
								.findEmployeeById(nums[i]);
						// 列
						empName = employee2.getEmpName();
						String empSex = employee2.getEmpSex() + "";
						String empTel = employee2.getEmpTel();
						String empEmail = employee2.getEmpEmail();
						String posName = employee2.getPosition().getPosName();
						String empEducation = employee2.getEmpEducation();
						String empCardId = employee2.getEmpCardId();
						String deptName = employee2.getDept().getDeptName();
						System.out.println("我的部门呢：" + deptName + "==="
								+ employee2.getDept().getDeptName());
						String empAddress = employee2.getEmpAddress();
						String empCreateDate = employee2.getEmpCreateDate();
						List<String> list = new ArrayList<String>();
						list.add(empName);
						list.add(empSex);
						list.add(empTel);
						list.add(empEmail);
						list.add(posName);
						list.add(empEducation);
						list.add(empCardId);
						list.add(deptName);
						list.add(empAddress);
						list.add(empCreateDate);
						ls.add(list);
					}
				}
				System.out.println("传过去的ls是：" + ls);
				model.addAttribute("employees", ls);
				request.getSession().removeAttribute("empName");

				GetPDFUtils.exportPdfDocument(ls);

				return "redirect:poi";
				// 生成PDF
			}

		} else if (Integer.parseInt(t) == 2) {
			// 按名字生成
			if ((empName != null && !empName.equals(" "))
					&& (ids == null || ids.equals(""))) {
				Map<String, Object> map = new HashMap<>();
				// 添加属性
				map.put("empName", empName);
				// System.out.println("里面的方法执行了");
				IEmployeeMapper mapper = MyBatisUtils.getSession().getMapper(
						IEmployeeMapper.class);
				List<Employee> employees2 = employeeService.findAllByWhat(map);
				System.out.println("查询结果：" + employees2);
				model.addAttribute("employees", employees2);
				System.out.println("外面的方法执行了");
				/*
				 * List<Position> positions = positionService.queryAll();
				 * System.out.println("position为：" + positions);
				 * model.addAttribute("positions", positions); List<Dept> depts
				 * = deptService.queryAll(); model.addAttribute("depts", depts);
				 */
				MyBatisUtils.commit();
				MyBatisUtils.close();
				// 生成Excel
				System.out.println("生成Excel的方法执行中");

				File dir = new File(new File(request.getSession()
						.getServletContext().getRealPath("/")).getParent(),// 绝对路径下的HRProSources文件夹
						"excel");
				// 判断目录是否存在
				if (!dir.exists()) {
					dir.mkdirs();// 如果目录没有创建过，就创建目录
				}
				File file = new File(dir, System.currentTimeMillis() + ".xls");
				System.out.println(file.toString());
				String xlsFile = file.toString(); // 产生的Excel文件的名称
				FileOutputStream os = new FileOutputStream(xlsFile);
				String[] strMeaning = { "姓名", "性别", "手机号码", "邮箱", "职位", "学历",
						"身份证号码", "部门", "联系地址", "建档日期" };
				String[] strName = { "empName", "empSex", "empTel", "empEmail",
						"position", "empEducation", "empCardId", "dept",
						"empAddress", "empCreateDate" };
				List<Employee> ls = new ArrayList<Employee>();
				for (int i = 0; i < employees2.size(); i++) {
					Employee employee2 = employees2.get(i);
					empName = employee2.getEmpName();
					String empSex = employee2.getEmpSex() + "";
					String empTel = employee2.getEmpTel();
					String empEmail = employee2.getEmpEmail();
					String position = employee2.getPosition().getPosName();
					String empEducation = employee2.getEmpEducation();
					String empCardId = employee2.getEmpCardId();
					String dept = employee2.getDept().getDeptName();
					System.out.println("我的部门呢：" + dept + "==="
							+ employee2.getDept().getDeptName());
					String empAddress = employee2.getEmpAddress();
					String empCreateDate = employee2.getEmpCreateDate();
					ls.add(employee2);
				}
				ExportExcel.exportExcel(strMeaning, strName, ls, os);
				request.getSession().removeAttribute("empName");
				return "redirect:poi";
			} else if ((empName == null || empName.equals(" "))
					&& (ids != null && !ids.equals(""))) {

				// 按Id生成
				System.out.println(ids);
				// 生成Excel
				System.out.println("生成Excel的方法执行中");

				File dir = new File(new File(request.getSession()
						.getServletContext().getRealPath("/")).getParent(),// 绝对路径下的HRProSources文件夹
						"excel");
				// 判断目录是否存在
				if (!dir.exists()) {
					dir.mkdirs();// 如果目录没有创建过，就创建目录
				}
				File file = new File(dir, System.currentTimeMillis() + ".xls");
				System.out.println(file.toString());
				String xlsFile = file.toString(); // 产生的Excel文件的名称
				FileOutputStream os = new FileOutputStream(xlsFile);
				String[] strMeaning = { "姓名", "性别", "手机号码", "邮箱", "职位", "学历",
						"身份证号码", "部门", "联系地址", "建档日期" };
				String[] strName = { "empName", "empSex", "empTel", "empEmail",
						"position", "empEducation", "empCardId", "dept",
						"empAddress", "empCreateDate" };
				List<Employee> ls = new ArrayList<Employee>();
				// 分割IDS
				String[] strs = ids.split(",");
				int[] nums = new int[strs.length];

				for (int i = 0; i < strs.length; i++) {
					if (strs[i] != null) {
						nums[i] = Integer.parseInt(strs[i].trim());
						Employee employee2 = employeeService
								.findEmployeeById(nums[i]);
						empName = employee2.getEmpName();
						String empSex = employee2.getEmpSex() + "";
						String empTel = employee2.getEmpTel();
						String empEmail = employee2.getEmpEmail();
						String position = employee2.getPosition().getPosName();
						String empEducation = employee2.getEmpEducation();
						String empCardId = employee2.getEmpCardId();
						String dept = employee2.getDept().getDeptName();
						System.out.println("我的部门呢：" + dept + "==="
								+ employee2.getDept().getDeptName());
						String empAddress = employee2.getEmpAddress();
						String empCreateDate = employee2.getEmpCreateDate();
						ls.add(employee2);
					}
				}
				model.addAttribute("employees", ls);
				ExportExcel.exportExcel(strMeaning, strName, ls, os);
				request.getSession().removeAttribute("empName");
			}
		}
		return "poi/poi";
	}

}
