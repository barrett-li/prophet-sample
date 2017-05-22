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
		<title>JFreeChart Action演示</title>
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
			function getData(value, rowKey, columnKey){
				alert(value + ', ' + rowKey + ', ' + columnKey);
			}
		</script>
	</head>
	<body>
		<ww:property value="chartImageInfo.usemapContent" escape="false"/>
		<img src="<ww:property value="chartImageInfo.src"/>" width="<ww:property value="chartImageInfo.width"/>" height="<ww:property value="chartImageInfo.height"/>" border="0" usemap="<ww:property value="chartImageInfo.usemapName"/>">
	</body>
</html>