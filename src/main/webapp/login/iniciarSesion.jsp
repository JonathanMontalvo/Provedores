<%--
  Created by IntelliJ IDEA.
  User: johnn
  Date: 03/11/2024
  Time: 11:18 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Iniciar Sesión</title>
    <meta charset="UTF-8">
    <!-- Enlace a Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Enlace a tus estilos personalizados -->
    <link rel="stylesheet" href="./assets/css/styleHeader.css">
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card mt-5">
                <div class="card-header text-center">
                    <h3>Iniciar Sesión</h3>
                </div>
                <div class="card-body">
                    <form action="login" method="post">
                        <div class="form-group">
                            <label for="email">Correo</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Contraseña</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Entrar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
