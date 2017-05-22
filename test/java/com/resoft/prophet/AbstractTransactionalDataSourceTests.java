/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet;

import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * 该类继承自Spring Framework的AbstractTransactionalDataSourceSpringContextTests类，<br/> 提供Spring Context管理和缓存、App Context管理和缓存、公共数据初始化等扩展。
 * 
 * @author 韦海晗
 */
public class AbstractTransactionalDataSourceTests extends AbstractTransactionalDataSourceSpringContextTests {

	public AbstractTransactionalDataSourceTests() {
		// super.setDefaultRollback(false);
		setAutowireMode(AUTOWIRE_BY_NAME);
	}

	public String[] getConfigLocations() {
		return new String[] { "classpath:applicationContext*.xml" };
	}

	/**
	 * 该方法提供App Context管理和缓存、公共数据初始化等扩展。
	 * 
	 */
	public void onSetUpInTransaction() {

	}

	/**
	 * 执行SQL返回行数据的MAP。
	 * 
	 * @param sql
	 * @return 行数据的MAP。
	 */
	public Map getObject(String sql) {
		try {
			Map map = jdbcTemplate.queryForMap(sql);
			return map;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * 执行SQL返回行数据的MAP的列表。
	 * 
	 * @param sql
	 * @return 行数据的MAP的列表。
	 */
	public List getList(String sql) {
		try {
			List list = jdbcTemplate.queryForList(sql);
			return list;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
