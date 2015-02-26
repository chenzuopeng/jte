package org.jte.struts2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jte.IframeQueryView;
import org.jte.ImageQueryView;
import org.jte.QueryView;
import org.jte.constant.Constants;
import org.jte.grid.Grid;

/**
 *
 * 此类提供一些快捷方法,简化在Struts框架中使用Jte
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋
 * @version 1.00.00, 2010-9-14 上午11:57:56
 * @history:
 *
 */
public final class JteStruts2Utils {

	private final static String REQ_HEAD_CONTENT_TYPE_JSION = "application/json;charset=utf-8";

	private final static JSONResponse SUCCESS = new JSONResponse(true, "执行成功");

	/**
	 * 用于展示QueryView的页面
	 */
	public final static String RESULT_QUERY_PAGE = "QueryPage";

	/**
	 * 用于展示Grid的页面
	 */
	public final static String RESULT_TABLE_PAGE = "TablePage";

	/**
	 * 用于展示IframeQueryView的页面
	 */
	public final static String RESULT_IFRAME_QUERY_PAGE = "IframeQueryPage";

	/**
	 * 用于展示ImageQueryView的页面
	 */
	public final static String RESULT_IMAGE_QUERY_PAGE = "ImageQueryPage";

	public final static String REQ_ATTR_QUERY_VIEW = "queryView";

	public final static String REQ_ATTR_GRID_PANEL = "gridPanel";

	public final static String REQ_ATTR_IFRAME_QUERY_VIEW = "iframeQueryView";

	public final static String REQ_ATTR_IMAGE_QUERY_VIEW = "imageQueryView";

	private JteStruts2Utils() {
	}

	/**
	 * 展示一个QueryView
	 * @param queryView 要展示的QueryView
	 * @return 一般将此方法的返回值,作为Action方法的返回值
	 */
	public static String renderQueryView(QueryView queryView) {
		ServletActionContext.getRequest().setAttribute(REQ_ATTR_QUERY_VIEW, queryView);
		return RESULT_QUERY_PAGE;
	}

	/**
	 * 展示一个Grid
	 * @param grid 要展示的Grid
	 * @return 一般将此方法的返回值,作为Action方法的返回值
	 */
	public static String renderTableView(Grid grid) {
		ServletActionContext.getRequest().setAttribute(REQ_ATTR_GRID_PANEL, grid);
		return RESULT_TABLE_PAGE;
	}

	/**
	 * 展示一个IframeQueryView
	 * @param iframeQueryView 要展示的IframeQueryView
	 * @return 一般将此方法的返回值,作为Action方法的返回值
	 */
	public static String renderIframeQueryView(IframeQueryView iframeQueryView){
		ServletActionContext.getRequest().setAttribute(REQ_ATTR_IFRAME_QUERY_VIEW, iframeQueryView);
		return RESULT_IFRAME_QUERY_PAGE;
	}

	/**
	 * 展示一个ImageQueryView
	 * @param iframeQueryView 要展示的ImageQueryView
	 * @return 一般将此方法的返回值,作为Action方法的返回值
	 */
	public static String renderImageQueryView(ImageQueryView imageQueryView){
		ServletActionContext.getRequest().setAttribute(REQ_ATTR_IMAGE_QUERY_VIEW, imageQueryView);
		return RESULT_IMAGE_QUERY_PAGE;
	}

	/**
	 * 将一个对象的JSON形式返回客户端
	 * @param object 对象
	 * @return 此方法一般返回一个null
	 * @throws RuntimeException 出现错误时抛出此异常
	 */
	public static String renderJSON(Object object) {
		try {
			ServletActionContext.getResponse().setContentType(REQ_HEAD_CONTENT_TYPE_JSION);
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			out.print(new ObjectMapper().writeValueAsString(object));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	/**
	 * 将一个List的JSON形式返回客户端
	 * @param data 列表数据
	 * @param total 总计记录数
	 * @return 此方法一般返回一个null
	 * @throws RuntimeException 出现错误时抛出此异常
	 */
	@SuppressWarnings("serial")
	public static String renderJSON(final List<?> data, final long total) {
		try {
			ServletActionContext.getResponse().setContentType(REQ_HEAD_CONTENT_TYPE_JSION);
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			out.print(new ObjectMapper().writeValueAsString(new HashMap<String, Object>() {
				{
					put("data", data);
					put("total", total);
				}
			}));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	/**
	 * 回调接口.
	 */
	public interface Callback {
		/**
		 * 回调方法,其中执行具体的业务逻辑
		 * @throws Exception 执行回调方法发送异常时，抛出此异常.
		 */
		public void doAction() throws Exception;
	}

	/**
	 * 用于响应客户端Ajax请求的方法
	 * @param callback 回调接口实现类,提供具体的操作
	 * @return
	 */
	public static String doAjaxRequest(Callback callback) {
		JSONResponse response = null;
		try {
			callback.doAction();
			response = SUCCESS;
		} catch (Exception e) {
			response = new JSONResponse(false, "执行失败  - " + e.getMessage());
		}
		return renderJSON(response);
	}

	/**
	 * 回调接口,实现此接口的类需要提供根据主键获取记录的具体实现.
	 */
	public interface ExtractRecordCallback{
		public Object extract(String id);
	}

	/**
	 * 主键获取记录
	 * @param callback 回调接口实现
	 * @return
	 */
	public static String extractRecord(ExtractRecordCallback callback){
		List<Object> result=new ArrayList<Object>();
		String id = ServletActionContext.getRequest().getParameter(Constants.REQUEST_PARAM_ID);
		result.add(callback.extract(id));
		return JteStruts2Utils.renderJSON(result);
	}

	/**
	 * 回调接口,实现此接口的类需要提供根据提供的主键列表删除对应的记录的具体实现.
	 */
	public interface DeleteCallback {
		/**
		 * 回调方法.
		 * @param ids 主键列表
		 * @throws Exception 方法执行失败时,抛出此异常.
		 */
		public void doAction(String[] ids) throws Exception;
	}

	/**
	 * 根据提供的主键列表删除对应的记录.
	 * @param deleteCallback 回调接口
	 * @return
	 */
	public static String delete(final DeleteCallback deleteCallback) {
		String id = ServletActionContext.getRequest().getParameter(Constants.REQUEST_PARAM_ID);
		final String[] ids = id.split(",");
		return doAjaxRequest(new Callback() {
			public void doAction() throws Exception {
				deleteCallback.doAction(ids);
			}
		});
	}

	/**
	 * 将相对路径转换成绝对路径
	 * @param relativePath 相对路径
	 * @return 绝对路径
	 */
	public static String getFullPath(String relativePath){
		String fullPath=FilenameUtils.concat(ServletActionContext.getRequest().getContextPath(),relativePath);
		fullPath=FilenameUtils.normalize(fullPath,true);//必须进行格式化处理以便支持不同的操作系统。主要为了处理windows和linux/unix间路径分割符不同。
		return fullPath;
	}
	
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println(new ObjectMapper().writeValueAsString(new JSONResponse(true,"aaa")));
	}
	
}
