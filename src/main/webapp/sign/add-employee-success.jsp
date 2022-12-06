<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee added</title>
</head>
<body>
<% String empName = (String)request.getAttribute("empName"); %>>
<script>alert('Successfully added employee: ${empName}');</script>
<jsp:include page="add" />
</body>
</html>