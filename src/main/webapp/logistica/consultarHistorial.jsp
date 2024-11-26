<%--
  Created by IntelliJ IDEA.
  User: johnn
  Date: 24/11/2024
  Time: 01:11 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consultar Historial de Ventas</title>
    <meta charset="UTF-8">
    <!-- Enlace a Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Enlace a tus estilos personalizados -->
    <link rel="stylesheet" href="../assets/css/styleHeader.css">
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="container mt-5 custom-container">
    <h2>Historial de Ventas</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID Venta</th>
            <th>Fecha Venta</th>
            <th>Total</th>
            <th>Status Pago</th>
            <th>Productos Vendidos</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="venta" items="${historialVentas}">
            <tr>
                <td>${venta.id}</td>
                <td>${venta.fechaVenta}</td>
                <td>${venta.total}</td>
                <td>
                    <c:choose>
                        <c:when test="${venta.statusPago == 1}">Realizado</c:when>
                        <c:when test="${venta.statusPago == 2}">Cancelado</c:when>
                    </c:choose>
                </td>
                <td>
                    <c:forEach var="producto" items="${venta.productosVendidos}">
                        <div>
                            <strong>ID Producto:</strong> ${producto.idProducto.toHexString()} <br>
                            <strong>Cantidad:</strong> ${producto.cantidad} <br>
                            <strong>Total:</strong> ${producto.total}
                        </div>
                        <hr>
                    </c:forEach>
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
