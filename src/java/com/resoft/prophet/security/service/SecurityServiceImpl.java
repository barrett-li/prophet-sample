/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.security.service;

import java.util.List;

import com.resoft.prophet.security.dao.AuthoritiesDAO;
import com.resoft.prophet.security.dao.MenuDAO;
import com.resoft.prophet.security.dao.ResourceDAO;
import com.resoft.prophet.security.dao.UserDAO;
import com.resoft.prophet.security.domain.User;

/**
 * 系统安全管理服务实现类。
 * 
 * @author 韦海晗
 */
public class SecurityServiceImpl implements SecurityService {
	private AuthoritiesDAO authoritiesDAO;

	private ResourceDAO resourceDAO;

	private UserDAO userDAO;

	private MenuDAO menuDAO;

	public void setAuthoritiesDAO(AuthoritiesDAO authoritiesDAO) {
		this.authoritiesDAO = authoritiesDAO;
	}

	public void setResourceDAO(ResourceDAO resourceDAO) {
		this.resourceDAO = resourceDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.resoft.prophet.security.SecurityService#getAuthoritiesByResString(java.lang.String, java.lang.String)
	 */
	public List getAuthoritiesByResString(String resString, String rolePrefix) {
		return authoritiesDAO.getAuthoritiesByResString(resString, rolePrefix);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.resoft.prophet.security.SecurityService#getAuthoritiesByUsername(java.lang.String, java.lang.String)
	 */
	public List getAuthoritiesByUsername(String username, String rolePrefix) {
		return authoritiesDAO.getAuthoritiesByUsername(username, rolePrefix);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.resoft.prophet.security.SecurityService#getResource()
	 */
	public List getResourceByTypes(String[] types) {
		return resourceDAO.getResourceByTypes(types);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.resoft.prophet.security.SecurityService#getUserByUsername(java.lang.String)
	 */
	public User getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.resoft.prophet.security.service.SecurityService#getMenusByParentId(java.lang.String, java.lang.String)
	 */
	public List getMenusByParentId(String parentId, String userId) {
		return menuDAO.getMenusByParentId(parentId, userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.resoft.prophet.security.service.SecurityService#updateUser(com.resoft.prophet.security.domain.User)
	 */
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.resoft.prophet.security.service.SecurityService#getUserById(java.lang.String)
	 */
	public User getUserById(String id) {
		return userDAO.getUserById(id);
	}
}
