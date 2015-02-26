<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>认证统计</title>
		<style type="text/css">
			 #loading-mask{   
			         position:absolute;   
			         left:0;   
			         top:0;   
			         width:100%;   
			         height:100%;   
			         z-index:20000;   
			        
			     }   
			     #loading{   
			         position:absolute;   
			         left:45%;   
			         top:40%;   
			         padding:2px;
			         z-index:20001;   
			         height:auto;   
			         display:none;
			     }   
			       
			     #loading .loading-indicator{   
			         background:white;   
			         color:#444;   
			        font:bold 20px tahoma,arial,helvetica;   
			         padding:5px;   
			         margin:0;
			         width:110px;			     
			         height:24px;
			         border:4px solid #A0B4DC;  
			     }   
			     #loading-msg {   
			         font: normal 12px arial,tahoma,sans-serif;   
			     }   
			body div td span{font-size:12px;}
			.gtable{border:1px solid #AACCF6}				
			.trEven{background: #fff;text-align:left;padding-left:2px;}		
			.trOdd{background: #FAFAFA;border-top:1px solid #EDEDED;text-align:left;padding-left:2px;}
			.trEven td{border-top:1px solid #EDEDED;font-size:12px;height:21px;padding-left:2px;}
			.trOdd td{border-top:1px solid #EDEDED;font-size:12px;height:21px;padding-left:2px;}
			.gheaderover{ clear:both;background:#D8E7FB; height:44px;cursor:pointer;text-align:center;}
  			.gheaderout{font-size:12px;border:1px solid #AACCF6;border-left:0px;border-top:0px;background:#D8E7FB; height:22px;cursor:pointer;text-align:center;}
		</style>
	</head>
	
	<body>
	<script type="text/javascript" src="/ext/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="/ext/ext-all-debug.js"></script>
    <script type="text/javascript" src="/ext/build/locale/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/common/common.js"></script>
    <script type="text/javascript" src="/js/common/ext-extend.js"></script>
	<script type="text/javascript" src="/test/authStat.js"></script>
	 <div id="loading">   
	     <div class="loading-indicator">   
	       <img src="/ext/resources/images/default/shared/large-loading.gif" width="22" height="22" style="margin-right:8px;float:left;vertical-align:top;"/>   
	       <span id="loading-msg">Loading ...</span>   
	     </div>   
	 </div>   	
	</body>
</html>
