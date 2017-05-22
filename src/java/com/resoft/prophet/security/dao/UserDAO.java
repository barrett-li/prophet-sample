/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.security.dao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.resoft.prophet.security.domain.User;

/**
 * 用户信息DAO。
 * 
 * @author 韦海晗
 */
public class UserDAO extends SqlMapClientDaoSupport {
	/**
	 * 根据用户ID返回用户信息
	 * 
	 * @param id 用户名ID
	 * 
	 * @return 用户信息
	 */
	public User getUserById(String id) {
		return (User) getSqlMapClientTemplate().queryForObject("Security.getUserById", id);
	};

	/**
	 * 更新用户。<br/>
	 * 
	 * @param user 用户
	 */
	public void updateUser(User user) {
		getSqlMapClientTemplate().update("Security.updateUser", user);
	};

	/**
	 * 根据用户名返回用户信息
	 * 
	 * @param username 用户名
	 * @return 用户信息
	 */
	public User getUserByUsername(String username) {
		return (User) getSqlMapClientTemplate().queryForObject("Security.getUserByUsername", username);
	}
}
