package demo.action;

import java.io.IOException;
import java.util.LinkedHashSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.jte.Component;
import org.jte.constant.Bool;
import org.jte.form.ComboField;
import org.jte.form.DateField;
import org.jte.form.Form;
import org.jte.form.HiddenField;
import org.jte.form.TextField;
import org.jte.grid.Column;
import org.jte.grid.Grid;
import org.jte.grid.Grid.Editable;
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
 * @author 陈作朋
 * @version 1.00.00, 2010-10-6 下午07:28:06
 * @history:
 *
 */
@Namespace("/table")
public class TableDemoAction {

	@Autowired
	private UserService userService;

	@Action(value = "extractRecords")
	public String extractRecords() throws IOException {
		ActionUtils.showParamOfReq();
		Page<User> page = new Page<User>();
		ActionUtils.initializePageByJteReq(page);
		this.userService.getAll(page);
		return JteStruts2Utils.renderJSON(page.getResult(), page.getTotalCount());
	}

	@Action(value = "extractRecord")
	public String extractRecord() {
		ActionUtils.showParamOfReq();
		return JteStruts2Utils.extractRecord(new JteStruts2Utils.ExtractRecordCallback() {
			public Object extract(String id) {
				return TableDemoAction.this.userService.get(Long.parseLong(id));
			}
		});
	}

	@Action(value = "showPage")
	public String showPage() {
		String[][] statuses=new String[][] { { "enabled", "可用" }, { "disabled", "不可用" } };
		//构建编辑表单
		LinkedHashSet<Component> fields = new LinkedHashSet<Component>();

		final HiddenField idField = new HiddenField();
		idField.setName("id");
		idField.setLabel("id");
		fields.add(idField);

		final TextField nameField = new TextField();
		nameField.setName("name");
		nameField.setLabel("名称");
		nameField.setAllowBlank(Bool.FALSE);
		fields.add(nameField);

		final TextField loginNameField = new TextField();
		loginNameField.setName("login_name");
		loginNameField.setLabel("登录名");
		loginNameField.setAllowBlank(Bool.FALSE);
		fields.add(loginNameField);

		final TextField plainPasswordField = new TextField();
		plainPasswordField.setName("plain_password");
		plainPasswordField.setLabel("密码明文");
		plainPasswordField.setAllowBlank(Bool.FALSE);
		fields.add(plainPasswordField);

		final TextField shaPasswordField = new TextField();
		shaPasswordField.setName("sha_password");
		shaPasswordField.setLabel("密码密文");
		fields.add(shaPasswordField);

		final TextField emailField = new TextField();
		emailField.setName("email");
		emailField.setLabel("邮件地址");
		fields.add(emailField);

		final ComboField statusField = new ComboField();
		statusField.setLabel("状态");
		statusField.setName("status");
		statusField.setOptions(statuses);
		fields.add(statusField);


		final DateField createTimeField = new DateField();
		createTimeField.setDateFmt("yyyy-MM-dd HH:mm:ss");
//		createTimeField.setMinDate("#F{$dp.$D(\\'id_createtimetostring\\',{H:-1});}");
//		createTimeField.setMaxDate("#F{$dp.$D(\\'id_createtimetostring\\',{H:0});}");
		createTimeField.setName("createTimeToString");
		createTimeField.setLabel("时间");
		createTimeField.setAllowBlank(Bool.FALSE);
		createTimeField.setReadOnly(Bool.TRUE);
		fields.add(createTimeField);

		Form form = new Form("editForm");
		form.setWidth(250);
		form.addItems(fields);
		form.addButton(Form.instantiateAjaxSubmitButton());
		form.addButton(Form.instantiateResetButton());
		form.setUrl(JteStruts2Utils.getFullPath("table/save.action"));

		//构建内容展示表单
		Grid grid = new Grid("dataGridPanel");
		grid.setEditable(new Editable[] { Editable.DELETE, Editable.MODIFY, Editable.ADD });
		grid.setForm(form);
		grid.setExtractUrl(JteStruts2Utils.getFullPath("table/extractRecord.action"));
		grid.setDeleteUrl(JteStruts2Utils.getFullPath("table/delete.action"));
		grid.setIndexColumn("id");
		grid.addColumn(new Column("id", "id"));
		grid.addColumn(new Column("name", "名称"));
		grid.addColumn(new Column("login_name", "登录名"));
		grid.addColumn(new Column("plain_password", "密码明文"));
		grid.addColumn(new Column("sha_password", "密码密文"));
		grid.addColumn(new Column("status", "状态", new MapColumnRenderer(statuses)));
		grid.addColumn(new Column("createTimeToString", "时间"));
		grid.setStoreUrl(JteStruts2Utils.getFullPath("table/extractRecords.action"));
		grid.setPageSize(15);
		return JteStruts2Utils.renderTableView(grid);
	}

	@Action(value = "delete")
	public String delete() {
		ActionUtils.showParamOfReq();
		return JteStruts2Utils.delete(new JteStruts2Utils.DeleteCallback() {
			public void doAction(String[] ids) throws Exception {
				for (String id : ids) {
					TableDemoAction.this.userService.delete(Long.parseLong(id));
				}
			}
		});
	}

	@Action(value = "save")
	public String save() {
		ActionUtils.showParamOfReq();
		return JteStruts2Utils.doAjaxRequest(new JteStruts2Utils.Callback() {
			public void doAction() throws Exception {
				HttpServletRequest request = ServletActionContext.getRequest();
				User user = new User();
				String id = request.getParameter("id");
				if (StringUtils.isNotBlank(id)) {
					user.setId(Long.parseLong(id));
				}
				user.setName(request.getParameter("name"));
				user.setLogin_name(request.getParameter("login_name"));
				user.setPlain_password(request.getParameter("plain_password"));
				user.setSha_password(request.getParameter("sha_password"));
				user.setEmail(request.getParameter("email"));
				user.setStatus(request.getParameter("status"));
				user.setCreate_time(DateHelper.parseDate(request.getParameter("createTimeToString")));
				TableDemoAction.this.userService.save(user);
			}
		});
	}
}
