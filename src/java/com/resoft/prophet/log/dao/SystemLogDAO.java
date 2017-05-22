/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.log.dao;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.resoft.prophet.log.domain.SystemLog;

/**
 * 系统日志DAO。<br/>
 * 
 * @author 伍军军
 */

public class SystemLogDAO extends SqlMapClientDaoSupport {
	
	/**
	 * 插入系统日志信息。<br/>
	 * 
	 * @param type     日志类型
	 * @param content  日志类型
	 * @param userId   用户ID
	 * @param userName 用户名称
	 * 
	 */
	public void insertSystemLog(SystemLog systemLog) {
		
		getSqlMapClientTemplate().insert("SystemLog.insertSystemLog", systemLog);
	}
	
	/**
	 * 得到系统日志信息。<br/>
	 * 
	 * @param type      日志类型
	 * @param userName  用户名称
	 * @param beginTime 开始时间
	 * @param endTime   结束时间
	 * 
	 */
	public List getSystemLogs(String type, String result, String beginTime, String endTime) {
		Map map = new HashMap();
		map.put("type", type);
		map.put("result", result);
        map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		return getSqlMapClientTemplate().queryForList("SystemLog.getSystemLog", map);
	}
}