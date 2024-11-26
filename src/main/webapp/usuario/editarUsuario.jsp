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
    <c:if test="${param.updateFailed eq 'true'}">
        <div class="alert alert-danger" role="alert" id="updateFailedMessage">
            Número de teléfono ya registrado.
        </div>
    </c:if>
    <c:if test="${param.updateSuccess eq 'true'}">
        <div class="alert alert-success" role="alert" id="updateSuccessMessage">
            Datos actualizados correctamente.
        </div>
    </c:if>
    <c:if test="${param.passwordChangeSuccess eq 'true'}">
        <div class="alert alert-success" role="alert" id="passwordChangeSuccessMessage">
            Contraseña actualizada correctamente.
        </div>
    </c:if>
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
        <a href="#" class="btn btn-secondary" data-toggle="modal" data-target="#changePasswordModal">Cambiar Contraseña</a>
    </form>
</div>

<!-- Modal para cambiar contraseña -->
<div class="modal fade" id="changePasswordModal" tabindex="-1" role="dialog" aria-labelledby="changePasswordModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="changePasswordModalLabel">Cambiar Contraseña</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="changePasswordForm" action="${pageContext.request.contextPath}/usuario/cambiarPassword" method="post">
                    <div class="form-group">
                        <label for="newPassword">Nueva Contraseña</label>
                        <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                        <div class="invalid-feedback">
                            Por favor, ingrese una nueva contraseña.
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="confirmNewPassword">Repetir Nueva Contraseña</label>
                        <input type="password" class="form-control" id="confirmNewPassword" name="confirmNewPassword" required>
                        <div class="invalid-feedback">
                            Las contraseñas no coinciden.
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Cambiar Contraseña</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Enlace a jQuery y Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        // Ocultar el mensaje de éxito después de 2 segundos
        setTimeout(function() {
            var updateSuccessMessage = document.getElementById('updateSuccessMessage');
            if (updateSuccessMessage) {
                updateSuccessMessage.style.display = 'none';
            }
            var passwordChangeSuccessMessage = document.getElementById('passwordChangeSuccessMessage');
            if (passwordChangeSuccessMessage) {
                passwordChangeSuccessMessage.style.display = 'none';
            }
        }, 2000);
    });

    document.getElementById('changePasswordForm').addEventListener('submit', function(event) {
        var password = document.getElementById('newPassword').value;
        var confirmPassword = document.getElementById('confirmNewPassword').value;

        if (password !== confirmPassword) {
            event.preventDefault(); // Evitar el envío del formulario
            document.getElementById('confirmNewPassword').classList.add('is-invalid');
        } else {
            document.getElementById('confirmNewPassword').classList.remove('is-invalid');
        }

        // Añadir validación personalizada al formulario
        if (!this.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
            this.classList.add('was-validated');
        }
    });

    document.getElementById('confirmNewPassword').addEventListener('input', function() {
        var password = document.getElementById('newPassword').value;
        var confirmPassword = document.getElementById('confirmNewPassword').value;

        if (password !== confirmPassword) {
            document.getElementById('confirmNewPassword').classList.add('is-invalid');
        } else {
            document.getElementById('confirmNewPassword').classList.remove('is-invalid');
        }
    });
</script>
</body>
</html>
