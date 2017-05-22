<%--
Prophet 1.0
CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD

http://resoft.css.com.cn
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.acegisecurity.ui.webapp.AuthenticationProcessingFilter"%>

<%@ include file="/templets/common/global.jsp"%>
<%@ include file="/templets/common/taglibs.jsp"%>

<html>
	<head>
		<title>Prophet示例：登录</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- Common JS -->
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
		<!-- Ext JS Extend -->
		<!-- Prophet CSS -->
		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/styles/common.css"></link>
		<!-- Page JS -->
		<script type="text/javascript">
		
		</script>
	</head>
	<body>
		<h2>
			登录！
		</h2>
		<br />
		<br />
		<c:if test="${not empty param.login_error}">
			<font color="red">无效的用户名、密码，请重试！<BR>
			</font>
		</c:if>
		一般用户权限（用户名/密码）：alice/123
		<br />
		管理用户权限（用户名/密码）：steven/123
		<form action="<c:url value='/j_acegi_security_check'/>" method="POST">
			<table>
				<tr>
					<td>
						用户名:
					</td>
					<td>
						<input type='text' name='j_username' <c:if test="${not empty param.login_error}">value='<%=session.getAttribute(AuthenticationProcessingFilter.ACEGI_SECURITY_LAST_USERNAME_KEY)%>'</c:if> />
					</td>
				</tr>
				<tr>
					<td>
						密码:
					</td>
					<td>
						<input type='password' name='j_password' />
					</td>
				</tr>
				<tr>
					<td>
						<input name="reset" type="reset" />
					</td>
					<td>
						<input name="submit" type="submit" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>