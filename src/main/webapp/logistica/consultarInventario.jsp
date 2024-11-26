<%--
  Created by IntelliJ IDEA.
  User: johnn
  Date: 27/10/2024
  Time: 02:37 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consultar Inventario</title>
    <meta charset="UTF-8">
    <!-- Enlace a Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Enlace a tus estilos personalizados -->
    <link rel="stylesheet" href="../assets/css/styleHeader.css">
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="container mt-5 custom-container">
    <h2>Inventario de Proveedores</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID Inventario</th>
            <th>ID Producto</th>
            <th>Nombre Producto</th>
            <th>Proveedor</th>
            <th>Cantidad</th>
            <th>Ubicación</th>
            <th>Fecha Actualización</th>
            <th>Reabastecer</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="inventario" items="${inventarios}">
            <tr>
                <td>${inventario.id}</td>
                <td>${inventario.idProducto}</td>
                <td>${productoNombres[inventario.id]}</td>
                <td>${productoProveedores[inventario.id]}</td>
                <td>${inventario.cantidad}</td>
                <td>${inventario.ubicacion}</td>
                <td>${inventario.fechaActualizacion}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/logistica/reabastecimiento" method="get">
                        <input type="hidden" name="id" value="${inventario.id}">
                        <button type="submit" class="btn btn-primary btn-sm">Reabastecer</button>
                    </form>
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
