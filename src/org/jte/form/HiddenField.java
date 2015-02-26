package org.jte.form;

import org.jte.constant.Type;

/**
 *
 * 表示一个表单的隐藏域
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋
 * @version 1.00.00, 2010-10-6 下午08:55:44
 * @history:
 *
 */
public class HiddenField extends TextField {

	@Override
	public Type getType() {
		return Type.HIDDEN;
	}
}
