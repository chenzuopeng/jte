package org.jte.grid;

import org.jte.constant.Bool;
import org.jte.utils.Assert;

/**
 *
 * 此类表示的是表格中的列.
 *
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋
 * @version 1.00.00, 2010-9-5 下午06:02:02
 * @history:
 *
 */
public class Column {

	/**
	 * 列的名称
	 */
	private String name;

	/**
	 * 在表格头部中显示的列的文本
	 */
	private String label;

	/**
	 * 此列能否进行排序
	 */
	private Bool sortable = Bool.TRUE;

	/**
	 * 一个JavaScript函数,用于进行此列对象的展示
	 */
	private String rendererFunc;

	/**
	 * 在同时设置rendererFunc属性和renderer属性情况下,rendererFunc属性起作用
	 */
	private ColumnRenderer renderer;

	public Column() {
		super();
	}

	public Column(String name, String label) {
		super();
		setName(name);
		setLabel(label);
	}

	public Column(String name, String label, Bool sortable, String rendererFunc) {
		super();
		setName(name);
		setLabel(label);
		setSortable(sortable);
		setRendererFunc(rendererFunc);
	}

	public Column(String name, String label,ColumnRenderer renderer) {
		super();
		setName(name);
		setLabel(label);
		setRenderer(renderer);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		Assert.notBlank(name);
		this.name = name;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		Assert.notBlank(label);
		this.label = label;
	}

	public Bool getSortable() {
		return this.sortable;
	}

	public void setSortable(Bool sortable) {
		Assert.notNull(sortable);
		this.sortable = sortable;
	}

	public String getRendererFunc() {
		return rendererFunc;
	}

	public void setRendererFunc(String rendererFunc) {
		this.rendererFunc = rendererFunc;
	}

	public ColumnRenderer getRenderer() {
		return renderer;
	}

	public void setRenderer(ColumnRenderer renderer) {
		this.renderer = renderer;
	}

}
