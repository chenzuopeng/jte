/**
 * 初始化一个用于展示页面的窗口
 * */
function initializeIframeWindow(title, width, height, url) {
	var _window = new Ext.Window(
			{
				title : title,
				closable : true,
				autoHeight : true,
				modal : true,
				html : String
						.format(
								'<iframe src="{0}" width="100%" height="100%" frameborder="0" style="margin:0;padding:0" />',
								url)
			});
	return _window;
}

/**
 * 初始化一个用于展示表单的窗口
 * */
function initializeFormWindow(title, width, form) {
	var _window = new Ext.Window({
		title : title,
		width : width,
		autoHeight : true,
		closable : true,
		modal : true,
		items : form
	});
	return _window;
}

/**
 * 使用ajax方式提交一个Form
 * param：
 *    form：表单对象
 *    fn：提交成功后的回调函数
 * */
function submitFormByAjax(form, fn) {
	form.getForm().submit({
		clientValidation : true,
		success : function(form, action) {
			Ext.Msg.alert("Success", action.result.msg, fn);
		},
		failure : function(form, action) {
			switch (action.failureType) {
			case Ext.form.Action.CLIENT_INVALID:
				Ext.Msg.alert("提示", "查询表单验证错误，请重新填写");
				break;
			case Ext.form.Action.CONNECT_FAILURE:
				Ext.Msg.alert("提示", "网络通信异常");
				break;
			case Ext.form.Action.SERVER_INVALID:
				Ext.Msg.alert("提示", action.result.msg);
				break;
			default:
				Ext.Msg.alert("提示", action.result.msg);
			}
		}
	});
}