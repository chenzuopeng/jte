package demo.action;

import java.util.LinkedHashSet;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.jte.Button;
import org.jte.IframeQueryView;
import org.jte.constant.Bool;
import org.jte.form.ComboField;
import org.jte.form.Field;
import org.jte.form.TableLayoutForm;
import org.jte.form.TextField;
import org.jte.struts2.JteStruts2Utils;

@Namespace("/iframeQueryDemo")
public class IframeQueryPageDemoAction {

	private static IframeQueryView iframeQueryView;

	static {
		ComboField comboField = new ComboField();
		comboField.setLabel("统计范围");
		comboField.setName("range");
		comboField.setAllowBlank(Bool.FALSE);
		comboField.setValue("MONTH");
		comboField.setOptions(new String[][] { { "MONTH", "月" }, { "YEAR", "年" } });
		TextField textField = new TextField();
		textField.setLabel("url");
		textField.setName("url");
		LinkedHashSet<Field> fields = new LinkedHashSet<Field>();
		fields.add(textField);
		fields.add(comboField);
		TableLayoutForm form = new TableLayoutForm();
		form.setUrl("http://localhost:8080/jte/table/submit.action");
		form.setName("queryForm");
		form.setFields(fields);
		form.addButton(new Button("查询",IframeQueryView.SUBMIT_BUTTON_HANDLER));
		form.init();
		iframeQueryView = new IframeQueryView(form, "submitHandler");
	}

	@Action(value = "showPage")
	public String showPage() {
		return JteStruts2Utils.renderIframeQueryView(iframeQueryView);
	}
}
