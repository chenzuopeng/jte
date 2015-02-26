package org.jte.form;

import org.apache.commons.lang.StringUtils;
import org.jte.constant.Type;

/**
 *
 * 此类表示一个下拉列表框域
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋
 * @version 1.00.00, 2010-9-5 下午06:15:25
 * @history:
 *
 */
public class ComboField extends TextField {

	/**
	 * 默认值
	 */
	private String value="";

	/**
	 * 元素
	 */
	private String[][] options = new String[0][0];

	@Override
	public Type getType() {
		return Type.COMBO;
	}

	public String[][] getOptions() {
		return this.options;
	}

	public void setOptions(String[][] options) {
		this.options = options;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		if(StringUtils.isNotBlank(value)){
		    this.value = value;
		}
	}

	@Override
	public void setInitValue(String initValue) {
		throw new RuntimeException("not support setter for field initValue");
	}

}
