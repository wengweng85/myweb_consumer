<%@ page language="java" contentType="text/html; charset=gbk"  pageEncoding="gbk"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>页面列表</title>
    <link href="<c:url value='/resource/hplus/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/animate.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/style.min.css'/>" rel="stylesheet">
    <!-- Data Tables -->
    <link href="<c:url value='/resource/hplus/js/plugins/dataTables/datatables.min.css'/>" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
         <!-- 查询条件 -->
         <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>查询条件</h5>
            </div>
            <div class="ibox-content">
            <form class="form-horizontal" id="query_form" action="<c:url value='/solr/querylist'/>" onsubmit="return false;">
		        <div class="form-group">
		           <div class="col-sm-7">
		               <input type="text" name="q" class="form-control" value="${q}" placeholder="请输入您需要查找的内容...">
		           </div>
	               <div class="col-sm-4">
	                  <button type="button" class="btn btn-w-m btn-info" onclick="query()">查询</button>
	                  <button type="button" class="btn btn-w-m btn-info" onclick="document.getElementById('query_form').reset()">重置</button>
	                  <button type="button" class="btn btn-w-m btn-info" onclick="add_page()">新增</button>
	               </div>
		       </div>
	       </form>
	       </div>
        </div>
            
        <!-- 查询结果 -->    
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>查询结果列表</h5>
            </div>
            <!-- 模型 -->
            <script id="tpl" type="text/x-handlebars-template" >
                <button type="button" class="btn btn-info" onclick="gotoedit('{{id}}')">编辑</button> 
	            <button type="button" class="btn btn-danger" onclick="dd('{{id}}')" >删除</button> 
            </script>
            <div class="ibox-content">
                <table class="table table-striped table-bordered table-hover dataTables-example">
                    <thead>
                        <tr>
                            <th>编号</th>
                            <th>标题</th>
                            <th>内容</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- End Panel Basic -->
    </div>
    <script src="<c:url value='/resource/hplus/js/vue.min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/jquery.min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/handlebars-v2.0.0-min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/plugins/layer/layer.min.js'/>"></script>
    <!-- data table extend -->
    <script src="<c:url value='/resource/hplus/js/plugins/dataTables/datatables.js'/>"></script>
    <script src="<c:url value='/resource/drag/dragcommon.js'/>"></script>
    <script src="<c:url value='/resource/drag/draglist.js'/>"></script>
    <script type="text/javascript">
    var datatable;
    //页面模型数据准备
    var options={
    	//列模型	
	   	columns:[
  	         { "data": "id" },
  	         { "data": "f_title_ik" },
  	         { "data": "f_value_ik" },
  	         { "data": null }
   	     ],
   	    //列自定义 
		columnDefs:[ {
  	         "targets": 3,
  	         "render": function ( data, type, full, meta ) {
		          var tpl = $("#tpl").html();  
		  	      //预编译模板  
		  	      var template = Handlebars.compile(tpl);  
		  	      return template(data);
  	         }
	  	  }
	    ],
	    //表格jquery selector
		datatable_selector:'.dataTables-example',
		//对应查询form
		query_form_selector:'#query_form'	   		
    };
    
    
    //初始化
    $(function(){
    	datatable=tableinit(options);
    });
    
    //查询
    function query(){
    	datatable.ajax.reload();
    }
    
    
    //跳转至编辑页面
    function gotoedit(id){
    	layer.open({
   		  type: 2,
   		  title: '编辑页面',
   		  shadeClose: true,
   		  shade: 0.8,
   		  area: ['50%', '90%'],
   		  content: "<c:url value='/drag/toEdit'/>"+"/"+id //iframe的url
   		});
    }
     
    //删除
    function dd(id){
    	layer.confirm('确定删除此页面配置吗',function(){
    		var url = "<c:url value='/drag/delete'/>"+"/"+id;
        	$.ajax({
                type : "get",
                url : url,
                dataType : "json",
                success:function(response,textStatus){
                	layer.msg(response.message);
                	query();
                },
                error : function() {
                    layer.msg('发生错误了');
                }
            });
    	})
    }
    
    
    //新增
    function add_page(){
    	layer.open({
	   		  type: 2,
	   		  title: '新增页面',
	   		  shadeClose: true,
	   		  shade: 0.8,
	   		  area: ['50%', '90%'],
	   		  content: "<c:url value='/drag/toadd'/>" //iframe的url
   		});
    }  
    
    
    </script>
</body>

</html>
