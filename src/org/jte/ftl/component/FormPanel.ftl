<#include "../Commons.ftl"/>
<#setting number_format="computer">
<#assign builtInButtonHandlers = ["reset","htmlSubmit", "ajaxSubmit"]>

<#macro Options options>
	<#list options as option>
		['${option?first}','${option?last}']<#if option_has_next>,</#if>
	</#list>
</#macro>

<#macro Datefield datefield hasFunc=true>
     <#if hasFunc>
        function(){
     </#if>
           new WdatePicker({
                             dateFmt:'${datefield.dateFmt}'
                             <#if datefield.minDate?has_content>
                               ,minDate:'${datefield.minDate}'
                             </#if>
                             <#if datefield.maxDate?has_content>
                               ,maxDate:'${datefield.maxDate}'
                             </#if>
                          });
     <#if hasFunc>
        }
     </#if>
</#macro>

<#macro TextField field>
   <#local xtype = field.getType()?lower_case>
     <#switch xtype>
	   <#case "combo">
	     hiddenName : '${field.name}',
	     displayField : "label",
		 valueField : "value",
		 mode : 'local',
		 store : new Ext.data.SimpleStore({
		             fields : ["value","label"],
					 data : [<@Options options=field.options/>]
				 }),
	     <#if field.value != "">
            value:'${field.value}',
         </#if>
		 emptyText : "请选择${field.label}",
		 triggerAction: 'all',
	     <#break>
	   <#case "datefield">
	     <#local xtype = 'textfield'>
	     <#if !field.focusListener?has_content>
			 listeners :{
			 	focus : <@Datefield datefield=field/>
			 },
	     </#if>
	     <#break>
	   <#default>
     </#switch>

     <#-- 公共属性  -->
     xtype : '${xtype}',
     id : '${generateId(field.name)}',
     name : '${field.name}',
     allowBlank : ${field.allowBlank},
     readOnly : ${field.readOnly},
     anchor : '95%',
     <#if field.regex?has_content>
        regex:${field.regex},
     </#if>
     <#if field.regexText?has_content>
        regexText:'${field.regexText}',
     </#if>
     <#if field.initValue?has_content>
        initValue:'${field.initValue}',
     </#if>
     <#if field.focusListener?has_content>
		 listeners :{
		 	focus : ${field.focusListener}
		 },
     </#if>
     fieldLabel : '${field.label}'
</#macro>

<#macro Button formName button>
	xtype : 'button',
	text : '${button.text}',
	<#if button.handler?has_content>
	   <#if builtInButtonHandlers?seq_contains(button.handler)>
	      handler : ${formName}_${button.handler}
	   <#else>
	      handler : ${button.handler}
	   </#if>
	</#if>
</#macro>

<#macro Panel formName panel>
		<#if panel.width != 0>
		   	 width : '${panel.width}',
		</#if>
		<#if panel.height != 0>
		   	 height : '${panel.height}',
		</#if>
		<#if panel.items.size() != 0>
			items : [
			   <#list panel.items as item>
			      {
			        <@Item formName=formName item=item/>
			      }<#if item_has_next>,</#if>
			   </#list>
			],
		</#if>
		<#if panel.name?has_content>
		    id : '${generateId(panel.name)}',
		</#if>
		frame:${panel.frame},
		layout : '${panel.layout}',
		hideBorders: true,
		xtype : 'panel'
</#macro>

<#macro Item formName item>
   <#switch item.getType()>
	  <#case "panel">
	     <@Panel formName=formName panel=item/>
	     <#break>
	  <#case "button">
	     <@Button formName=formName button=item/>
	     <#break>
	  <#default>
	     <@TextField field=item/>
   </#switch>
</#macro>

<#--
    表单
-->
<#macro FormPanel form>

    <#local formName = "${form.name}">

	var ${formName} = new Ext.form.FormPanel( {

	   <#if form.title !="">
	      title: '${form.title}',
	   </#if>

	   <#if form.width != 0>
		  width : '${form.width}',
	   </#if>

	   <#if form.height != 0>
		  height : '${form.height}',
	   </#if>

	   <#if form.url != "">
		  url : '${form.url}',
	   </#if>
	   id : '${generateId(formName)}',
	   labelAlign : 'RIGHT',
	   labelWidth : 60,
	   hideBorders: true,
	   frame:${form.frame},
	   bodyStyle:'padding:10px',
	   <#if form.buttons.size() != 0>
		   buttons : [
			   <#list form.buttons as button>
			      {
			        <@Item formName=formName item=button/>
			      }<#if button_has_next>,</#if>
			   </#list>
		   ],
	   </#if>
       items : [
           <#list form.items as item>
		      {
		        <@Item formName=formName item=item/>
		      }<#if item_has_next>,</#if>
		   </#list>
       ]
    });


		<#--重置表单-->
	    function ${formName}_reset(){
	        ${formName}.getForm().reset();
	    };

		<#--Html方式提交表单(同步)-->
	    function ${formName}_htmlSubmit(){
	       if (${formName}.getForm().isValid()) {
	         ${formName}.getForm().getEl().dom.submit();
	         <#if form.doAfterSuccessfulSubmit?has_content>
	             ${form.doAfterSuccessfulSubmit}();
	         </#if>
	       }else{
	         Ext.MessageBox.alert("提示", "查询表单验证错误，请重新填写");
	       };
	    }

		<#--ajax方式提交表单(异步)-->
	    function ${formName}_ajaxSubmit(){
	       <#if form.doAfterSuccessfulSubmit?has_content>
	          submitFormByAjax(${formName},${form.doAfterSuccessfulSubmit});
	       <#else>
	          submitFormByAjax(${formName});
	       </#if>
	    }


    <#if form.callback != "">
        ${form.callback}(${formName});
    </#if>
</#macro>