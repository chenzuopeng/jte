package org.jte.form;

import java.util.LinkedHashSet;


/**
 *
 * 行布局的表单
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋
 * @version 1.00.00, 2010-10-6 下午12:38:02
 * @history:
 *
 */
public class RowLayoutForm extends TableLayoutForm {

	public RowLayoutForm(String name, LinkedHashSet<Field> fields,String url) {
		this(name,fields,url,TableLayoutForm.DEFAULT_COLUMN_WIDTH, TableLayoutForm.DEFAULT_ROW_HEIGHT);
	}

	public RowLayoutForm(String name,LinkedHashSet<Field> fields,String url,int columnWidth, int rowHeight){
		setName(name);
        setFields(fields);
        setColumnCount(1);
        setUrl(url);
		setWidth(columnWidth);
		setHeight(rowHeight);
		init();
	}

}
