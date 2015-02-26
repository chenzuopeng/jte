package demo.utils;

import java.util.Enumeration;

import org.apache.struts2.ServletActionContext;
import org.jte.constant.Constants;
import org.springside.modules.orm.Page;

/**
 *
 *
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋 Jan 19, 2011
 * @version 1.00.00
 * @history:
 *
 */
public final class ActionUtils {

	private ActionUtils(){
	}

	@SuppressWarnings("rawtypes")
	public static void showParamOfReq() {
		System.out.println("Request param : -------------------------");
		for (Enumeration e = ServletActionContext.getRequest().getParameterNames(); e.hasMoreElements();) {
			String name = e.nextElement().toString();
			System.out.println(name + "=" + ServletActionContext.getRequest().getParameter(name));
		}
		System.out.println("---------------------------------");
	}

    public static void initializePageByJteReq(final Page<?> page){
   	 page.setPageSize(Integer.valueOf(ServletActionContext.getRequest().getParameter(Constants.REQUEST_PARAM_PAGE_SIZE)));
   	 page.setPageNoByFirst(Integer.valueOf(ServletActionContext.getRequest().getParameter(Constants.REQUEST_PARAM_START)));
    }

}
