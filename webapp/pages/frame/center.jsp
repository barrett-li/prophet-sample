<%--
Prophet 1.0
CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD

http://resoft.css.com.cn
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.resoft.prophet.security.SecurityContext"%>
<%@ page import="com.resoft.prophet.security.acegi.userdetails.User"%>

<%@ include file="/templets/common/global.jsp"%>
<%@ include file="/templets/common/taglibs.jsp"%>

<html>
	<head>
		<title>Prophet示例：欢迎</title>
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
			欢迎使用Prophet示例系统!
		</h2>
		<br />
		<br />
		<p>
			当前登录用户信息:
			<%
			User user = SecurityContext.getUser(request);
			if (user != null) {
			%>
			<br />
			用户ID:<%=user.getId()%><br />
			用户名:<%=user.getUsername()%><br />
			密码:<%=user.getPassword()%><br />
			<%
			}
			%>
		</p>
	</body>
</html>
