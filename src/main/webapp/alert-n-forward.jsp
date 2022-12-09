<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alert</title>
</head>
<body>
<% 
String alrtMsg = (String)request.getAttribute("alrtMsg");
String forwardAddr = (String) request.getAttribute("frowardAddr");
%>
<script>
window.location.href = "${forwardAddr}";
alert('${alrtMsg}');
</script>
</body>
</html>