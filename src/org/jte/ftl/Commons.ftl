<#assign  request=stack.findValue("@org.apache.struts2.ServletActionContext@getRequest()")>
<#assign  contextPath=request.getContextPath()>

<!--
   根据参数生成一个Id
-->
<#function generateId input>
   <#return "id_"+input?lower_case>
</#function>

<!--
   html页面的头
-->
<#macro html_page_head>
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	    <#include "/ftl/Head.ftl"/>
	</head>
	<body>
	<script type='text/javascript'>
	    Ext.QuickTips.init();
		Ext.onReady(function() {
</#macro>

<!--
   html页面的尾
-->
<#macro html_page_bottom>
		});
	</script>
	</body>
	</html>
</#macro>