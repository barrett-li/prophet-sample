/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.sample.service;

import java.util.List;

import com.resoft.prophet.charts.sample.dao.BankLoanDAO;

/**
 * 为图表提供数据服务实现类。
 * 
 * @author 韦海晗
 */
public class ChartsDataServiceImpl implements ChartsDataService {
	private BankLoanDAO bankLoanDAO;

	public void setBankLoanDAO(BankLoanDAO bankLoanDAO) {
		this.bankLoanDAO = bankLoanDAO;
	}

	/* (non-Javadoc)
	 * @see com.resoft.prophet.charts.sample.service.ChartsDataService#getBankLoans(java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public List getBankLoans(String area, String org, Integer firstResult, Integer maxResults, String sort, String direction) {
		return bankLoanDAO.getBankLoans(area, org, firstResult, maxResults, sort, direction);
	}

	/* (non-Javadoc)
	 * @see com.resoft.prophet.charts.sample.service.ChartsDataService#countBankLoans(java.lang.String, java.lang.String)
	 */
	public Integer countBankLoans(String area, String org) {
		return bankLoanDAO.countBankLoans(area, org);
	}
}
