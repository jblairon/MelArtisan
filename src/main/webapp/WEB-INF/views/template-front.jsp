<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" />

<link href='resources/css/fullcalendar.min.css' rel='stylesheet' />

<meta name="viewport"
	content="width=device-width, initial-scale=1 shrink-to-fit=no ">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Les Artisans de Lille métropole</title>

<base
	href="<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/"%>" />

<link href="resources/css/bootstrap.min.css" rel="stylesheet"
	id="bootstrap-css" />

<link href='resources/css/style.css' rel='stylesheet' />
<link href='resources/css/animation_1.css' rel='stylesheet' />
<link href='resources/css/animation_2.css' rel='stylesheet' />
<link href='resources/css/animations.css' rel='stylesheet' />
<link href='resources/fronts/glyphicons-halflings-regular.eot'
	rel='stylesheet' />

<style>
body {
	/* 	background-image: url("resources/images/background_liste_front.jpg"); */
	padding-top: 54px;
	background-attachment: fixed;
	background-color: #B4C8D4;
}

.copyright {
	margin-top: 20px;
}

@media ( min-width : 992px) {
	body {
		padding-top: 56px;
	}
}

.accueil {
	font-size: 25px;
}

.titre-menu {
	font-family: "Serif (serif red), Times New Roman", Times;
	font-size: 30px;
}

.portfolio-item {
	width: 30%;
	margin-bottom: 30px;
	border: 10px solid #ffffff;
}

.pagination {
	margin-bottom: 30px;
}
</style>

</head>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container logo">
			<a class="navbar-brand titre-menu" href="categories">MelArtisans</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active accueil"><a class="nav-link"
						href="categories"><span class="glyphicon glyphicon-home"></span>&nbsp;
							Accueil <span class="sr-only">(current)</span> </a></li>
				</ul>
			</div>
		</div>


		<!-- Menu déroulaant pour les catégories -->
		<div class="dropdown">
			<button class="btn btn-secondary dropdown-toggle categories1"
				type="button" data-toggle="dropdown">
				Catégories <span class="caret"></span>
			</button>
			<ul class=" dropdown-menu" id="navbarSupportedContent">
				<c:forEach var="categorie" items="${categories }">
					<li><a class="nav-link dropdown"
						href="societe/listeParCategorie?categorieId=${categorie.id }"><c:out
								value="${categorie.libelle }"></c:out></a></li>
				</c:forEach>


				<li><a class="nav-link dropdown" href="categories">Tous</a></li>
			</ul>
		</div>
		<!-- Fin menu déroulant -->



		<div class="collapse navbar-collapse coordonnees"
			id="navbarResponsive">
			<ul class="navbar-nav ml-auto">

				<li class="nav-item"><a class="nav-link"
					href="societe/lister-front"><span
						class="glyphicon glyphicon-signal"></span>&nbsp Les artisans</a></li>

				<li class="nav-item"><a class="nav-link"
					href="authenticate?contact=false"><span
						class="glyphicon glyphicon-user"></span>&nbsp Connexion</a></li>

				<li class="nav-item"><a class="nav-link" href="signup"> <span
						class="glyphicon glyphicon-pencil"></span>&nbsp S'inscrire
				</a></li>

				<li class="nav-item"><a class="nav-link"
					href="authenticate?contact=true"> <span
						class="glyphicon glyphicon-envelope"></span>&nbsp Contacter
						MelArtisan
				</a></li>
			</ul>
		</div>
	</nav>

	<c:if test="${societe == null }">
		<div style="width: 93%;">
			<IMG alt="Logo artisanat" src="resources/images/logo_artisanat.png"
				style="width: 100%; height: 500px;" /> <IMG alt="Logo mel"
				src="resources/images/logo_mel.jpg"
				style="width: 30%; margin-left: 1100px; margin-top: -750px;" />
		</div>
	</c:if>


	<!-- Page Content -->
	<div class="container" style="width: 100%; margin: auto; height: auto;">
		<decorator:body />
	</div>

	<!-- Footer -->
	<footer class="bg-dark fixed-bottom">
		<div>
			<p class="text-center text-white copyright">Copyright &copy; 2019
				: José Blairon</p>
		</div>
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js">
		
	</script>



</body>

</html>