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
    <title>Agregar Usuario</title>
    <meta charset="UTF-8">
    <!-- Enlace a Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Enlace a tus estilos personalizados -->
    <link rel="stylesheet" href="../assets/css/styleHeader.css">
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="container mt-5">
    <h2>Registrar Nuevo Usuario</h2>
    <form id="userForm" action="${pageContext.request.contextPath}/administrador/agregar" method="post" novalidate>
        <div class="form-group">
            <label for="nombre">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" required>
        </div>
        <div class="form-group">
            <label for="apellidoPaterno">Apellido Paterno</label>
            <input type="text" class="form-control" id="apellidoPaterno" name="apellidoPaterno" required>
        </div>
        <div class="form-group">
            <label for="apellidoMaterno">Apellido Materno</label>
            <input type="text" class="form-control" id="apellidoMaterno" name="apellidoMaterno" required>
        </div>
        <div class="form-group">
            <label for="correo">Correo</label>
            <input type="email" class="form-control" id="correo" name="correo" required>
        </div>
        <div class="form-group">
            <label for="telefono">Teléfono</label>
            <input type="tel" class="form-control" id="telefono" name="telefono" required>
        </div>
        <div class="form-group">
            <label for="password">Contraseña</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="confirmPassword">Repetir Contraseña</label>
            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
            <div class="invalid-feedback">
                Las contraseñas no coinciden.
            </div>
        </div>
        <div class="form-group">
            <label for="rol">Rol</label>
            <select class="form-control" id="rol" name="rol" required>
                <option value="Administrador">Administrador</option>
                <option value="Ventas">Ventas</option>
                <option value="Cliente">Cliente (ERP)</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Guardar Usuario</button>
    </form>
</div>
<!-- Enlace a jQuery y Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    document.getElementById('confirmPassword').addEventListener('input', function() {
        var password = document.getElementById('password').value;
        var confirmPassword = document.getElementById('confirmPassword').value;

        if (password !== confirmPassword) {
            document.getElementById('confirmPassword').classList.add('is-invalid');
        } else {
            document.getElementById('confirmPassword').classList.remove('is-invalid');
        }
    });
</script>
</body>
</html>
