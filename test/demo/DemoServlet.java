package demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 *
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋 Feb 10, 2011
 * @version 1.00.00
 * @history:
 *
 */
public class DemoServlet extends HttpServlet {

	static int i=0;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    PrintWriter out = resp.getWriter();
	    out.println("document.write('"+(i++)+"');");
	}

}
