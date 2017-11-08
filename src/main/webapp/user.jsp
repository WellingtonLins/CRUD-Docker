<%@ page language="java" contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Add new user</title>
        <link rel="stylesheet" href="css/style.css">
       	<link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/bootstrap-responsive.css"/>
    </head>
    <body>
        <div class="container espaco">
            <div class="offset3 span6 offset3">
                <form class="form-signin" role="form" method="POST" action='UserController' name="frmAddUser">
                    <h2 class="form-signin-heading">Insira seus dados</h2>

                    User ID : <input type="text"  class="form-control" readonly="readonly" name="id"
                                     value="<c:out value="${user.id}" />" /> <br /> 
                    Nome : <input
                        type="text" name="nome"  class="form-control"
                        value="<c:out value="${user.nome}" />" /> <br /> 
                    CPF: <input
                        type="text" name="CPF"  class="form-control"
                        value="<c:out value="${user.cpf}" />" /> <br /> 

                    <button class="btn btn-lg btn-primary btn-block" type="submit">Enviar</button>
                </form>

            </div>
        </div>
    </body>
</html>