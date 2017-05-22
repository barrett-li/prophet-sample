/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.log.service;

import java.util.List;

import com.resoft.prophet.log.dao.SystemLogDAO;
import com.resoft.prophet.log.dao.UserLogDAO;
import com.resoft.prophet.log.domain.SystemLog;
import com.resoft.prophet.log.domain.UserLog;

/**
 * 日志相关服务实现。<br/>
 *
 * @author 伍军军
 */
public class LogServiceImpl implements LogService {
		
	private UserLogDAO userLogDAO;
	private SystemLogDAO systemLogDAO;

	public void setSystemLogDAO(SystemLogDAO systemLogDAO) {
		this.systemLogDAO = systemLogDAO;
	}

	public void setUserLogDAO(UserLogDAO userLogDAO) {
		this.userLogDAO = userLogDAO;
	}
	
	/* (non-Javadoc)
	 * @see com.resoft.prophet.log.service.LogService#getSystemLogs(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List getSystemLogs(String type, String result, String beginTime, String endTime) {
		
		return systemLogDAO.getSystemLogs(type, result, beginTime, endTime);
	}

	/* (non-Javadoc)
	 * @see com.resoft.prophet.log.service.LogService#getUserLogs(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List getUserLogs(String type, String userName, String beginTime, String endTime) {
		
		return userLogDAO.getUserLogs(type,userName,beginTime,endTime);
	}

	/* (non-Javadoc)
	 * @see com.resoft.prophet.log.service.LogService#insertSystemLog(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void insertSystemLog(SystemLog systemLog) {
	
		systemLogDAO.insertSystemLog(systemLog);
		
	}
	
	/* (non-Javadoc)
	 * @see com.resoft.prophet.log.service.LogService#insertUserLog(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void insertUserLog(UserLog userLog) {
		
		userLogDAO.insertUserLog(userLog);
		
	}
	
 }