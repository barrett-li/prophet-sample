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
		<title id="title">Localization Example</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- Common JS -->
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
		<!-- Ext JS Extend -->
		<!-- Page JS -->
	    <!-- Ext localization javascript -->
	    <script type="text/javascript" id="extlocale"></script>
	 
	    <!-- Locale and example extension javascript -->
	    <script type="text/javascript">
	 
	    // 根据url上指定的语言进行解码
	    var locale = window.location.search 
	                 ? Ext.urlDecode(window.location.search.substring(1)).locale 
	                 : ''
	    ;
	 	
	    // 将本地化的JS文件加入head元素内
	    var head = Ext.fly(document.getElementsByTagName('head')[0]);
	    if(locale) {
	        Ext.fly('extlocale').set({src:'<%=rootPath%>/scripts/extjs/lib/locale/ext-lang-' + locale + '.js'});
	    }
	    
	    // 预先定义的window继承类
	    Ext.ns('Tutorial');
	    Tutorial.LocalizationWin = Ext.extend(Ext.Window, {
	         titleText:'Localization Example'
	        ,selectLangText:'Select Language'
	        ,textFieldText:'Text Field'
	        ,dateFieldText:'Date Field'
	        ,initComponent:function() {
	            Ext.apply(this, {
	                 width:500
	                ,id:'winid'
	                ,height:300
	                ,layout:'fit'
	                ,border:false
	                ,closable:false
	                ,title:this.titleText
	                ,items:[{
	                     xtype:'form'
	                    ,frame:true
	                    ,defaultType:'textfield'
	                    ,items:[{
	                          xtype:'combo'
	                         ,fieldLabel:this.selectLangText
	                         ,name:'locale'
	                         ,store:new Ext.data.SimpleStore({
	                             id:0
	                            ,fields:['file', 'locale']
	                            ,data:[
	                            	['zh', 'Chinese']
	                                ,['cs', 'Czech']
	                                ,['en', 'English']
	                                ,['fr', 'French']
	                                ,['de', 'German']
	                                ,['gr', 'Greece']
	                                ,['hu', 'Hungarian']
	                                ,['it', 'Italian']
	                                ,['ja', 'Japaneese']
	                                ,['pl', 'Polish']
	                                ,['pt', 'Portugal']
	                                ,['ru', 'Russian']
	                                ,['sk', 'Slovak']
	                                ,['es', 'Spanish']
	                                ,['sv_SE', 'Swedish']
	                                ,['tr', 'Turkish']
	                            ]
	                         })
	                        ,listeners:{
	                            select:{fn:function(combo){
	                                window.location.search = '?' 
	                                    + Ext.urlEncode({locale:combo.getValue()})
	                                ;
	                            }}
	                        }
	                        ,mode:'local'
	                        ,editable:false
	                        ,forceSelection:true
	                        ,valueField:'file'
	                        ,displayField:'locale'
	                        ,triggerAction:'all'
	                        ,value:locale
	                     },{
	                         fieldLabel:this.textFieldText
	                        ,allowBlank:false
	                    },{
	                         xtype:'datefield'
	                        ,fieldLabel:this.dateFieldText
	                        ,allowBlank:false
	                    }]
	                }]
	            }); // eo apply
	            Tutorial.LocalizationWin.superclass.initComponent.apply(this, arguments);
	        } // eo function initComponent
	    }); // eo Tutorial.LocalizationWin

	    Ext.onReady(function() {
	        Ext.QuickTips.init();
	        Ext.form.Field.prototype.msgTarget = 'side';
	 
	        // create example window
	        var win = new Tutorial.LocalizationWin();
	        win.show();
	    });
	    </script>
	</head>
	<body>
	</body>
</html>
