package com.qf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qf.entity.Message;

/**
 * @ClassName: IMessageMapper
 * @Description: Message的映射文件接口
 * @author 赵凯琦
 * @date 2017-7-11 下午10:38:10
 */
public interface IMessageMapper {

	/**
	 * @Title: queryAll
	 * @Description: 获取全部通告的集合，用来查询页面的展示
	 * @param @return
	 * @return List<Message>
	 * @throws
	 */
	@Select("select * from message as a join user as b on a.userId=b.userId")
	@Results({
			// 对应用户
			@Result(property = "user.userId", column = "userId"),
			@Result(property = "user.userName", column = "userName"),
			@Result(property = "user.loginName", column = "loginName"),
			@Result(property = "user.userPassWord", column = "userPassWord"),
			@Result(property = "user.status", column = "status"),
			@Result(property = "user.createDate", column = "createDate"), })
	@ResultType(Message.class)
	List<Message> queryAll();

	/**
	 * @Title: findMessageByTitle
	 * @Description: 通过标题查找通知
	 * @param @param msgTitle
	 * @param @return
	 * @return List<Message>
	 * @throws
	 */
	@Select("select * from message as a join user as b on a.userId=b.userId where msgTitle like '%' #{param1} '%'")
	@Results({
			// 对应用户
			@Result(property = "user.userId", column = "userId"),
			@Result(property = "user.userName", column = "userName"),
			@Result(property = "user.loginName", column = "loginName"),
			@Result(property = "user.userPassWord", column = "userPassWord"),
			@Result(property = "user.status", column = "status"),
			@Result(property = "user.createDate", column = "createDate"), })
	@ResultType(Message.class)
	List<Message> findMessageByTitle(String msgTitle);

	/**
	 * @Title: findMessageByTitleAndContent
	 * @Description: 通过标题和内容查找通知
	 * @param @param msgTitle
	 * @param @param msgContent
	 * @param @return
	 * @return List<Message>
	 * @throws
	 */
	@Select("select * from message as a join user as b on a.userId=b.userId where msgTitle like '%' #{param1} '%' and msgContent like '%' #{param2} '%'")
	@Results({
			// 对应用户
			@Result(property = "user.userId", column = "userId"),
			@Result(property = "user.userName", column = "userName"),
			@Result(property = "user.loginName", column = "loginName"),
			@Result(property = "user.userPassWord", column = "userPassWord"),
			@Result(property = "user.status", column = "status"),
			@Result(property = "user.createDate", column = "createDate"), })
	@ResultType(Message.class)
	List<Message> findMessageByTitleAndContent(String msgTitle,
			String msgContent);

	/**
	 * @Title: findMessageByContent
	 * @Description: 通过内容查找通知
	 * @param @param msgContent
	 * @param @return
	 * @return List<Message>
	 * @throws
	 */

	@Select("select * from message as a join user as b on a.userId=b.userId where msgContent like '%' #{param1} '%'")
	@Results({
			// 对应用户
			@Result(property = "user.userId", column = "userId"),
			@Result(property = "user.userName", column = "userName"),
			@Result(property = "user.loginName", column = "loginName"),
			@Result(property = "user.userPassWord", column = "userPassWord"),
			@Result(property = "user.status", column = "status"),
			@Result(property = "user.createDate", column = "createDate"), })
	@ResultType(Message.class)
	List<Message> findMessageByContent(String msgContent);

	/**
	 * @Title: deleteMessageById
	 * @Description: 通过Id删除数据
	 * @param @param msgId
	 * @return void
	 * @throws
	 */
	@Delete("delete from Message where msgId=#{param1}")
	void deleteMessageById(int msgId);

	/**
	 * @Title: findMessageById
	 * @Description: 通过Id查找通知
	 * @param @param msgId
	 * @param @return
	 * @return Message
	 * @throws
	 */
	@Select("select * from Message where msgId=#{param1}")
	Message findMessageById(int msgId);

	/**
	 * @Title: updateMessage
	 * @Description: 修改通知
	 * @param @param message
	 * @param @return
	 * @return int
	 * @throws
	 */
	@Update("update Message set msgTitle=#{msgTitle},msgContent=#{msgContent} where msgId=#{msgId}")
	int updateMessage(Message message);

	/**
	 * @Title: add
	 * @Description: 添加通知
	 * @param @param message
	 * @param @return
	 * @return int
	 * @throws
	 */
	@Insert("insert into Message(msgTitle,msgContent,msgCreateDate,userId) values(#{msgTitle},#{msgContent},#{msgCreateDate},#{user.userId})")
	@Options(useGeneratedKeys = true, keyProperty = "msgId")
	int add(Message message);

}
