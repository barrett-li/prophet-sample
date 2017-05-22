/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.fusioncharts;

import com.resoft.prophet.charts.fusioncharts.FusionElement;

import junit.framework.TestCase;

/**
 * FusionElement单元测试类
 * 
 * @author 韦海晗
 */
public class FusionElementTest extends TestCase {
	private FusionElement fusionElement;

	protected void setUp() throws Exception {
		fusionElement = new FusionElement("chart");
	}

	public void testAddAttribute() {
		fusionElement.addAttribute("caption", "Monthly Revenue");
		fusionElement.addAttribute("xAxisName", null);
		fusionElement.addAttribute(null, "Revenue");
		fusionElement.addAttribute(null, null);

		assertEquals((String) fusionElement.getAttribute("caption"), "Monthly Revenue");
		assertEquals((String) fusionElement.getAttribute("xAxisName"), null);
	}

	public void testAddElement() {
		fusionElement.addElement(new FusionElement("set1"));
		fusionElement.addElement(new FusionElement("vLine"));
		fusionElement.addElement(new FusionElement("set2"));

		assertEquals(fusionElement.getElementCount(), 3);

		assertEquals(((FusionElement) fusionElement.getElement(0)).getName(), "set1");
		assertEquals(((FusionElement) fusionElement.getElement(1)).getName(), "vLine");
		assertEquals(((FusionElement) fusionElement.getElement(2)).getName(), "set2");
	}
}
