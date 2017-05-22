/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.log.web;

import java.util.List;
import com.resoft.prophet.log.service.LogService;
import com.resoft.prophet.web.WebWorkAction;

/**
 * 得到用户日志的Action。<br/>
 * 
 * @author 韦海晗
 */

public class GetUserLogsAction extends WebWorkAction {

	private static final long serialVersionUID = -3008387440326068350L;

	private LogService logService;

	private String type;

	private String userName;

	private String beginTime;

	private String endTime;

	private List userLogs;
	
	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getBeginTime() {
		return beginTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public List getUserLogs() {
		return userLogs;
	}
	
	/**
	 * 得到用户日志。<br/>
	 * 
	 */
	public String execute() throws Exception {
		this.userLogs = logService.getUserLogs(type, userName, beginTime, endTime);
		return SUCCESS;
	}
	
}
