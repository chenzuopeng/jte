<%@ page import="java.util.*"%>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" >
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="authObject" value="${authObject}" />
<%
String contextPath=request.getContextPath();
  
%>
<script type="text/javascript">
   var contextPath='<%=contextPath%>';
   var authObject = <%= request.getSession().getAttribute("authObject")%>;
</script>

<!--<script type='text/javascript' src='${ctx}/common/prototype.js'></script>-->
<link rel="stylesheet" type="text/css"
	href="${ctx}/ext/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css" />
<!-- dwr -->
<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>
<script type='text/javascript' src='${ctx}/dwr/util.js'></script>
<script type="text/javascript" src="${ctx}/ext/adapter/prototype/prototype.js"></script>
<!-- global var -->
<script type="text/javascript">
   var _Ext_BLANK_IMAGE_URL = "${ctx}/images/s.gif";
   var _ctx = "${ctx}";
   var _ctx2 = "/nms_swlh";
   var _page_size = 20;
</script>
<style type="text/css">
.x-grid3-row td {
	-moz-user-select: text;
}
</style>

