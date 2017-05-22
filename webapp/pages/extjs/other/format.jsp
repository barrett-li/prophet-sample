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
  		<script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/Format.js"></script>
  		<!-- Prophet CSS -->
  		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/styles/common.css"></link>
  		<!-- Page JS -->
		<script type="text/javascript">
		Ext.onReady(function() {
			var log = [];
			log.push('usMoney format: ' + Ext.util.Format.usMoney('123456789.12345'));
			log.push('cnMoney format: ' + Ext.ux.util.Format.cnMoney('123456789.12345'));
			log.push('cnMoney format: ' + Ext.ux.util.Format.money('123456789.12345'));
			log.push('money format: ' + Ext.ux.util.Format.money('123456789.125', '$'));
			log.push('percent format: ' + Ext.ux.util.Format.percent('0.35673'));
			log.push('percent format: ' + Ext.ux.util.Format.percent('0.35676'));
			log.push('date format: ' + Ext.util.Format.date('2009/1/12', 'Y-m-d'));
			Ext.getBody().dom.innerHTML = log.join('<br/>');
		});
		</script>
	</head>
	<body>

	</body>
</html>