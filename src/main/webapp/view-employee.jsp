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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
        rel="stylesheet"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="<c:url value="/resources/js/view.js" />"></script>
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"/>
        <title>View employees</title>
    </head>

    <%-- Navbar --%>

    <jsp:include page="navbar.jsp" />

    <body>

        <%-- Java scriptlet --%>
    
        <% 
        ArrayList<Employee> empList = (ArrayList<Employee>) request.getAttribute("empList");
        int prev = (Integer) request.getAttribute("prev");
        int next = (Integer) request.getAttribute("next");
        %>

        <%-- Search field --%>

        <div class="container">
            <form action="view" method="get">
                <div>
                    <input class="form-control" name="name" placeholder="Search name (regex also works)"> 
                </div>
            </form>
        </div>

        <%-- Table --%>

        <div class='container border mt-1'>
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
                </tbody>
            </table>

            <%-- Add employee button --%>

            <form action='add' method='post'>
                <button class='btn btn-outline-primary' type='submit'>Add new employee</button>
            </form>

        </div>

        <%-- Pagination --%>

        <div class="container">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li id="first-item" class="page-item"><a class="page-link" href="view">First</a></li>
                    <li id="prev-item" class="page-item"><a id="prev-link" class="page-link" href="view?p=${prev}">Previous</a></li>
                    <li id="next-item" class="page-item"><a class="page-link" href="view?p=${next}">Next</a></li>
                </ul>
            </nav>
        </div>

        <%-- JS --%>

        <script>
            onLoadFunction(${empList.isEmpty()}, ${next});
        </script>

    </body>
</html>