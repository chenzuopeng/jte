package demo.action;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

/**
 *
 *
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved 
 * @Company: 北京福富软件有限公司 
 * @author 陈作朋 Jun 30, 2011
 * @version 1.00.00
 * @history:
 * 
 */
@Namespace("/action")
public class TestAction {

	@Action(value = "/param")
	public String test(){
		HttpServletRequest request=ServletActionContext.getRequest();
		printParams();
		return null;
	}
	
	public void printParams(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Enumeration enumeration=request.getParameterNames();
		System.out.print("params:");
		while (enumeration.hasMoreElements()) {
			String paramName=enumeration.nextElement().toString();
			System.out.print(paramName+":"+Arrays.toString(request.getParameterValues(paramName)));
		}
	}
}
