/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.security.acegi.intercept.web;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.ConfigAttributeEditor;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.intercept.web.AbstractFilterInvocationDefinitionSource;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.resoft.prophet.security.acegi.resourcedetails.ResourceDetails;
import com.resoft.prophet.security.acegi.resourcedetails.ResourceProvider;

/**
 * URL资源默认处理器。<br/>
 * 该类实现{@link org.acegisecurity.intercept.web.AbstractFilterInvocationDefinitionSource}接口，
 * 用于从缓存中读取URL资源，
 * 用于与Acegi安全框架集成。
 * 
 * @author 韦海晗
 */
public class FilterInvocationDefinitionSource extends AbstractFilterInvocationDefinitionSource {
	private boolean convertUrlToLowercaseBeforeComparison = false;
	private boolean useAntPath = false;
	private final PathMatcher pathMatcher = new AntPathMatcher();
	private final PatternMatcher matcher = new Perl5Matcher();
	private ResourceProvider resourceProvider;

	public void setResourceProvider(ResourceProvider resourceProvider) {
		this.resourceProvider = resourceProvider;
	}

	public Iterator getConfigAttributeDefinitions() {
		return null;
	}

	public void setConvertUrlToLowercaseBeforeComparison(boolean convertUrlToLowercaseBeforeComparison) {
		this.convertUrlToLowercaseBeforeComparison = convertUrlToLowercaseBeforeComparison;
	}

	public boolean isConvertUrlToLowercaseBeforeComparison() {
		return convertUrlToLowercaseBeforeComparison;
	}

	public boolean isUseAntPath() {
		return useAntPath;
	}

	public void setUseAntPath(boolean useAntPath) {
		this.useAntPath = useAntPath;
	}
	
	/**
	 * 返回当前URL对应的权限信息。
	 * 
	 * @param url URL字符串
	 * 
	 * @return 权限信息
	 */
	public ConfigAttributeDefinition lookupAttributes(String url) {

		if (isUseAntPath()) {
			// Strip anything after a question mark symbol, as per SEC-161.
			int firstQuestionMarkIndex = url.lastIndexOf("?");

			if (firstQuestionMarkIndex != -1) {
				url = url.substring(0, firstQuestionMarkIndex);
			}
		}

		List urls = resourceProvider.getUrlResStrings();

		//倒叙排序
		Collections.sort(urls);
		Collections.reverse(urls);

		if (convertUrlToLowercaseBeforeComparison) {
			url = url.toLowerCase();
		}

		GrantedAuthority[] authorities = new GrantedAuthority[0];
		for (Iterator iterator = urls.iterator(); iterator.hasNext();) {
			String resString = (String) iterator.next();
			boolean matched = false;
			if (isUseAntPath()) {
				matched = pathMatcher.match(resString, url);
			} else {
				Pattern compiledPattern;
				Perl5Compiler compiler = new Perl5Compiler();
				try {
					compiledPattern = compiler.compile(resString, Perl5Compiler.READ_ONLY_MASK);
				} catch (MalformedPatternException mpe) {
					throw new IllegalArgumentException("Malformed regular expression: " + resString);
				}

				matched = matcher.matches(url, compiledPattern);
			}
			if (matched) {
				ResourceDetails rd = resourceProvider.getAuthorityFromCache(resString);
				authorities = rd.getAuthorities();
				break;
			}
		}

		if (authorities.length > 0) {
			String authoritiesStr = " ";
			for (int i = 0; i < authorities.length; i++) {
				authoritiesStr += authorities[i].getAuthority() + ",";
			}
			String authStr = authoritiesStr.substring(0, authoritiesStr.length() - 1);
			ConfigAttributeEditor configAttrEditor = new ConfigAttributeEditor();
			configAttrEditor.setAsText(authStr.trim());
			return (ConfigAttributeDefinition) configAttrEditor.getValue();
		}

		return null;
	}
}