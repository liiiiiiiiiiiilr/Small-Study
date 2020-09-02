
layui.use('table', function(){
    var table = layui.table;
    
    table.render({
      elem: '#test'
      ,url:'/search/findhub'
      ,toolbar: true
      ,title: '用户数据表'
      ,totalRow: true
      ,cols: [[
        {field:'id', title:'编号', width:100, fixed: 'left', unresize: true, sort: true, totalRowText: '合计行'}
        ,{field:'name', title:'名称', width:150}
        ,{field:'stype', title:'型号', width:150}
        ,{field:'price', title:'价格', width:150, sort: true, totalRow: true}
        ,{field:'num', title:'数量', width:150,sort: true,totalRow:true}
        ,{field:'time', title:'入库时间', width:150, sort: true}
        ,{field:'warnnum', title:'警戒数量', width:100, sort: true, totalRow: true}
        ,{field:'status', title:'入库状态', width:150, totalRow: true}
      ]]
      ,page: true
      
    });
  });
  
  $(document).ready(function () {
    $("#id").bind('keydown', function (e) {
        if (e.keyCode == "13") {
          var table = layui.table;
    
          table.render({
            elem: '#test'
            ,url:'/search/hubid'
            ,toolbar: true
            ,title: '用户数据表'
            ,totalRow: true
            ,where:{
              name :$('#id').val(),
            }
            ,cols: [[
              {field:'id', title:'编号', width:100, fixed: 'left', unresize: true, sort: true, totalRowText: '合计行'}
              ,{field:'name', title:'名称', width:150}
              ,{field:'stype', title:'型号', width:150}
              ,{field:'price', title:'价格', width:150, sort: true, totalRow: true}
              ,{field:'num', title:'数量', width:150,sort: true,totalRow:true}
              ,{field:'time', title:'入库时间', width:150, sort: true}
              ,{field:'warnnum', title:'警戒数量', width:100, sort: true, totalRow: true}
              ,{field:'status', title:'入库状态', width:150, totalRow: true}
            ]]
            ,page: true
            
          });

          layer.open({
            title:'提示'
            ,type:1
            ,time: 3000
            ,area: ['400px', '200px']
            ,content:"<p align='center'>再次点击<库存信息>即可查询所有信息</p>"
            ,btn:'朕知道了'
            ,btnAlign: 'c'
            ,yes:function(){
              layer.closeAll();
            }
  
  
          });
        }
    })
  });