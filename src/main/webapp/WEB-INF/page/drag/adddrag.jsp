<%@ page language="java" contentType="text/html; charset=gbk"  pageEncoding="gbk"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--360�����������webkit�ں˽���-->
     <title>�����Ӽ��༭ҳ��</title>
    <link href="<c:url value='/resource/hplus/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/animate.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/plugins/codemirror/codemirror.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/plugins/codemirror/ambiance.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/style.min.css'/>" rel="stylesheet">
</head>
<body >
    <body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
        <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-sm-12 b-r">
                            <div class="form-group">
                                <input type="hidden" id="id" name="id" class="form-control" value="${design.id}">
                            </div>
                            <div class="form-group">
                                <label>����</label>
                                <input type="name" id="page_name"  name="page_name" placeholder="����" class="form-control" value="${design.name}" aria-required="true" aria-invalid="false" class="valid">
                            </div>
                            <div class="form-group">
                                <label>����</label>
                                <input type="describe" id="page_describe"  name="page_describe" placeholder="����" class="form-control" value="${design.describe}">
                            </div>
                             <div class="form-group">
                                <label>���ݲο�д��</label>
                                <textarea id="sample_serialized_data" readonly="readonly" >[{ "x": 0, "y": 0,"width": 12, "height": 12,"dataurl":"http://127.0.0.1:8080/myweb/resource/hplus/graph_echarts.html"} ]</textarea>
                            </div>
                            <div class="form-group">
                                <label>ҳ������</label>
                                <textarea id="serialized_data" name="serialized_data">${design.serialized_data}</textarea>
                            </div>
                         <div class="form-group" style="text-align: right;">
                              <button class="btn btn-primary " onclick="savePage()" type="submit">��������</button>
                              <button class="btn btn-danger "  onclick="cc()">�ر�</button>
                         </div>
                        </div>
                    </div>
                </div>
             </div>
         </div>
        </div>
    </div>   
    <script src="<c:url value='/resource/hplus/js/jquery.min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/plugins/layer/layer.min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/plugins/codemirror/codemirror.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/plugins/codemirror/mode/javascript/javascript.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/plugins/validate/jquery.validate.min.js'/>"></script>
    <script>
      var editor_sample; 
      var editor_one;    
      $(document).ready(function(){
   	    editor_sample=CodeMirror.fromTextArea(
				document.getElementById("sample_serialized_data"),
		            {
						lineNumbers:true,
						theme:"ambiance"
					}
				);
   	    editor_sample.setSize('auto','100px');
   	    
        editor_one=CodeMirror.fromTextArea(
		document.getElementById("serialized_data"),
            {
				lineNumbers:true,
				theme:"ambiance"
			}
		);
        editor_one.setSize('auto','250px');
      });
      
      //����ҳ��������Ϣ
      function savePage(){
    	var name=$('#page_name').val();
    	if(!name){
    		layer.msg('���Ʋ���Ϊ��');
    		$('#page_name').focus();
    		return ;
    	}
      	var describe=$('#page_describe').val();
    	if(!describe){
    		layer.msg('��������Ϊ��');
    		$('#page_describe').focus();
    		return ;
    	}  
      	var url="<c:url value='/drag/saveorupdate'/>"
      	$.ajax({
              type : "post",
              url : url,
              dataType : "json",
              data:{
              	  id:$('#id').val(),
              	  serialized_data:editor_one.getValue(),
              	  name:$('#page_name').val(),
              	  describe:$('#page_describe').val()
              },
              success:function(response,textStatus){
            	  layer.msg(response.message);
            	  if(response.success){
                  	  parent.query(); 
            	  }
              },
              error : function() {
              	layer.msg('����������');
              }
          });
      }
      //�رո�ҳ��
      var index = parent.layer.getFrameIndex(window.name); //�ȵõ���ǰiframe�������
      function cc(){
      	parent.layer.close(index); //��ִ�йر�
      }
    </script>
</body>
</html>
