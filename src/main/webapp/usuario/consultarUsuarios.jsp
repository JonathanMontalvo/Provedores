<%--
  Created by IntelliJ IDEA.
  User: johnn
  Date: 03/11/2024
  Time: 01:37 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consultar Usuarios</title>
    <meta charset="UTF-8">
    <!-- Enlace a Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Enlace a tus estilos personalizados -->
    <link rel="stylesheet" href="../assets/css/styleHeader.css">
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="container mt-5 custom-container">
    <h2>Listado de Usuarios</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Correo</th>
            <th>Teléfono</th>
            <th>Rol</th>
            <th>Status</th>
            <th>Detalles</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="usuario" items="${usuarios}">
            <tr>
                <td>${usuario.correo}</td>
                <td>${usuario.telefono}</td>
                <td>${usuario.rol}</td>
                <td>
                    <c:choose>
                        <c:when test="${usuario.status == 0}">Inactivo</c:when>
                        <c:when test="${usuario.status == 1}">Activo</c:when>
                    </c:choose>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/administrador/editar?correo=${usuario.correo}" class="btn btn-warning btn-sm">Actualizar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<!-- Enlace a jQuery y Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
