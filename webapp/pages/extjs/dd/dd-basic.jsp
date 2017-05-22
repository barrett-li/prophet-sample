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
		<style type="text/css">
			.dd-demo {
			    position:relative;
			    border:4px solid #666;
			    text-align:center;
			    color:#fff;
			    cursor:move;
			    height:60px;
			    width:60px;
			}
			
			.dd-demo-em {
			    border:4px solid purple;
			}
			
			#dd-demo-1 { 
			    background-color:#6D739A;top:0px; left:105px;
			}
			
			#dd-demo-2 { 
			    background-color:#566F4E;top:50px; left:245px;
			}
			
			#dd-demo-3 {
			    background-color:#7E5B60;top:-100px; left:385px;
			}
		</style>
		<script type="text/javascript">
			var dd, dd2, dd3;
			Ext.onReady(function() {
				dd = new Ext.dd.DD("dd-demo-1", "WindowDrag");   
				dd2 = new Ext.dd.DD("dd-demo-2", "WindowDrag");
				dd3 = new Ext.dd.DD("dd-demo-3", "WindowDrag");
				
				var d = Ext.get('dd-demo-1');
				//
		        this.startMove = function(){
		        	Ext.getDom('position').innerHTML = d.getTop() + ',' + d.getLeft();
		        	Ext.getDom('state').innerHTML = "startDrag";
		        }
		        
		        this.onDrag = function(){
		        	Ext.getDom('position').innerHTML = d.getTop() + ',' + d.getLeft();
		        	Ext.getDom('state').innerHTML = "onDrag";
		        }
		        
		        this.endMove = function(){
		        	Ext.dd.DD.prototype.endDrag.apply(dd, arguments);
		        	Ext.getDom('position').innerHTML = d.getTop() + ',' + d.getLeft();
		        	Ext.getDom('state').innerHTML = "endMove";
		        }

		        dd.startDrag = this.startMove.createDelegate(this);
		        dd.onDrag = this.onDrag.createDelegate(this);
		        dd.endDrag = this.endMove.createDelegate(this);
			});
		</script>
	</head>
	<body>
		<div>
			<div id="dd-demo-1" class="dd-demo"></div>
			<div id="dd-demo-2" class="dd-demo"></div>
			<div id="dd-demo-3" class="dd-demo"></div>
			<div id="state"></div>
			<div id="position"></div>
		</div>
		
	</body>
</html>
