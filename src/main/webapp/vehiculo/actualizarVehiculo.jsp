<%--
  Created by IntelliJ IDEA.
  User: johnn
  Date: 28/10/2024
  Time: 02:12 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Actualizar Vehículo</title>
  <!-- Enlace a Bootstrap CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <!-- Enlace a tus estilos personalizados -->
  <link rel="stylesheet" href="../assets/css/styleHeader.css">
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="container mt-5">
  <h2>Actualizar Vehículo</h2>
  <form action="${pageContext.request.contextPath}/vehiculos/actualizar" method="post">
    <div class="form-group">
      <label for="placa">Placa</label>
      <input type="text" class="form-control" id="placa" name="placa" value="${vehiculo.placa}" readonly>
    </div>
    <div class="form-group">
      <label for="marca">Marca</label>
      <input type="text" class="form-control" id="marca" name="marca" value="${vehiculo.marca}" required>
    </div>
    <div class="form-group">
      <label for="modelo">Modelo</label>
      <input type="text" class="form-control" id="modelo" name="modelo" value="${vehiculo.modelo}" required>
    </div>
    <div class="form-group">
      <label for="capacidad">Capacidad de Carga</label>
      <input type="number" class="form-control" id="capacidad" name="capacidad" step="0.01" value="${vehiculo.capacidad}" required>
    </div>
    <div class="form-group">
      <label for="dimensiones">Dimensiones de la Caja de Carga</label>
      <input type="text" class="form-control" id="dimensiones" name="dimensiones" value="${vehiculo.dimensiones}" required>
    </div>
    <div class="form-group">
      <label for="anio">Año</label>
      <input type="number" class="form-control" id="anio" name="anio" value="${vehiculo.anio}" required>
    </div>
    <div class="form-group">
      <label for="tipo">Tipo de Vehículo</label>
      <input type="text" class="form-control" id="tipo" name="tipo" value="${vehiculo.tipo}" required>
    </div>
    <div class="form-group">
      <label for="fechaAdquisicion">Fecha de Adquisición</label>
      <input type="date" class="form-control" id="fechaAdquisicion" name="fechaAdquisicion" value="${vehiculo.fechaAdquisicion}" required>
    </div>
    <div class="form-group">
      <label for="estado">Estado del Vehículo</label>
      <select class="form-control" id="estado" name="estado" required>
        <option value="Disponible" ${vehiculo.estado == 'Disponible' ? 'selected' : ''}>Disponible</option>
        <option value="Entregar paquete" ${vehiculo.estado == 'Entregar paquete' ? 'selected' : ''}>Entregar paquete</option>
        <option value="Recoger paquete" ${vehiculo.estado == 'Recoger paquete' ? 'selected' : ''}>Recoger paquete</option>
        <option value="En mantenimiento" ${vehiculo.estado == 'En mantenimiento' ? 'selected' : ''}>En mantenimiento</option>
        <option value="Fuera de servicio" ${vehiculo.estado == 'Fuera de servicio' ? 'selected' : ''}>Fuera de servicio</option>
      </select>
    </div>
    <div class="form-group">
      <label for="combustible">Tipo de Combustible</label>
      <select class="form-control" id="combustible" name="combustible" required>
        <option value="Gasolina" ${vehiculo.combustible == 'Gasolina' ? 'selected' : ''}>Gasolina</option>
        <option value="Hidrógeno" ${vehiculo.combustible == 'Hidrógeno' ? 'selected' : ''}>Hidrógeno</option>
        <option value="Diésel" ${vehiculo.combustible == 'Diésel' ? 'selected' : ''}>Diésel</option>
        <option value="Eléctrico" ${vehiculo.combustible == 'Eléctrico' ? 'selected' : ''}>Eléctrico</option>
        <option value="Gas" ${vehiculo.combustible == 'Gas' ? 'selected' : ''}>Gas</option>
      </select>
    </div>
    <div class="form-group">
      <label for="ultimoMantenimiento">Fecha de Último Mantenimiento</label>
      <input type="date" class="form-control" id="ultimoMantenimiento" name="ultimoMantenimiento" value="${vehiculo.ultimoMantenimiento}" required>
    </div>
    <div class="form-group">
      <label for="ubicacion">Ubicación</label>
      <input type="text" class="form-control" id="ubicacion" name="ubicacion" value="${vehiculo.ubicacion}" required>
    </div>
    <button type="submit" class="btn btn-primary">Actualizar</button>
    <a href="${pageContext.request.contextPath}/vehiculos/consultar" class="btn btn-secondary">Cancelar</a>
  </form>
</div>
<!-- Enlace a jQuery y Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
