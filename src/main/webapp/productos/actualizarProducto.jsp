<%--
Created by IntelliJ IDEA.
User: johnn
Date: 28/10/2024
Time: 06:09 p. m.
To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Actualizar Producto</title>
    <!-- Enlace a Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Enlace a tus estilos personalizados -->
    <link rel="stylesheet" href="../assets/css/styleHeader.css">
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="container mt-5">
    <h2>Actualizar Producto</h2>
    <!-- Enctype multipart/form-data: Asegura que el formulario pueda enviar archivos (en este caso la imagen) -->
    <form action="${pageContext.request.contextPath}/productos/actualizar" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${producto.id}">
        <div class="form-group">
            <label for="nombre">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="${producto.nombre}" required>
        </div>
        <div class="form-group">
            <label for="descripcion">Descripción</label>
            <input type="text" class="form-control" id="descripcion" name="descripcion" value="${producto.descripcion}" required>
        </div>
        <div class="form-group">
            <label for="precio">Precio</label>
            <input type="number" class="form-control" id="precio" name="precio" step="0.01" value="${producto.precio}" required>
        </div>
        <div class="form-group">
            <label for="dimensiones">Dimensiones</label>
            <input type="text" class="form-control" id="dimensiones" name="dimensiones" value="${producto.dimensiones}" required>
        </div>
        <div class="form-group">
            <label for="capacidad">Capacidad</label>
            <input type="number" class="form-control" id="capacidad" name="capacidad" step="0.01" value="${producto.capacidad}" required>
        </div>
        <div class="form-group">
            <label for="proveedor">Proveedor</label>
            <input type="text" class="form-control" id="proveedor" name="proveedor" value="${producto.proveedor}" required>
        </div>
        <div class="form-group">
            <label for="status">Status</label>
            <select class="form-control" id="status" name="status" required>
                <option value="1" <c:if test="${producto.status == 1}">selected</c:if>>Activo</option>
                <option value="0" <c:if test="${producto.status == 0}">selected</c:if>>Inactivo</option>
            </select>
        </div>
        <div class="form-group">
            <label for="imagen">Imagen</label>
            <input type="file" class="form-control-file" id="imagen" name="imagen" accept="image/*">
            <small class="form-text text-muted">Deje esto vacío si no desea cambiar la imagen</small>
        </div>
        <button type="submit" class="btn btn-primary">Actualizar</button>
        <a href="${pageContext.request.contextPath}/productos/consultar" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
<!-- Enlace a jQuery y Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
