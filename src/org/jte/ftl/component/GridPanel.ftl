<#include "../Commons.ftl"/>
<#include "FormPanel.ftl"/>
<#assign RESPONSE_ATTRIBUTE_DATA='${stack.findValue("@org.jte.constant.Constants@RESPONSE_ATTRIBUTE_DATA")}'>
<#assign RESPONSE_ATTRIBUTE_TOTAL='${stack.findValue("@org.jte.constant.Constants@RESPONSE_ATTRIBUTE_TOTAL")}'>
<#assign REQUEST_PARAM_START='${stack.findValue("@org.jte.constant.Constants@REQUEST_PARAM_START")}'>
<#assign REQUEST_PARAM_PAGE_SIZE='${stack.findValue("@org.jte.constant.Constants@REQUEST_PARAM_PAGE_SIZE")}'>
<#assign REQUEST_PARAM_ID='${stack.findValue("@org.jte.constant.Constants@REQUEST_PARAM_ID")}'>
<#assign FUNC_NAME_DO_AFTER_SUCCESSFUL_SUBMIT_FOR_EDIT_FORM='${stack.findValue("@org.jte.grid.Grid@DO_AFTER_SUCCESSFUL_SUBMIT_WHEN_EDIT_FORM")}'>
<#--
     获取列名列表
-->
<#macro ColumnNames columns>
   <#list columns as column>'${column.name}'<#if column_has_next>,</#if></#list>
</#macro>

<#--
     转换Renderer
-->
<#macro Renderer renderer>
   renderer : function(value){
	 <#if renderer.getType()=="MAP">
	     var map={<#list renderer.map as item>"${item?first}":"${item?last}"<#if item_has_next>,</#if></#list>};
	     return map[value];
	 <#else>
	     return value;
     </#if>
   },
</#macro>

<#--
     转换Column
-->
<#macro Columns columns>
	<#list columns as column>
	{
	  <#if column.rendererFunc?has_content>
	     renderer : ${column.rendererFunc},
	  <#elseif column.renderer?has_content>
         <@Renderer renderer=column.renderer/>
	  </#if>
	  sortable : ${column.sortable},
	  header : '${column.label}',
	  dataIndex : '${column.name}'
	}<#if column_has_next>,</#if>
	</#list>
</#macro>

<#--
     编辑按钮
-->
<#macro Tbar buttons>
    <#list buttons as button>
	    <#switch button>
	     <#case "ADD">
		     {
	            id: 'addButton',
				text : '添加',
				xtype : 'button',
				handler : doAdd
			 }
	     <#break>
	     <#case "MODIFY">
		     {
	            id: 'editButton',
				text : '修改',
				xtype : 'button',
				handler : doEdit
			 }
	     <#break>
	     <#case "DELETE">
		     {
			        id: 'deleteButton',
					text : '删除',
					xtype : 'button',
					handler : doDelete
			 }
	    </#switch>
	    <#if button_has_next>,</#if>
	</#list>
</#macro>

<#--
      表格
-->
<#macro GridPanel grid>

    <#local gridName = grid.name>

	<#local pageSize = gridName+"PageSize">

	<#local dataStoreName = gridName+"DataStore">

	<#local gridColumnModelName = gridName+"ColumnModel">

	<#local checkboxSelectionModel = gridName+"CheckboxSelectionModel">

	var ${pageSize} = ${grid.pageSize};

	var ${dataStoreName} = new Ext.data.JsonStore(
			{
				autoLoad : {params : {
					          ${REQUEST_PARAM_START} : 0,
				              ${REQUEST_PARAM_PAGE_SIZE} : ${pageSize}
					        }
				},
				proxy : new Ext.data.HttpProxy(
						{
							method : 'post',
							url : '${grid.storeUrl}'
						}),
				root : '${RESPONSE_ATTRIBUTE_DATA}',
		        totalProperty : '${RESPONSE_ATTRIBUTE_TOTAL}',
		        fields : [<@ColumnNames  columns=grid.columns/>]
			}
	);

	var ${gridColumnModelName} = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),<@Columns columns=grid.columns/>]);

	<#local editable = (grid.editable?size > 0)>

	<#local editables = grid.editable>

	var ${gridName} = new Ext.grid.GridPanel( {
	    id : '${generateId(gridName)}',
		frame : true,
		stripeRows : true,
		autoScroll : true,
		loadMask: true,
		viewConfig : {
			autoFill : true
		},
		store : ${dataStoreName},
		cm : ${gridColumnModelName},
		<#if editable>
		   tbar : [
		      <@Tbar buttons=editables/>
		   ],
		</#if>
		bbar : new Ext.PagingToolbar( {
			store : ${dataStoreName},
			paramNames:{start:'${REQUEST_PARAM_START}',limit:'${REQUEST_PARAM_PAGE_SIZE}'},
			pageSize : ${pageSize},
			displayInfo : true,
			displayMsg : '第{0}条到{1}条,一共{2}条记录',
			emptyMsg : '没有记录'
		})
	});

	<#if editable>

		var ${checkboxSelectionModel}=new Ext.grid.CheckboxSelectionModel();

		${gridName}.colModel = new Ext.grid.ColumnModel([${checkboxSelectionModel},new Ext.grid.RowNumberer(),<@Columns columns=grid.columns/>]);

	    ${gridName}.selModel =${checkboxSelectionModel};

	    <#if grid.form?has_content>

	        <#local editFormName = grid.form.name>

		    <#local eidtWindow = grid.name+"Window">

		    <#local jsonReader = grid.name+"JsonReader">

		    var ${jsonReader} = new Ext.data.JsonReader({},[
		       <#list grid.form.fields as field>
			      {name: '${field.name}', type: 'string'}<#if field_has_next>,</#if>
			   </#list>
		    ]);

		    <@FormPanel form=grid.form/>

		    ${editFormName}.getForm().reader=${jsonReader};

			    var ${eidtWindow} = new Ext.Window({
			        id : '${generateId(eidtWindow)}',
			        width : '${grid.form.width+14}',
			        autoHeight : true,
			        closable : true,
			        closeAction : 'hide',
					modal : true,
					listeners :{
				 	   hide : function(){
				 	      ${editFormName}.getForm().reset();
				 	      ${gridName}.getSelectionModel().clearSelections();
				 	   }
				    },
			        items : ${editFormName}
			    });
	    </#if>

	    <#if editables?seq_contains("ADD")>
		    function doAdd(){
		        ${eidtWindow}.show();
		    }
	    </#if>

	    <#if editables?seq_contains("MODIFY")>
		    function doEdit(){
				var record = ${gridName}.getSelectionModel().getSelected();
				if( record == undefined){
	                Ext.Msg.alert('提示','请选择要修改的记录');
				}else{
				   	${eidtWindow}.show();
				    var id = record.get('${grid.indexColumn}');
				    ${editFormName}.getForm().load({
	                    url : '${grid.extractUrl}',
	                    params : {
	                       ${REQUEST_PARAM_ID} : id
	                    }
	                  });
				}
			}
		</#if>

		<#if editables?seq_contains("MODIFY") || editables?seq_contains("ADD")>
            function ${FUNC_NAME_DO_AFTER_SUCCESSFUL_SUBMIT_FOR_EDIT_FORM}(){
                ${eidtWindow}.hide();
                ${gridName}.getStore().reload();
            }
		</#if>

	    <#if editables?seq_contains("DELETE")>
		    function doDelete(){
		       var records = ${gridName}.getSelectionModel().getSelections();
			   if(records.length == 0){
			        Ext.Msg.alert('提示','请选择要删除的记录');
			   }else{
				    Ext.MessageBox.confirm("提示","确定要删除吗?",function(btn){
			           if( btn == "yes" ){
					       var ids=new Array();
					       var i;
					       for (i=0;i<=records.length-1;i++){
				              ids[i]=records[i].get('${grid.indexColumn}');
				           }

					       Ext.Ajax.request({
								clientValidation : true,
								waitMsg : "正在删除...",
								waitTitle : "提示",
								url : '${grid.deleteUrl}',
								params : {
						           ${REQUEST_PARAM_ID} : ids.join()
								},
								method : 'GET',
								success : function() {
								    var i;
								    for(i=0;i<=records.length-1;i++){
				                      ${dataStoreName}.remove(records[i]);
				                    }
									Ext.Msg.alert('提示','删除成功');
								},
								failure : function() {
									Ext.Msg.alert('提示','删除失败');
								}
						    })
			           }
			       });
			   }
		    }
	    </#if>
    </#if>

	<#if grid.callback != "">
        ${grid.callback}(${gridName});
    </#if>

</#macro>



