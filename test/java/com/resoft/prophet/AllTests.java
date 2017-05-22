/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.resoft.prophet.charts.fusioncharts.FusionChartTest;
import com.resoft.prophet.charts.fusioncharts.FusionElementTest;
import com.resoft.prophet.charts.sample.service.ChartsDataServiceTest;
import com.resoft.prophet.log.service.LogServiceTest;
import com.resoft.prophet.org.service.OrgServiceTest;
import com.resoft.prophet.security.PasswordEncoderTest;
import com.resoft.prophet.security.service.SecurityServiceTest;
import com.resoft.prophet.security.userdetails.UserDetailsServiceTest;

/**
 * 所有的单元测试类Suite
 * 
 * @author 韦海晗
 */
public class AllTests {
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for com.resoft.prophet.*");
		suite.addTestSuite(FusionElementTest.class);
		suite.addTestSuite(FusionChartTest.class);
		suite.addTestSuite(LogServiceTest.class);
		suite.addTestSuite(OrgServiceTest.class);
		suite.addTestSuite(ChartsDataServiceTest.class);
		suite.addTestSuite(PasswordEncoderTest.class);
		suite.addTestSuite(SecurityServiceTest.class);
		suite.addTestSuite(UserDetailsServiceTest.class);
		return suite;
	}
}
