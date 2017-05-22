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
 * 查询部门Action。<br/>
 * 
 * @author 韦海晗
 */
public class GetDeptAction extends WebWorkAction {
	private static final long serialVersionUID = -3674498965562297863L;

	private OrgService orgService;

	private Department department;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}

	/**
	 * 得到部门。<br/>
	 * 
	 */
	public String execute() throws Exception {
		department = orgService.getDept(department.getId());
		return SUCCESS;
	}

}
