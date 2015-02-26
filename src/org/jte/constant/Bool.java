package org.jte.constant;

/**
 *
 * 表示布尔类型的枚举
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋
 * @version 1.00.00, 2010-9-6 下午02:55:44
 * @history:
 *
 */
public enum Bool {

	/**
	 * 真
	 */
	TRUE,

	/**
	 * 假
	 */
	FALSE;

	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
