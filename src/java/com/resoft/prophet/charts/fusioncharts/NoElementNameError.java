/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.fusioncharts;

/**
 * 当创建DOM4J的Element对象时，没有找到FusionElement的name属性。<br/>
 * 
 * @author 韦海晗
 */
public class NoElementNameError extends RuntimeException {
	private static final long serialVersionUID = -2137402091027010524L;

	public NoElementNameError() {
        super();
    }

    public NoElementNameError(String s) {
        super(s);
    }

    public NoElementNameError(Throwable throwable) {
        super(throwable);
    }

    public NoElementNameError(String s, Throwable throwable) {
        super(s, throwable);
    }
}
