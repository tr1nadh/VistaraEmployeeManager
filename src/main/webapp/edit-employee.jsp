<%@page import="com.example.vistaraemployeemanager.model.Employee"%>
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
    rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"/>
    <script src="<c:url value="/resources/js/script.js" />"></script>
    <title>Edit employee</title>
</head>
<body>
<% Employee employee = (Employee) request.getAttribute("emp");%>
<c:set var="emp" value="${employee}" />
    <div class='container border mt-3'>
    <h2 class='text-center mb-4'>Edit employee</h2>
    <form id="form" action='saveEmployee' method='post'>
    <label class='visually-hidden-focusable'>Id <input type='text' name='id' value='<%=employee.getId()%>'></label>
    <div class='form-floating mb-3 mt-3'>
    <input id="name-field" class='form-control' type='text' placeholder='Enter name' name='name' value='<%=employee.getName()%>'/>
    <label>Name</label>
    </div>
    <div class='form-floating mb-3 mt-3'>
    <input id="password-field" class='form-control' type='password' placeholder='Enter password' name='password' value='<%=employee.getPassword()%>' />
    <label>Password</label>
    </div>
    <div class='form-floating mb-3 mt-3'>
    <input id="email-field" class='form-control' type='email' placeholder='Enter email' name='email' value='<%=employee.getEmail()%>' />
    <label>Email</label>
    </div>
    <div class='mb-3 mt-3'>
                    <select title='countryselect' class='form-select form-select-md' name='country'>
                <option value='India'>India</option>
                <option value='UK'>UK</option>
                <option value='USA'>USA</option>
                <option value='UAE'>UAE</option>
                <option value='Other'>Other</option>
                </select>
    </div>
                
                <input class='btn btn-outline-success' onclick="submitForm()" type="button" value='Save' />
                <a class='btn btn-danger' href='view'>Cancel</a>
    
    </form>
    </div>
</body>
</html>