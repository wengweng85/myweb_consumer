<%@ page language="java" contentType="text/html; charset=gbk"  pageEncoding="gbk"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ҳ���б�</title>
    <link href="<c:url value='/resource/hplus/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/animate.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/style.min.css'/>" rel="stylesheet">
    <!-- Data Tables -->
    <link href="<c:url value='/resource/hplus/js/plugins/dataTables/datatables.min.css'/>" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
         <!-- ��ѯ���� -->
         <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>��ѯ����</h5>
            </div>
            <div class="ibox-content">
            <form class="form-horizontal" id="query_form" action="<c:url value='/solr/querylist'/>" onsubmit="return false;">
		        <div class="form-group">
		           <div class="col-sm-7">
		               <input type="text" name="q" class="form-control" value="${q}" placeholder="����������Ҫ���ҵ�����...">
		           </div>
	               <div class="col-sm-4">
	                  <button type="button" class="btn btn-w-m btn-info" onclick="query()">��ѯ</button>
	                  <button type="button" class="btn btn-w-m btn-info" onclick="document.getElementById('query_form').reset()">����</button>
	                  <button type="button" class="btn btn-w-m btn-info" onclick="add_page()">����</button>
	               </div>
		       </div>
	       </form>
	       </div>
        </div>
            
        <!-- ��ѯ��� -->    
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>��ѯ����б�</h5>
            </div>
            <!-- ģ�� -->
            <script id="tpl" type="text/x-handlebars-template" >
                <button type="button" class="btn btn-info" onclick="gotoedit('{{id}}')">�༭</button> 
	            <button type="button" class="btn btn-danger" onclick="dd('{{id}}')" >ɾ��</button> 
            </script>
            <div class="ibox-content">
                <table class="table table-striped table-bordered table-hover dataTables-example">
                    <thead>
                        <tr>
                            <th>���</th>
                            <th>����</th>
                            <th>����</th>
                            <th>����</th>
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
    //ҳ��ģ������׼��
    var options={
    	//��ģ��	
	   	columns:[
  	         { "data": "id" },
  	         { "data": "f_title_ik" },
  	         { "data": "f_value_ik" },
  	         { "data": null }
   	     ],
   	    //���Զ��� 
		columnDefs:[ {
  	         "targets": 3,
  	         "render": function ( data, type, full, meta ) {
		          var tpl = $("#tpl").html();  
		  	      //Ԥ����ģ��  
		  	      var template = Handlebars.compile(tpl);  
		  	      return template(data);
  	         }
	  	  }
	    ],
	    //���jquery selector
		datatable_selector:'.dataTables-example',
		//��Ӧ��ѯform
		query_form_selector:'#query_form'	   		
    };
    
    
    //��ʼ��
    $(function(){
    	datatable=tableinit(options);
    });
    
    //��ѯ
    function query(){
    	datatable.ajax.reload();
    }
    
    
    //��ת���༭ҳ��
    function gotoedit(id){
    	layer.open({
   		  type: 2,
   		  title: '�༭ҳ��',
   		  shadeClose: true,
   		  shade: 0.8,
   		  area: ['50%', '90%'],
   		  content: "<c:url value='/drag/toEdit'/>"+"/"+id //iframe��url
   		});
    }
     
    //ɾ��
    function dd(id){
    	layer.confirm('ȷ��ɾ����ҳ��������',function(){
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
                    layer.msg('����������');
                }
            });
    	})
    }
    
    
    //����
    function add_page(){
    	layer.open({
	   		  type: 2,
	   		  title: '����ҳ��',
	   		  shadeClose: true,
	   		  shade: 0.8,
	   		  area: ['50%', '90%'],
	   		  content: "<c:url value='/drag/toadd'/>" //iframe��url
   		});
    }  
    
    
    </script>
</body>

</html>
