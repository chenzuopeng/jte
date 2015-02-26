package org.jte.grid;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang.Validate;
import org.jte.Callbackable;
import org.jte.form.Form;

/**
 *
 * 此类表示一个表格
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋
 * @version 1.00.00, 2010-9-5 下午06:02:02
 * @history:
 *
 */
public class Grid extends Callbackable {

	public final static String DO_AFTER_SUCCESSFUL_SUBMIT_WHEN_EDIT_FORM="doAfterSuccessfulSubmitForEditForm";

	/**
	 * 表格的名称
	 *   注：表格的ID是根据此名称生成的,规则：”id_“前缀+name的小写形式，例如：name=textField,id为id_textfield
	 */
	private String name;

	/**
	 * 表格包含的列列表
	 */
	private Set<Column> columns = new LinkedHashSet<Column>();

	/**
	 * 每页展示的记录数
	 */
	private int pageSize = 10;

	/**
	 * 提供的编辑功能
	 */
	private Editable[] editable = new Editable[0];

	/**
	 * 获取表格展示数据的URL
	 */
	private String storeUrl;

	/**
	 * 编辑内容的表单
	 */
	private Form form;

	/**
	 * 获取指定记录信息的URL
	 */
	private String extractUrl;

	/**
	 * 处理删除记录操作的URL
	 */
	private String deleteUrl;

	/**
	 * 所有类的标示
	 */
	private String indexColumn;

	/**
	 * 表示表格提供的编辑枚举
	 */
	public enum Editable {
		/**
		 * 添加记录功能
		 */
		ADD,
		/**
		 * 修改记录功能
		 */
		MODIFY,
		/**
		 * 删除记录功能
		 */
		DELETE;
	}

	public Grid(String name) {
		super();
		setName(name);
	}

	public void addColumn(Column column) {
		this.columns.add(column);
	}

	public void setColumns(Set<Column> columns) {
		this.columns = columns;
	}

	public Set<Column> getColumns() {
		return Collections.unmodifiableSet(this.columns);
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getStoreUrl() {
		return this.storeUrl;
	}

	public void setStoreUrl(String storeUrl) {
		this.storeUrl = storeUrl;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Editable[] getEditable() {
		return this.editable;
	}

	public void setEditable(Editable[] editable) {
		this.editable = editable;
	}

	public Form getForm() {
		return this.form;
	}

	public void setForm(Form form) {
		Validate.notNull(form, "form can not null");
		this.form = form;
		this.form.setDoAfterSuccessfulSubmit(DO_AFTER_SUCCESSFUL_SUBMIT_WHEN_EDIT_FORM);
	}

	public String getExtractUrl() {
		return this.extractUrl;
	}

	public void setExtractUrl(String extractUrl) {
		this.extractUrl = extractUrl;
	}

	public String getIndexColumn() {
		return this.indexColumn;
	}

	public void setIndexColumn(String indexColumn) {
		this.indexColumn = indexColumn;
	}

	public String getDeleteUrl() {
		return this.deleteUrl;
	}

	public void setDeleteUrl(String deleteUrl) {
		this.deleteUrl = deleteUrl;
	}

}