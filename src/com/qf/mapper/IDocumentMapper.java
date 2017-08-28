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

import com.qf.entity.Document;

/**
 * @ClassName: IDocumentMapper
 * @Description: Document的映射文件接口
 * @author 赵凯琦
 * @date 2017-7-11 下午10:38:10
 */
public interface IDocumentMapper {

	/**
	 * @Title: queryAll
	 * @Description: 获取全部通告的集合，用来查询页面的展示
	 * @param @return
	 * @return List<Document>
	 * @throws
	 */
	@Select("select * from document as a join user as b on a.userId=b.userId")
	@Results({
			// 对应用户
			@Result(property = "user.userId", column = "userId"),
			@Result(property = "user.userName", column = "userName"),
			@Result(property = "user.loginName", column = "loginName"),
			@Result(property = "user.userPassWord", column = "userPassWord"),
			@Result(property = "user.status", column = "status"),
			@Result(property = "user.createDate", column = "createDate"), })
	@ResultType(Document.class)
	List<Document> queryAll();

	/**
	 * @Title: findDocumentByTitle
	 * @Description: 通过标题查找通知
	 * @param @param docTitle
	 * @param @return
	 * @return List<Document>
	 * @throws
	 */
	@Select("select * from document as a join user as b on a.userId=b.userId where docTitle like '%' #{param1} '%'")
	@Results({
			// 对应用户
			@Result(property = "user.userId", column = "userId"),
			@Result(property = "user.userName", column = "userName"),
			@Result(property = "user.loginName", column = "loginName"),
			@Result(property = "user.userPassWord", column = "userPassWord"),
			@Result(property = "user.status", column = "status"),
			@Result(property = "user.createDate", column = "createDate"), })
	@ResultType(Document.class)
	List<Document> findDocumentByTitle(String docTitle);


	/**
	 * @Title: deleteDocumentById
	 * @Description: 通过Id删除数据
	 * @param @param docId
	 * @return void
	 * @throws
	 */
	@Delete("delete from document where docId=#{param1}")
	void deleteDocumentById(int docId);

	/**
	 * @Title: findDocumentById
	 * @Description: 通过Id查找通知
	 * @param @param docId
	 * @param @return
	 * @return document
	 * @throws
	 */
	@Select("select * from Document where docId=#{param1}")
	Document findDocumentById(int docId);

	/**
	 * @Title: updateDocument
	 * @Description: 修改通知
	 * @param @param Document
	 * @param @return
	 * @return int
	 * @throws
	 */
	@Update("update Document set docTitle=#{docTitle},docRemark=#{docRemark},docFileName=#{docFileName} where docId=#{docId}")
	int updateDocument(Document document);

	/**
	 * @Title: add
	 * @Description: 添加通知
	 * @param @param document
	 * @param @return
	 * @return int
	 * @throws
	 */
	@Insert("insert into document (docTitle,docRemark,docCreateDate,userId,docFileName,upFile) values(#{docTitle},#{docRemark},#{docCreateDate},#{user.userId},#{docFileName},#{upFile})")
	@Options(useGeneratedKeys = true, keyProperty = "docId")
	int add(Document document);

}
