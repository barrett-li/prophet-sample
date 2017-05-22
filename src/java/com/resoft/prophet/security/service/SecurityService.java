/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.security.service;

import java.util.List;

import com.resoft.prophet.security.domain.User;

/**
 * 系统安全管理服务接口。
 * 
 * @author 韦海晗
 */
public interface SecurityService {
	/**
	 * 根据用户名和前缀返回权限信息
	 * 
	 * @param username 用户名
	 * @param rolePrefix 权限前缀
	 * @return 权限信息
	 */
	public List getAuthoritiesByUsername(String username, String rolePrefix);

	/**
	 * 根据资源串返回权限信息列表
	 * 
	 * @param resString 资源串
	 * 
	 * @return 权限信息列表
	 */
	public List getAuthoritiesByResString(String resString, String rolePrefix);

	/**
	 * 返回指定类型的资源信息列表
	 * 
	 * @param types 资源类型
	 * 
	 * @return 资源信息列表
	 */
	public List getResourceByTypes(String[] types);

	/**
	 * 根据用户名返回用户信息
	 * 
	 * @param username 用户名
	 * 
	 * @return 用户信息
	 */
	public User getUserByUsername(String username);

	/**
	 * 根据用户ID返回用户信息
	 * 
	 * @param id 用户名ID
	 * 
	 * @return 用户信息
	 */
	public User getUserById(String id);

	/**
	 * 更新用户。<br/>
	 * 
	 * @param user 用户
	 */
	public void updateUser(User user);

	/**
	 * 返回指定父菜单ID和用户ID的菜单列表
	 * 
	 * @param parentId 父菜单ID
	 * @param userId 用户ID
	 * 
	 * @return 菜单列表
	 */
	public List getMenusByParentId(String parentId, String userId);

}
