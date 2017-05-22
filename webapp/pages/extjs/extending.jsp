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
		<title>Extending Ext Components</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- Common JS -->
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
  		<!-- Ext JS Extend -->
  		<!-- Page JS -->
		<script type="text/javascript">

		MyComponent = Ext.extend(Ext.Component, {
		    //缺省构造参数，可被自定义设置覆盖
		    propA: 1,
		 
		    initComponent: function(){
		       	//在组件初始化期间调用的代码
		 		alert('调用initComponent');
				//因为配置对象应用到了“this”，所以属性可以在这里被覆盖，或者添加新的属性
				//（如items,tools,buttons）
				Ext.apply(this, {
				    propA: 3
				});
		 
		       	//调用父类代码之前        
		 
		        //调用父类构造函数（必须）
		        MyComponent.superclass.initComponent.apply(this, arguments);
		 
		       //调用父类代码之后
		        //如：设置事件处理和渲染组件
		    },
		 
		    //覆盖其他父类方法
		    onRender: function(){
		 
		        //调用父类代码之前
		 		alert('调用onRender');
		        //调用父类相应方法（必须）
		        MyComponent.superclass.onRender.apply(this, arguments);
		 
		        //调用父类代码之后
		 
		    }
		});
		 
		//注册成xtype以便能够延迟加载
		Ext.reg('mycomponentxtype', MyComponent);
		
		Ext.onReady(function() {
			var myComponent = new MyComponent({
			    propA: 2
			});
			
			// 或者延迟加载:
			// {..
			//  items: {xtype: 'mycomponentxtype', propA: 2}
			// ..}
			
			myComponent.render();
		});
		</script>
	</head>
	<body>
	</body>
</html>