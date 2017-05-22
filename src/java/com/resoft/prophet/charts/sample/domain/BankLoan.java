/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.sample.domain;

/**
 * 图表演示数据，银行贷款，内容如下：<br/>
 * 地区：<br/>
 * 北京，上海，深圳<br/>
 * 机构：<br/>
 * 中国银行，建设银行，工商银行，交通银行，农业银行<br/>
 * 指标：<br/>
 * 不良贷款总额，逾期贷款总额，违约贷款总额<br/>
 * 
 * @author 韦海晗
 */
public class BankLoan {
	private String area;
	private String org;
	private Integer deadLoans; //不良贷款总额
	private Integer overdueLoans; //逾期贷款总额
	private Integer violationLoans; //违约贷款总额
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOrg() {
		return org;
	}
	
	public void setOrg(String org) {
		this.org = org;
	}

	public Integer getDeadLoans() {
		return deadLoans;
	}

	public void setDeadLoans(Integer deadLoans) {
		this.deadLoans = deadLoans;
	}

	public Integer getOverdueLoans() {
		return overdueLoans;
	}

	public void setOverdueLoans(Integer overdueLoans) {
		this.overdueLoans = overdueLoans;
	}

	public Integer getViolationLoans() {
		return violationLoans;
	}

	public void setViolationLoans(Integer violationLoans) {
		this.violationLoans = violationLoans;
	}
}
