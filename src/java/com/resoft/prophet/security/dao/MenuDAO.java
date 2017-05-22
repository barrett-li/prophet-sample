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
 * 菜单DAO。
 * 
 * @author 韦海晗
 */
public class MenuDAO extends SqlMapClientDaoSupport {
	/**
	 * 返回指定父菜单ID和用户ID的菜单列表
	 * 
	 * @param parentId 父菜单ID
	 * @param userId 用户ID
	 * 
	 * @return 菜单列表
	 */
	public List getMenusByParentId(String parentId, String userId) {
		Map map = new HashMap();
		map.put("parentId", parentId);
		map.put("userId", userId);
		return getSqlMapClientTemplate().queryForList("Security.getMenusByParentId", map);
	}
}
