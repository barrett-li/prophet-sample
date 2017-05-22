/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.log.service;

import java.util.List;

import com.resoft.prophet.AbstractTransactionalDataSourceTests;
import com.resoft.prophet.log.domain.SystemLog;
import com.resoft.prophet.log.domain.UserLog;
import com.resoft.prophet.util.DateUtil;
import com.resoft.prophet.util.UUIDGenerator;

/**
 * 日志接口单元测试类。
 * 
 * @author 伍军军
 */
public class LogServiceTest extends AbstractTransactionalDataSourceTests {
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

    public void onSetUpInTransaction() {
        jdbcTemplate.execute("delete from PRO_USER_LOG");
        jdbcTemplate.execute("insert into PRO_USER_LOG (USER_LOG_ID, USER_LOG_TYPE, CONTENT,USERID,USERNAME,CREATETIME)"
                + "values ('1', '1100', '插入数据1','id1','admin1', '2008-09-02')");
        jdbcTemplate.execute("insert into PRO_USER_LOG (USER_LOG_ID, USER_LOG_TYPE, CONTENT,USERID,USERNAME,CREATETIME)"
                + "values ('2', '220', '插入数据2','id2','admin2','2008-09-02')");
        jdbcTemplate.execute("insert into PRO_USER_LOG (USER_LOG_ID, USER_LOG_TYPE, CONTENT,USERID,USERNAME,CREATETIME)"
                + "values ('3', '330', '插入数据3','id3','admin3', '2008-09-02')");
        
        jdbcTemplate.execute("delete from PRO_SYSTEM_LOG");
        jdbcTemplate.execute("insert into PRO_SYSTEM_LOG (SYS_LOG_ID, SYS_LOG_TYPE, CONTENT,SYS_LOG_RESULT,CREATETIME)"
                + "values ('1', '110', '插入数据1','11', '2008-09-02')");
        jdbcTemplate.execute("insert into PRO_SYSTEM_LOG (SYS_LOG_ID, SYS_LOG_TYPE, CONTENT,SYS_LOG_RESULT,CREATETIME)"
                + "values ('2', '220', '插入数据2','22','2008-09-02')");
        jdbcTemplate.execute("insert into PRO_SYSTEM_LOG (SYS_LOG_ID, SYS_LOG_TYPE, CONTENT,SYS_LOG_RESULT,CREATETIME)"
                + "values ('3', '330', '插入数据3','33', '2008-09-02')");
    }

    public void testInsertUserLog() throws Exception {
		String deptFullName = "@@@@@@@@@@";
		String userId = "1";
		String userName = "admin";
		String type = "1111";
		String content = userName + "新增部门:" + deptFullName;

		UserLog userLog = new UserLog();
		userLog.setId(UUIDGenerator.getUUID());
		userLog.setType(type);
		userLog.setContent(content);
		userLog.setUserId(userId);
		userLog.setUserName(userName);
		userLog.setCreateTime(DateUtil.today2String(DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
		logService.insertUserLog(userLog);

		List logList = (List) logService.getUserLogs("", "", "", "");
		assertEquals(4, logList.size());
		List logList1 = (List) logService.getUserLogs("", "admin", "", "");
		assertEquals(1, logList1.size());
		assertEquals("admin", ((UserLog) logList1.get(0)).getUserName());
		List logList2 = (List) logService.getUserLogs("", "admin", "", "");
		assertEquals("admin新增部门:@@@@@@@@@@", ((UserLog) logList2.get(0)).getContent());
	}

	public void testInsertSystemLog() throws Exception {
		String type = "1111";
		String content = "系统日志:1";
		SystemLog systemLog = new SystemLog();
		systemLog.setId(UUIDGenerator.getUUID());
		systemLog.setType(type);
		systemLog.setContent(content);
		systemLog.setResult("31");
		systemLog.setCreateTime(DateUtil.today2String(DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
		List logList0 = (List) logService.getSystemLogs("", "", "", "");
		assertEquals(3, logList0.size());
		logService.insertSystemLog(systemLog);
		List logList = (List) logService.getSystemLogs("", "", "", "");
		assertEquals(4, logList.size());
	}
    
}
