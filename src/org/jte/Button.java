package org.jte;

import org.apache.commons.lang.StringUtils;
import org.jte.constant.Type;

/**
 *
 * 表示一个按钮组件
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋
 * @version 1.00.00, 2010-9-6 下午05:10:52
 * @history:
 *
 */
public class Button extends Component {

	/**
	 * 按钮显示的文本
	 */
	private String text = "";

	/**
	 * 在按钮被点击时执行的JavaScript的函数
	 */
	private String handler = "";

	public Button() {
		super();
	}

	public Button(String text, String handler) {
		super();
		this.text = text;
		setHandler(handler);
	}

	@Override
	public Type getType() {
		return Type.BUTTON;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		if (StringUtils.isNotBlank(text)) {
			this.text = text;
		}
	}

	public String getHandler() {
		return this.handler;
	}

	public void setHandler(String handler) {
		if (StringUtils.isNotBlank(handler)) {
			this.handler = handler;
		}
	}
}
