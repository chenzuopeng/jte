package demo.action;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.jsearch.Constants;
import org.jsearch.OperateType;
import org.jsearch.annotation.Criterion;
import org.jte.Button;
import org.jte.QueryView;
import org.jte.constant.Bool;
import org.jte.form.ComboField;
import org.jte.form.DateField;
import org.jte.form.Field;
import org.jte.form.Form;
import org.jte.form.TableLayoutForm;
import org.jte.form.TextField;
import org.jte.grid.Column;
import org.jte.grid.Grid;
import org.jte.grid.MapColumnRenderer;
import org.jte.struts2.JteStruts2Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;

import demo.data.User;
import demo.data.UserService;
import demo.utils.ActionUtils;
import demo.utils.DateHelper;

/**
 * 
 * 
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋 May 24, 2011
 * @version 1.00.00
 * @history:
 * 
 */
@Namespace("/query")
public class QueryDemoAction {

	@Autowired
	private UserService userService;

	@Action(value = "showPage")
	public String showPage() {
		
		//定义查询表单
		String[][] statuses = new String[][] { { "enabled", "可用" }, { "disabled", "不可用" } };
		LinkedHashSet<Field> fields = new LinkedHashSet<Field>();
        //添加一个“名称”查询域
		TextField nameField = new TextField();
		nameField.setName("name");
		nameField.setLabel("名称");
		fields.add(nameField);
		//添加一个“登录名”查询域
		TextField loginNameField = new TextField();
		loginNameField.setName("login_name");
		loginNameField.setLabel("登录名");
//		fields.add(loginNameField);
		//添加一个“状态”查询域
		ComboField statusField = new ComboField();
		statusField.setLabel("状态");
		statusField.setName("status");
		statusField.setOptions(statuses);
		fields.add(statusField);
		//添加一个“开始时间”查询域
		DateField beginTimeField = new DateField();
		beginTimeField.setDateFmt("yyyy-MM-dd HH:mm:ss");
		beginTimeField.setName("beginTime");
		beginTimeField.setLabel("开始时间");
		beginTimeField.setReadOnly(Bool.TRUE);
		fields.add(beginTimeField);
		//添加一个“结束时间”查询域
		DateField endTimeField = new DateField();
		endTimeField.setDateFmt("yyyy-MM-dd HH:mm:ss");
		endTimeField.setName("endTime");
		endTimeField.setLabel("结束时间");
		endTimeField.setReadOnly(Bool.TRUE);
		fields.add(endTimeField);

		TableLayoutForm form = new TableLayoutForm("queryForm");
		form.setFields(fields);
		form.setFrame(Bool.FALSE);
		form.setHasButtonBar(false); //没有按钮区域
		form.addButton(new Button("提交", QueryView.SUBMIT_BUTTON_HANDLER)); //添加一个提交按钮
		form.addButton(Form.instantiateResetButton()); //添加一个提交重置
		form.setUrl(JteStruts2Utils.getFullPath("query/doQuery.action")); //设置表单提交的URL
		form.init();
        
		//定义展示数据的表格
		Grid grid = new Grid("dataGridPanel");
		grid.setIndexColumn("id"); //设置索引列
		//添加表格列
		grid.addColumn(new Column("id", "id"));
		grid.addColumn(new Column("name", "名称"));
		grid.addColumn(new Column("login_name", "登录名"));
		grid.addColumn(new Column("plain_password", "密码明文"));
		grid.addColumn(new Column("sha_password", "密码密文"));
		grid.addColumn(new Column("status", "状态", new MapColumnRenderer(statuses)));
		grid.addColumn(new Column("createTimeToString", "时间"));
		grid.setStoreUrl(JteStruts2Utils.getFullPath("query/doQuery.action")); //获取表格数据的URL
		grid.setPageSize(15);
        
		//定义查询视图对象
		QueryView queryView = new QueryView();
		queryView.setForm(form);
		queryView.setGrid(grid);
		return JteStruts2Utils.renderQueryView(queryView);
	}

	@Action(value = "doQuery")
	public String doQuery() {
		Page<User> page = new Page<User>();
		ActionUtils.initializePageByJteReq(page); //初始化Page对象，主要是进行页大小和起始页的设置等
		this.userService.findByBean(page, new UserSBean());
		return JteStruts2Utils.renderJSON(page.getResult(), page.getTotalCount());
	}

	@SuppressWarnings("unused")
	public static class UserSBean {

//		@Criterion(operate = OperateType.LIKE,expression=Constants.PLACEHOLDER_LIKE+"%")
//		private String login_name;

		@Criterion(operate = OperateType.LIKE,expression=Constants.PLACEHOLDER_LIKE+"%")
		private String name;

		@Criterion
		private String status;

		@Criterion(operate = OperateType.BETWEEN)
		private Date[] create_time;

		public UserSBean() {
//			this.login_name = ServletActionContext.getRequest().getParameter("login_name");
			this.name = ServletActionContext.getRequest().getParameter("name");
			this.status = ServletActionContext.getRequest().getParameter("status");
			String beginTime = ServletActionContext.getRequest().getParameter("beginTime");
			String endTime = ServletActionContext.getRequest().getParameter("endTime");
			if (StringUtils.isNotBlank(beginTime) && StringUtils.isNotBlank(endTime)) {
				this.create_time = new Date[] { DateHelper.parseDate(beginTime), DateHelper.parseDate(endTime) };
			}
		}
	}
}
