/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

// 创建命名空间
Ext.namespace('myNameSpace');

// 允许这个指南同时在Ext2.0 和1.1 上同时工作
Ext.Ext2 = (Ext.version && (Ext.version.indexOf("2") == 0));
 
// 创建命名空间
Ext.namespace('myNameSpace');
 
// 创建应用程序
myNameSpace.app = function() {
    // 元素还没创建,未能访问
 
    // 私有变量
    var btn1;
    var privVar1 = 11;
 
    // 私有函数
    var btn1Handler = function(button, event) {
        alert('privVar1=' + privVar1);
        alert('this.btn1Text=' + this.btn1Text);
    };
 
    // 公共空间
    return {
        // 公共的属性,如,要转译的字符串
        btn1Text: 'Button 1',
 
        // 公共方法
        init: function() {
            if (Ext.Ext2) {
                btn1 = new Ext.Button({
                    renderTo: 'btn1-ct',
                    text: this.btn1Text,
                    handler: btn1Handler,
        			scope:this // 增加scope属性来指明this为myNameSpace.app
                });
            } else {
                btn1 = new Ext.Button('btn1-ct', {
                    text: this.btn1Text,
                    handler: btn1Handler,
        			scope:this // 增加scope属性来指明this为myNameSpace.app
                });
            }
        }
    };
}(); //程序底部

// 改变（重写）公共变量btn1Text的值
Ext.apply(myNameSpace.app, {
    btn1Text:'Taste 1'
});

/*
 * 出现了btn1Handler 未定义的错误。这是因为新的函数虽然尝试访问私有变量但它实际是不允许的。
 * 正如所见，原init是特权函数可访问私有空间，但新的init却不能。
 * 之所以不能访问私有空间，是因为：禁止外部代码No code from outside，哪怕是尝试重写特权方法。

Ext.apply(myNameSpace.app, {
      btn1Text:'Taste 1'
    , init: function() {
        try {
            btn1 = new Ext.Button('btn1-ct', {
                  text: this.btn1Text
                , handler: btn1Handler
                , scope: this
            });
        }
        catch(e) {
            alert('错误: "' + e.message + '" 发生在行： ' + e.lineNumber);
        }
    }
});
 * */
 
// 文件底部