<%--
Prophet 1.0
CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD

http://resoft.css.com.cn
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/templets/common/global.jsp"%>
<%@ include file="/templets/common/taglibs.jsp"%>
<html>
	<head>
		<title></title>
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
		Ext.onReady(function() {
			var log = [];
			//dt = （当前日期）
			var dt = new Date();
			log.push(dt);
			
			//dt = Thu May 25 2006 (today's month/day in 2006)
			var dt = Date.parseDate("2006", "Y");
			log.push(dt);
			
			//dt = Sun Jan 15 2006 （指定日期的所有部分）
			var dt = Date.parseDate("2006-11-15", "Y-m-d");
			log.push(dt);
			
			//dt = Sun Jan 15 2006 15:20:01 GMT-0600 (CST)
			dt = Date.parseDate("2006-1-15 3:20:01 PM", "Y-m-d h:i:s A" );
			log.push(dt);
			
			Ext.getBody().dom.innerHTML = log.join('<br/>');
		});
		</script>
	</head>
	<body>

	</body>
</html>