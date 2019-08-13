$(function(){
	$('#homepage').datagrid({
		url:"query",
		pagination:true,
 		pageNumber:1,
 		pageSize:5,
 		pageList:[5,10],
		columns:[[
			{field:'sid',title:"学号",checkbox:true},
			{field:'sname',title:"姓名"},
			{field:'sex',title:"性别" },
			{field:'hobby',title:"手机号"},
			{field:'cname',title:"班级"},
		]]
	});
	
	$("#manager_add").dialog({
		closed:true,
	    title: '增加学生',
	    width: 400,
	    height: 300,
	    dataType:"json",
	    buttons:[
			{
				
				text:"提交",
				handler:function(){
					if ($('manager_add').form('validate')) {
						 alert( $("input[type='radio']:checked").val())
						$.ajax({
							type:"post",
							url: "stuAdd",
							data:{
								sname:$('input[name="name"]').val(),
								sex:$("input[type='radio']:checked").val(),
								hobby:$('input[name="phone"]').val(),
								cname:$("#cc").combotree("getText"),
								cid:$("#cc").combotree("getValue")
							},
							beforeSend : function(){
								$.messager.progress({
									
									text:"正在增加中..."
								})
								
							},
							success: function(result) {
								$.messager.progress('close');
								if(result == "true") {
									alert("未知错误,请重试!")
								}
								else{
								
									$.messager.show({
										title:'提示',
										msg:"增加成功",
									});	
									$('#manager_add').dialog('close').form('reset');
									$('#homepage').datagrid('reload');
								}
							}
							
						})
					}
					
				}
			
			},
			{
				text:'取消',
				handler:function(){
					$('#manager_add').dialog('close').form('reset')
				}
			},
		],
	});
	$('#cc').combobox({
	    url:'combobox',
	    textField:'text',   
	    valueField:'id',
	    editable:false,
	   
	});
	$('#manager_update').dialog({
		width:350,
		title:'修改',
		modal:true,
		closed:true,
		buttons:[
			{
				text:"提交",
				handler:function(){

					if ($("#manager_update").form('validate')) {
						$.ajax({
							url:'stuUpdate',
							type:'get',
							data:{
								sid:$('input[name="edit_id"]').val(),
								sname:$('input[name="edit_name"]').val(),
								sex:$("input[type='radio']:checked").val(),
								hobby:$('input[name="edit_phone"]').val(),
								cname:$("#edit_cc").combotree("getText"),
								
							},
							
							beforeSend:function(){
								$.messager.progress({
									text:"正在修改中..."
								})
							},
						
							success: function(result) {
								$.messager.progress("close");
								if (result=='false') {
									$.messager.alert("修改失败","未知错误!","warning")
								
								}else{
									$.messager.show({
										title:'提示',
										msg:'修改成功',
									});
									$("#manager_update").dialog('close').form('reset');
									$("#homepage").datagrid("reload")
								}
							}
						});
					}
	 		   
				}	
			},
			{
				text:'取消',
				handler:function(){
					$('#manager_update').dialog('close').form('reset')
				}
			},
		],
	})
	marager_tool = {
			add : function(){
				$('#manager_add').dialog('open');
				$('input[name="name"]').focus();
			},
			
			remove : function(){
				var rows=$('#homepage').datagrid('getSelections');
				if (rows.length > 0) {
					var flag=$.messager.confirm('确定操作：','你确定要删除所选记录吗?','warning');
					if (flag) {
						var ids = [];
						for (var i = 0; i < rows.length; i++) {
							ids.push(rows[i].sid)
						}
						$.ajax({
							type:'get',
							url:"stuDelect",
							data:{
								sid: ids.join(","),
							},
						beforeSend : function(){
							$('#homepage').datagrid('loading');
						},
						success: function(data) {
							if(data) {
								$('#homepage').datagrid('loaded');
								$('#homepage').datagrid('load');
								$('#homepage').datagrid('unselectAll');
								$.messager.show({
									title:'提示',
									msg:"删除成功",
								})
							}
						}
						})
					}
				}else{
					$.messager.alert('提示：','请选择要删除的记录','warning');
				}
			},
			edit : function (){
				var rows=$('#homepage').datagrid('getSelections');
				if (rows.length>1) {
					$.messager.alert('警告操作：','只能选定一条','warning')
				}else if(rows.length == 1){
					$('#manager_update').form('load',{
						edit_id:rows[0].sid,
						edit_name:rows[0].sname,
						edit_sex:rows[0].sex!='男'?0:1,
						edit_phone:rows[0].hobby,
						edit_cname:rows[0].cname,
					}).dialog('open');
					$('#edit_cc').combobox({
					    url:'combobox',
					    textField:'text',   
					    valueField:'id',
					    editable:false,
					   
					})
				}else if(rows.length == 0){
					$.messager.alert('警告操作：','必须选定一条','warning')
				}
			},
		}

})