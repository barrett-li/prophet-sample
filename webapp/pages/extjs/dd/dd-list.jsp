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
		
		div.workarea { padding:10px; float:left }
		
		ul.draglist { 
		    position: relative;
		    width: 200px; 
		    height:240px;
		    background: #f7f7f7;
		    border: 1px solid gray;
		    list-style: none;
		    margin:0;
		    padding:0;
		}
		
		ul.draglist li {
		    margin: 1px;
		    cursor: move; 
		}
		
		ul.draglist_alt { 
		    position: relative;
		    width: 200px; 
		    list-style: none;
		    margin:0;
		    padding:0;
		    /*
		       The bottom padding provides the cushion that makes the empty 
		       list targetable.  Alternatively, we could leave the padding 
		       off by default, adding it when we detect that the list is empty.
		    */
		    padding-bottom:20px;
		}
		
		ul.draglist_alt li {
		    margin: 1px;
		    cursor: move; 
		}
		
		
		li.list1 {
		    background-color: #D1E6EC;
		    border:1px solid #7EA6B2;
		}
		
		li.list2 {
		    background-color: #D8D4E2;
		    border:1px solid #6B4C86;
		}
		
		#user_actions { float: right; }
		
		</style>
		<script type="text/javascript">
			DDList = function(el, config){
				DDList.superclass.constructor.call(this, el, config);
				
			    var el = Ext.get(this.getDragEl());
			    el.setStyle("opacity", 0.67); // The proxy is slightly transparent
			}
			
			Ext.extend(DDList, Ext.dd.DragSource, {
			
			    onEndDrag : function(data, e){
			    	var srcEl = this.getEl();
			    	Ext.fly(srcEl).setStyle("visibility", "");
			    },

			    afterDragOver: function(target, e, id) {
			        var srcEl = this.getEl();
			        var destEl = Ext.getDom(id);
			        
			        var childNodes  = destEl.childNodes;
			        var len = childNodes.length;
			        var lastNade = Ext.fly(childNodes[len - 1]);
			        
			        var y = Ext.EventObject.getPageY(e);
			        
			        if(len <= 0 || (y > (lastNade.getY() + lastNade.getHeight()))){
			        	destEl.insertBefore(srcEl);
			        } else {
				        for(var i = 0; i < len; i++){
				        	var n = Ext.fly(childNodes[i]);
				        	var min = n.getY();
				        	var max = min + n.getHeight();
				        	if(y >= min && y <=max){
				        		destEl.insertBefore(srcEl, n.dom);
				        		break;
				        	}
				        }
			        }
				    Ext.fly(srcEl).setStyle("visibility", "hidden");
				}
				
			});
			
			Ext.onReady(function() {
			
		        var rows = 3, cols = 2, i, j;
		        for (i = 1;i < cols + 1; i = i + 1) {
		        	//new Ext.dd.DDTarget('ul' + i, "g");
		        	new Ext.dd.DropTarget('ul' + i, {group: 'g'});
		        }
		
		        for (i = 1; i < cols + 1; i = i + 1) {
		            for (j = 1; j < rows + 1; j = j + 1) {
		            	new DDList("li" + i + "_" + j, {group: 'g'});
		            }
		        }
		        
	        	Ext.EventManager.on("showButton", "click", showOrder);
	        	Ext.EventManager.on("switchButton", "click", switchStyles);
			});

		    function showOrder() {
		        var parseList = function(ul, title) {
		            var items = ul.getElementsByTagName("li");
		            var out = title + ": ";
		            for (i=0;i<items.length;i=i+1) {
		                out += items[i].id + " ";
		            }
		            return out;
		        };

		        var ul1=Ext.getDom("ul1"), ul2=Ext.getDom("ul2");
		        alert(parseList(ul1, "List 1") + "\n" + parseList(ul2, "List 2"));
		
		    }
		
		    function switchStyles(){
		        Ext.getDom("ul1").className = "draglist_alt";
		        Ext.getDom("ul2").className = "draglist_alt";
		    }
		</script>
	</head>
	<body>
		<div class="workarea">
		  <h3>List 1</h3>
		  <ul id="ul1" class="draglist">
		    <li class="list1" id="li1_1">list 1, item 1</li>
		    <li class="list1" id="li1_2">list 1, item 2</li>
		    <li class="list1" id="li1_3">list 1, item 3</li>
		  </ul>
		</div>
		
		<div class="workarea">
		  <h3>List 2</h3>
		  <ul id="ul2" class="draglist">
		    <li class="list2" id="li2_1">list 2, item 1</li>
		    <li class="list2" id="li2_2">list 2, item 2</li>
		    <li class="list2" id="li2_3">list 2, item 3</li>
		  </ul>
		</div>
		
		<div id="user_actions">
		  <input type="button" id="showButton" value="Show Current Order" />
		  <input type="button" id="switchButton" value="Remove List Background" />
		</div>
	</body>
</html>
