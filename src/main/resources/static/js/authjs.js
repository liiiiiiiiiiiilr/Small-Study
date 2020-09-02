
layui.use('table', function(){
    var table = layui.table;
    table.render({
      elem: '#demo'
      ,url:'/login/get'
      ,toolbar: true
      ,title: '用户数据表'
      ,cols: [[
        {field:'id', title:'id', width:250, fixed: 'left', unresize: true,edit:true}
        ,{field:'name', title:'姓名', width:270, edit:true}
        ,{field:'position', title:'职位', width:270,  edit:true}
        ,{field:'user', title:'用户名', width:275,  edit:true}
        ,{field:'password', title:'密码', width:280,edit:true}
        ,{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}
      ]]
      ,page: true
    });
    
  });
  
  $(document).ready(function () {
   
    $("#id").bind('keydown', function (e) {
        if (e.keyCode == "13") {
          var table = layui.table;
          table.render({
            elem: '#demo'
            ,url:'/search/costid'
            ,toolbar: true
            ,title: '用户数据表'
            ,where:{
              id :$('#id').val(),
            }
            ,totalRow: true
            
            ,cols: [[
              {field:'id', title:'编号', width:200 , fixed: 'left', unresize: true,totalRowText:"合计:"}
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
            ,content:"<p align='center'>再次点击即可查询所有信息</p>"
            ,btn:'朕知道了'
            ,btnAlign: 'c'
            ,yes:function(){
              layer.closeAll();
            }
  
  
          });
        }
    })
  });