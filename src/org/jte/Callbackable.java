package org.jte;

import org.apache.commons.lang.StringUtils;

/**
 *
 * 实现此接口的类表示提供回调功能
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋
 * @version 1.00.00, 2010-9-10 下午12:04:46
 * @history:
 *
 */
public abstract class Callbackable {

	/**
	 * 回调的JavaScript函数名
	 */
	private String callback="";

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		if(StringUtils.isNotBlank(callback)){
			this.callback = callback;
		}
	}

}
