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
import com.resoft.prophet.log.domain.UserLog;

/**
 * 用户日志DAO。<br/>
 * 
 * @author 伍军军
 */

public class UserLogDAO extends SqlMapClientDaoSupport {
	
	/**
	 * 插入用户日志信息。
	 * 
	 * @param userLog   用户日志领域对象
	 * 
	 */
	public void insertUserLog(UserLog userLog) {
		
		getSqlMapClientTemplate().insert("UserLog.insertUserLog", userLog);
	}
	
	/**
	 * 得到用户日志信息。<br/>
	 * 
	 * @param type      日志类型
	 * @param userName  用户名称
	 * @param beginTime 开始时间
	 * @param endTime   结束时间
	 * 
	 */
	public List getUserLogs(String type, String userName, String beginTime, String endTime) {
		Map map = new HashMap();
		map.put("type", type);
		map.put("userName", userName);
        map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		return getSqlMapClientTemplate().queryForList("UserLog.getUserLogs", map);
	}
}