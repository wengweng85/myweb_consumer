<%@ page language="java" contentType="text/html; charset=gbk"  pageEncoding="gbk"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@taglib uri="http://www.myweb.com/mywebtag" prefix="web" %>
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
            <form  class="form-horizontal" id="query_form" action="<c:url value='/drag/querylist'/>" onsubmit="return false;">
		        <div class="form-group">
		          <label class="col-sm-1 control-label">����</label>
		           <div class="col-sm-2">
		               <input type="text" name="page_name" class="form-control">
		           </div>
		           <label class="col-sm-1 control-label">����</label>
		           <div class="col-sm-2">
		               <input type="text" name="page_describe" class="form-control"> 
		           </div>
		           <label class="col-sm-1 control-label">��������</label>
		           <div class="col-sm-1">
		               <web:select codetype="AAB301" id="aac004" name="aac004"  value="${v}"/> 
		           </div>
	               <div class="col-sm-4">
	                  <button type="button" class="btn btn-w-m btn-info" onclick="query()">��ѯ</button>
	                  <button type="button" class="btn btn-w-m btn-info" onclick="document.getElementById('query_form').reset()">����</button>
	                  <button type="button" class="btn btn-w-m btn-info" onclick="add_page()">����</button>
	                  <button type="button" class="btn btn-w-m btn-info" onclick="file_upload()">�ļ��ϴ�</button>
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
	            <button type="button" class="btn btn-info" onclick="edit('{{id}}')">ҳ����ӻ��༭</button>
	            <button type="button" class="btn btn-info" onclick="view('{{id}}')">ҳ��Ԥ��</button>  
	            <button type="button" class="btn btn-danger" onclick="dd('{{id}}')" >ɾ��</button> 
            </script>
            <div class="ibox-content">
                <table class="table table-striped table-bordered table-hover dataTables-example">
                    <thead>
                        <tr>
                            <th>���</th>
                            <th>����</th>
                            <th>˵��</th>
                            <th>����</th>
                            <th>ʱ��</th>
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
  	         { "data": "page_name" },
  	         { "data": "page_describe" },
  	         { "data": "serialized_data" },
  	         { "data": "designtime" },
  	         { "data": null }
   	     ],
   	    //���Զ��� 
		columnDefs:[ {
  	         "targets": 5,
  	         "render": function ( data, type, full, meta ) {
		          var tpl = $("#tpl").html();  
		  	      //Ԥ����ģ��  
		  	      var template = Handlebars.compile(tpl);  
		  	      return template(data);
  	         }
	  	  },
	  	  {
	          "targets": [0],
	          "visible": true
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
	
    //���ݱ༭
    function edit(id){
    	var url = "<c:url value='/drag/gotoedit'/>";
    	openwindow('editwindow',url+'/'+id); 
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
    
    //Ԥ��
    function view(id){
    	var url = "<c:url value='/drag/view'/>";
    	openwindow('viewwindow',url+'/'+id);
    }
    //����ҳ��
    function openwindow(windoowname,url){
        //window.open(url,windoowname,'width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')
         window.open(url,windoowname);
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
    
    //�ļ��ϴ�
    function file_upload(){
    	layer.open({
	   		  type: 2,
	   		  title: '�ļ��ϴ�',
	   		  shadeClose: true,
	   		  shade: 0.8,
	   		  area: ['60%', '90%'],
	   		  content: "<c:url value='/drag/toupload'/>" //iframe��url
   		});
    }  
    
    
    
    </script>
</body>

</html>
