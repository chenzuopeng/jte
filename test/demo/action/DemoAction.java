package demo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.codehaus.jackson.map.ObjectMapper;
import org.jte.QueryView;
import org.jte.constant.Bool;
import org.jte.constant.Constants;
import org.jte.form.ComboField;
import org.jte.form.Field;
import org.jte.form.Form;
import org.jte.form.HiddenField;
import org.jte.form.RowLayoutForm;
import org.jte.form.TableLayoutForm;
import org.jte.form.TextField;
import org.jte.grid.Column;
import org.jte.grid.Grid;
import org.jte.struts2.JSONResponse;
import org.jte.struts2.JteStruts2Utils;

/**
 * 
 * 
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋
 * @version 1.00.00, 2010-9-4 上午11:13:26
 * @history:
 * 
 */

@Namespace("/demo")
public class DemoAction {

	public static class UserInfoStatusVo {

		private String userId;

		private String status;

		public UserInfoStatusVo(String userId, String status) {
			super();
			this.userId = userId;
			this.status = status;
		}

		public String getUserId() {
			return this.userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String GetStatus() {
			return this.status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

	}

	public static class Log {

		private String id;

		private String type;

		private String table;

		private String time;

		public Log(String id, String type, String table, String time) {
			super();
			this.id = id;
			this.type = type;
			this.table = table;
			this.time = time;
		}

		public String getId() {
			return this.id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getType() {
			return this.type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getTable() {
			return this.table;
		}

		public void setTable(String table) {
			this.table = table;
		}

		public String getTime() {
			return this.time;
		}

		public void setTime(String time) {
			this.time = time;
		}

	}

	@Action(value = "list")
	public String list() throws IOException {

		String start = ServletActionContext.getRequest().getParameter(Constants.REQUEST_PARAM_START);

		String limit = ServletActionContext.getRequest().getParameter(Constants.REQUEST_PARAM_PAGE_SIZE);

		// System.out.println("----start:"+start);
		// System.out.println("----pageSize:"+limit);

		String name = ServletActionContext.getRequest().getParameter("name");

		showParam();

		// List<UserInfoStatusVo> infoStatusVos = new
		// ArrayList<UserInfoStatusVo>();
		//
		// for (int i = 0; i < Integer.valueOf(limit); i++) {
		// infoStatusVos.add(new UserInfoStatusVo(name, "正常状态" + (i +
		// Integer.valueOf(start))));
		// }

		List<Log> logs = new ArrayList<Log>();

		logs.add(new Log("root", "登录", "用户表", "2010-09-10 10:30:00"));
		logs.add(new Log("root", "修改", "配置表", "2010-09-10 10:35:00"));
		logs.add(new Log("root", "注销", "用户表", "2010-09-10 10:50:00"));
		logs.add(new Log("sunspot", "登录", "用户表", "2010-09-10 11:30:00"));
		logs.add(new Log("sunspot", "注销", "用户表", "2010-09-10 12:30:00"));
		logs.add(new Log("admin", "登录", "用户表", "2010-09-10 10:00:00"));
		logs.add(new Log("admin", "新增", "配置表", "2010-09-10 10:30:00"));
		logs.add(new Log("admin", "新增", "用户表", "2010-09-10 11:30:00"));
		logs.add(new Log("admin", "修改", "用户表", "2010-09-10 12:30:00"));
		logs.add(new Log("admin", "注销", "用户表", "2010-09-10 13:30:00"));
		return JteStruts2Utils.renderJSON(logs, 30);
	}

	@Action(value = "submit")
	public String submit() throws IOException {
		showParam();
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		out.print(new ObjectMapper().writeValueAsString(new JSONResponse(false, "执行失败")));
		return null;
	}

	@SuppressWarnings("unchecked")
	public void showParam() {
		for (Enumeration e = ServletActionContext.getRequest().getParameterNames(); e.hasMoreElements();) {
			String name = e.nextElement().toString();
			System.out.println(name + "=" + ServletActionContext.getRequest().getParameter(name));
		}
	}

	@Action(value = "showForm", results = { @Result(name = "TEST", type = "freemarker", location = "/ftl/Test.ftl") })
	public String showForm() {
		LinkedHashSet<Field> fields = new LinkedHashSet<Field>();

		final TextField textField = new TextField();
		textField.setName("name");
		textField.setLabel("操作人ID");
		textField.setAllowBlank(Bool.FALSE);

		final TextField textField1 = new TextField();
		textField1.setName("pwd");
		textField1.setLabel("操作表");

		final TextField textField2 = new TextField();
		textField2.setName("pwd1");
		textField2.setLabel("开始日期");

		final TextField textField3 = new TextField();
		textField3.setName("pwd2");
		textField3.setLabel("结束日期");

		fields.add(textField);
		fields.add(textField1);
		fields.add(textField2);
		fields.add(textField3);

		RowLayoutForm rowLayoutForm = new RowLayoutForm("tableLayoutForm", fields,
				JteStruts2Utils.getFullPath("submit.action"));
		rowLayoutForm.addButton(Form.instantiateAjaxSubmitButton());
		rowLayoutForm.addButton(Form.instantiateResetButton());
		ServletActionContext.getRequest().setAttribute("form", rowLayoutForm);
		return "TEST";
	}

	@Action(value = "showPage")
	public String queryPage2() {
		final TextField textField = new TextField();
		textField.setName("name");
		textField.setLabel("操作人ID");
		textField.setAllowBlank(Bool.FALSE);

		final TextField textField1 = new TextField();
		textField1.setName("pwd");
		textField1.setLabel("操作表");

		final TextField textField2 = new TextField();
		textField2.setName("pwd1");
		textField2.setLabel("开始日期");

		final TextField textField3 = new TextField();
		textField3.setName("pwd2");
		textField3.setLabel("结束日期");

		final ComboField comboField = new ComboField();
		comboField.setLabel("性别");
		comboField.setName("sex");
		comboField.setOptions(new String[][] { { "0", "男" }, { "1", "女" } });
		comboField.setValue("1");
		final HiddenField hiddenField = new HiddenField() {
			{
				setName("aaa1");
			}
		};
		final HiddenField hiddenField1 = new HiddenField() {
			{
				setName("aaa2");
			}
		};
		final HiddenField hiddenField3 = new HiddenField() {
			{
				setName("aaa3");
			}
		};

		String url = JteStruts2Utils.getFullPath("list.action");

		TableLayoutForm form = new TableLayoutForm("form");
		form.setUrl(url);
		form.setHasButtonBar(false);
		form.setFields(new LinkedHashSet<Field>() {
			{
				add(textField);
				add(textField1);
				add(hiddenField3);
				add(textField2);
				add(hiddenField1);
				add(hiddenField);
				add(textField3);
				add(comboField);
			}
		});
		form.setFrame(Bool.FALSE);
		form.addButton(Form.instantiateAjaxSubmitButton());
		form.addButton(Form.instantiateResetButton());
		form.init();
		Grid grid = new Grid("dataGridPanel");
		grid.setPageSize(15);
		grid.setStoreUrl(url);
		grid.setColumns(new LinkedHashSet<Column>() {
			{
				add(new Column("id", "操作人ID"));
				add(new Column("type", "操作类型"));
				add(new Column("table", "操作表"));
				add(new Column("time", "操作时间"));
			}
		});

		QueryView queryView = new QueryView();
		queryView.setForm(form);
		queryView.setGrid(grid);

		return JteStruts2Utils.renderQueryView(queryView);
	}

	private static Grid historicalUserStatusPage = new Grid("gridPanel") {
		{
			addColumn(new Column("id", "告警ID"));
			addColumn(new Column("ip", "主机IP"));
			addColumn(new Column("name", "主机名称"));
			addColumn(new Column("no", "告警编号"));
			addColumn(new Column("status", "告警状态", Bool.FALSE, "showStatus"));
			addColumn(new Column("module", "告警模块"));
			addColumn(new Column("info", "告警信息", Bool.FALSE, "showInfo"));
			addColumn(new Column("time", "告警时间"));
			addColumn(new Column("id", "告警声音", Bool.FALSE, "showMusic"));
			this.setStoreUrl(ServletActionContext.getRequest().getContextPath() + "/demo!data.action");
			this.setPageSize(15);
		}
	};

	public static class GJ {
		private String id;
		private String ip;
		private String name;
		private String no;
		private String status = "";
		private String module;
		private String info;
		private String time;

		public GJ(String id, String ip, String name, String no, String module, String info, String time) {
			super();
			this.id = id;
			this.ip = ip;
			this.name = name;
			this.no = no;
			this.module = module;
			this.info = info;
			this.time = time;
		}

		public String getId() {
			return this.id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getIp() {
			return this.ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getNo() {
			return this.no;
		}

		public void setNo(String no) {
			this.no = no;
		}

		public String getStatus() {
			return this.status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getModule() {
			return this.module;
		}

		public void setModule(String module) {
			this.module = module;
		}

		public String getInfo() {
			return this.info;
		}

		public void setInfo(String info) {
			this.info = info;
		}

		public String getTime() {
			return this.time;
		}

		public void setTime(String time) {
			this.time = time;
		}

	}

	// public String data() throws Exception{
	// QueryViewJSONResponse queryViewJSONResponse = new
	// QueryViewJSONResponse();
	// queryViewJSONResponse.setData(new ArrayList<GJ>(){{
	// add(new
	// GJ("23180","192.168.21.9","认证服务器 1","9007","controller","【controller】 【认证阀值告警】认证服务器 1在时间：【2010-09-21 11:07:55】，认证次数超过设定阀值:【10】,当前值为:【12】","2010-09-21 11:07:58"));
	// add(new
	// GJ("23181","192.168.21.9","认证服务器 1","9007","controller","【controller】 【认证阀值告警】认证服务器 1在时间：【2010-09-22 10:01:55】，认证次数超过设定阀值:【10】,当前值为:【11】","2010-09-22 10:07:58"));
	// add(new
	// GJ("23182","192.168.21.9","认证服务器 1","9007","controller","【controller】 【认证阀值告警】认证服务器 1在时间：【2010-09-22 11:07:55】，认证次数超过设定阀值:【10】,当前值为:【11】","2010-09-22 11:08:10"));
	// }});
	// queryViewJSONResponse.setTotal(5);
	// return JteStruts2Utils.render(queryViewJSONResponse);
	// }

	public String gj() {
		return JteStruts2Utils.renderTableView(historicalUserStatusPage);
	}
}
