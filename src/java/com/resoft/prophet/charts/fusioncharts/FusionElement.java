/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.fusioncharts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * FusionCharts使用XML文件创建和操作图表，FusionElement对象用于映射FusionCharts配置文件中的一个元素。<br/>
 * 每个FusionElement对象包含一个属性对象的映射，该映射表示FusionCharts配置文件中用于创建和操作图表的一组属性。<br/>
 * 每个FusionElement对象包含一个FusionElement对象的列表，该列表表示FusionCharts配置文件中的一组子元素。
 * 
 * @author 韦海晗
 */
public class FusionElement {
	protected String name;
	protected Map attributes = new HashMap();
	protected List elements = new ArrayList();
	
	/**
	 * 构造一个新的FusionElement，它表示一个空的元素。
	 */
	FusionElement() {
		
	}
	
	/**
	 * 构造一个新的指定名称的FusionElement。
	 * 
	 * @param name 元素名称。
	 */
	FusionElement(String name) {
		setName(name);
	}
	
	/**
	 * 设置FusionElement的名称。
	 * 
	 * @param name 元素名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 返回FusionElement的名称。
	 * 
	 * @return name 元素名称。
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 设置FusionElement的属性。
	 * 
	 * @param name 属性名称。
	 * @param value 属性值。
	 */
	public void addAttribute(String name, Object value) {
		if(name != null && value != null) {
			attributes.put(name, value);
		}
	}
	
	/**
	 * 返回指定名称的属性。
	 * 
	 * @param name 所返回属性的名称。
	 * @return attribute 元素的属性。
	 */
	public Object getAttribute(String name) {
		return attributes.get(name);
	}
	
	/**
	 * 将指定的元素追加到子元素列表的尾部。
	 * 
	 * @param fusionElement - 元素。
	 */
	public void addElement(FusionElement fusionElement) {
		if(fusionElement != null) {
			elements.add(fusionElement);
		}
	}
	
	/**
	 * 返回子元素列表中指定位置上的元素。
	 * 
	 * @param index 所返回元素的索引。
	 * @return fusionElement 元素。
	 */
	public FusionElement getElement(int index) {
		return (FusionElement) elements.get(index);
	}
	
	/**
	 * 返回子元素列表中的元素数。
	 * 
	 * @return int - 子元素列表中的元素数。
	 */
	public int getElementCount(){
		return elements.size();
	}
	
	/**
	 * 创建一个DOM4J的Element对象。<br/>
	 * Element对象的属性由FusionElement对象的属性指定。<br/>
	 * Element对象的子元素由FusionElement对象的子元素指定。
	 * 
	 * @return dom4jElement 创建一个DOM4J的Element对象。
	 */
	public Element createDOM4JElement() throws NoElementNameError {
		//如果没有找到FusionElement的名称，抛出NoElementNameFoundError异常
		if(name == null) {
			throw new NoElementNameError();
		}
		Element dom4jElement = DocumentHelper.createElement(name);
		//设置属性
		for(Iterator iterator = attributes.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry) iterator.next();
			dom4jElement.addAttribute((String) entry.getKey(), objectToString(entry.getValue()));
		}
		//设置元素
		for(Iterator iterator = elements.iterator(); iterator.hasNext();) {
			FusionElement fusionElement = (FusionElement) iterator.next();
			dom4jElement.add(fusionElement.createDOM4JElement());
		}
		return dom4jElement;
	}
	
	/**
	 * 将此指定的对象转换为一个新的字符串。
	 * 
	 * @param obj 需要转换的对象。
	 * @return str 新的字符串。
	 */
	protected String objectToString(Object obj){
		String str = obj.toString();
		if (obj instanceof Boolean) {
			Boolean bool = (Boolean) obj;
			str =  (bool.booleanValue() ? "1" : "0");
		}
		return str;
	}
}
