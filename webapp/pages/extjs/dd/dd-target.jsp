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
			.slot { border:2px solid #aaaaaa; background-color:#dddddd; color:#666666; text-align:center; width:60px; height:60px; }
			.player { border:2px solid #bbbbbb; background-color:#7E695E; color:#eeeeee; text-align:center; width:60px; height:60px; }
		</style>
		<script type="text/javascript">
			var slot1, slot2, slot3, player1, player2;
			
			MyDragSource = function(el, config){
				MyDragSource.superclass.constructor.call(this, el, config);
			}
			
			Ext.extend(MyDragSource, Ext.dd.DragSource, {
			
			    getDragData : function(e){
			    	// 取拖动的数据
			        return Ext.dd.Registry.getHandleFromEvent(e);
			    },
			    
			    onStartDrag : function(target, e, id){
			    	// 取所有的目标元素
					var targets = Ext.dd.DragDropMgr.getRelated(this, true);   
					for (var i = 0; i < targets.length; i++) {   
					    var target = targets[i];
					    Ext.get(target.id).setStyle('border-color', 'red');
					}
			    },
			    
				afterDragDrop: function(target, e, id){
        			var srcEl = Ext.get(this.getEl());
        			var destEl = Ext.get(id);
					// 在目标元素中增加当前拖动的元素
	                destEl.appendChild(srcEl); // insert above
	                
					var targets = Ext.dd.DragDropMgr.getRelated(this, true);   
					for (var i = 0; i < targets.length; i++) {
					    Ext.get(targets[i].id).setStyle('border-color', '#aaaaaa');
					}
					
					// 显示数据
					var data = this.dragData;
					Ext.getDom('v1').innerHTML = id + ' value is ' + data.text + '.';
				}
			});
			
			Ext.onReady(function() {
				player1 = new MyDragSource('pt1', {group: 'g1'});
				player2 = new MyDragSource('pt2', {group: 'g2'});
				
				// 设置要拖动的数据
	            Ext.dd.Registry.register('pt2', {
	                text: '999',
	                handles: ['pt1', 'pt2'],
	                isHandle: false
	            });

				slot1 = new Ext.dd.DropTarget('t1', {group: 'g1'});
				slot2 = new Ext.dd.DDTarget('t2', "g2");
				slot3 = new Ext.dd.DDTarget('t3', "g2");
				
				new Ext.dd.DDTarget('pc2', "g2");
				new Ext.dd.DDTarget('pc3', "g2");
			});
		</script>
	</head>
	<body>
			<table>
				<tr>
					<td>
						<div class="slot" id="t1" ></div>
					</td>
					<td><div class="slot" id="t2" ></div></td>
					<td>
						<div class="slot" id="t3" ></div>
					</td>
				</tr>
				<tr>
					<td id="pc1" style="height:60px;">
						<div class="player" id="pt1" >1</div>
					</td>
					<td id="pc2" style="height:60px;"></td>
					<td id="pc3" style="height:60px;">
						<div class="player" id="pt2" >2</div>
					</td>
				</tr>
				<tr>
					<td id="v1" style="height:60px;" colspan="3">
					</td>
				</tr>
			</table>
	</body>
</html>
