/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.org.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.resoft.prophet.org.domain.Department;
import com.resoft.prophet.org.service.OrgService;
import com.resoft.prophet.web.ExcelAction;

/**
 * 部门EXCEL导出Action。<br/>
 * 
 * @author 韦海晗
 */
public class ExprotExcelDeptsAction extends ExcelAction {
	private static final long serialVersionUID = -3674498965562297863L;

	private OrgService orgService;

	private String fullName;

	private String shortName;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	/**
	 * 部门列表导出excel。<br/>
	 * 
	 */
	public String execute() throws Exception {

		List deptList = orgService.getDepts(fullName, shortName, null, null, "", "");
		List headerRow = new ArrayList();
		List valueRows = new ArrayList();
		headerRow.add("部门ID");
		headerRow.add("部门全称");
		headerRow.add("部门简称");
		headerRow.add("备注");
		Iterator it = deptList.iterator();
		while (it.hasNext()) {
			List valueRow = new ArrayList();
			Department department = (Department) it.next();
			valueRow.add(department.getId());
			valueRow.add(department.getFullName());
			valueRow.add(department.getShortName());
			valueRow.add(department.getRemark());
			valueRows.add(valueRow);
		}
		excelForNoTemplate(headerRow, valueRows, "部门信息.xls");
		return SUCCESS;
	}

}
