<%--
Created by IntelliJ IDEA.
User: johnn
Date: 27/10/2024
Time: 06:30 p. m.
To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Get the context path --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="${contextPath}/">
            <img src="${contextPath}/assets/img/carro.webp" alt="Logo">
            Paquetería
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav ml-auto">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="productosDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Gestión de Productos
                            </a>
                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="productosDropdown">
                                <a class="dropdown-item" href="${contextPath}/productos/agregar">Agregar Productos</a>
                                <a class="dropdown-item" href="${contextPath}/productos/consultar">Consultar Productos</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="logisticaDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Gestión de Logística
                            </a>
                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="logisticaDropdown">
                                <a class="dropdown-item" href="${contextPath}/logistica/inventario">Visualizar Inventario</a>
                                <a class="dropdown-item" href="${contextPath}/logistica/historial">Historial Ventas</a>
                                <a class="dropdown-item" href="${contextPath}/logistica/compras">Historial Compras</a>

                            </div>
                        </li>
                        <c:if test="${sessionScope.user.rol == 'Administrador'}">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="administradorDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Gestión de Usuarios
                                </a>
                                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="administradorDropdown">
                                    <a class="dropdown-item" href="${contextPath}/administrador/agregar">Agregar Usuario</a>
                                    <a class="dropdown-item" href="${contextPath}/administrador/consultar">Consultar Usuarios</a>
                                </div>
                            </li>
                        </c:if>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="perfilDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Perfil
                            </a>
                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="perfilDropdown">
                                <a class="dropdown-item" href="${contextPath}/usuario/editar">Editar Información</a>
                                <a class="dropdown-item" href="${contextPath}/logout">Cerrar Sesión</a>
                            </div>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="${contextPath}/login">Iniciar Sesión</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </nav>
</header>
