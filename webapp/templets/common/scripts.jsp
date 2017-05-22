		<%--
		global scripts
		--%>
		<script type="text/javascript">
		// app path.
		var ROOT_PATH = '<%=rootPath%>';
		// DWR path.
		var DWR_ROOT_PATH = ROOT_PATH + '/dwr';
		// scripts root path.
		var SCRIPTS_ROOT_PATH = ROOT_PATH + '/scripts';
		// charts root path.
		var CHARTS_ROOT_PATH = ROOT_PATH + '/charts';
		</script>
		<!-- Ext JS - JavaScript Library 2.0 (http://extjs.com/license) -->
		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/scripts/extjs/lib/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%=rootPath%>/scripts/extjs/lib/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/scripts/extjs/lib/ext-all-debug.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/scripts/extjs/lib/locale/ext-lang-zh_CN.js"></script>
		<!-- Ext JS Extend -->
		<script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/Ext.js"></script>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
		<script type="text/javascript" src="<%=rootPath%>/dwr/engine.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/scripts/dwr/Common.js"></script>