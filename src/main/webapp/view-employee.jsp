<%@page import="com.example.vistaraemployeemanager.model.Employee"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
    rel="stylesheet"
  />
  <link rel="stylesheet" href="css/style.css" />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <title>View employees</title>
</head>
<body>
	<%
	ArrayList<Employee> empList = (ArrayList<Employee>) request.getAttribute("empList");
	%>
    <div class='container border mt-3'>
    <h2 class='text-center mb-4'>View Employees</h2>
    <table class='table table-hover'>
    <tbody>
    <tr>
    <th>Id</th>
    <th>Name</th>
    <th>Password</th>
    <th>Email</th>
    <th>Country</th>
    <th>Edit</th>
    <th>Delete</th>
    </tr>
    <c:forEach items="${empList}" var="emp">
        <c:set var="id" value="${emp.getId()}" />
    <tr>
    <td>${id}</td>
    <td>${emp.getName()}</td>
    <td>${emp.getPassword()}</td>
    <td>${emp.getEmail()}</td>
    <td>${emp.getCountry()}</td>
    <td><a class='btn btn-success btn-sm' href='editEmployee?id=${id}'>Edit</a> </td>
    <td><a class='btn btn-danger btn-sm' onClick='confirmDelete(${id})'>Delete</a></td>
    </tr>
    </c:forEach>
    </tbody></table>
                        <form action='add' method='post'>
                <button class='btn btn-outline-primary' type='submit'>Add new employee</button>
                </form>
    </div>
<script>
function confirmDelete(id) {
    let text = "Are you sure, you want to delete?";
    if (confirm(text) == true) {
        window.location.assign("deleteEmployee?id=" + id);
    }
}
</script>
</body>
</html>