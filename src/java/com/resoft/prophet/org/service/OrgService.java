/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.org.service;

import java.util.List;
import com.resoft.prophet.exception.EntityExistedException;
import com.resoft.prophet.exception.HasRefEntitysException;
import com.resoft.prophet.org.domain.Department;
import com.resoft.prophet.org.domain.Employee;

/**
 * 组织机构管理服务接口。<br/>
 *
 * @author 伍军军
 */
public interface OrgService {
	/**
	 * 新增部门。<br/>
	 * 
	 * @param department 部门
	 * 
	 * @throws EntityExistedException 如果数据表中存在相同的部门，则抛出该异常
	 */
	public void insertDept(Department department) throws EntityExistedException;
	
	/**
	 * 删除部门。<br/>
	 * 
	 * @param id 部门ID
	 * 
	 * @throws HasRefEntitysException 如果该部门还有下属员工，则抛出该异常
	 */
	public void deleteDept(String id) throws HasRefEntitysException;

	/**
	 * 更新部门。<br/>
	 * 
	 * @param department 部门
	 * 
	 * @throws EntityExistedException 如果数据表中存在相同的部门，则抛出该异常
	 */
	public void updateDept(Department department) throws EntityExistedException;

	/**
	 * 根据部门ID获得部门。<br/>
	 * 
	 * @param id 部门ID
	 * 
	 * @return 部门
	 */
	public Department getDept(String id);

	/**
	 * 获得全部部门列表。<br/>
	 * 
	 * @param sort 列表排序字段
	 * @param direction 列表排序方式, 'ASC'或'DESC'
	 * 
	 * @return 部门列表
	 */
    public List getAllDepts(String sort, String direction);
	
	/**
	 * 根据部门全称或简称获得部门列表。
	 * 
	 * @param fullName 部门全称
	 * @param shortName 部门简称
	 * @param firstResult 列表起始位置
	 * @param maxResults 列表最大记录数
	 * @param sort 列表排序字段
	 * @param direction 列表排序方式, 'ASC'或'DESC'
	 * 
	 * @return 部门列表
	 */
	public List getDepts(String fullName, String shortName, Integer firstResult, Integer maxResults, String sort, String direction);

	/**
	 * 根据部门全称或简称统计部门记录数。<br/>
	 * 
	 * @param fullName 部门全称
	 * @param shortName 部门简称
	 * 
	 * @return 部门记录数
	 */
	public Integer countDepts(String fullName, String shortName);
	
	/**
	 * 新增员工。<br/>
	 * 
	 * @param employee 员工
	 * 
	 */
	public void insertEmployee(Employee employee);
	
	/**
	 * 删除员工。<br/>
	 * 
	 * @param id 员工ID
	 * 
	 */
	public void deleteEmployee(String id);

	/**
	 * 更新员工。<br/>
	 * 
	 * @param employee 员工
	 */
	public void updateEmployee(Employee employee);

	/**
	 * 根据员工ID获得员工。<br/>
	 * 
	 * @param id 员工ID
	 * 
	 * @return 员工
	 */
	public Employee getEmployee(String id);

	/**
	 * 根据员工全称或所属部门ID获得员工列表。<br/>
	 * 
	 * @param name 员工全称
	 * @param departmentId 所属部门ID
	 * @param firstResult 列表起始位置
	 * @param maxResults 列表最大记录数
	 * @param sort 列表排序字段
	 * @param direction 列表排序方式, 'ASC'或'DESC'
	 * 
	 * @return 员工列表
	 */
	public List getEmployees(String name, String departmentId, Integer firstResult, Integer maxResults, String sort, String direction);
	
	/**
	 * 根据员工全称或所属部门ID统计部门记录数。<br/>
	 * 
	 * @param name 员工全称
	 * @param departmentId 所属部门ID
	 * 
	 * @return 员工记录数
	 */
	public Integer countEmployees(String name, String departmentId);
}
