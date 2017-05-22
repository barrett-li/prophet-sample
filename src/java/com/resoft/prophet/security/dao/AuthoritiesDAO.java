/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.security.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * 权限DAO。
 * 
 * @author 韦海晗
 */
public class AuthoritiesDAO extends SqlMapClientDaoSupport {
	/**
	 * 根据用户名和前缀返回权限信息
	 * 
	 * @param username 用户名
	 * @param rolePrefix 权限前缀
	 * @return 权限信息
	 */
	public List getAuthoritiesByUsername(String username, String rolePrefix) {
        Map map = new HashMap();
        map.put("username", username);
        map.put("rolePrefix", rolePrefix);
        return getSqlMapClientTemplate().queryForList("Security.getAuthoritiesByUsername", map);
	}
	
	/**
	 * 根据资源串返回权限信息列表。
	 * 
	 * @param resString 资源串
	 * 
	 * @return 权限信息列表
	 */
	public List getAuthoritiesByResString(String resString, String rolePrefix) {
        Map map = new HashMap();
        map.put("resString", resString);
        map.put("rolePrefix", rolePrefix);
        return getSqlMapClientTemplate().queryForList("Security.getAuthoritiesByResString", map);
	}
}
