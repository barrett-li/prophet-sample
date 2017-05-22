/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.log.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;

import com.resoft.prophet.log.domain.UserLog;
import com.resoft.prophet.log.service.LogService;
import com.resoft.prophet.org.domain.Department;
import com.resoft.prophet.org.domain.Employee;
import com.resoft.prophet.util.DateUtil;
import com.resoft.prophet.util.UUIDGenerator;

/**
 * 组织机构日志处理类。<br/>
 *
 * @author 伍军军
 */

public class OrgUserLog {
    private static Log log = LogFactory.getLog(OrgUserLog.class);

    private LogService logService;
    

	public void setLogService(LogService logService) {
        this.logService = logService;
    }
    
    /**
	 * 记录日志方法。<br/>
	 * 
	 * @param call   连接点对象   
	 * 
	 * @return Object  
     * @throws Throwable 
	 * 
	 */
    public Object writeLog(ProceedingJoinPoint call) throws Throwable {
        Object obj = null;
        String methodName = call.getSignature().getName();
        String target = call.getTarget().getClass().getName();
        log.info("The method(" + methodName + ") of Target(" + target + ") is a PointCut.");
        Object[] args = call.getArgs();
        if ("insertDept".equals(methodName)) {
            Department department = (Department) args[0];
            obj = call.proceed();
            writeInsertDeptLog(department);
        }
        if ("deleteDept".equals(methodName)) {
        	String  id  = (String) args[0];
        	//Department department = orgService.getDept(id);
            obj = call.proceed();
            writeDeleteDeptLog(id);
        }
        if ("insertEmployee".equals(methodName)) {
        	Employee employee = (Employee) args[0];
            obj = call.proceed();
            writeInsertEmployeeLog(employee);
        }
        if ("deleteEmployee".equals(methodName)) {
        	String  id  = (String) args[0];
            obj = call.proceed();
            writeDeleteEmployeeLog(id);
        }
        return obj;
    }

    /**
	 * 记录插入部门日志。<br/>
	 * 
	 * @param department   部门对象    
	 * 
	 */
    private void writeInsertDeptLog(Department department) {
        if (department == null) {
            return;
        }
        String deptFullName = department.getFullName();
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
    }
    
    /**
	 * 记录删除部门日志。<br/>
	 * 
	 * @param id   部门id   
	 * 
	 */
    private void writeDeleteDeptLog(String id) {  

        String deptFullName = id;
        String userId = "1";
        String userName = "admin";
        String type = "";
        String content = userName + "删除部门对应id为:" + deptFullName;
        UserLog userLog = new UserLog();
		userLog.setId(UUIDGenerator.getUUID());
		userLog.setType(type);
		userLog.setContent(content);
		userLog.setUserId(userId);
		userLog.setUserName(userName);
		userLog.setCreateTime(DateUtil.today2String(DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
        logService.insertUserLog(userLog);
    }

    /**
	 * 记录插入员工日志。<br/>
	 * 
	 * @param employee   员工对象    
	 * 
	 */
    private void writeInsertEmployeeLog(Employee employee) {
        if (employee == null) {
            return;
        }
        String name = employee.getName();
        String userId = "1";
        String userName = "admin";
        String type = "";
        String content = userName + "新增员工:" + name;
        UserLog userLog = new UserLog();
		userLog.setId(UUIDGenerator.getUUID());
		userLog.setType(type);
		userLog.setContent(content);
		userLog.setUserId(userId);
		userLog.setUserName(userName);
		userLog.setCreateTime(DateUtil.today2String(DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
        logService.insertUserLog(userLog);
    }
    
    
    /**
	 * 记录删除员工日志。<br/>
	 * 
	 * @param id   员工ID    
	 * 
	 */
    private void writeDeleteEmployeeLog(String id) {
        
        String name = id;
        String userId = "1";
        String userName = "admin";
        String type = "";
        String content = userName + "删除员工对于id为:" + name;
        UserLog userLog = new UserLog();
		userLog.setId(UUIDGenerator.getUUID());
		userLog.setType(type);
		userLog.setContent(content);
		userLog.setUserId(userId);
		userLog.setUserName(userName);
		userLog.setCreateTime(DateUtil.today2String(DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
        logService.insertUserLog(userLog);
    }
}
