<%--
  Created by IntelliJ IDEA.
  User: johnn
  Date: 27/10/2024
  Time: 06:30 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Get the context path --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="${contextPath}/index.jsp">
            <img src="${contextPath}/assets/img/carro.webp" alt="Logo">
            Paquetería
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="productosDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Gestión de Productos
                    </a>
                    <div class="dropdown-menu" aria-labelledby="productosDropdown">
                        <a class="dropdown-item" href="${contextPath}/productos/agregar">Agregar Productos</a>
                        <a class="dropdown-item" href="${contextPath}/productos/consultar">Consultar Productos</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="vehiculosDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Gestión de Vehículos
                    </a>
                    <div class="dropdown-menu" aria-labelledby="vehiculosDropdown">
                        <a class="dropdown-item" href="${contextPath}/vehiculos/agregar">Agregar Vehículo</a>
                        <a class="dropdown-item" href="${contextPath}/vehiculos/consultar">Consultar Vehículo</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="pedidosDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Gestión de Pedidos
                    </a>
                    <div class="dropdown-menu" aria-labelledby="pedidosDropdown">
                        <a class="dropdown-item" href="${contextPath}/pedidos/agregar">Agregar Pedido</a>
                        <a class="dropdown-item" href="${contextPath}/pedidos/consultar">Consultar Pedido</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="logisticaDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Gestión Logística
                    </a>
                    <div class="dropdown-menu" aria-labelledby="logisticaDropdown">
                        <a class="dropdown-item" href="${contextPath}/logistica/gestionLogistica.jsp">Gestión</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</header>
