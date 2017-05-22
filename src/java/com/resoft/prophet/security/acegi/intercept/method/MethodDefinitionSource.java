/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.security.acegi.intercept.method;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.ConfigAttributeEditor;
import org.acegisecurity.GrantedAuthority;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.util.Assert;

import com.resoft.prophet.security.acegi.resourcedetails.ResourceDetails;
import com.resoft.prophet.security.acegi.resourcedetails.ResourceProvider;

/**
 * 方法资源默认处理器。<br/>
 * 该类实现{@link org.acegisecurity.intercept.web.AbstractFilterInvocationDefinitionSource}接口，
 * 用于从缓存中读取方法资源，
 * 用于与Acegi安全框架集成。
 * 
 * @author 韦海晗
 */
public class MethodDefinitionSource implements org.acegisecurity.intercept.method.MethodDefinitionSource {
	private static final Log logger = LogFactory.getLog(MethodDefinitionSource.class);

	private ResourceProvider resourceProvider;

	/**
	 * 设置资源处理器。
	 */
	public void setResourceProvider(ResourceProvider resourceProvider) {
		this.resourceProvider = resourceProvider;
	}
	
    /**
     * Accesses the <code>ConfigAttributeDefinition</code> that applies to a given secure object.<P>Returns
     * <code>null</code> if no <code>ConfigAttribiteDefinition</code> applies.</p>
     *
     * @param object the object being secured
     *
     * @return the <code>ConfigAttributeDefinition</code> that applies to the passed object
     *
     * @throws IllegalArgumentException if the passed object is not of a type supported by the
     *         <code>ObjectDefinitionSource</code> implementation
     */
	public ConfigAttributeDefinition getAttributes(Object object) {
		Assert.notNull(object, "Object cannot be null");

		if (object instanceof MethodInvocation) {
			MethodInvocation miv = (MethodInvocation) object;
			return this.lookupAttributes(miv.getThis().getClass(), miv.getMethod());
		}

		if (object instanceof JoinPoint) {
			JoinPoint jp = (JoinPoint) object;
			Class targetClazz = jp.getTarget().getClass();
			String targetMethodName = jp.getStaticPart().getSignature().getName();
			Class[] types = ((CodeSignature) jp.getStaticPart().getSignature()).getParameterTypes();

			if (logger.isDebugEnabled()) {
				logger.debug("Target Class: " + targetClazz);
				logger.debug("Target Method Name: " + targetMethodName);

				for (int i = 0; i < types.length; i++) {
					logger.debug("Target Method Arg #" + i + ": " + types[i]);
				}
			}

			try {
				return this.lookupAttributes(targetClazz, targetClazz.getMethod(targetMethodName, types));
			} catch (NoSuchMethodException nsme) {
				throw new IllegalArgumentException("Could not obtain target method from JoinPoint: " + jp);
			}
		}

		throw new IllegalArgumentException("Object must be a MethodInvocation or JoinPoint");
	}
	
    /**
     * Indicates whether the <code>ObjectDefinitionSource</code> implementation is able to provide
     * <code>ConfigAttributeDefinition</code>s for the indicated secure object type.
     *
     * @param clazz the class that is being queried
     *
     * @return true if the implementation can process the indicated class
     */
	public boolean supports(Class clazz) {
		return (MethodInvocation.class.isAssignableFrom(clazz) || JoinPoint.class.isAssignableFrom(clazz));
	}

	/**
	 * 从resourceCache中获取当前方法对应的ResourceDetails{@link com.resoft.prophet.security.acegi.resourcedetails.ResourceDetails}，
	 * 最后返回由权限名组装成的ConfigAttributeDefinition(@link org.acegisecurity.ConfigAttributeDefinition)。
	 * 
	 * @param clszz 包含方法的类
	 * @param mi 要匹配的方法
	 * 
	 * @return Holds a group of {@link ConfigAttribute}s that are associated with a given secure object target.
	 */
	protected ConfigAttributeDefinition lookupAttributes(Class clszz, Method mi) {
		Assert.notNull(mi, "lookupAttrubutes in the DBMethodDefinitionSource is null");
		
		// 获取所有的function
		List methodStrings = resourceProvider.getFunctions();

		Set auths = new HashSet();
		// 取权限的合集
		for (Iterator iter = methodStrings.iterator(); iter.hasNext();) {
			String methodString = (String) iter.next();
			if (isMatch(clszz, mi, methodString)) {
				ResourceDetails resourceDetails = resourceProvider.getAuthorityFromCache(methodString);
				if (resourceDetails == null) {
					break;
				}
				GrantedAuthority[] authorities = resourceDetails.getAuthorities();
				if (authorities == null || authorities.length == 0) {
					break;
				}
				auths.addAll(Arrays.asList(authorities));
			}
		}

		if (auths.size() == 0)
			return null;

		ConfigAttributeEditor configAttrEditor = new ConfigAttributeEditor();
		String authoritiesStr = " ";

		for (Iterator iter = auths.iterator(); iter.hasNext();) {
			GrantedAuthority authority = (GrantedAuthority) iter.next();
			authoritiesStr += authority.getAuthority() + ",";
		}

		String authStr = authoritiesStr.substring(0, authoritiesStr.length() - 1);

		configAttrEditor.setAsText(authStr);
		return (ConfigAttributeDefinition) configAttrEditor.getValue();
	}

    /**
     * If available, all of the <code>ConfigAttributeDefinition</code>s defined by the implementing class.<P>This
     * is used by the {@link AbstractSecurityInterceptor} to perform startup time validation of each
     * <code>ConfigAttribute</code> configured against it.</p>
     *
     * @return an iterator over all the <code>ConfigAttributeDefinition</code>s or <code>null</code> if unsupported
     */
	public Iterator getConfigAttributeDefinitions() {
		return null;
	}

	/**
	 * 如果要匹配的方法与方法名字相同，返回true。
	 * 
	 * @param clszz 包含方法的类
	 * @param mi 要匹配的方法
	 * @param methodString 方法名字
	 */
	public static boolean isMatch(Class clszz, Method mi, String methodString) {
		boolean isMatch = true;
		try {
			int lastDotIndex = methodString.lastIndexOf('.');
			String className = methodString.substring(0, lastDotIndex);
			String methodName = methodString.substring(lastDotIndex + 1);

			// 判断类是否相等
			if (!clszz.getName().equals(className))
				isMatch = false;

			// 判断接口是否相等
			Class[] interfaces = clszz.getInterfaces();
			for (int i = 0; i < interfaces.length; i++) {
				Class inf = interfaces[i];
				if (inf.getName().equals(className)) {
					isMatch = true;
				}
			}

			// 判断方法是否相等
			if (isMatch
					&& !(mi.getName().equals(methodName)
							|| (methodName.endsWith("*") && mi.getName().startsWith(methodName.substring(0, methodName.length() - 1))) || (methodName
							.startsWith("*") && mi.getName().endsWith(methodName.substring(1, methodName.length())))))
				isMatch = false;

		} catch (Exception e) {
			isMatch = false;
		}
		return isMatch;
	}

}
