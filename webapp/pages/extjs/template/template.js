/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

Ext.onReady(function(){

    var html = '<a id="{id}" href="{url}" class="nav">{text}</a><br />';
	
	var tpl = new Ext.Template(html);
	// 编译模版
	tpl.compile();
	
	// 将数据填入到模板中
	tpl.append('blog-roll', {
	    id: 'link1', 
	    url: 'http://www.jackslocum.com/', 
	    text: "Jack's Site"
	});
	tpl.append('blog-roll', {
	    id: 'link2', 
	    url: 'http://www.extjs.com/', 
	    text: "Jack's New Site"
	});
	
	// 模板格式化函数
	var tpl2 = new Ext.Template(
    	'<div>{content:ellipsis(15)}<br/><a href="{moreLink}">Read More</a></div>'
	);
	tpl2.append('blog-roll', {
	    content: '通过处理，其中有12个字符是属于内容本身的，另外三个字符是”...“,一共15个字符。', 
	    moreLink: 'http://www.jackslocum.com/'
	});
	
	// 自定义格式化函数
	var testCustomTpl = new Ext.Template(
	    '<div>User: {username} IsRevoked: {revoked:this.yesNoFormat}</div>'
	);
	testCustomTpl.yesNoFormat = function(value) {
		return value ? 'Yes' : 'No';
	};		
	testCustomTpl.append('blog-roll', {username: 'aconran', revoked: 1});
});