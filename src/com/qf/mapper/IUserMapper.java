package com.qf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qf.entity.User;

/**
 * @ClassName: UserMapper
 * @Description: User的映射文件接口
 * @author 赵凯琦
 * @date 2017-7-10 上午4:11:50
 */
public interface IUserMapper {
	/**
	 * @Title: add
	 * @Description: 添加用户
	 * @param @param user
	 * @param @return
	 * @return int
	 * @throws
	 */
	@Insert("insert into user(userName,loginName,userPassWord,status,createDate) values(#{userName},#{loginName},#{userPassWord},#{status},#{createDate})")
	/* @Options(useGeneratedKeys = true, keyProperty = "id") */
	int add(User user);

	/**
	 * @Title: queryAll
	 * @Description: 查询所有用户
	 * @param @return
	 * @return List<User>
	 * @throws
	 */
	@Select("select * from user order by userId DESC")
	@ResultType(User.class)
	List<User> queryAll();

	/**
	 * @Title: findUserByNameAndPwd
	 * @Description: 通过登录名和密码查找用户
	 * @param @param loginName
	 * @param @param userPassWord
	 * @param @return
	 * @return int
	 * @throws
	 */
	@Select("select COUNT(*) from user where loginName=#{param1} and userPassWord=#{param2}")
	int findUserByNameAndPwd(String loginName, String userPassWord);

	/**
	 * @Title: queryUserByLoginName
	 * @Description: 通过登录名查找用户
	 * @param @param loginName
	 * @param @return
	 * @return User
	 * @throws
	 */
	@Select("select * from user where loginName=#{param1}")
	User queryUserByLoginName(String loginName);

	/**
	 * @Title: deleteUserById
	 * @Description: 通过ID删除用户
	 * @param @param userId
	 * @return void
	 * @throws
	 */
	@Delete("delete from user where userId=#{param1}")
	public void deleteUserById(int userId);

	/**
	 * @Title: findUserByName
	 * @Description: 通过用户名查找用户
	 * @param @param userName
	 * @param @return
	 * @return List<User>
	 * @throws
	 */
	@Select("select * from user where userName=#{param1}")
	@ResultType(User.class)
	List<User> findUserByName(String userName);

	/**
	 * @Title: findUserByStatus
	 * @Description: 通过状态查找用户
	 * @param @param status
	 * @param @return
	 * @return List<User>
	 * @throws
	 */
	@Select("select * from user where status=#{param1}")
	@ResultType(User.class)
	List<User> findUserByStatus(int status);

	/**
	 * @Title: findUserByStaAndName
	 * @Description: 通过状态和用户名查找用户
	 * @param @param userName
	 * @param @param status
	 * @param @return
	 * @return List<User>
	 * @throws
	 */
	@Select("select * from user where userName=#{param1} and status=#{param2}")
	@ResultType(User.class)
	List<User> findUserByStaAndName(String userName, int status);

	/**
	 * @Title: updateUser
	 * @Description: 修改用户
	 * @param @param user
	 * @param @return
	 * @return int
	 * @throws
	 */
	@Update("update user set userName=#{userName},loginName=#{loginName},userPassWord=#{userPassWord},status=#{status} where userId=#{userId}")
	int updateUser(User user);

	/**
	 * @Title: findUserById
	 * @Description: 通过Id查找用户
	 * @param @param userId
	 * @param @return
	 * @return User
	 * @throws
	 */
	@Select("select * from user where userId=#{param1}")
	User findUserById(int userId);
}
