
layui.use('table', function(){
    var table = layui.table;
    
    table.render({
      elem: '#test'
      ,url:'/search/findcustom'
      ,toolbar: true
      ,title: '用户数据表'
      ,totalRow: true
      ,cols: [[
        {field:'id', title:'客户编号', width:100, fixed: 'left', unresize: true,sort:true}
        ,{field:'idcard', title:'身份证编号', width:150}
        ,{field:'name', title:'联系人', width:100}
        ,{field:'stime', title:'客户送达时间', width:150}
        ,{field:'type', title:'客户性质', width:100}
        ,{field:'company', title:'单位名称', width:150}
        ,{field:'ftel', title:'座机号码', width:100}
        ,{field:'tel', title:'手机号', width:100}
        ,{field:'address', title:'用户地址', width:150}
        ,{field:'postcode', title:'邮编', width:100}
        ,{field:'email', title:'邮件', width:100}
      ]]
      ,page: true
      ,id:'test'
      
    });
  });

  $(document).ready(function () {
    $("#id").bind('keydown', function (e) {
        if (e.keyCode == "13") {
            var table = layui.table;
    
            table.render({
              elem: '#test'
              ,url:'/search/customid'
              ,toolbar: true
              ,where:{
                id :$('#id').val(),
              }
              ,title: '用户数据表'
              ,totalRow: true
              ,cols: [[
                {field:'id', title:'客户编号', width:150, fixed: 'left', unresize: true}
                ,{field:'idcard', title:'身份证编号', width:150}
                ,{field:'name', title:'联系人', width:100, sort: true}
                ,{field:'stime', title:'客户送达时间', width:150}
                ,{field:'type', title:'客户性质', width:100}
                ,{field:'company', title:'单位名称', width:100}
                ,{field:'ftel', title:'座机号码', width:100}
                ,{field:'tel', title:'手机号', width:100}
                ,{field:'address', title:'用户地址', width:150}
                ,{field:'postcode', title:'邮编', width:100}
                ,{field:'email', title:'邮件', width:100}
              ]]
              ,page: true
              ,id:'test'
              
            });

            layer.open({
              title:'提示'
              ,type:1
              ,time: 3000
              ,area: ['400px', '200px']
              ,content:"<p align='center'>再次点击<客户信息>即可查询所有信息</p>"
              ,btn:'朕知道了'
              ,btnAlign: 'c'
              ,yes:function(){
                layer.closeAll();
              }
    
    
            });

        }
    });
});