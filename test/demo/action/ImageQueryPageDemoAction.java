package demo.action;

import java.util.LinkedHashSet;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.jte.Button;
import org.jte.IframeQueryView;
import org.jte.ImageQueryView;
import org.jte.constant.Bool;
import org.jte.form.ComboField;
import org.jte.form.Field;
import org.jte.form.TableLayoutForm;
import org.jte.form.TextField;
import org.jte.struts2.JteStruts2Utils;

@Namespace("/imageQueryDemo")
public class ImageQueryPageDemoAction {

	private static ImageQueryView imageQueryView;

	static{
		ComboField comboField = new ComboField();
		comboField.setLabel("统计范围");
		comboField.setName("range");
		comboField.setAllowBlank(Bool.FALSE);
		comboField.setValue("MONTH");
		comboField.setOptions(new String[][] { { "MONTH", "月" }, { "YEAR", "年" } });
		TextField textField=new TextField();
		textField.setLabel("url");
		textField.setName("url");
		LinkedHashSet<Field> fields=new LinkedHashSet<Field>();
		fields.add(textField);
		fields.add(comboField);
		TableLayoutForm form=new TableLayoutForm();
		form.setUrl("http://localhost:8080/jte/table/submit.action");
		form.setName("queryForm");
		form.setFields(fields);
		form.addButton(new Button("查询",ImageQueryView.SUBMIT_BUTTON_HANDLER));
		form.setHasButtonBar(false);
		form.init();
		imageQueryView=new ImageQueryView();
		imageQueryView.setForm(form);
		imageQueryView.setImageWidth(600);
		imageQueryView.setImageHeight(300);
		imageQueryView.setCallback("showImage");
	}

	@Action(value="showImage")
    public String showImage(){
    	return JteStruts2Utils.renderImageQueryView(imageQueryView);
    }
}
