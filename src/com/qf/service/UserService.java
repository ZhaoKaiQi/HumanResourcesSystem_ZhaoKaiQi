package com.qf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qf.entity.User;
import com.qf.mapper.IUserMapper;

/**
 * @ClassName: UserService
 * @Description: 用户的业务逻辑层
 * @author 赵凯琦
 * @date 2017-7-11 下午10:23:04
 */
@Service
public class UserService {
	// 注入userMapper
	@Autowired
	private IUserMapper mapper;

	/**
	 * @Title: add
	 * @Description: 添加用户的方法
	 * @param @param user
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean add(User user) {
		return mapper.add(user) > 0 ? true : false;
	}

	/**
	 * @Title: queryAll
	 * @Description: 查询全部user的方法
	 * @param @return
	 * @return List<User>
	 * @throws
	 */
	public List<User> queryAll() {
		return mapper.queryAll();
	}

	/**
	 * @Title: findUserByNameAndPwd
	 * @Description: 校验登录名和密码是否正确的方法
	 * @param @param loginName
	 * @param @param userPassWord
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int findUserByNameAndPwd(String loginName, String userPassWord) {

		if (null == loginName || "".equals(loginName) || null == userPassWord
				|| "".equals(userPassWord)) {
			throw new RuntimeException("所输入登录名或密码为空...");
		} else {
			System.out.println("业务逻辑层执行到了" + loginName + "=" + userPassWord);
			return mapper.findUserByNameAndPwd(loginName, userPassWord);
		}
	}

	/**
	 * @Title: queryUserByLoginName
	 * @Description: 通过登录名查找用户信息
	 * @param @param loginName
	 * @param @return
	 * @return User
	 * @throws
	 */
	public User queryUserByLoginName(String loginName) {
		if (null == loginName || "".equals(loginName)) {
			throw new RuntimeException("所输入登录名为空...");
		} else {
			System.out.println("业务逻辑层执行到了" + loginName);
			// User user=
			return mapper.queryUserByLoginName(loginName);
		}
	}

	/**
	 * @Title: deleteUserById
	 * @Description: 通过id删除用户的方法
	 * @param @param userId
	 * @return void
	 * @throws
	 */
	public void deleteUserById(int userId) {
		if (userId <= 0 || "".equals(userId)) {
			throw new RuntimeException("所输入数据有问题...");
		} else {
			System.out.println("业务逻辑层执行到了" + userId);
			// User user=
			mapper.deleteUserById(userId);
		}
	}

	/**
	 * @Title: findUserByName
	 * @Description: 通过用户名查找用户的方法
	 * @param @param userName
	 * @param @return
	 * @return List<User>
	 * @throws
	 */
	public List<User> findUserByName(String userName) {
		return mapper.findUserByName(userName);
	}

	/**
	 * @Title: findUserByStatus
	 * @Description: 通过用户状态查找用户的方法
	 * @param @param status
	 * @param @return
	 * @return List<User>
	 * @throws
	 */
	public List<User> findUserByStatus(int status) {
		return mapper.findUserByStatus(status);
	}

	/**
	 * @Title: findUserByStaAndName
	 * @Description: 通过用户名和用户状态查找用户方法
	 * @param @param userName
	 * @param @param status
	 * @param @return
	 * @return List<User>
	 * @throws
	 */
	public List<User> findUserByStaAndName(String userName, int status) {
		return mapper.findUserByStaAndName(userName, status);
	}

	/**
	 * @Title: updateUser
	 * @Description: 通过Id更改用户的方法
	 * @param @param user
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean updateUser(User user) {
		return mapper.updateUser(user) > 0 ? true : false;
	}

	/**
	 * @Title: findUserById
	 * @Description: 通过Id查找用户
	 * @param @param userId
	 * @param @return
	 * @return User
	 * @throws
	 */
	public User findUserById(int userId) {
		return mapper.findUserById(userId);
	}
}
