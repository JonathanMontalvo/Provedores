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
    <meta charset="UTF-8">
    <!-- Enlace a Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Enlace a tus estilos personalizados -->
    <link rel="stylesheet" href="../assets/css/styleHeader.css">
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="container mt-3">
    <c:choose>
        <c:when test="${not empty productos}">
            <h1 class="mb-3">Consultar Productos</h1>
            <div class="row">
                <c:forEach var="producto" items="${productos}">
                    <div class="col-md-4 mb-4">
                        <div class="card">
                            <c:if test="${not empty producto.imagen}">
                                <img src="${producto.imagen}" class="card-img-top" alt="${producto.nombre}">
                            </c:if>
                            <div class="card-body">
                                <h5 class="card-title"><strong>${producto.nombre}</strong></h5>
                                <p class="card-text">${producto.descripcion}</p>
                                <p class="card-text"><strong>Precio:</strong> $${producto.precio}</p>
                                <p class="card-text"><strong>Dimensiones:</strong> ${producto.dimensiones}</p>
                                <p class="card-text"><strong>Capacidad:</strong> ${producto.capacidad} kg</p>
                                <p class="card-text"><strong>Marca:</strong> ${producto.marca}</p>
                                <a href="${pageContext.request.contextPath}/productos/actualizar?id=${producto.id}" class="btn btn-primary">Editar</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <h1 class="text-center text-warning">No hay productos disponibles</h1>
        </c:otherwise>
    </c:choose>
</div>
<!-- Enlace a jQuery y Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
