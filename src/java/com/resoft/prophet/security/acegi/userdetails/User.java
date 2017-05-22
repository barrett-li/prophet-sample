/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.security.acegi.userdetails;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;
import org.springframework.util.Assert;

/**
 * 当前用户登录信息。<br/> 
 * 该类实现{@link org.acegisecurity.userdetails.UserDetails}接口，
 * 用于与Acegi安全框架集成。
 * 
 * @author 韦海晗
 */
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	private String id;
	private String password;
	private String username;
	private GrantedAuthority[] authorities;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;

	/**
	 * Construct the <code>User</code> with the details required by {@link org.acegisecurity.providers.dao.DaoAuthenticationProvider}.
	 * 
	 * @param id the id presented to the <code>DaoAuthenticationProvider</code>
	 * @param username the username presented to the <code>DaoAuthenticationProvider</code>
	 * @param password the password that should be presented to the <code>DaoAuthenticationProvider</code>
	 * @param enabled set to <code>true</code> if the user is enabled
	 * @param accountNonExpired set to <code>true</code> if the account has not expired
	 * @param credentialsNonExpired set to <code>true</code> if the credentials have not expired
	 * @param accountNonLocked set to <code>true</code> if the account is not locked
	 * @param authorities the authorities that should be granted to the caller if they presented the correct username and password and the user is enabled
	 * 
	 * @throws IllegalArgumentException if a <code>null</code> value was passed either as a parameter or as an element in the <code>GrantedAuthority[]</code>
	 *             array
	 */
	public User(String id, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
			GrantedAuthority[] authorities) throws IllegalArgumentException {
		if (((id == null) || "".equals(id)) || ((username == null) || "".equals(username)) || (password == null)) {
			throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
		}

		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.accountNonLocked = accountNonLocked;
		setAuthorities(authorities);
	}

	public boolean equals(Object rhs) {
		if (!(rhs instanceof User) || (rhs == null)) {
			return false;
		}

		User user = (User) rhs;

		// We rely on constructor to guarantee any User has non-null and >0
		// authorities
		if (user.getAuthorities().length != this.getAuthorities().length) {
			return false;
		}

		for (int i = 0; i < this.getAuthorities().length; i++) {
			if (!this.getAuthorities()[i].equals(user.getAuthorities()[i])) {
				return false;
			}
		}

		// We rely on constructor to guarantee non-null username and password
		return (this.getPassword().equals(user.getPassword()) 
				&& this.getId().equals(user.getId())
				&& this.getUsername().equals(user.getUsername())
				&& (this.isAccountNonExpired() == user.isAccountNonExpired()) 
				&& (this.isAccountNonLocked() == user.isAccountNonLocked())
				&& (this.isCredentialsNonExpired() == user.isCredentialsNonExpired()) 
				&& (this.isEnabled() == user.isEnabled()));
	}

	public GrantedAuthority[] getAuthorities() {
		return authorities;
	}

	public String getId() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public int hashCode() {
		int code = 9792;

		if (this.getAuthorities() != null) {
			for (int i = 0; i < this.getAuthorities().length; i++) {
				code = code * (this.getAuthorities()[i].hashCode() % 7);
			}
		}

		if (this.getPassword() != null) {
			code = code * (this.getPassword().hashCode() % 7);
		}

		if (this.getUsername() != null) {
			code = code * (this.getUsername().hashCode() % 7);
		}

		if (this.isAccountNonExpired()) {
			code = code * -2;
		}

		if (this.isAccountNonLocked()) {
			code = code * -3;
		}

		if (this.isCredentialsNonExpired()) {
			code = code * -5;
		}

		if (this.isEnabled()) {
			code = code * -7;
		}

		return code;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	protected void setAuthorities(GrantedAuthority[] authorities) {
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority array");

		for (int i = 0; i < authorities.length; i++) {
			Assert.notNull(authorities[i], "Granted authority element " + i + " is null - GrantedAuthority[] cannot contain any null elements");
		}

		this.authorities = authorities;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString()).append(": ");
		sb.append("Id: ").append(this.id).append("; ");
		sb.append("Username: ").append(this.username).append("; ");
		sb.append("Password: [PROTECTED]; ");
		sb.append("Enabled: ").append(this.enabled).append("; ");
		sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
		sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
		sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");

		if (this.getAuthorities() != null) {
			sb.append("Granted Authorities: ");

			for (int i = 0; i < this.getAuthorities().length; i++) {
				if (i > 0) {
					sb.append(", ");
				}

				sb.append(this.getAuthorities()[i].toString());
			}
		} else {
			sb.append("Not granted any authorities");
		}

		return sb.toString();
	}
}