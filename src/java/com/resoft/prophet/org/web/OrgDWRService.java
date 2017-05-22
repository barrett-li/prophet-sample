/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.org.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.resoft.prophet.exception.EntityExistedException;
import com.resoft.prophet.exception.HasRefEntitysException;
import com.resoft.prophet.org.domain.Department;
import com.resoft.prophet.org.domain.Employee;
import com.resoft.prophet.org.service.OrgService;
import com.resoft.prophet.util.UUIDGenerator;
import com.resoft.prophet.web.DWRService;

/**
 * 组织机构管理DWR服务。<br/>
 * 
 * @author 韦海晗
 */
public class OrgDWRService extends DWRService {
	private OrgService orgService;

	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}

	/**
	 * 新增部门
	 * 
	 * @param department 部门
	 * 
	 * @throws EntityExistedException 如果数据表中存在相同的部门，则抛出该异常
	 */
	public void insertDept(Department department) throws EntityExistedException {
		department.setId(UUIDGenerator.getUUID());
		orgService.insertDept(department);
	}

	/**
	 * 删除部门
	 * 
	 * @param id 部门ID
	 * 
	 * @throws HasRefEntitysException 如果该部门还有下属员工，则抛出该异常
	 */
	public void deleteDept(String id) throws HasRefEntitysException {
		orgService.deleteDept(id);
	}

	/**
	 * 更新部门
	 * 
	 * @param department 部门
	 * 
	 * @throws EntityExistedException 如果数据表中存在相同的部门，则抛出该异常
	 */
	public void updateDept(Department department) throws EntityExistedException {
		orgService.updateDept(department);
	}

	/**
	 * 根据部门ID获得部门
	 * 
	 * @param id 部门ID
	 * 
	 * @return 部门
	 */
	public Department getDept(String id) {
		return orgService.getDept(id);
	}

	/**
	 * 获得全部部门列表。<br/>
	 * 
	 * @param start 当前页起始行
	 * @param limit 当前页结束行
	 * @param sort 排序的列
	 * @param direction 排序顺序，'ASC'或者'DESC'
	 * @param root 表格数据集根节点键名，用于设置结果集的键
	 * @param totalProperty 表格数据集行数节点键名，用于设置行数的键
	 * @param successProperty 表示构造是否成功键名，用于设置构造成功的键
	 * @param params 查询参数
	 * 
	 * @return 部门数据集，该数据集被json协议序列化后的表示： { 'totalProperty': 2, 'successProperty': true, 'root': [ { 'id': 1, 'name': 'Bill' }, { 'id': 2, 'name':
	 *         'Ben' } ] }
	 *         
	 * @exception Exception
	 */
    public Map getAllDeptsDataset(Integer start, Integer limit, String sort, String direction, String root, String totalProperty, String successProperty, Map params) {
		List depts = orgService.getAllDepts(sort, direction);
		Integer count = new Integer(depts.size());

		Map deptsMap = new HashMap();
		deptsMap.put(root, depts);
		deptsMap.put(totalProperty, count);
		return deptsMap;
    };
    
	/**
	 * 获得全部部门列表。<br/>
	 * 
	 * @param sort 列表排序字段
	 * @param direction 列表排序方式, 'ASC'或'DESC'
	 * 
	 * @return 部门列表
	 */
	public List getAllDepts(String sort, String direction) {
		return orgService.getAllDepts(sort, direction);
	}
    
	/**
	 * 根据查询参数取部门数据集。
	 * 
	 * @param start 当前页起始行
	 * @param limit 当前页结束行
	 * @param sort 排序的列
	 * @param direction 排序顺序，'ASC'或者'DESC'
	 * @param root 表格数据集根节点键名，用于设置结果集的键
	 * @param totalProperty 表格数据集行数节点键名，用于设置行数的键
	 * @param successProperty 表示构造是否成功键名，用于设置构造成功的键
	 * @param params 查询参数
	 * 
	 * @return 部门数据集，该数据集被json协议序列化后的表示： { 'totalProperty': 2, 'successProperty': true, 'root': [ { 'id': 1, 'name': 'Bill' }, { 'id': 2, 'name':
	 *         'Ben' } ] }
	 *         
	 * @exception Exception
	 */
	public Map getDeptsDataset(Integer start, Integer limit, String sort, String direction, String root, String totalProperty, String successProperty, Map params) {
		String fullName = (String) params.get("fullName");
		String shortName = (String) params.get("shortName");

		List depts = orgService.getDepts(fullName, shortName, start, limit, sort, direction);
		Integer count = orgService.countDepts(fullName, shortName);

		Map deptsMap = new HashMap();
		deptsMap.put(root, depts);
		deptsMap.put(totalProperty, count);
		return deptsMap;
	}

	/**
	 * 新增员工
	 * 
	 * @param employee 员工
	 */
	public void insertEmployee(Employee employee) {
		employee.setId(UUIDGenerator.getUUID());
		orgService.insertEmployee(employee);
	}

	/**
	 * 删除员工
	 * 
	 * @param id 员工ID
	 */
	public void deleteEmployee(String id) {
		orgService.deleteEmployee(id);
	}

	/**
	 * 更新员工
	 * 
	 * @param employee 员工
	 */
	public void updateEmployee(Employee employee) {
		orgService.updateEmployee(employee);
	}

	/**
	 * 根据员工ID获得员工
	 * 
	 * @param id 员工ID
	 * 
	 * @return 员工
	 */
	public Employee getEmployee(String id) {
		return orgService.getEmployee(id);
	}

	/**
	 * 根据查询参数取员工数据集
	 * 
	 * @param start 当前页起始行
	 * @param limit 当前页结束行
	 * @param sort 排序的列
	 * @param direction 排序顺序，'ASC'或者'DESC'
	 * @param root 表格数据集根节点键名，用于设置结果集的键
	 * @param totalProperty 表格数据集行数节点键名，用于设置行数的键
	 * @param successProperty 表示构造是否成功键名，用于设置构造成功的键
	 * @param params 查询参数
	 * 
	 * @return 员工数据集，该数据集被json协议序列化后的表示： { 'totalProperty': 2, 'successProperty': true, 'root': [ { 'id': 1, 'name': 'Bill' }, { 'id': 2, 'name':
	 *         'Ben' } ] }
	 *         
	 * @exception Exception.
	 */
	public Map getEmployeesDataset(Integer start, Integer limit, String sort, String direction, String root, String totalProperty, String successProperty, Map params) {
		String fullName = (String) params.get("fullName");
		String departmentId = (String) params.get("departmentId");

		List employees = orgService.getEmployees(fullName, departmentId, start, limit, sort, direction);
		Integer count = orgService.countEmployees(fullName, departmentId);

		Map employeesMap = new HashMap();
		employeesMap.put(root, employees);
		employeesMap.put(totalProperty, count);
		return employeesMap;
	}
}
