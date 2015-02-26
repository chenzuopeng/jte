package org.jte.struts2;

/**
 *
 * 此类表示一个JSON类型的响应
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋
 * @version 1.00.00, 2010-9-7 下午03:09:59
 * @history:
 *
 */
public class JSONResponse {

	/**
	 * 成功标示
	 */
	private boolean success;

	/**
	 * 响应消息
	 */
	private String msg;

	public JSONResponse() {
		super();
	}

	public JSONResponse(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
