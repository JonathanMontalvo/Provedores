<%--
  Created by IntelliJ IDEA.
  User: johnn
  Date: 25/11/2024
  Time: 01:41 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reabastecimiento de Inventario</title>
    <meta charset="UTF-8">
    <!-- Enlace a Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Enlace a tus estilos personalizados -->
    <link rel="stylesheet" href="../assets/css/styleHeader.css">
    <script>
        function calcularTotal() {
            var cantidad = document.getElementById("cantidad").value;
            var costo = document.getElementById("costoUnidad").value;
            var total = cantidad * costo;
            document.getElementById("total").value = total.toFixed(2);
        }
    </script>
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="container mt-5">
    <h2>Reabastecimiento de Inventario</h2>
    <form action="${pageContext.request.contextPath}/logistica/reabastecimiento" method="post">
        <div class="form-group">
            <label for="idInventario">ID Inventario</label>
            <input type="text" class="form-control" id="idInventario" name="idInventario" value="${idInventario}" readonly>
        </div>
        <div class="form-group">
            <label for="cantidad">Cantidad</label>
            <input type="number" class="form-control" id="cantidad" name="cantidad" min="1" required oninput="calcularTotal()">
        </div>
        <div class="form-group">
            <label for="costoUnidad">Costo por Unidad</label>
            <input type="text" class="form-control" id="costoUnidad" name="costoUnidad" value="${String.format('%.2f', costo)}" readonly>
        </div>
        <div class="form-group">
            <label for="total">Costo Total</label>
            <input type="text" class="form-control" id="total" name="costoRestock" readonly>
        </div>
        <button type="submit" class="btn btn-primary">Reabastecer</button>
        <a href="${pageContext.request.contextPath}/logistica/inventario" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
<!-- Enlace a jQuery y Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
