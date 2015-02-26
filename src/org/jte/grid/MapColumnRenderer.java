package org.jte.grid;

import org.jte.utils.Assert;

/**
 *
 *
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋 Jan 23, 2011
 * @version 1.00.00
 * @history:
 *
 */
public class MapColumnRenderer extends ColumnRenderer {

	private String[][] map;

	public MapColumnRenderer(String[][] map) {
		super();
		setMap(map);
	}

	@Override
	public ColumnRendererType getType() {
		return ColumnRendererType.MAP;
	}

	public String[][] getMap() {
		return map;
	}

	public void setMap(String[][] map) {
		Assert.notNull(map);
		this.map = map;
	}

}
