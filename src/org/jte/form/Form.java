package org.jte.form;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jte.Button;
import org.jte.Component;
import org.jte.Panel;
import org.jte.constant.Bool;

/**
 *
 * 此类表示一个表单
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋
 * @version 1.00.00, 2010-9-5 下午06:02:02
 * @history:
 *
 */
public class Form extends Panel {

	/**
	 * 表单的标题
	 */
	private String title = "";

	/**
	 * 提交表单的URL
	 */
	private String url = "";

	/**
	 * 可以指定一个JavaScript函数,在表单提交成功后的回调此函数.
	 *   注:此函数只会在使用From对象解析为Extjs对象时自动生成的提交函数(表单名_htmlSubmit()和表单名_ajaxSubmit()这2个方法中)中调用.
	 */
	private String doAfterSuccessfulSubmit;

	/**
	 * 初始化一个带有预定义功能的重置按钮
	 */
	public static Button instantiateResetButton() {
		return new Button("重置", "reset");
	}

	/**
	 * 初始化一个带有预定义功能的以HTML方式提交表单的提交按钮
	 */
	public static Button instantiateHtmlSubmitButton() {
		return new Button("提交", "htmlSubmit");
	}

	/**
	 * 初始化一个带有预定义功能的以Ajax方式提交表单的提交按钮
	 */
	public static Button instantiateAjaxSubmitButton() {
		return new Button("提交", "ajaxSubmit");
	}

	{
		setFrame(Bool.TRUE);
	}

	public Form() {
		super();
	}

	public Form(String name) {
		super();
		setName(name);
	}

	public Set<Field> getFields() {
		Set<Field> fields = new LinkedHashSet<Field>();
		getFields(this.getItems(), fields);
		return fields;
	}

	private void getFields(Set<Component> items, Set<Field> fields) {
		for (Component component : items) {
			if (component instanceof Field) {
				fields.add((Field) component);
			} else if (component instanceof Panel) {
				getFields(((Panel) component).getItems(), fields);
			}
		}
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		if (StringUtils.isNotBlank(title)) {
			this.title = title;
		}
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		if (StringUtils.isNotBlank(url)) {
			this.url = url;
		}
	}

	public String getDoAfterSuccessfulSubmit() {
		return doAfterSuccessfulSubmit;
	}

	public void setDoAfterSuccessfulSubmit(String doAfterSuccessfulSubmit) {
		this.doAfterSuccessfulSubmit = doAfterSuccessfulSubmit;
	}
}