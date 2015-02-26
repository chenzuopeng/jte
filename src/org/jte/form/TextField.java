package org.jte.form;

import org.apache.commons.lang.StringUtils;
import org.jte.constant.Bool;
import org.jte.constant.Type;

/**
 *
 * 此类表示一个文本框域
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋
 * @version 1.00.00, 2010-9-5 下午06:02:02
 * @history:
 *
 */
public class TextField extends Field {

	/**
	 * 文本框域的标签域显示的文本
	 */
	private String label = "";

	/**
	 * 是否允许为空
	 */
	private Bool allowBlank = Bool.TRUE;

	/**
	 * 是否只读
	 */
	private Bool readOnly = Bool.FALSE;

	/**
	 * 用于自动验证内容JavaScript的正则表达
	 */
	private String regex = "";

	/**
	 * 内容验证失败后显示的信息
	 */
	private String regexText = "";

	/**
	 * 初始值
	 */
	private String initValue = "";

	/**
	 * 焦点监听器
	 */
	private String focusListener = "";

	@Override
	public Type getType() {
		return Type.TEXTFIELD;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		if (StringUtils.isNotBlank(label)) {
			this.label = label;
		}
	}

	public Bool getAllowBlank() {
		return this.allowBlank;
	}

	public void setAllowBlank(Bool allowBlank) {
		this.allowBlank = allowBlank;
	}

	public String getFocusListener() {
		return this.focusListener;
	}

	public void setFocusListener(String focusListener) {
		if (StringUtils.isNotBlank(focusListener)) {
			this.focusListener = focusListener;
		}
	}

	public String getRegex() {
		return this.regex;
	}

	public void setRegex(String regex) {
		if (StringUtils.isNotBlank(regex)) {
			this.regex = regex;
		}
	}

	public String getRegexText() {
		return this.regexText;
	}

	public void setRegexText(String regexText) {
		if (StringUtils.isNotBlank(regexText)) {
			this.regexText = regexText;
		}
	}

	public String getInitValue() {
		return this.initValue;
	}

	public void setInitValue(String initValue) {
		if (StringUtils.isNotBlank(initValue)) {
			this.initValue = initValue;
		}
	}

	public Bool getReadOnly() {
		return this.readOnly;
	}

	public void setReadOnly(Bool readOnly) {
		this.readOnly = readOnly;
	}
}
