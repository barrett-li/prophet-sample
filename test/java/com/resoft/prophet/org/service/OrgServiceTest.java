/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.org.service;

import java.util.List;

import com.resoft.prophet.AbstractTransactionalDataSourceTests;
import com.resoft.prophet.exception.EntityExistedException;
import com.resoft.prophet.exception.HasRefEntitysException;
import com.resoft.prophet.org.domain.Department;
import com.resoft.prophet.org.domain.Employee;
import com.resoft.prophet.util.UUIDGenerator;

/**
 * 组织机构接口单元测试类
 * 
 * @author 伍军军
 */
public class OrgServiceTest extends AbstractTransactionalDataSourceTests {
	private OrgService orgService;

	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}

    public void onSetUpInTransaction() {
        jdbcTemplate.execute("delete from PRO_DEPARTMENT");
        jdbcTemplate.execute("insert into PRO_DEPARTMENT (DEPT_ID, FULLNAME, SHORTNAME, REMARK)"
                + "values ('1', 'a', 'b', 'c')");
        jdbcTemplate.execute("insert into PRO_DEPARTMENT (DEPT_ID, FULLNAME, SHORTNAME, REMARK)"
                + "values ('2', 'aa', 'bb', 'cc')");
        jdbcTemplate.execute("insert into PRO_DEPARTMENT (DEPT_ID, FULLNAME, SHORTNAME, REMARK)"
                + "values ('3', 'a3', 'bb', 'cc')");

        jdbcTemplate.execute("delete from PRO_EMPLOYEE");
        jdbcTemplate.execute("insert into PRO_EMPLOYEE (EMPLOYEE_ID, EMPLOYEE_NAME, DEPT_ID, EMAIL, MOBILE)"
                + "values ('3', 'd', '1', 'e', 'f')");
        jdbcTemplate.execute("insert into PRO_EMPLOYEE (EMPLOYEE_ID, EMPLOYEE_NAME, DEPT_ID, EMAIL, MOBILE)"
                + "values ('4', 'dd', '2', 'ee', 'ff')");
    }
 
    public void testInsertDept() throws Exception {
		String id = UUIDGenerator.getUUID();
		String fullName = "aaa";
		String shortName = "bbb";
		String remark = "ccc";

		Department department1 = new Department();
		department1.setId(id);
		department1.setFullName(fullName);
		department1.setShortName(shortName);
		department1.setRemark(remark);
		orgService.insertDept(department1);

		Department department2 = orgService.getDept(id);
		assertEquals(id, department2.getId());
		assertEquals(fullName, department2.getFullName());
		assertEquals(shortName, department2.getShortName());
		assertEquals(remark, department2.getRemark());
	}

	public void testInsertSameDept() throws Exception {
		String id = UUIDGenerator.getUUID();
		String fullName = "a";
		String shortName = "b";
		String remark = "c";

		Department department1 = new Department();
		department1.setId(id);
		department1.setFullName(fullName);
		department1.setShortName(shortName);
		department1.setRemark(remark);
		try {
			orgService.insertDept(department1);
			assertTrue(false);
		} catch (EntityExistedException e) {
			assertTrue(true);
		}
	}

	public void testDeleteDept() throws Exception {
		String id = "3";
		orgService.deleteDept(id);
		assertEquals(null, orgService.getDept(id));

	}

	public void testDeleteDeptHasEmployee() throws Exception {
		String id = "1";
		try {
			orgService.deleteDept(id);
			assertTrue(false);
		} catch (HasRefEntitysException e) {
			assertTrue(true);
		}
	}

	public void testUpdateDept() throws Exception {
		String id = "1";
		String fullName = "aaa";
		String shortName = "bbb";
		String remark = "ccc";

		Department department1 = orgService.getDept(id);
		department1.setFullName(fullName);
		department1.setShortName(shortName);
		department1.setRemark(remark);
		orgService.updateDept(department1);

		Department department2 = orgService.getDept(id);
		assertEquals(id, department2.getId());
		assertEquals(fullName, department2.getFullName());
		assertEquals(shortName, department2.getShortName());
		assertEquals(remark, department2.getRemark());
	}

	public void testUpdateSameDept() throws Exception {
		String id = "1";
		String fullName = "aa";
		String shortName = "bb";
		String remark = "cc";

		Department department1 = orgService.getDept(id);
		department1.setFullName(fullName);
		department1.setShortName(shortName);
		department1.setRemark(remark);
		try {
			orgService.updateDept(department1);
			assertTrue(false);
		} catch (EntityExistedException e) {
			assertTrue(true);
		}
	}

	public void testGetDept() throws Exception {
		String id = "1";
		Department department1 = orgService.getDept(id);
		assertEquals(id, department1.getId());
		assertEquals("a", department1.getFullName());
		assertEquals("b", department1.getShortName());
		assertEquals("c", department1.getRemark());
	}

	public void testGetAllDepts() throws Exception {
		List list = orgService.getAllDepts("DEPT_ID", "DESC");
		assertTrue(list.size() == 3);
		assertEquals(((Department) list.get(0)).getId(), "3");
		assertTrue(orgService.getAllDepts("DEPT_ID", null).size() == 3);
		assertTrue(orgService.getAllDepts("DEPT_ID", "DESC").size() == 3);
		assertTrue(orgService.getAllDepts(null, null).size() == 3);
	}

	public void testGetDepts() throws Exception {
		String fullName = "a";
		String shortName = "b";
		assertTrue(orgService.getDepts(fullName, null, new Integer(0), new Integer(4), "DEPT_ID", "DESC").size() == 1);
		assertTrue(orgService.getDepts(null, shortName, new Integer(0), new Integer(4), "DEPT_ID", null).size() == 1);
		assertTrue(orgService.getDepts(fullName, shortName, new Integer(0), new Integer(4), "DEPT_ID", null).size() == 1);
		assertTrue(orgService.getDepts(null, null, new Integer(0), new Integer(4), "DEPT_ID", null).size() == 3);
	}

	public void testCountDepts() {
		String fullName = "a";
		String shortName = "b";
		assertTrue(orgService.countDepts(fullName, null).intValue() == 1);
		assertTrue(orgService.countDepts(null, shortName).intValue() == 1);
		assertTrue(orgService.countDepts(fullName, shortName).intValue() == 1);
		assertTrue(orgService.countDepts(null, null).intValue() == 3);
	}

	public void testInsertEmployee() throws Exception {
		String id = UUIDGenerator.getUUID();
		String name = "ddd";
		String departmentId = "1";
		Department department = new Department();
		department.setId(departmentId);
		String email = "eee";
		String mobile = "fff";

		Employee employee1 = new Employee();
		employee1.setId(id);
		employee1.setName(name);
		employee1.setDepartment(department);
		employee1.setEmail(email);
		employee1.setMobile(mobile);
		orgService.insertEmployee(employee1);

		Employee employee2 = orgService.getEmployee(id);
		assertEquals(id, employee2.getId());
		assertEquals(name, employee2.getName());
		assertEquals(departmentId, employee2.getDepartment().getId());
		assertEquals(email, employee2.getEmail());
		assertEquals(mobile, employee2.getMobile());
	}

	public void testDeleteEmployee() throws Exception {
		String id = "3";
		orgService.deleteEmployee(id);
		assertEquals(null, orgService.getEmployee(id));
	}

	public void testUpdateEmployee() throws Exception {
		String id = "3";
		String name = "ddd";
		String departmentId = "1";
		Department department = new Department();
		department.setId(departmentId);
		String email = "eee";
		String mobile = "fff";

		Employee employee1 = orgService.getEmployee(id);
		employee1.setName(name);
		employee1.setDepartment(department);
		employee1.setEmail(email);
		employee1.setMobile(mobile);
		orgService.updateEmployee(employee1);

		Employee employee2 = orgService.getEmployee(id);
		assertEquals(id, employee2.getId());
		assertEquals(name, employee2.getName());
		assertEquals(departmentId, employee2.getDepartment().getId());
		assertEquals(email, employee2.getEmail());
		assertEquals(mobile, employee2.getMobile());
	}

	public void testGetEmployee() throws Exception {
		String id = "3";
		Employee employee1 = orgService.getEmployee(id);
		assertEquals(id, employee1.getId());
		assertEquals("d", employee1.getName());
		assertEquals("1", employee1.getDepartment().getId());
		assertEquals("e", employee1.getEmail());
		assertEquals("f", employee1.getMobile());
	}

	public void testGetEmployees() throws Exception {
		String name = "d";
		String departmentId = "1";
		assertTrue(orgService.getEmployees(name, null, new Integer(0), new Integer(4), "EMPLOYEE_ID", "DESC").size() == 1);
		assertTrue(orgService.getEmployees(null, departmentId, new Integer(0), new Integer(4), "EMPLOYEE_ID", null).size() == 1);
		assertTrue(orgService.getEmployees(name, departmentId, new Integer(0), new Integer(4), "EMPLOYEE_ID", null).size() == 1);
		assertTrue(orgService.getEmployees(null, null, new Integer(0), new Integer(4), "EMPLOYEE_ID", null).size() == 2);
	}

	public void testCountEmployees() {
		String name = "d";
		String departmentId = "1";
		assertTrue(orgService.countEmployees(name, null).intValue() == 1);
		assertTrue(orgService.countEmployees(null, departmentId).intValue() == 1);
		assertTrue(orgService.countEmployees(name, departmentId).intValue() == 1);
		assertTrue(orgService.countEmployees(null, null).intValue() == 2);
	}
	
}
