package com.qf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qf.entity.Message;
import com.qf.mapper.IMessageMapper;

/**
 * @ClassName: MessageService
 * @Description: 部门的业务逻辑层
 * @author 赵凯琦
 * @date 2017-7-11 下午10:39:51
 */
@Service
public class MessageService {
	// 注入MessageMapper
	@Autowired
	private IMessageMapper mapper;

	/**
	 * @Title: queryAll
	 * @Description: 获取全部通告的集合，用来查询页面的展示
	 * @param @return
	 * @return List<Message>
	 * @throws
	 */
	public List<Message> queryAll() {
		return mapper.queryAll();
	}

	/**
	 * @Title: findMessageByTitle
	 * @Description: 通过标题查找通知
	 * @param @param msgTitle
	 * @param @return
	 * @return List<Message>
	 * @throws
	 */
	public List<Message> findMessageByTitle(String msgTitle) {
		return mapper.findMessageByTitle(msgTitle);
	}

	/**
	 * @Title: findMessageByTitleAndContent
	 * @Description: 通过标题和内容查找通知
	 * @param @param msgTitle
	 * @param @param msgContent
	 * @param @return
	 * @return List<Message>
	 * @throws
	 */
	public List<Message> findMessageByTitleAndContent(String msgTitle,
			String msgContent) {
		if (null == msgTitle || "".equals(msgTitle) || null == msgContent
				|| "".equals(msgContent)) {
			throw new RuntimeException("所输入标题或内容为空...");
		} else {
			System.out.println("业务逻辑层执行到了" + msgTitle + "=" + msgContent);
			return mapper.findMessageByTitleAndContent(msgTitle, msgContent);
		}
	}

	/**
	 * @Title: findMessageByContent
	 * @Description: 通过内容查找通知
	 * @param @param msgContent
	 * @param @return
	 * @return List<Message>
	 * @throws
	 */
	public List<Message> findMessageByContent(String msgContent) {
		System.out.println("传进来的文本为：" + msgContent);
		return mapper.findMessageByContent(msgContent);
	}

	/**
	 * @Title: deleteMessageById
	 * @Description: 通过Id删除数据
	 * @param @param msgId
	 * @return void
	 * @throws
	 */
	public void deleteMessageById(int msgId) {
		if (msgId <= 0 || "".equals(msgId)) {
			throw new RuntimeException("所输入数据有问题...");
		} else {
			System.out.println("业务逻辑层执行到了" + msgId);
			// User user=
			mapper.deleteMessageById(msgId);
		}
	}

	/**
	 * @Title: findMessageById
	 * @Description: 通过Id查找通知
	 * @param @param msgId
	 * @param @return
	 * @return Message
	 * @throws
	 */
	public Message findMessageById(int msgId) {
		return mapper.findMessageById(msgId);
	}

	/**
	 * @Title: updateMessage
	 * @Description: 修改通知
	 * @param @param message
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean updateMessage(Message message) {
		return mapper.updateMessage(message) > 0 ? true : false;
	}

	/**
	 * @Title: add
	 * @Description: 添加通知
	 * @param @param message
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean add(Message message) {
		return mapper.add(message) > 0 ? true : false;
	}
}
