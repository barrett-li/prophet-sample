/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.org.web;

import com.resoft.prophet.org.domain.Department;
import com.resoft.prophet.org.service.OrgService;
import com.resoft.prophet.util.UUIDGenerator;
import com.resoft.prophet.web.WebWorkAction;

/**
 * 增加部门Action。<br/>
 * 
 * @author 韦海晗
 */
public class InsertDeptAction extends WebWorkAction {
	private static final long serialVersionUID = -3674498965562297863L;

	private OrgService orgService;

	private Department department;

	private String message;
	
	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 增加部门。<br/>
	 * 
	 */
	public String execute() throws Exception {

		String id = UUIDGenerator.getUUID();
		department.setId(id);

		orgService.insertDept(department);
		
		this.setMessage("新增部门成功！");
		return SUCCESS;
	}

}
