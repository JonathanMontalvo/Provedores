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
    <title>Agregar Vehículo</title>
    <meta charset="UTF-8">
    <!-- Enlace a Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Enlace a tus estilos personalizados -->
    <link rel="stylesheet" href="../assets/css/styleHeader.css">
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="container mt-5">
    <h2>Registrar Nuevo Vehículo</h2>
    <form action="${pageContext.request.contextPath}/vehiculo" method="post">
        <div class="form-group">
            <label for="placa">Placa</label>
            <input type="text" class="form-control" id="placa" name="placa" required>
        </div>
        <div class="form-group">
            <label for="marca">Marca</label>
            <input type="text" class="form-control" id="marca" name="marca" required>
        </div>
        <div class="form-group">
            <label for="modelo">Modelo</label>
            <input type="text" class="form-control" id="modelo" name="modelo" required>
        </div>
        <div class="form-group">
            <label for="capacidad">Capacidad de Carga</label>
            <input type="number" class="form-control" id="capacidad" name="capacidad" step="0.01" placeholder="En kilogramos" required>
        </div>
        <div class="form-group">
            <label for="dimensiones">Dimensiones de la Caja de Carga</label>
            <input type="text" class="form-control" id="dimensiones" name="dimensiones" placeholder="Ej. 2x2x2 m" required>
        </div>
        <div class="form-group">
            <label for="anio">Año</label>
            <input type="number" class="form-control" id="anio" name="anio" required>
        </div>
        <div class="form-group">
            <label for="tipo">Tipo de Vehículo</label>
            <input type="text" class="form-control" id="tipo" name="tipo" required>
        </div>
        <div class="form-group">
            <label for="fechaAdquisicion">Fecha de Adquisición</label>
            <input type="date" class="form-control" id="fechaAdquisicion" name="fechaAdquisicion" required>
        </div>
        <div class="form-group">
            <label for="estado">Estado del Vehículo</label>
            <select class="form-control" id="estado" name="estado" required>
                <option value="Disponible">Disponible</option>
                <option value="Entregar paquete">Entregar paquete</option>
                <option value="Recoger paquete">Recoger paquete</option>
                <option value="En mantenimiento">En mantenimiento</option>
                <option value="Fuera de servicio">Fuera de servicio</option>
            </select>
        </div>
        <div class="form-group">
            <label for="combustible">Tipo de Combustible</label>
            <select class="form-control" id="combustible" name="combustible" required>
                <option value="Gasolina">Gasolina</option>
                <option value="Hidrógeno">Hidrógeno</option>
                <option value="Diésel">Diésel</option>
                <option value="Eléctrico">Eléctrico</option>
                <option value="Gas">Gas</option>
            </select>
        </div>
        <div class="form-group">
            <label for="ultimoMantenimiento">Fecha de Último Mantenimiento</label>
            <input type="date" class="form-control" id="ultimoMantenimiento" name="ultimoMantenimiento" required>
        </div>
        <div class="form-group">
            <label for="ubicacion">Ubicación</label>
            <input type="text" class="form-control" id="ubicacion" name="ubicacion" placeholder="Zona de trabajo" required>
        </div>
        <button type="submit" class="btn btn-primary">Guardar</button>
        <a href="${pageContext.request.contextPath}/vehiculos/agregar" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
<!-- Enlace a jQuery y Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
