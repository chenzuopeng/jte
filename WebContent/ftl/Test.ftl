<#include "/org/jte/ftl/Commons.ftl"/>
<#include "/org/jte/ftl/component/FormPanel.ftl"/>
<#include "/org/jte/ftl/component/View.ftl"/>

<@html_page_head/>

        <@FormPanel form=form/>

		var win = new Ext.Viewport( {
			layout : "fit",
			frame : true,
			items : [{
				region : "center",
				layout : "fit",
				autoWidth:true,
				height : 80,
				frame: true,
				items : new Ext.Panel({
		                  layout : 'form',
		                  width : '800',
		                  hideBorders:true,
		                  items : ${form.name}
		                })
			    }]
	   });

<@html_page_bottom/>