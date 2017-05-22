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
		<title>Prophet示例：拖放演示</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- Common JS -->
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
  		<script type="text/javascript" src="<%=rootPath%>/dwr/security/interface/securityDWRService.js"></script>
  		<!-- Ext JS Extend -->
  		<script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/TreeDWRLoader.js"></script>
		<!-- Prophet CSS -->
		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/styles/common.css"></link>
		<!-- Page JS -->
		<script type="text/javascript">
			Ext.onReady(function() {
				new HeadDropZone('h1', {group: 'TreeDD'});
				// 创建菜单树
		        var tree = new Ext.tree.TreePanel({
					applyTo: 'menu-tree',
					animate: true, 
					autoScroll: true,
					loader: new Ext.ux.tree.TreeDWRLoader({
						method: securityDWRService.getMenuTreeNodes
					}),
					enableDD: true,
			        // 使树的拖拽功能失效
			        dropZone: true,
					lines: false,
					containerScroll: true,
					root: new Ext.tree.AsyncTreeNode({
						text: 'Prophet', 
						draggable: false,
						expanded: true,
						id: '0'
					}),
					border: false,
					dropConfig: {appendOnly: true}
				});
			});

			HeadDropZone = function(el, config){
				HeadDropZone.superclass.constructor.call(this, el, config);
			}
			
			Ext.extend(HeadDropZone, Ext.dd.DropZone, {
			    onContainerDrop : function(dd, e, data){
			        var h = Ext.getDom(this.getEl());
			        var tb = h.parentNode.getElementsByTagName('tbody')[0];
			        var n = data.node;
			        
			    	Ext.DomHelper.append(h.childNodes[0], {tag: 'td', html: Ext.util.Format.trim(n.text)});
			    	for(var i = 0; i < tb.childNodes.length; i++){
			    		var id = Ext.id();
			    		Ext.DomHelper.append(tb.childNodes[i], {tag: 'td', id: id, html: '&nbsp;', style: 'background-color: #D8D4E2'});
			    		new ROWDropZone(id, {group: 'TreeDD'});
			    	}
			        // 隐藏proxy，设置该返回值为true，否则为false
			        // 或dd.hideProxy();
			        dd.hideProxy();
			        return false;
			    },
				
				onContainerOver : function(dd, e, data){
					// 改变图标
					return this.dropAllowed;
				}, 

			    onNodeOut : function(n, dd, e, data){
			    	// 改变样式
			        if(this.overClass){
			            this.el.removeClass(this.overClass);
			        }
			    }
			});

			ROWDropZone = function(el, config){
				ROWDropZone.superclass.constructor.call(this, el, config);
			}
			
			Ext.extend(ROWDropZone, Ext.dd.DropZone, {
			    onContainerDrop : function(dd, e, data){
			        var td = Ext.getDom(this.getEl());
			        var n = data.node;
			        td.text = Ext.util.Format.trim(n.text);
			        td.innerHTML = Ext.util.Format.trim(n.text);
			        // 隐藏proxy，设置该返回值为true，否则为false
			        // 或dd.hideProxy();
			        dd.hideProxy();
			        return false;
			    },

				onContainerOver : function(dd, e, data){
					return this.dropAllowed;
				}, 

			    onNodeOut : function(n, dd, e, data){
			        if(this.overClass){
			            this.el.removeClass(this.overClass);
			        }
			    }
			});
		</script>
	</head>
	<body style="padding: 5px;">
		<table style="width: 100%; height: 100%;">
			<colgroup width="220px;" style="vertical-align: top;"/>
			<colgroup width="5px;" style="vertical-align: top;"/>
			<colgroup style="vertical-align: top;"/>
			<tr>
				<td>
					<div id="menu-tree" style="border:1px solid purple;overflow: hidden;height: 400px;width: 100%;"></div>
				</td>
				<td></td>
				<td>
					<table style="width: 100%; height: 100%; border:1px solid purple;" border="1">
						<colgroup width="50px;"/>
						<thead id="h1" style="background-color:#6D739A;">
							<td>
								列
							</td>
						</thead>
						<tbody>
							<tr>
								<td>
									行
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>