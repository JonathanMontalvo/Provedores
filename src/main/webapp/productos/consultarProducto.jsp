<%--
  Created by IntelliJ IDEA.
  User: johnn
  Date: 27/10/2024
  Time: 02:06 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consultar Productos</title>
    <!-- Enlace a Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Enlace a tus estilos personalizados -->
    <link rel="stylesheet" href="../assets/css/styleHeader.css">
    <style>
        .custom-container {
            margin-left: 8%; /* Ajusta este valor según sea necesario */
            margin-right: auto;
        }
    </style>
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="container mt-5 custom-container">
    <h2>Listado de Productos</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Precio</th>
            <th>Dimensiones</th>
            <th>Capacidad</th>
            <th>Marca</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="producto" items="${productos}">
            <tr>
                <td>${producto.nombre}</td>
                <td>${producto.descripcion}</td>
                <td>${producto.precio}</td>
                <td>${producto.dimensiones}</td>
                <td>${producto.capacidad}</td>
                <td>${producto.marca}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/productos/actualizar?nombre=${producto.nombre}" class="btn btn-warning btn-sm">Actualizar</a>
                    <a href="${pageContext.request.contextPath}/productos/borrar?nombre=${producto.nombre}" class="btn btn-danger btn-sm">Borrar</a>
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
