<!DOCTYPE html>
<html>
<head>
<script>
    function load (){
        var logoutUrl = 'http://www.catest.com:8080/cas-server/logout?service=http://127.0.0.1:8091/web-consumer/shiro-cas';
        window.location.href=logoutUrl;
    }
</script>
</head>
<body onload="load()"></body>
</html>
