<%@ page language="java" contentType="text/html; charset=gbk"  pageEncoding="gbk"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- �Զ�ˢ��120s-->
    <!-- 
    <meta http-equiv="refresh" content="120">
    -->
    
    <!--360�����������webkit�ں˽���-->
    <title>ҳ��Ԥ��-${design.name}</title>
    <link href="<c:url value='/resource/hplus/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/animate.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/style.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/drag/dist/gridstack.css'/>" rel="stylesheet">
    <style type="text/css">
        .grid-stack {
        }
        .grid-stack-item-content {
        }
    </style>
</head>

<body onload="onload();">
    <div style="display: none;">
	    <textarea id="saved-data">
	    ${design.serialized_data}
	    </textarea>
    </div>
    
    <div class="grid-stack">
    </div>
    <script src="<c:url value='/resource/hplus/js/jquery.min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/resource/drag/js/jquery-ui.min.js'/>"></script>
    <script src="<c:url value='/resource/drag/js/lodash.min.js'/>"></script>
    <script src="<c:url value='/resource/drag/dist/gridstack.min.js'/>"></script>
    <script src="<c:url value='/resource/drag/dragview.js'/>"></script>
    
    <!-- dwr -->
    <script src="<c:url value='/dwr/engine.js'/>"></script>
    <script src="<c:url value='/dwr/util.js'/>"></script>
    <script src="<c:url value='/dwr/interface/dwrdragview.js'/>"></script>
    <script type="text/javascript">
    dwr.engine._errorHandler = function(message, ex) {dwr.engine._debug("Error: " + ex.name + ", " + ex.message, true);};
    </script>
 
</body>
</html>
