package org.jte.constant;

/**
 *
 * 组件类型
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋
 * @version 1.00.00, 2010-9-6 下午02:41:47
 * @history:
 *
 */
public enum Type {
	BOX, BUTTON, COLORPALETTE, COMPONENT, CONTAINER, CYCLE, DATAVIEW, DATEPICKER, EDITOR, EDITORGRID, GRID, PAGING, PANEL, PROGRESS, PROPERTYGRID, SLIDER, SPLITBUTTON, STATUSBAR, TABPANEL, TREEPANEL, VIEWPORT, WINDOW, FORM, CHECKBOX, COMBO, DATEFIELD, FIELD, FIELDSET, HIDDEN, HTMLEDITOR, LABEL, NUMBERFIELD, RADIO, TEXTAREA, TEXTFIELD, TIMEFIELD, TRIGGER;

	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
