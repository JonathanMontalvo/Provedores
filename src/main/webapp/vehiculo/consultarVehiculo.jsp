<%--
  Created by IntelliJ IDEA.
  User: johnn
  Date: 28/10/2024
  Time: 07:05 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consultar Vehículos</title>
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
    <h2>Listado de Vehículos</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Placa</th>
            <th>Marca</th>
            <th>Modelo</th>
            <th>Capacidad de Carga</th>
            <th>Dimensiones</th>
            <th>Año</th>
            <th>Tipo</th>
            <th>Estado</th>
            <th>Ubicación</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="vehiculo" items="${vehiculos}">
            <tr>
                <td>${vehiculo.placa}</td>
                <td>${vehiculo.marca}</td>
                <td>${vehiculo.modelo}</td>
                <td>${vehiculo.capacidad}</td>
                <td>${vehiculo.dimensiones}</td>
                <td>${vehiculo.anio}</td>
                <td>${vehiculo.tipo}</td>
                <td>${vehiculo.estado}</td>
                <td>${vehiculo.ubicacion}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/vehiculos/actualizar?placa=${vehiculo.placa}" class="btn btn-warning btn-sm">Actualizar</a>
                    <a href="${pageContext.request.contextPath}/vehiculos/borrar?placa=${vehiculo.placa}" class="btn btn-danger btn-sm">Borrar</a>
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
