package org.jte;

/**
*
* 此类表示一个包含一个查询表单和一个用于展示内容的区域,此区域是一个Image域
* @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
* @Company: 北京福富软件有限公司
* @author 陈作朋
* @version 1.00.00, 2010-9-5 下午06:02:02
* @history:
*
*/
import org.jte.form.Form;
import org.jte.utils.Assert;

public class ImageQueryView extends Callbackable{

    /**
     * 用于提交此页面内表单的JavaScript的函数
     *    注：在根据这个类的对象生成页面对象同时会自动生成一个用于提交在此页面中的表单的JavaScript的函数;绝大多数情况下,在提交此页面上的表单时,需要使用这个函数.
     */
	public final static String SUBMIT_BUTTON_HANDLER="htmlQuerySubmit";

	/**
	 * 包含的表格
	 */
	private Form form;

	/**
	 * 图片域的宽度
	 */
	private int imageWidth;

	/**
	 * 图片域的高度
	 */
	private int imageHeight;

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		Assert.notNull(form);
		this.form = form;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

}
