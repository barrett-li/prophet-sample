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
			var sort = 'ASC';
			
			// alternate sort directions
			var sort1 = sort.toggle('ASC', 'DESC');
			log.push('sort1:' + sort1);
			
			// instead of conditional logic:
			var sort2 = (sort == 'ASC' ? 'DESC' : 'ASC');
			log.push('sort2:' + sort2);
			
			var cls = 'my-class', text = 'Some text';
			var s = String.format('<div class="{0}">{1}</div>', cls, text);
			log.push('s:' + s);
			// s now contains the string: '<div class="my-class"
			Ext.getBody().dom.innerHTML = log.join('<br/>');
		});
		</script>
	</head>
	<body>
		
	</body>
</html>