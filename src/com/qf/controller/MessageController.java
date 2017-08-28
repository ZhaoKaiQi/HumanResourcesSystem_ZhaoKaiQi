package com.qf.controller;

import java.util.Date;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.qf.entity.Message;
import com.qf.service.MessageService;
import com.qf.service.UserService;
import com.qf.utils.EmailUtils;

/**
 * @ClassName: MessageController
 * @Description: 处理通告的控制层
 * @author 赵凯琦
 * @date 2017-7-11 下午10:35:27
 */
@Controller
public class MessageController {

	// 注入MessageService
	@Autowired
	private MessageService messageService;
	// 注入UserService
	@Autowired
	private UserService userService;

	/**
	 * @Title: showAddNotice
	 * @Description: 下面是通告的添加跳转
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/showAddNotice")
	public String showAddNotice() {
		return "notice/showAddNotice";
	}

	/**
	 * @Title: selectMessage
	 * @Description: 下面是查询通告
	 * @param @param model
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/selectMessage")
	public String selectMessage(Model model, HttpServletRequest request) {
		// 获取页面传来的标题
		String msgTitle = request.getParameter("msgTitle");
		// 获取页面传来的通知内容
		String msgContent = request.getParameter("msgContent");
		System.out.println("======================");
		System.out.println("查询通告的方法体执行进来了");
		System.out.println("======================");
		// 通过标题查找用户的方法
		if ((msgTitle != null && !"".equals(msgTitle))
				&& (msgContent == null || "".equals(msgContent))) {
			List<Message> messages = messageService
					.findMessageByTitle(msgTitle);
			System.out.println("查询结果为：" + messages);
			model.addAttribute("messages", messages);
			return "notice/notice";

			// 通过标题和内容查找通知的方法（模糊查询）
		} else if ((msgTitle != null && !"".equals(msgTitle))
				&& (msgContent != null && !"".equals(msgContent))) {
			List<Message> messages = messageService
					.findMessageByTitleAndContent(msgTitle, msgContent);
			System.out.println("查询结果为：" + messages);
			model.addAttribute("messages", messages);
			return "notice/notice";

			// 通过内容查找通知方法
		} else if ((msgContent != null && !"".equals(msgContent))
				&& (msgTitle == null || "".equals(msgTitle))) {
			// 通过标题获取当前的通告
			List<Message> messages = messageService
					.findMessageByContent(msgContent);
			System.out.println("查询结果为：" + messages);
			model.addAttribute("messages", messages);
			return "notice/notice";
		} else {
			// 获取全部通知表对象的方法，用来展示查询
			List<Message> messages = messageService.queryAll();
			System.out.println("查询的结果是;" + messages);
			model.addAttribute("messages", messages);
			return "notice/notice";
		}
	}

	/**
	 * @Title: removeMessage
	 * @Description: 下面是删除通告的方法
	 * @param @param message
	 * @param @param model
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/removeMessage")
	public String removeMessage(Message message, Model model,
			HttpServletRequest request) {
		String ids = request.getParameter("ids");
		System.out.println(ids);
		String[] strs = ids.split(",");
		int[] nums = new int[strs.length];
		for (int i = 0; i < strs.length; i++) {
			if (strs[i] != null) {
				nums[i] = Integer.parseInt(strs[i].trim());
				messageService.deleteMessageById(nums[i]);
			}
		}
		List<Message> messages = messageService.queryAll();
		// 将用户对象存储到session中
		model.addAttribute("messages", messages);
		return "notice/notice";
	}

	/**
	 * @Title: showUpdateNotice
	 * @Description: 下面是通告的查询跳转
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/showUpdateNotice")
	public String showUpdateNotice(Model model, Message message,
			HttpServletRequest request) {
		String mId = request.getParameter("msgId");
		int msgId = Integer.parseInt(mId);
		Message message2 = messageService.findMessageById(msgId);
		System.out.println("通告中：" + message2);
		request.getSession().setAttribute("message2", message2);
		return "notice/showUpdateNotice";
	}

	/**
	 * @Title: updateMessage
	 * @Description: 下面是修改通告的方法
	 * @param @param message
	 * @param @param model
	 * @param @param session
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/updateMessage")
	public String updateMessage(Message message, Model model,
			HttpSession session, HttpServletRequest request) {
		if (messageService.updateMessage(message)) {
			System.out.println("控制层成功添加的方法实现了");
			// session.invalidate();
			List<Message> messages = messageService.queryAll();
			// 将用户对象存储到session中
			model.addAttribute("messages", messages);
			// model.addAttribute("msg3", "恭喜你！修改成功！请重新登录！");
			return "notice/notice";
		} else {
			model.addAttribute("msg2", "对不起，修改有误，请核对信息！");
			return "notice/showUpdateNotice";
		}
	}

	/**
	 * @Title: previewNotice
	 * @Description: 预览文本的跳转
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/previewNotice")
	public String previewNotice(Model model, Message message,
			HttpServletRequest request) {
		String mId = request.getParameter("msgId");
		int msgId = Integer.parseInt(mId);
		Message message3 = messageService.findMessageById(msgId);
		System.out.println("通告中：" + message3);
		request.getSession().setAttribute("message3", message3);
		return "notice/previewNotice";
	}

	/**
	 * @Title: save
	 * @Description: 下面是添加通告的方法
	 * @param @param message
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/addMessage")
	public String save(Message message, Model model, HttpServletRequest request) {
		System.out.println(message);
		// DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String uId = request.getParameter("userId");
		int userId = Integer.parseInt(uId);
		message.setUser(userService.findUserById(userId));
		message.setMsgCreateDate(new Date(System.currentTimeMillis()));
		System.out.println(message);
		if (messageService.add(message)) {
			System.out.println("控制层成功添加的方法实现了");
			List<Message> messages = messageService.queryAll();
			// 将用户对象存储到session中
			model.addAttribute("messages", messages);
			return "notice/notice";
		} else {
			model.addAttribute("msg2", "添加用户失败，请核正信息！");
			return "notice/showAddNotice";
		}
	}

	/**
	 * @Title: showAddEmail
	 * @Description: 发送邮件
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/showAddEmail")
	public String showAddEmail() {
		return "notice/showAddEmail";
	}

	/**
	 * @Title: sendEmail
	 * @Description: 发送邮件的方法
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/sendEmail")
	public String sendEmail(Model model, HttpServletRequest request) {
		String email = request.getParameter("email");// 发送至
		String title = request.getParameter("title");// 邮件标题
		String content = request.getParameter("content");// 邮件内容
		System.out.println("发送邮件的方法执行到方法体，获取数据：" + email + "==" + content
				+ "==" + title);
		EmailUtils.sendEmail(email, title, content);
		List<Message> messages = messageService.queryAll();
		// 将用户对象存储到session中
		model.addAttribute("messages", messages);
		return "notice/notice";
	}

	/**
	 * @throws JSONException 
	 * @throws ClientException
	 * @throws ServerException
	 * @Title: sendMessage
	 * @Description: 发送短信通知
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/sendMessage")
	public String sendMessage(Model model, HttpServletRequest request)
			throws ServerException, ClientException, JSONException {
		String tel = request.getParameter("tel");// 发送至
		String content = request.getParameter("content");// 短信内容
		System.out.println("发送短信的方法执行到方法体，获取数据：" + tel + content);
		// 设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		// 初始化ascClient需要的几个参数
		final String product = "Dysmsapi";// 短信API产品名称
		final String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名
		// 替换成你的AK
		final String accessKeyId = "LTAIoiklcKfHAvD5";// 你的accessKeyId,参考本文档步骤2
		final String accessKeySecret = "PTS7dkCRLAMB2e3Csi1Uq9U7Izmx5h";// 你的accessKeySecret，参考本文档步骤2
		// 初始化ascClient,暂时不支持多region
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
				accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product,
				domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		// 组装请求对象
		SendSmsRequest request1 = new SendSmsRequest();
		// 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为20个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		request1.setPhoneNumbers(tel);
		// 必填:短信签名-可在短信控制台中找到
		request1.setSignName("千峰成都");
		// 必填:短信模板-可在短信控制台中找到
		request1.setTemplateCode("SMS_77825003");
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		//JSONObject jsonObject=new JSONObject();
		//jsonObject.put("tel", tel);
		//jsonObject.put("content", content);
		request1.setTemplateParam("{\"number\":\""+tel+"\", \"name\":\""+content+"\"}");
		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request1.setOutId("对方已接收！");
		// 请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request1);
		if (sendSmsResponse.getCode() != null
				&& sendSmsResponse.getCode().equals("OK")) {
			// 请求成功
		}
		System.out.println("控制层成功添加的方法实现了");
		List<Message> messages = messageService.queryAll();
		// 将用户对象存储到session中
		model.addAttribute("messages", messages);
		return "notice/notice";
	}

	/**
	 * @Title: showAddMsg
	 * @Description: 发送短信
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/showAddMsg")
	public String showAddMsg() {
		return "notice/showAddMsg";
	}
}
