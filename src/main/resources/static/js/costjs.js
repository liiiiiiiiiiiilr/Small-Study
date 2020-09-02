
layui.use('table', function(){
  var table = layui.table;
  table.render({
    elem: '#test'
    ,url:'/search/findcost'
    ,toolbar: true
    ,title: '用户数据表'
    ,totalRow: true
    ,cols: [[
      {field:'id', title:'编号', width:200 , fixed: 'left', unresize: true, sort: true,totalRowText:"合计:"}
      ,{field:'cost', title:'费用', width:250,totalRow:true,  sort: true}
      ,{field:'materialcost', title:'材料费用', width:250,totalRow:true,  sort: true}
      ,{field:'promise', title:'保修承诺,', width:210, sort: true, totalRow: true}
      ,{field:'note', title:'注意事项', width:200,  sort: true}
      ,{field:'time', title:'结算日期', width:200, sort: true}
    ]]
    ,page: true
    
  });

  
});

$(document).ready(function () {
  $("#id").bind('keydown', function (e) {
    var that = this;
      if (e.keyCode == "13") {
        var table = layui.table;
        table.render({
          elem: '#test'
          ,url:'/search/costid'
          ,toolbar: true
          ,title: '用户数据表'
          ,where:{
            id :$('#id').val(),
          }
          ,totalRow: true
          
          ,cols: [[
            {field:'id', title:'编号', width:200 , fixed: 'left', unresize: true, sort: true,totalRowText:"合计:"}
            ,{field:'cost', title:'费用', width:250,totalRow:true,  sort: true}
            ,{field:'materialcost', title:'材料费用', width:250,totalRow:true,  sort: true}
            ,{field:'promise', title:'保修承诺,', width:210, sort: true, totalRow: true}
            ,{field:'note', title:'注意事项', width:200,  sort: true}
            ,{field:'time', title:'结算日期', width:200, sort: true}
          ]]
          ,page: true
          
        });
        layer.open({
          title:'提示'
          ,type:1
          ,time: 3000
          ,area: ['400px', '200px']
          ,content:"<p align='center'>再次点击<结算信息>即可查询所有信息</p>"
          ,btn:'朕知道了'
          ,btnAlign: 'c'
          ,yes:function(){
            layer.closeAll();
          }


        });
      }
  })
});
