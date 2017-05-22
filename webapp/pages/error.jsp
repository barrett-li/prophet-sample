<%--
Prophet 1.0
CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD

http://resoft.css.com.cn
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/templets/common/global.jsp"%>
<%@ include file="/templets/common/taglibs.jsp"%>
<%
Exception exception = (Exception) request.getAttribute("exception");
%>
<html>
	<head>
		<title>Prophet示例：系统错误</title>
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
		<h2 style="color: red;">
			系统错误！
		</h2>
		<br />
		<br />
		<a href="javascript: history.go(-1);" target="_top">返回</a>
		<br />
		<br />
        <%
            if (exception != null) {
                exception.printStackTrace(new java.io.PrintWriter(out));
            } else {
        %>
                No exception message!
        <%
            }
        %>
	</body>
</html>
