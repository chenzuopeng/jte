<#include "../Commons.ftl"/>
<#include "FormPanel.ftl"/>
<#include "GridPanel.ftl"/>
<#--
        查询视图:上面查询表单,下面表格
 -->
<#macro QueryView queryView>

    <#local grid = queryView.grid>

    <#local form = queryView.form>

    <@GridPanel  grid=grid/>

    <@FormPanel form=form/>

    function querySubmit(){
        if (${form.name}.getForm().isValid()) {
			${grid.name}.getStore().baseParams = {
			    <#list form.fields as field>
                    ${field.name} : ${form.name}.findById('${generateId(field.name)}').getValue()<#if field_has_next>,</#if>
                </#list>
			};
			${grid.name}.getStore().load({
				params :{
		          ${REQUEST_PARAM_START} : 0,
				  ${REQUEST_PARAM_PAGE_SIZE} : ${grid.pageSize}
	            },
				add : false
			 });
       }else{
         Ext.MessageBox.alert("提示", "查询表单验证错误，请重新填写");
       };
    }

	var win = new Ext.Viewport( {
		layout : "border",
		frame : true,
		items : [ {
			region : "north",
			layout : "fit",
			height : ${form.height},
			frame: true,
			items : new Ext.Panel({
	                  layout : 'form',
	                  hideBorders:true,
	                  items : ${form.name}
	                })
		},{
			region : "center",
			layout : "fit",
			items : ${grid.name}
		} ]
	})

	<#if queryView.callback?has_content>
        ${queryView.callback}(${form.name},${grid.name});
    </#if>
</#macro>

<#macro TableView grid>

    <@GridPanel  grid=grid/>

	var win = new Ext.Viewport( {
		layout : "border",
		frame : true,
		items : [{
			region : "center",
			layout : "fit",
			items : ${grid.name}
		} ]
	});

</#macro>

<#macro extractQueryStringFromForm form>
    <#list form.fields as field>
          ${field.name}=${form.name}.findById('${generateId(field.name)}').getValue()<#if field_has_next>&</#if>
    </#list>
</#macro>

<!--
   将表单域组织成GET请求查询字符串的形式
-->
<#function extractQueryString form>
   <#local queryString = "">
   <#list form.fields as field>
          <#local queryString = queryString +"\""+field.name+"=\"+"+form.name+".findById('"+generateId(field.name)+"').getValue()">
          <#if field_has_next>
             <#local queryString = queryString + "+\"&\"+">
          </#if>
    </#list>
   <#return queryString>
</#function>

<#macro IframeQueryView iframeQueryView>

    <#local form = iframeQueryView.form>

    <@FormPanel form=form/>

	var win = new Ext.Viewport( {
			layout : "border",
			frame : true,
			items : [ {
				region : "north",
				layout : "fit",
				height:${form.height},
				items : new Ext.Panel({
		                  layout : 'form',
		                  hideBorders:true,
		                  frame: true,
		                  items : ${form.name}
		                })
			},{
				region : "center",
				layout : "fit",
				items : {
				     layout : 'column',
                     hideBorders:true,
                     xtype : 'panel',
                     html : '<iframe id="show_area" scrolling="auto" height="100%" width="100%" ></iframe>'
				}
			} ]
		});

		function htmlQuerySubmit(){
		   if (${form.name}.getForm().isValid()) {
                var iframe=document.getElementById("show_area");
                iframe.src="${form.url}?"+${extractQueryString(form)};
                <#if iframeQueryView.callback?has_content>
                    ${iframeQueryView.callback}(${form.name},iframe);
                </#if>
		    }else{
		        Ext.MessageBox.alert("提示", "查询表单验证错误，请重新填写");
		    };
		}

</#macro>

<#macro ImageQueryView imageQueryView>

       <#local form = imageQueryView.form>

        <@FormPanel form=form/>

		var imagePanel = new Ext.BoxComponent({
		    autoEl: {
		        tag: 'img',
		        width: ${imageQueryView.imageWidth},
		        height: ${imageQueryView.imageHeight},
		        src: String.format('{0}/images/blank.png',contextPath)
		    }
        });

		var win = new Ext.Viewport( {
			layout : "border",
			frame : true,
			items : [ {
				region : "north",
				layout : "fit",
				height : ${form.height},
				items : new Ext.Panel({
		                  layout : 'form',
		                  hideBorders:true,
		                  frame: true,
		                  items : ${form.name}
		                })
			},{
				region : "center",
				layout : "fit",
				items : {
				     layout : 'column',
                     hideBorders:true,
                     xtype : 'panel',
                     items : imagePanel
				}
			} ]
		});

	   function htmlQuerySubmit(){
		   if (${form.name}.getForm().isValid()) {
		        imagePanel.getEl().dom.src="${form.url}?"+${extractQueryString(form)};
                <#if imageQueryView.callback?has_content>
                    ${imageQueryView.callback}(${form.name},imagePanel);
                </#if>
		    }else{
		        Ext.MessageBox.alert("提示", "查询表单验证错误，请重新填写");
		    };

		}

</#macro>

