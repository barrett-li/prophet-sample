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
		</style>
		<script type="text/javascript">
			var dd;
			Ext.onReady(function() {
				dd = new Ext.dd.DD("dd-demo-1", "WindowDrag");   
				
				//var d = Ext.get('dd-demo-1');
				var c = Ext.get('constrain');
				//
		        this.startMove = function(){
		        	//dd.constrainTo(document.body, {top: d.getHeight(), left: d.getWidth(), right: d.getWidth(), bottom: d.getHeight()});
		        	dd.constrainTo(c);
		        }
		        dd.startDrag = this.startMove.createDelegate(this);
			});
		</script>
	</head>
	<body>
		<div id="constrain" style="border:1px solid #99bbe8; overflow: hidden; width: 500px; height: 500px; padding: 5px; overflow: auto;">
			<div id="dd-demo-1" class="dd-demo"></div>
		</div>
		
	</body>
</html>
