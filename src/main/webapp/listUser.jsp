<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <link rel="stylesheet" href="css/style.css">
       	<link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/bootstrap-responsive.css"/>
        <title>Show All Users</title>
    </head>
    <body>
        <div class="container espaco">
            <div class="row">
                 <h2 class="form-signin-heading">Lista de Pessoas</h2>
                <div class="offset3 span6 offset3 ">
                    <table class="table  table-hover table-bordered table-striped ">

                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nome</th>               
                                <th>CPF</th>

                                <th colspan=2>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${users}" var="user">
                                <tr>
                                    <td><c:out value="${user.id}" /></td>
                                    <td><c:out value="${user.nome}" /></td>
                                    <td><c:out value="${user.cpf}"/></td>
                                    <td><a href="UserController?action=edit&userId=<c:out value="${user.id}"/>">Update</a></td>
                                    <td><a href="UserController?action=delete&userId=<c:out value="${user.id}"/>">Delete</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <p><a href="UserController?action=insert">Add User</a></p>
                </div>
            </div>
        </div>
    </body>
</html>