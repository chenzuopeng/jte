package org.jte;

import java.util.LinkedHashSet;

import org.jte.constant.Bool;
import org.jte.form.Field;
import org.jte.form.Form;
import org.jte.form.TableLayoutForm;
import org.jte.grid.Column;
import org.jte.grid.Grid;
import org.jte.utils.Assert;

/**
 *
 * 此类表示一个包含一个查询表单和一个用于展示数据的表格的页面
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋
 * @version 1.00.00, 2010-9-5 下午06:02:02
 * @history:
 *
 */
public class QueryView extends Callbackable {

    /**
     * 用于提交此页面内表单的JavaScript的函数
     *    注：在根据这个类的对象生成页面对象同时会自动生成一个用于提交在此页面中的表单的JavaScript的函数;绝大多数情况下,在提交此页面上的表单时,需要使用这个函数.
     */
	public final static String SUBMIT_BUTTON_HANDLER="querySubmit";

	/**
	 * 包含的表格
	 */
	private Grid grid;

	/**
	 * 包含的表单
	 */
	private Form form;

	public QueryView() {
		super();
	}

	public QueryView(LinkedHashSet<Field> fields, LinkedHashSet<Column> columns, int pageSize, String url) {
		this.grid = new Grid("grid");
		this.grid.setColumns(columns);
		this.grid.setPageSize(pageSize);
		this.grid.setStoreUrl(url);
		TableLayoutForm tableLayoutForm = new TableLayoutForm();
		tableLayoutForm.setName("form");
		tableLayoutForm.setFields(fields);
		tableLayoutForm.setUrl(url);
		tableLayoutForm.setFrame(Bool.FALSE);
		tableLayoutForm.addButton(new Button("查询",SUBMIT_BUTTON_HANDLER));
		tableLayoutForm.addButton(Form.instantiateResetButton());
		tableLayoutForm.init();
		this.form = tableLayoutForm;
	}

	public Grid getGrid() {
		return this.grid;
	}

	public void setGrid(Grid grid) {
		Assert.notNull(grid);
		this.grid = grid;
	}

	public Form getForm() {
		return this.form;
	}

	public void setForm(Form form) {
		Assert.notNull(form);
		this.form = form;
	}
}