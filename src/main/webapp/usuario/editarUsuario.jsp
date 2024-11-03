<%--
  Created by IntelliJ IDEA.
  User: johnn
  Date: 03/11/2024
  Time: 11:22 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Usuario</title>
    <meta charset="UTF-8">
    <!-- Enlace a Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Enlace a tus estilos personalizados -->
    <link rel="stylesheet" href="../assets/css/styleHeader.css">
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="container mt-5">
    <h2>Editar Usuario</h2>
    <form action="${pageContext.request.contextPath}/usuario/editar" method="post">
        <div class="form-group">
            <label for="correo">Correo</label>
            <input type="email" class="form-control" id="correo" name="correo" value="${usuario.correo}" readonly>
        </div>
        <div class="form-group">
            <label for="nombre">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="${usuario.nombre}" required>
        </div>
        <div class="form-group">
            <label for="apellidoPaterno">Apellido Paterno</label>
            <input type="text" class="form-control" id="apellidoPaterno" name="apellidoPaterno" value="${usuario.apellidoPaterno}" required>
        </div>
        <div class="form-group">
            <label for="apellidoMaterno">Apellido Materno</label>
            <input type="text" class="form-control" id="apellidoMaterno" name="apellidoMaterno" value="${usuario.apellidoMaterno}" required>
        </div>
        <div class="form-group">
            <label for="telefono">Teléfono</label>
            <input type="tel" class="form-control" id="telefono" name="telefono" value="${usuario.telefono}" required>
        </div>
        <button type="submit" class="btn btn-primary">Actualizar Datos</button>
    </form>
</div>
<!-- Enlace a jQuery y Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
