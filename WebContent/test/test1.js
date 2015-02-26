Ext.onReady(function() {

		var _jsonFormReader = new Ext.data.JsonReader( {
			root : 'data',
			totalProperty : 'total',
			fields : [ 'userId', 'status','time' ]
		});

		var authRSM = new Ext.grid.CheckboxSelectionModel();

		var authDS = new Ext.data.Store(
				{
					autoLoad : {
						params : {
							start : 15,
							limit : this.limit
						}
					},
					proxy : new Ext.data.HttpProxy(
							{
								method : 'post',
								url : contextPath + '/test!test2.action'
							}),
					reader : _jsonFormReader
				});

		openLink = function(actionUrl, text) {
			var item = {};
			item.id = actionUrl;
			item.text = text;
			parent.temponItemClick(item);
		};

		var authCM = new Ext.grid.ColumnModel( [ authRSM, {
			header : "用户号码",
			dataIndex : 'userId',
			sortable : true
		}, {
			header : "历史状态",
			dataIndex : 'status',
			sortable : true
		}, {
			header : "时间",
			dataIndex : 'time',
			sortable : true
		}]);
		
		showHistoryStatus= function(userId) {
			alert(userId);
		}
		
		var authGridPanel = new Ext.grid.GridPanel( {
			frame : true,
			stripeRows : true,
			autoScroll : true,
			viewConfig : {
				autoFill : true
			},
			sm : authRSM,
			store : authDS,
			cm : authCM,
			bbar : new Ext.PagingToolbar( {
				store : authDS,
				pageSize : 15,
				displayInfo : true,
				displayMsg : '第{0}条到{1}条,一共{2}条记录',
				emptyMsg : '没有记录'
			})
		});

		var queryPanelFrom = new Ext.form.FormPanel( {
			items : [{
				xtype : "panel",
				layout : "column",
				baseCls : "x-plain",
				items : [ {
					columnWidth : '.3',
					xtype : 'panel',
					layout : 'form',
					baseCls : 'x-plain',
					items : [ {
						xtype : 'textfield',
						name : 'userId',
						id : 'userId',
						anchor : '95%',
						fieldLabel : '用户号码'
					} ]
				},{
					columnWidth : '.1',
					xtype : 'panel',
					layout : 'form',
					baseCls : 'x-plain',
					items : [ {
						xtype : 'button',
						text : '查询',
						handler : query
					} ]
				} ]

			} ]
		});

		function query() {
			if (queryPanelFrom.form.isValid()) {
				var userId = queryPanelFrom.findById('userId')
						.getValue();
				authDS.load( {
					params : {
						start : 0,
						limit : 15,
						userId : userId
					},
					add : false
				});
			} else
				Ext.MessageBox.alert("提示", "查询表单验证错误，请重新填写");
		}

		var win = new Ext.Viewport( {
			layout : "border",
			frame : true,
			items : [ {
				region : "north",
				layout : "fit",
				height : 40,
				items : queryPanelFrom
			}, {
				region : "center",
				layout : "fit",
				items : authGridPanel
			} ]
		});
	});