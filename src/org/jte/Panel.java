package org.jte;

import java.util.LinkedHashSet;
import java.util.Set;

import org.jte.constant.Bool;
import org.jte.constant.Layout;
import org.jte.constant.Type;

/**
 *
 * 此类表示一个面板组件,作为其他组件的容器
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋
 * @version 1.00.00, 2010-9-5 下午06:20:33
 * @history:
 *
 */
public class Panel extends Component {

	/**
	 * 包含的组件列表
	 */
	private Set<Component> items = new LinkedHashSet<Component>();

	/**
	 * 包含的按钮列表
	 */
	private Set<Button> buttons = new LinkedHashSet<Button>();

	/**
	 * 布局类型
	 */
	private Layout layout = Layout.FORM;

	/**
	 * 此属性会在根据此类对象生成Extjs对象时,设置生成的Extjs对象的frame为对应的值
	 */
	private Bool frame = Bool.FALSE;

	public Panel() {
		super();
	}

	public Panel(Layout layout) {
		super();
		this.layout = layout;
	}

	public void addItem(Component component) {
		this.items.add(component);
	}

	public void addItems(Set<Component> components) {
		this.items.addAll(components);
	}

	public Set<Component> getItems() {
		return this.items;
	}

	public void addButton(Button button) {
		this.buttons.add(button);
	}

	public void addButtons(Set<Button> buttons) {
		this.buttons.addAll(buttons);
	}

	public Set<Button> getButtons() {
		return this.buttons;
	}

	public Layout getLayout() {
		return this.layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	@Override
	public Type getType() {
		return Type.PANEL;
	}

	public Bool getFrame() {
		return this.frame;
	}

	public void setFrame(Bool frame) {
		this.frame = frame;
	}

}
