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
 * 资源DAO。
 * 
 * @author 韦海晗
 */
public class ResourceDAO extends SqlMapClientDaoSupport {
	/**
	 * 返回指定类型的资源信息列表。
	 * @param types 资源类型
	 * 
	 * @return 资源信息列表
	 */
	public List getResourceByTypes(String[] types) {
		StringBuffer buf = new StringBuffer();
		buf.append("'");
		for(int i = 0; i < types.length; i++) {
			buf.append(types[i]);
			buf.append(i < (types.length - 1) ? "', '" : "");
		}
		buf.append("'");
		Map map = new HashMap();
		map.put("types", buf.toString());
		return getSqlMapClientTemplate().queryForList("Security.getResourceByTypes", map);
	}
}
