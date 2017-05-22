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
 * 删除部门Action。<br/>
 * 
 * @author 韦海晗
 */
public class DeleteDeptAction extends WebWorkAction {
	private static final long serialVersionUID = -3674498965562297863L;

	private OrgService orgService;
	private String message;
	private Department department;

	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public String getMessage() {
		return message;
	}
	
	/**
	 * 删除部门。<br/>
	 * 
	 */
	public String execute() throws Exception {
		orgService.deleteDept(department.getId());
		
		this.message = "删除部门成功！";
		return SUCCESS;
	}

}
