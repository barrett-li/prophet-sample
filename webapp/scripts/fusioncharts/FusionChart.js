/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

// used to find the Automation server name
function getDomDocumentPrefix() {
	if (getDomDocumentPrefix.prefix)
		return getDomDocumentPrefix.prefix;
	
	var prefixes = ["MSXML2", "Microsoft", "MSXML", "MSXML3"];
	var o;
	for (var i = 0; i < prefixes.length; i++) {
		try {
			// try to create the objects
			o = new ActiveXObject(prefixes[i] + ".DomDocument");
			return getDomDocumentPrefix.prefix = prefixes[i];
		}
		catch (ex) {};
	}
	
	throw new Error("Could not find an installed XML parser");
}

// XmlDocument factory
function XmlDocument() {}

XmlDocument.create = function () {
	try {
		// DOM2
		if (document.implementation && document.implementation.createDocument) {
			var doc = document.implementation.createDocument("", "", null);
			
			// some versions of Moz do not support the readyState property
			// and the onreadystate event so we patch it!
			if (doc.readyState == null) {
				doc.readyState = 1;
				doc.addEventListener("load", function () {
					doc.readyState = 4;
					if (typeof doc.onreadystatechange == "function")
						doc.onreadystatechange();
				}, false);
			}
			
			return doc;
		}
		if (window.ActiveXObject)
			return new ActiveXObject(getDomDocumentPrefix() + ".DomDocument");
	}
	catch (ex) {}
	throw new Error("Your browser does not support XmlDocument objects");
};

// Create the loadXML method and xml getter for Mozilla
if (window.DOMParser &&
	window.XMLSerializer &&
	window.Node && Node.prototype && Node.prototype.__defineGetter__) {

	// XMLDocument did not extend the Document interface in some versions
	// of Mozilla. Extend both!
	XMLDocument.prototype.loadXML = 
	Document.prototype.loadXML = function (s) {
		
		// parse the string to a new doc	
		var doc2 = (new DOMParser()).parseFromString(s, "text/xml");
		
		// remove all initial children
		while (this.hasChildNodes())
			this.removeChild(this.lastChild);
			
		// insert and import nodes
		for (var i = 0; i < doc2.childNodes.length; i++) {
			this.appendChild(this.importNode(doc2.childNodes[i], true));
		}
	};
	
	
	/*
	 * xml getter
	 *
	 * This serializes the DOM tree to an XML String
	 *
	 * Usage: var sXml = oNode.xml
	 *
	 */
	// XMLDocument did not extend the Document interface in some versions
	// of Mozilla. Extend both!
	XMLDocument.prototype.__defineGetter__("xml", function () {
		return (new XMLSerializer()).serializeToString(this);
	});
	Document.prototype.__defineGetter__("xml", function () {
		return (new XMLSerializer()).serializeToString(this);
	});
}

/**
 * FusionCharts使用XML文件创建和操作图表，FusionElement对象用于映射FusionCharts配置文件中的一个元素。<br/>
 * 每个FusionElement对象包含一个属性对象的映射，该映射表示FusionCharts配置文件中用于创建和操作图表的一组属性。<br/>
 * 每个FusionElement对象包含一个FusionElement对象的列表，该列表表示FusionCharts配置文件中的一组子元素。
 * 
 * @author 韦海晗
 */
FusionElement = function(config) {
	this.name = '';
	this.attributes = {};
	this.elements = [];
	
	if (config) {
		this.attributes = config;
	}
}
FusionElement.prototype = {
	/**
	 * 设置FusionElement的名称。
	 * 
	 * @param name 元素名称。
	 */
	setName: function(name) {
		this.name = name;
	},
	
	/**
	 * 返回FusionElement的名称。
	 * 
	 * @return name 元素名称。
	 */
	getName: function() {
		return this.name;
	},
	
	/**
	 * 设置FusionElement的属性。
	 * 
	 * @param name 属性名称。
	 * @param value 属性值。
	 */
	addAttribute: function(name, value) {
		if(name != null && value != null) {
			this.attributes[name] = value;
		}
	},
	
	/**
	 * 返回指定名称的属性。
	 * 
	 * @param name 所返回属性的名称。
	 * @return attribute 元素的属性。
	 */
	getAttribute: function(name) {
		return this.attributes[name];
	},
	
	/**
	 * 将指定的元素追加到子元素列表的尾部。
	 * 
	 * @param fusionElement - 元素。
	 */
	addElement: function(fusionElement) {
		if(fusionElement != null) {
			this.elements.push(fusionElement);
		}
	},
	
	/**
	 * 返回子元素列表中指定位置上的元素。
	 * 
	 * @param index 所返回元素的索引。
	 * @return fusionElement 元素。
	 */
	getElement: function(index) {
		return this.elements[index];
	},
	
	/**
	 * 返回子元素列表中的元素数。
	 * 
	 * @return int - 子元素列表中的元素数。
	 */
	getElementCount: function(){
		return this.elements.length;
	},
	
	/**
	 * 创建一个DOM的Element对象。<br/>
	 * Element对象的属性由FusionElement对象的属性指定。<br/>
	 * Element对象的子元素由FusionElement对象的子元素指定。
	 * 
	 * @return domElement 创建一个DOM4J的Element对象。
	 */
	createDOMElement: function(){
		//如果没有找到FusionElement的名称，抛出异常
		if(this.name == null || this.name == '') {
			throw '没有找到FusionElement的名称。';
		}
		var dom = XmlDocument.create();
		var domElement = dom.createElement(this.name);
		//设置属性
		for(var i in this.attributes) {
			domElement.setAttribute(i, this.objectToString(this.attributes[i]));
		}
		//设置元素
		for(var i = 0; i < this.elements.length; i++) {
			var fusionElement = this.elements[i];
			domElement.appendChild(fusionElement.createDOMElement());
		}
		return domElement;
	},
	
	/**
	 * 将此指定的对象转换为一个新的字符串。
	 * 
	 * @param obj - 需要转换的对象。
	 * @return str - 新的字符串。
	 */
	objectToString: function(obj) {
		if (typeof obj == 'boolean') {
			return obj ? '1' : '0';
		}
		return obj;
	}
}

/**
 * FusionChart对象用于映射FusionCharts配置文件中的chart元素，FusionChart对象用于映射FusionCharts配置文件中的chart元素。<br/>
 * 
 * @author 韦海晗
 */
FusionChart = function() {
	this.superclass = FusionElement;
	this.superclass.apply(this, arguments);

	this.name = 'chart';
};
FusionChart.emptyChartXML = '<chart></chart>';
FusionChart.prototype = new FusionElement;
/**
 * 将FusionChart转换为FusionCharts XML字符串。
 * 
 * @return str XML字符串。
 */
FusionChart.prototype.toXML = function() {
	var dom = XmlDocument.create();
    dom.appendChild(this.createDOMElement());
	return dom.xml.replace(/"/g, '\'');
}

/**
 * FusionCharts使用XML文件创建和操作图表，Categories对象用于映射FusionCharts配置文件中的categories元素。<br/>
 * categories元素允许你捆绑图表的X轴标签。
 * 
 * @author 韦海晗
 */
Categories = function() {
	this.superclass = FusionElement;
	this.superclass.apply(this, arguments);
	
	this.name = 'categories';
};
Categories.prototype = new FusionElement;

/**
 * FusionCharts使用XML文件创建和操作图表，Category对象用于映射FusionCharts配置文件中的category元素。<br/>
 * 每个category元素表示一个X轴标签。
 * 
 * @author 韦海晗
 */
Category = function() {
	this.superclass = FusionElement;
	this.superclass.apply(this, arguments);
	
	this.name = 'category';
};
Category.prototype = new FusionElement;

/**
 * FusionCharts使用XML文件创建和操作图表，DataSet对象用于映射FusionCharts配置文件中的dataset元素。<br/>
 * 每个dataset元素包含一组数据。
 * 
 * @author 韦海晗
 */
DataSet = function() {
	this.superclass = FusionElement;
	this.superclass.apply(this, arguments);
	
	this.name = 'dataset';
};
DataSet.prototype = new FusionElement;

/**
 * FusionCharts使用XML文件创建和操作图表，Line对象用于映射FusionCharts配置文件中的line元素。<br/>
 * trendLines元素和其子元素line，用于在图表中定义趋势线。
 * 
 * @author 韦海晗
 */
Line = function() {
	this.superclass = FusionElement;
	this.superclass.apply(this, arguments);
	
	this.name = 'line';
};
Line.prototype = new FusionElement;

/**
 * FusionCharts使用XML文件创建和操作图表，Set对象用于映射FusionCharts配置文件中的set元素。<br/>
 * 每个set元素(chart或dataset元素的子元素)表示被用于划分图表上区域的数据集合。
 * 
 * @author 韦海晗
 */
Set = function() {
	this.superclass = FusionElement;
	this.superclass.apply(this, arguments);
	
	this.name = 'set';
};
Set.prototype = new FusionElement;

/**
 * FusionCharts v3 引入Styles可以帮助你在图表中设置字体，应用动态效果、渐变等各种特效，Style对象用于映射FusionCharts配置文件中的style元素。<br/>
 * style元素定义一个样式对象，该对象要定义一个唯一的名称，你可以将该样式对象应用在图表不同的元素中。
 * 
 * @author 韦海晗
 */
Style = function () {
	this.superclass = FusionElement;
	this.superclass.apply(this, arguments);
	
	this.name = 'style';
}
Style.STYLETYPE_FONT = 'Font';
Style.STYLETYPE_ANIMATION = 'Animation';
Style.STYLETYPE_SHADOW = 'Shadow';
Style.STYLETYPE_GLOW = 'Glow';
Style.STYLETYPE_BEVEL = 'Bevel';
Style.STYLETYPE_BLUR = 'Blur';
Style.prototype = new FusionElement;

/**
 * FusionCharts v3 引入Styles可以帮助你在图表中设置字体，应用动态效果、渐变等各种特效，Styles对象用于映射FusionCharts配置文件中的styles元素。<br/>
 * styles元素定义一组样式对象。
 * 
 * @author 韦海晗
 */
Styles = function () {
	this.superclass = FusionElement;
	this.superclass.apply(this, arguments);
	
	this.name = 'styles';
	this.definition = new FusionElement();
	this.definition.setName('definition');
	this.application = new FusionElement();
	this.application.setName('application');
}
Styles.prototype = new FusionElement;

/**
 * 返回表示application的FusionElement对象。
 * 
 * @return application 表示application的FusionElement对象。
 */
Styles.prototype.getApplication = function() {
	return this.application;
};

/**
 * 返回表示definition的FusionElement对象。
 * 
 * @return definition 表示definition的FusionElement对象。
 */
Styles.prototype.getDefinition = function() {
	return this.definition;
};

	/**
	 * 为图表定义一个样式对象。
	 * 
	 * @return style 样式对象。
	 */
Styles.prototype.definitionStyle = function(style) {
	this.definition.addElement(style);
};

/**
 * 将一组样式对象应用于图表对象。
 * 
 * @param toObject 图表对象的名称。
 * @param styles 一组样式对象名称，多个用“,”分隔。
 */
Styles.prototype.applyStyles = function(toObject, styles) {
	var apply = new FusionElement();
	apply.setName('apply');
	apply.addAttribute('toObject', toObject);
	apply.addAttribute('styles', styles);
	this.application.addElement(apply);
};

/**
 * 创建一个DOM的Element对象。<br/>
 * Element对象的属性由FusionElement对象的属性指定。<br/>
 * Element对象的子元素由FusionElement对象的子元素指定。
 * 
 * @return domElement 创建一个DOM的Element对象。
 */
Styles.prototype.createDOMElement = function(){
    //调用父类相应方法
    var domElement = this.superclass.prototype.createDOMElement.apply(this, arguments);

	//设置definition
	domElement.appendChild(this.definition.createDOMElement());
	//设置application
	domElement.appendChild(this.application.createDOMElement());
	return domElement;
};

/**
 * FusionCharts使用XML文件创建和操作图表，TrendLines对象用于映射FusionCharts配置文件中的trendLines元素。<br/>
 * trendLines元素和其子元素line，用于在图表中定义趋势线。
 * 
 * @author 韦海晗
 */
TrendLines = function () {
	this.superclass = FusionElement;
	this.superclass.apply(this, arguments);
	
	this.name = 'trendLines';
}
TrendLines.prototype = new FusionElement;

/**
 * FusionCharts使用XML文件创建和操作图表，VerticalLine对象用于映射FusionCharts配置文件中的vLine元素。<br/>
 * 每个vLine元素表示数据分隔线，用于帮助你分隔数据区域。
 * 
 * @author 韦海晗
 */
VerticalLine = function () {
	this.superclass = FusionElement;
	this.superclass.apply(this, arguments);
	
	this.name = 'vLine';
}
VerticalLine.prototype = new FusionElement;