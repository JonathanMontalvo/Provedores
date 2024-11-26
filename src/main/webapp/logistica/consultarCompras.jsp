<%--
  Created by IntelliJ IDEA.
  User: johnn
  Date: 25/11/2024
  Time: 06:56 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Consultar Compras</title>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="../assets/css/styleHeader.css">
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="container mt-5 custom-container">
  <h2>Compras de Proveedores</h2>
  <table class="table table-striped">
    <thead>
    <tr>
      <th>ID Compra</th>
      <th>ID Inventario</th>
      <th>Cantidad</th>
      <th>Costo Restock</th>
      <th>Fecha Restock</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="compra" items="${compras}">
      <tr>
        <td>${compra.id}</td>
        <td>${compra.idInventario.toHexString()}</td>
        <td>${compra.cantidad}</td>
        <td>${compra.costoRestock}</td>
        <td>${compra.fechaRestock}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
