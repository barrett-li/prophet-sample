/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.security.web;

import org.acegisecurity.providers.dao.SaltSource;
import org.acegisecurity.providers.encoding.PasswordEncoder;

import com.resoft.prophet.security.acegi.userdetails.User;
import com.resoft.prophet.security.service.SecurityService;
import com.resoft.prophet.web.WebWorkAction;

/**
 * 设置用户密码的Action。<br/>
 * 
 * @author 韦海晗
 */
public class SetUserPasswordAction extends WebWorkAction {
	private static final long serialVersionUID = -3008387440326068350L;

	private SecurityService securityService;

	private PasswordEncoder passwordEncoder;

	private SaltSource saltSource;

	private String newPassword;

	private String oldPassword;

	private String message;

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public void setSaltSource(SaltSource saltSource) {
		this.saltSource = saltSource;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 设置用户密码。<br/>
	 * 
	 */
	public String execute() throws Exception {
		User sessionUser = getUser();
		com.resoft.prophet.security.domain.User user = securityService.getUserById(sessionUser.getId());
		String salt = (String) saltSource.getSalt(sessionUser);
		if (passwordEncoder.isPasswordValid(user.getPassword(), oldPassword, salt)) {
			String encPass = passwordEncoder.encodePassword(newPassword, salt);
			user.setPassword(encPass);
			securityService.updateUser(user);
			this.setMessage("密码设置成功！");
		} else {
			this.setMessage("旧密码不正确，请重试！");
		}
		return SUCCESS;
	}
}
