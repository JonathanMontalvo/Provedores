<%--
  Created by IntelliJ IDEA.
  User: johnn
  Date: 03/11/2024
  Time: 11:22 a. m.
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
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger" role="alert">
                ${errorMessage}
        </div>
    </c:if>
    <form id="userForm" action="${pageContext.request.contextPath}/administrador/agregar" method="post">
        <div class="form-group">
            <label for="nombre">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" required>
            <div class="invalid-feedback">
                Por favor, ingrese un nombre.
            </div>
        </div>
        <div class="form-group">
            <label for="apellidoPaterno">Apellido Paterno</label>
            <input type="text" class="form-control" id="apellidoPaterno" name="apellidoPaterno" required>
            <div class="invalid-feedback">
                Por favor, ingrese un apellido paterno.
            </div>
        </div>
        <div class="form-group">
            <label for="apellidoMaterno">Apellido Materno</label>
            <input type="text" class="form-control" id="apellidoMaterno" name="apellidoMaterno" required>
            <div class="invalid-feedback">
                Por favor, ingrese un apellido materno.
            </div>
        </div>
        <div class="form-group">
            <label for="correo">Correo</label>
            <input type="email" class="form-control" id="correo" name="correo" required>
            <div class="invalid-feedback">
                Por favor, ingrese un correo válido.
            </div>
        </div>
        <div class="form-group">
            <label for="telefono">Teléfono</label>
            <input type="tel" class="form-control" id="telefono" name="telefono" required>
            <div class="invalid-feedback">
                Por favor, ingrese un número de teléfono.
            </div>
        </div>
        <div class="form-group">
            <label for="password">Contraseña</label>
            <input type="password" class="form-control" id="password" name="password" required>
            <div class="invalid-feedback">
                Por favor, ingrese una contraseña.
            </div>
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
                <option value="">Seleccione un rol</option>
                <option value="Administrador">Administrador</option>
                <option value="Ventas">Ventas</option>
            </select>
            <div class="invalid-feedback">
                Por favor, seleccione un rol.
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Guardar Usuario</button>
    </form>
</div>
<!-- Enlace a jQuery y Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    document.getElementById('userForm').addEventListener('submit', function(event) {
        var password = document.getElementById('password').value;
        var confirmPassword = document.getElementById('confirmPassword').value;

        if (password !== confirmPassword) {
            event.preventDefault(); // Evitar el envío del formulario
            document.getElementById('confirmPassword').classList.add('is-invalid');
        } else {
            document.getElementById('confirmPassword').classList.remove('is-invalid');
        }

        // Añadir validación personalizada al formulario
        if (!this.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
            this.classList.add('was-validated');
        }
    });

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
