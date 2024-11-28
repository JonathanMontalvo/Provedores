<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Proveedores</title>
    <!-- Enlace a Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Enlace a tus estilos personalizados -->
    <link rel="stylesheet" href="assets/css/styleIndex.css">
</head>
<body>
<jsp:include page="/includes/header.jsp" />
<div class="container content">
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="assets/img/imagen1.webp" class="d-block w-100" alt="Quienes Somos">
                <div class="carousel-caption d-none d-md-block">
                    <h5>¿Quiénes Somos?</h5>
                    <p>Somos una empresa dedicada a brindar productos de envios con más de 20 años de experiencia en el mercado.</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="assets/img/imagen2.webp" class="d-block w-100" alt="Qué Ofrecemos">
                <div class="carousel-caption d-none d-md-block">
                    <h5>¿Qué Ofrecemos?</h5>
                    <p>Ofrecemos una amplia gama de productos como cajas, sobres, paltes, entre otros; adaptados a tus necesidades.</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="assets/img/imagen3.webp" class="d-block w-100" alt="Qué Nos Caracteriza">
                <div class="carousel-caption d-none d-md-block">
                    <h5>¿Qué Nos Caracteriza?</h5>
                    <p>Nos caracteriza nuestra puntualidad, eficiencia y compromiso con la satisfacción del cliente. Siempre a tiempo, siempre confiables.</p>
                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>
<!-- Enlace a jQuery y Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
