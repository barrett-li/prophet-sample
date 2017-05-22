/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */
package com.resoft.prophet.org.web;

import com.resoft.prophet.org.domain.Department;
import com.resoft.prophet.org.service.OrgService;
import com.resoft.prophet.web.WebWorkAction;

/**
 * 更新部门Action。<br/>
 * 
 * @author 韦海晗
 */
public class UpdateDeptAction extends WebWorkAction {
	private static final long serialVersionUID = -3674498965562297863L;

	private OrgService orgService;

	private String message;
	
	private Department department;
	
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
	 * 修改部门。<br/>
	 * 
	 */
	public String execute() throws Exception {
		orgService.updateDept(department);
		
		this.setMessage("修改部门成功！");
		return SUCCESS;
	}

}
