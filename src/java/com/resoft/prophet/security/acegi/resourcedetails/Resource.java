/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.security.acegi.resourcedetails;

import org.acegisecurity.GrantedAuthority;
import org.springframework.util.Assert;

/**
 * 资源信息实现类。<br/>
 * 该类实现ResourceDetails{@link com.resoft.prophet.security.acegi.resourcedetails.ResourceDetails}接口，
 * 用于与Acegi安全框架集成。
 * 
 * @author 韦海晗
 */
public class Resource implements ResourceDetails {
	private static final long serialVersionUID = 1L;

	private String resString;

	private String resType;

	private GrantedAuthority[] authorities;

	public Resource() {
		
	}
	
	public Resource(String resString, String resType, GrantedAuthority[] authorities) {
		if (resString == null || "".equals(resString)) {
			throw new IllegalArgumentException("Cannot pass null or empty values to resource string");
		}
		if (resType == null || "".equals(resType)) {
			throw new IllegalArgumentException("Cannot pass null or empty values to resource type");
		}
		this.resString = resString;
		this.resType = resType;
		setAuthorities(authorities);
	}

	public String getResString() {
		return resString;
	}

	public void setResString(String resString) {
		this.resString = resString;
	}

	public GrantedAuthority[] getAuthorities() {
		return authorities;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	protected void setAuthorities(GrantedAuthority[] authorities) {
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority array");
		for (int i = 0; i < authorities.length; i++) {
			Assert.notNull(authorities[i], "Granted authority element " + i + " is null - GrantedAuthority[] cannot contain any null elements");
		}
		this.authorities = authorities;
	}
	
	public boolean equals(Object rhs) {
		if (!(rhs instanceof Resource))
			return false;
		Resource resauth = (Resource) rhs;
		if (resauth.getAuthorities().length != getAuthorities().length)
			return false;
		for (int i = 0; i < getAuthorities().length; i++) {
			if (!getAuthorities()[i].equals(resauth.getAuthorities()[i]))
				return false;
		}
		return getResString().equals(resauth.getResString()) && getResType().equals(resauth.getResType());
	}

	public int hashCode() {
		int code = 168;
		if (getAuthorities() != null) {
			for (int i = 0; i < getAuthorities().length; i++)
				code *= getAuthorities()[i].hashCode() % 7;
		}
		if (getResString() != null)
			code *= getResString().hashCode() % 7;
		return code;
	}

}
