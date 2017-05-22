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
			var sayHi = function(name){
				var v = 'Hi, ' + name;
				log.push('v1 value:' + v);
			    return v;
			}
			
			log.push('sayHi return:' + sayHi('Fred')); // return "Hi, Fred"
			
			var sayGoodbye = sayHi.createSequence(function(name){
				var v = 'Bye, ' + name;
				log.push('v2 value:' + v);
			    return v;
			});
			
			log.push('sayGoodbye return:' + sayGoodbye('Fred')); // both alerts show
			Ext.getDom('div-log').innerHTML = log.join('<br/>');
		});
		</script>
	</head>
	<body>
		<div id="div-log"></div>
	</body>
</html>