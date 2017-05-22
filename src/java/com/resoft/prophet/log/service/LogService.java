/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.log.service;

import java.util.List;

import com.resoft.prophet.log.domain.SystemLog;
import com.resoft.prophet.log.domain.UserLog;

/**
 * 日志相关服务。<br/>
 *
 * @author wujun
 */

public interface LogService {

	/**
	 * 插入系统日志信息。<br/>
	 * 
	 * @param type     日志类型
	 * @param content  日志类型
	 * @param result   结果
	 *  
	 */
    void insertSystemLog(SystemLog systemLog);

    /**
	 *得到系统日志信息。<br/>
	 * 
	 * @param type        日志类型
	 * @param result      结果
	 * @param beginTime   开始时间
	 * @param endTime     结束时间
	 *  
	 */
    List getSystemLogs(String type, String result, String beginTime, String endTime);

    
    /**
	 *插入用户日志信息。<br/>
	 * 
	 * @param type        日志类型
	 * @param content     日志内容
	 * @param userId      用户ID
	 * @param userName    用户名称
	 *  
	 */
    void insertUserLog(UserLog userLog);
    
    /**
	 *得到用户日志信息。<br/>
	 * 
	 * @param type        日志类型
	 * @param userName    用户名称
	 * @param beginTime   开始时间
	 * @param endTime     结束时间
	 *  
	 */
    List getUserLogs(String type, String userName, String beginTime, String endTime);
}
