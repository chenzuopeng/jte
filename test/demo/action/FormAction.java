package demo.action;

import java.util.LinkedHashSet;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.jte.Button;
import org.jte.constant.Bool;
import org.jte.form.ComboField;
import org.jte.form.Field;
import org.jte.form.Form;
import org.jte.form.RowLayoutForm;
import org.jte.form.TextField;

/**
 * 
 * 
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋 Jun 16, 2011
 * @version 1.00.00
 * @history:
 * 
 */
public class FormAction {

	@Action(value = "/test", results = { @Result(name = "success", type = "freemarker", location = "/ftl/Test.ftl") })
	public String showPage() {
		LinkedHashSet<Field> fields = new LinkedHashSet<Field>();

		TextField serviceNameTextField = new TextField();
		serviceNameTextField.setName("serviceName");
		serviceNameTextField.setLabel("系统名称");
		serviceNameTextField.setAllowBlank(Bool.FALSE);
		fields.add(serviceNameTextField);
		
		TextField ssDeviceNoTextField = new TextField();
		ssDeviceNoTextField.setName("ssDeviceNo");
		ssDeviceNoTextField.setLabel("设备号");
		ssDeviceNoTextField.setRegex("/[0-9]{16}/");
		ssDeviceNoTextField.setRegexText("16位数字");
		ssDeviceNoTextField.setAllowBlank(Bool.FALSE);
		fields.add(ssDeviceNoTextField);
		
/*		ComboField ssTypeComboField = new ComboField();
		ssTypeComboField.setLabel("系统类型");
		ssTypeComboField.setName("ssType");
		ssTypeComboField.setAllowBlank(Bool.FALSE);
		ssTypeComboField.setReadOnly(Bool.TRUE);
		ssTypeComboField.setOptions(new String[][]{{"1","全国UDB"},{"2","IT渠道系统"},{"3","业务系统"},{"4","Radius服务器 "},{"5","OTP服务器"}});
		fields.add(ssTypeComboField);*/
		
		TextField ssTypeTextField = new TextField();
		ssTypeTextField.setName("ssType");
		ssTypeTextField.setLabel("系统类型");
		ssTypeTextField.setRegex("/[0-9]{4}/");
		ssTypeTextField.setRegexText("4位数字");
		ssTypeTextField.setAllowBlank(Bool.FALSE);
		fields.add(ssTypeTextField);
		
		ComboField targetTypeComboField = new ComboField();
		targetTypeComboField.setLabel("目标系统类型");
		targetTypeComboField.setName("targetType");
		targetTypeComboField.setAllowBlank(Bool.FALSE);
		targetTypeComboField.setReadOnly(Bool.TRUE);
		targetTypeComboField.setOptions(new String[][]{{"1","全国UDB"},{"2","IT渠道系统"},{"3","业务系统"},{"4","Radius服务器 "},{"5","OTP服务器"}});
		fields.add(targetTypeComboField);
		
		TextField authPasswordTextField = new TextField();
		authPasswordTextField.setName("authPassword");
		authPasswordTextField.setLabel("认证密码");
		authPasswordTextField.setAllowBlank(Bool.FALSE);
		fields.add(authPasswordTextField);
		
		TextField secureKeyTextField = new TextField();
		secureKeyTextField.setName("secureKey");
		secureKeyTextField.setLabel("安全密钥");
		secureKeyTextField.setAllowBlank(Bool.FALSE);
		fields.add(secureKeyTextField);
		
		RowLayoutForm form = new RowLayoutForm("addForm", fields, "/add.action");
		form.setHasButtonBar(true);
		form.addButton(Form.instantiateAjaxSubmitButton());
		form.addButton(Form.instantiateResetButton());
		form.init();
		ServletActionContext.getRequest().setAttribute("form", form);
		return "success";
	}

}
