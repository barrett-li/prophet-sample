/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.org.web;

import java.util.List;

import com.resoft.prophet.org.service.OrgService;
import com.resoft.prophet.web.WebWorkAction;

/**
 * 查询部门Action。<br/>
 * 
 * @author 韦海晗
 */
public class GetDeptsAction extends WebWorkAction {
	private static final long serialVersionUID = -3674498965562297863L;

	private OrgService orgService;

	private String fullName;
	
	private String shortName;

	private List depts;
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}

	public List getDepts() {
		return depts;
	}

	/**
	 * 得到部门列表。<br/>
	 * 
	 */
	public String execute() throws Exception {
		this.depts = orgService.getDepts(fullName, shortName, null, null, "", "");
		return SUCCESS;
	}

}
