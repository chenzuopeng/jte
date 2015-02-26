package org.jte;

import org.jte.constant.Type;

/**
 *
 * 此类表示了一个组件
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋
 * @version 1.00.00, 2010-9-6 下午02:36:19
 * @history:
 *
 */
public abstract class Component extends Callbackable {

	/**
	 * 组件的名称
	 *   注：组件的ID是根据此名称生成的,规则：”id_“前缀+name的小写形式，例如：name=textField,id为id_textfield
	 */
	private String name;

	/**
	 * 组件的宽
	 */
	private int width;

	/**
	 * 组件的高
	 */
	private int height;

	/**
	 * 获取组件类型
	 * @return 组件类型
	 * 注：具体查看{@link org.jte.constant.Type}
	 */
	public abstract Type getType();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
