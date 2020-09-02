
layui.use('table', function(){
    var table = layui.table;
    
    table.render({
      elem: '#test'
      ,url:'/search/findrepair'
      ,toolbar: true
      ,title: '用户数据表'
      ,totalRow: true
      ,cols: [[
        {field:'id', title:'维修编号', width:100, fixed: 'left', unresize: true, sort: true, totalRowText: '合计行'}
        ,{field:'engineerid', title:'工程师id', width:120}
        ,{field:'scan', title:'检测记录', width:150}
        ,{field:'repair', title:'维修记录', width:100, sort: true, totalRow: true}
        ,{field:'time', title:'维修检测时间', width:200, sort: true}
        ,{field:'workload', title:'维修的工作量', width:100, sort: true, totalRow: true}
        ,{field:'parts', title:'维修使用的器件', width:100}
        ,{field:'status', title:'维修状态', width:150}
      ]]
      ,page: true
      
    });
  });
  
  $(document).ready(function () {
    $("#id").bind('keydown', function (e) {
        if (e.keyCode == "13") {
          var table = layui.table
          table.render({
            elem: '#test'
            ,url:'/search/repairid'
            ,toolbar: true
            ,title: '用户数据表'
            ,totalRow: true
            ,where:{
              name :$('#id').val(),
            }
            ,cols: [[
              {field:'id', title:'维修编号', width:100, fixed: 'left', unresize: true, sort: true, totalRowText: '合计行'}
              ,{field:'engineerid', title:'工程师id', width:120}
              ,{field:'scan', title:'检测记录', width:150}
              ,{field:'repair', title:'维修记录', width:100, sort: true, totalRow: true}
              ,{field:'time', title:'维修检测时间', width:200, sort: true}
              ,{field:'workload', title:'维修的工作量', width:100, sort: true, totalRow: true}
              ,{field:'parts', title:'维修使用的器件', width:100}
              ,{field:'status', title:'维修状态', width:150}
            ]]
            ,page: true
            
          });

          layer.open({
            title:'提示'
            ,type:1
            ,time: 3000
            ,area: ['400px', '200px']
            ,content:"<p align='center'>再次点击<维修信息>即可查询所有信息</p>"
            ,btn:'朕知道了'
            ,btnAlign: 'c'
            ,yes:function(){
              layer.closeAll();
            }
  
  
          });
        }
    })
  });