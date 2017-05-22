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
		<title>Prophet示例：页面框架</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- Common JS -->
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
  		<script type="text/javascript" src="<%=rootPath%>/dwr/security/interface/securityDWRService.js"></script>
  		<!-- Ext JS Extend -->
  		<script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/TreeDWRLoader.js"></script>
		<!-- Page JS -->
		<style type="text/css">
		html, body {
	        font:normal 12px verdana;
	        margin:0;
	        padding:0;
	        border:0 none;
	        overflow:hidden;
	        height:100%;
	    }
		p {
		    margin:5px;
		}
	    </style>
		<script type="text/javascript">
   		Ext.onReady(function(){

        Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
        
       	new Ext.Viewport({
            layout:'border',
            items:[
                new Ext.BoxComponent({
                    region:'north',
                    el: 'north',
                    height:42
                }),{
                	id:'west-panel',
                	contentEl:'west',
                    region:'west',
                    title:'Prophet示例',
                    split:true,
                    width: 200,
                    minSize: 175,
                    maxSize: 400,
                    collapsible: true,
                    margins:'0 0 5 5',
                    layout:'accordion',
                    layoutConfig:{
                        animate:true
                    }
                },{
                	id:'center-panel',
                	contentEl:'center',
                    region:'center',
                    title:true,
                    split:true,
                    margins:'0 5 5 0',
                    layout:'accordion',
                    layoutConfig:{
                        animate:true
                    }
                }
             ]
        });
        
        var tree = new Ext.tree.TreePanel({
			applyTo: 'west',
			animate: true, 
			autoScroll: true,
			loader: new Ext.ux.tree.TreeDWRLoader({
				method: securityDWRService.getMenuTreeNodes,
				baseParams: {'hrefTarget' : 'center'},
			  	listeners: { 
			  		// 异常处理
		            'loadexception': function(loader, node, response){
						Ext.MessageBox.alert("警告", response.responseText);
					}
            	}
			}),
			enableDD: false,
			lines: false,
			containerScroll: true,
			root: new Ext.tree.AsyncTreeNode({
				text: 'Prophet', 
				draggable: false,
				href: '<%=rootPath%>/pages/frame/center.jsp',
				hrefTarget : 'center',
				qtip: '欢迎!',
				expanded: true,
				id: '0'
			}),
			border: false,
			dropConfig: {appendOnly: true}
		});
		
		tree.on('click', function(node, e){
			Ext.getCmp('center-panel').setTitle(node.attributes.qtip);
		});
		
		Ext.get('center').dom.src = '<%=rootPath%>/pages/frame/center.jsp';
		Ext.getCmp('center-panel').setTitle('欢迎!');
    });
	</script>
	</head>
	<body>
		<div id="north">
			<p style="font-size: 18px; font-weight: bold">
				Prophet示例
			</p>
			<p style="text-align: right;">
				<a href="<%=rootPath%>/pages/security/setUserPassword.jsp" target="center">更改密码</a><span style="padding: 0px 5px 0px 5px;">|<span>
				<a href="<%=rootPath%>/j_acegi_logout">注销</a>
			</p>
		</div>
		<div id="west" style="width: 100%; height: 100%;">
			
		</div>
		<iframe id="center" name="center" frameborder="no" width="100%" height="100%"></iframe>
	</body>
</html>