/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */
package com.resoft.prophet.charts.sample.service;

import java.util.List;

import com.resoft.prophet.AbstractTransactionalDataSourceTests;
import com.resoft.prophet.charts.sample.domain.BankLoan;

/**
 * 演示数据服务单元测试类
 * 
 * @author 韦海晗
 */
public class ChartsDataServiceTest extends AbstractTransactionalDataSourceTests {
	private ChartsDataService chartsDataService;

	public void setChartsDataService(ChartsDataService chartsDataService) {
		this.chartsDataService = chartsDataService;
	}
	
    public void onSetUpInTransaction() {
        jdbcTemplate.execute("delete from PRO_BANK_LOAN");
        jdbcTemplate.execute("insert into PRO_BANK_LOAN (AREA, ORG, DEAD_LOANS, OVERDUE_LOANS, VIOLATION_LOANS) values " +
        		"('北京','中国银行', 23000.00, 30200.00, 46700.00)");
        jdbcTemplate.execute("insert into PRO_BANK_LOAN (AREA, ORG, DEAD_LOANS, OVERDUE_LOANS, VIOLATION_LOANS) values " +
        		"('北京','建设银行', 21000.00, 20200.00, 38700.00)");

        jdbcTemplate.execute("insert into PRO_BANK_LOAN (AREA, ORG, DEAD_LOANS, OVERDUE_LOANS, VIOLATION_LOANS) values " +
        		"('上海','中国银行', 53000.00, 40200.00, 46700.00)");
        jdbcTemplate.execute("insert into PRO_BANK_LOAN (AREA, ORG, DEAD_LOANS, OVERDUE_LOANS, VIOLATION_LOANS) values " +
        		"('上海','建设银行', 33000.00, 21200.00, 16700.00)");

        jdbcTemplate.execute("insert into PRO_BANK_LOAN (AREA, ORG, DEAD_LOANS, OVERDUE_LOANS, VIOLATION_LOANS) values " +
        		"('深圳','中国银行', 31200.00, 54200.00, 46700.00)");
        jdbcTemplate.execute("insert into PRO_BANK_LOAN (AREA, ORG, DEAD_LOANS, OVERDUE_LOANS, VIOLATION_LOANS) values " +
        		"('深圳','建设银行', 43000.00, 55200.00, 39800.00)");
    }
	
	public void testGetBankLoans() {
		String area = "北京";
		String org = "中国银行";
		List list = chartsDataService.getBankLoans(area, null, new Integer(0), new Integer(4), "DEAD_LOANS", "DESC");

		assertTrue(list.size() == 2);
		assertEquals(((BankLoan) list.get(0)).getOrg(), "中国银行");
		assertTrue(chartsDataService.getBankLoans(null, org, new Integer(0), new Integer(4), "AREA", null).size() == 3);
		assertTrue(chartsDataService.getBankLoans(area, org, new Integer(0), new Integer(4), "ORG", null).size() == 1);
		assertTrue(chartsDataService.getBankLoans(null, null, new Integer(0), new Integer(4), "ORG", null).size() == 4);
	}

	public void testCountBankLoans() {
		String area = "北京";
		String org = "中国银行";
		assertEquals(chartsDataService.countBankLoans(area, null).intValue(), 2);
		assertEquals(chartsDataService.countBankLoans(null, org).intValue(), 3);
		assertEquals(chartsDataService.countBankLoans(area, org).intValue(), 1);
		assertEquals(chartsDataService.countBankLoans(null, null).intValue(), 6);
	}

}
