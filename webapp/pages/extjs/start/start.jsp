<%--
Prophet 1.0
CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD

http://resoft.css.com.cn
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/templets/common/global.jsp"%>
<%@ include file="/templets/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
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
  		<link rel="stylesheet" type="text/css" href="start.css"></link>
  		<!-- Page JS -->
  		<script type="text/javascript" src="start.js"></script>
		<script type="text/javascript">
		
		</script>
	</head>
	<body>
	<h1>Introduction to Ext 2.0: Starter Page</h1>

    <div id="content">
        
    	<p>This is the starter page that accompanies the <a href="http://extjs.com/learn/Tutorial:Introduction_to_Ext2">Introduction to Ext 2.0 tutorial</a> on the Ext JS website.</p>

    	<p>This page is intended to help you interactively explore some of the capabilities of the Ext library, so please make sure that your script references are correct.  This page assumes by default that it is in a directory directly beneath the root Ext deployment directory.  For example, if your Ext directory structure is located at "C:\code\Ext\v1.0\," then this file should be saved in a directory like "C:\code\Ext\v1.0\tutorial\."  If you choose to locate this file somewhere else, then make sure you change the script references of this file as needed.</p>

    	<p>If you have any questions or issues getting this tutorial to work correctly, please stop by the <a href="http://extjs.com/forum/">Ext Forums</a> and ask for help!</p>	

    	<div id="myDiv">This is a test div.</div>
	
    	<input type="button" id="myButton" value="My Button" />
        
	</div>
	<div id="msg"></div>
	<div>
	    Name: <input type="text" id="name" />
	    <input type="button" id="okButton" value="OK" />
	</div>
	<div id="msg"></div>
	</body>
</html>