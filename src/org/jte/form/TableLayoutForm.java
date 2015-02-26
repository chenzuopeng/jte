package org.jte.form;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jte.Button;
import org.jte.Panel;
import org.jte.constant.Layout;
import org.jte.constant.Type;
import org.jte.utils.Assert;

/**
 *
 * 表格布局的表单
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋
 * @version 1.00.00, 2010-10-5 下午04:54:33
 * @history:
 *
 */
public class TableLayoutForm extends Form {

	/**
	 * 默认的列数
	 */
	public final static int DEFAULT_COLUMN_COUNT = 4;

	/**
	 * 默认的列宽
	 */
	public final static int DEFAULT_COLUMN_WIDTH = 250;

	/**
	 * 默认的行高
	 */
	public final static int DEFAULT_ROW_HEIGHT = 30;

	/**
	 * 列数
	 */
	private int columnCount = DEFAULT_COLUMN_COUNT;

	/**
	 * 列宽
	 */
	private int columnWidth = DEFAULT_COLUMN_WIDTH;

	/**
	 * 行高
	 */
	private int rowHeight = DEFAULT_ROW_HEIGHT;

	/**
	 * 根面板
	 */
	private Panel rootPanel = new Panel(Layout.COLUMN){
		{
			setName("root_panel");
		}
	};

	/**
	 * 表单包含的域的集合
	 */
	private LinkedHashSet<Field> fields = new LinkedHashSet<Field>();

	/**
	 * 非隐藏的表单域的个数
	 */
	private int notIsHiddenFieldCount;

	/**
	 * 是否显示按钮面板
	 */
	private boolean hasButtonBar = true;

	{
		this.addItem(this.rootPanel);
	}

	public TableLayoutForm() {
		super();
	}

	public TableLayoutForm(String name) {
		super(name);
	}

	public void init() {
		initSecondaryPanel();
		addButtons();
		evaluateHeight();
		evaluateWidth();
	}

	private void initSecondaryPanel() {
		int fieldCount = this.fields.size();
		int secondaryPanelCount = fieldCount >= this.columnCount ? this.columnCount : fieldCount;
		Panel[] secondaryPanels = new Panel[secondaryPanelCount];
		for (int i = 0; i < secondaryPanels.length; i++) {
			Panel panel = new Panel();
			panel.setName("secondary_panel_"+i);
			panel.setWidth(this.columnWidth);
			panel.setHeight(this.rowHeight);
			secondaryPanels[i] = panel;
			this.rootPanel.addItem(panel);
		}
		int i = 0;
		for (Field field : this.fields) {
			if (field.getType() != Type.HIDDEN) {
				secondaryPanels[i++ % this.columnCount].addItem(field);
			} else {
				addItem(field);
			}
		}
		this.notIsHiddenFieldCount = i;
	}

	private void addButtons() {
		if (this.getButtons().size() > 0&&(!this.hasButtonBar)) {
			Set<Button> buttons = this.getButtons();
			for (Button button : buttons) {
				Panel panel = new Panel();
				panel.setWidth(60);
				panel.addItem(button);
				this.rootPanel.addItem(panel);
			}
			buttons.clear();
		}
	}

	private int getRowCount() {
		return this.notIsHiddenFieldCount / this.columnCount
				+ (this.notIsHiddenFieldCount % this.columnCount > 0 ? 1 : 0);
	}

	private void evaluateHeight() {
		int height = getRowCount() * this.rowHeight + 50;
		if (StringUtils.isNotBlank(this.getTitle())) {
			height += DEFAULT_ROW_HEIGHT;
		}
		if (this.hasButtonBar) {
			height += DEFAULT_ROW_HEIGHT;
		}
		setHeight(height);
	}

	private void evaluateWidth() {
		int width = (this.notIsHiddenFieldCount >= this.columnCount ? this.columnCount : this.notIsHiddenFieldCount)
				* this.columnWidth + 50;
		if (!this.hasButtonBar) {
			width += this.columnWidth;
		}
		setWidth(width);
	}

	@Override
	public Set<Field> getFields() {
		return this.fields;
	}

	public void setFields(LinkedHashSet<Field> fields) {
		Assert.notNull(fields);
		this.fields = fields;
	}

	public int getColumnCount() {
		return this.columnCount;
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

	public int getColumnWidth() {
		return this.columnWidth;
	}

	public void setColumnWidth(int columnWidth) {
		this.columnWidth = columnWidth;
	}

	public int getRowHeight() {
		return this.rowHeight;
	}

	public void setRowHeight(int rowHeight) {
		this.rowHeight = rowHeight;
	}

	public boolean isHasButtonBar() {
		return this.hasButtonBar;
	}

	public void setHasButtonBar(boolean hasButtonBar) {
		this.hasButtonBar = hasButtonBar;
	}

}
