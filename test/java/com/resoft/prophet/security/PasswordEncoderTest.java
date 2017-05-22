/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.security;

import org.acegisecurity.providers.dao.SaltSource;
import org.acegisecurity.providers.encoding.PasswordEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

/**
 * Acegi密码加密器测试
 * 
 * @author 韦海晗
 */
public class PasswordEncoderTest extends AbstractDependencyInjectionSpringContextTests {
	private static final Log log = LogFactory.getLog(PasswordEncoderTest.class);

	private PasswordEncoder passwordEncoder;

	private SaltSource saltSource;

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public void setSaltSource(SaltSource saltSource) {
		this.saltSource = saltSource;
	}

	protected String[] getConfigLocations() {
		return new String[] { "classpath:applicationContext*.xml" };
	}

	public void testEncodePassword() throws Exception {
		String rawPass = "123";
		String encPass = passwordEncoder.encodePassword(rawPass, saltSource.getSalt(null));
		log.info(encPass);
		assertTrue(passwordEncoder.isPasswordValid(encPass, rawPass, saltSource.getSalt(null)));
	}
}
