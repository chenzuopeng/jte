Ext.onReady(function() {
	var month_cond,year_cond;
	var yearData = ad();
	function ad(){
		var d = new Array()
		for(var i = 1999 ; i < 2030;i++){
			d.push(new Array(i+"",i+""))
		}
		return d
	}
	var yearStore = new Ext.data.SimpleStore({
						fields :["returnValue","displayText"],
						data:yearData
					}); 
	
	
	var monthStore = new Ext.data.SimpleStore({
						fields :["returnValue","displayText"],
						data:[["0","全年"],["1","一月份"],["2","二月份"],["3","三月份"],["4","四月份"],["5","五月份"],["6","六月份"],["7","七月份"]
							,["8","八月份"],["9","九月份"],["10","十月份"],["11","十一月份"],["12","十二月份"]]
					});
	
	var queryPane = new Ext.form.FormPanel({
			labelAlign : "right",
			items : [{
						xtype	 : "panel",
						layout	 : "column",
						baseCls	 : "x-plain",
						items	 : [{	
								columnWidth : ".25",
								baseCls 	: "x-plain",
								items		: [{
											xtype		: "combo",
											fieldLabel	: "年份",
											name 		: "year_cond",
											id			: "year_cond_id",
											anchor		: "95%",													
											store		: yearStore,
											displayField: "displayText",
											valueField	: "returnValue",
											mode		: "local",
											allowBlank : false,
											width		:	180,
											emptyText	: "请选择年份"
										}]
								},{
									columnWidth : ".25",
									baseCls 	: "x-plain",
									items		: [{
												xtype		: "combo",
												fieldLabel	: "月份",
												name 		: "month_cond",
												id			: "month_cond_id",
												anchor		: "95%",													
												store		: monthStore,
												displayField: "displayText",
												valueField	: "returnValue",
												mode		: "local",
												allowBlank : false,
												width		:	180,
												emptyText	: "请选择月份",
												value		: "0"
											}]
									},{
									 	columnWidth :".17",
									 	baseCls 	: "x-plain",
										items		: [{
													xtype		: "button",
													text 		: "统计",
													handler     : function(){
														doStat();
													}
												}]
									}]
									
					}]
	
	})
	
	
	//搜索框 end
	var gridLimit = 2;
	var doStat = function(){	
		year_cond = queryPane.findById("year_cond_id").getValue();		
		month_cond = queryPane.findById("month_cond_id").getValue();
		if(year_cond==""){		
			Ext.MessageBox.alert("提示", "年份不能为空，请选择");
			return false;
		}
		
		if(queryPane.form.isValid()){			
			Ext.get('loading').setOpacity(1,{duration:0.1,callback:function(){this.show();}});
			store.load({
				params : {
							start : 1,
							limit : gridLimit,
							year_cond : year_cond,						
							month_cond : month_cond							 
						},
						add : false				
			})
		}else{
			Ext.MessageBox.alert("提示", "查询表单验证错误，请重新填写");
		}
		
		store.on("load",function(d,r){
			//alert(r[0].get("V0"));
			Ext.get('loading').setOpacity(0.0,{duration:0.1,callback:function(){this.hide();}});
			var o = document.getElementById("dataGridInner")
			o.innerHTML=creatHtmlGrid(r);
		})
	}
	var useTy = ['所有用户','手机用户','固话用户','PHS用户','宽带用户','领航用户'];
	var creatHtmlGrid = function(result){
		var os = '<table width="100%"  cellspacing="0" cellpadding="0" class="gtable">'
			+'  <tr>'
			  +'  <td rowspan="2" class="gheaderout">系统/\类型</td>'
			  +'  <td rowspan="2" class="gheaderout">手机认证用户数</td>'
			  +'  <td rowspan="2" class="gheaderout">固话认证用户数</td>'
			  +'  <td rowspan="2" class="gheaderout">PHS认证用户数</td>'
			  +'  <td rowspan="2" class="gheaderout">宽带认证用户数</td>'
    		  +'  <td rowspan="2" class="gheaderout">领航认证用户数</td>'
			  +'  <td colspan="4" class="gheaderout">合计</td>'
			 +' </tr>'
			 +' <tr>'
			 +'   <td class="gheaderout">认证用户数</td>'
			 + '  <td class="gheaderout">认证成功数</td>'
			 +'   <td class="gheaderout">认证失败数</td>'
			 + '  <td class="gheaderout">认证总次数</td>'			
			 +' </tr>'
			 for(var i =0 ; i < result.length;i++){
			 	var cl = "trEven";
			 	if(i%2!=0){
			 		cl = "trOdd";
			 	}else{
			 		cl = "trEven";
			 	}
				os +=' <tr class="'+cl+'">'
				 + '  <td >'+result[i].get("name")+'</td>'
				 + '  <td >'+result[i].get("V0")+'</td>'
				 + '  <td >'+result[i].get("V1")+'</td>'
				 + '  <td >'+result[i].get("V2")+'</td>'
				 + '  <td >'+result[i].get("V3")+'</td>'
				 + '  <td >'+result[i].get("V4")+'</td>'
				 + '  <td >'+result[i].get("V5")+'</td>'
				 + '  <td >'+result[i].get("V6")+'</td>'
				 + '  <td >'+result[i].get("V7")+'</td>'
				  + '  <td >'+result[i].get("V8")+'</td>'
				 +' </tr>'	
			 }
			os+='</table>'
		return os;
	}
	/**列表框 begin
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel([sm,{
											header : "存储用户数",
											dataIndex : 'V0',
											sortable : true,
											renderer:function(v){
												return v;
											}
										},{
											header:"通行证激活用户数",
											dataIndex : 'V1',
											sortable : true,
											renderer:function(v){
												return v;
											}										
										},{
											header:"活用户数跃",
											dataIndex : 'V2',
											sortable : true,
											renderer:function(v){
												return v;
											}										
										},{
											header:"拆机用户数",
											dataIndex : 'V3',
											sortable : true,
											renderer:function(v){
												return v;
											}										
										},{
											header:"拆机用户数",
											dataIndex : 'V4',
											sortable : true,
											renderer:function(v){
												return v;
											}										
										},{
											header:"拆机用户数",
											dataIndex : 'V5',
											sortable : true,
											renderer:function(v){
												return v;
											}										
										},{
											header:"拆机用户数",
											dataIndex : 'V6',
											sortable : true,
											renderer:function(v){
												return v;
											}										
										},{
											header:"拆机用户数",
											dataIndex : 'V7',
											sortable : true,
											renderer:function(v){
												return v;
											}										
										}]);
	**/									
	var reader = new Ext.data.JsonReader({
						root : 'data',
						totalProperty : 'total',
						fields : ['name','V0','V1','V2','V3','V4','V5','V6','V7','V8']
					})									
	var store = new Ext.data.Store({
						autoLoad : false,
						proxy : new Ext.data.HttpProxy({
									method:"GET",
									url:contextPath + "/statManage!authStat.action"
								}),
						
						params : {
							start : 1,
							limit : gridLimit
						},
						reader : reader
					})
					
					
	var PagingParamToolbar = Ext.extend(Ext.PagingToolbar,{
						doLoad : function(start){
							var o = {},pn = this.paramNames;							
							o[pn.start] = start;
							o[pn.limit] = this.pageSize;
							o['year_cond'] = year_cond;
							o['month_cond'] = month_cond;
							this.store.load({params:o})
						}
					})
					
	var gridPane = new Ext.Panel({
				frame : true,
				autoLoad : false,				
				html:'<div id="dataGridInner" style="width:100%;height:auto;"></div>',
				bodyStyle:"background:#fff"
	})
	/**
	var gridPane = new Ext.grid.GridPanel({
						frame 		: true,
						stripeRows  : true,
						
						autoScroll : true,
						loadMask : true,
						viewConfig : {
							autoFill : true,
							forceFit : true 
						},
						sm 			:sm,
						store		:store,
						cm			:cm
				   })
	**/
	var win = new Ext.Viewport({
				layout : "border",
				frame : true,
				items : [{
							region : "north",
							layout : "fit",
							height : 70,
							items : queryPane
						}, {
							region : "center",
							layout : "fit",
							items : gridPane
						}]
			});
});