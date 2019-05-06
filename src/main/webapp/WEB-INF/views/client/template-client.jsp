<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport"
	content="width=device-width, initial-scale=1 shrink-to-fit=no ">
<meta charset="utf-8" />

<title>Les Artisans de la métropole lilloise</title>

<base
	href="<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/"%>" />

<link href="resources/css/bootstrap.min.css" rel="stylesheet"
	id="bootstrap-css" />

<link href='resources/css/fullcalendar.min.css' rel='stylesheet' />
<link href='resources/css/fullcalendar.print.min.css' rel='stylesheet'
	media='print' />



<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href='resources/fronts/glyphicons-halflings-regular.eot'
	rel='stylesheet' />

<link href='resources/css/style.css' rel='stylesheet' />

<!-- Pour la notation des sociétés -->
<script type="text/javascript"
	src="resources/js/notation_par_etoiles/ListeEtoile.js"></script>

<style>
body {
	background-image: url("resources/images/background_client.jpg");
	background-attachment: fixed;
	padding-top: 54px;
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

#titre-menu {
	font-family: "Serif (serif red), Times New Roman", Times;
	font-size: 30px;
}

.portfolio-item {
	margin-bottom: 30px;
	border: 10px solid #ffffff;
}

.pagination {
	margin-bottom: 30px;
}

.navbar li {
	font-size: 15px;
	width: 200px;
	margin-left: 30px;
}

.dropdown {
	margin-right: 50px;
}

footer {
	height: 100px;
	padding-top: 20px;
	font-size: 20px;
}
</style>

</head>
<body>
<c:if test="${user_id == null }">
	<c:set var="user_id" value="0"></c:set>
</c:if>


	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		style="height: 100px">
		<div class="container">
			<a class="navbar-brand" id="titre-menu" href="client/accueil">
				MelArtisans</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link"
						href="client/accueil"><span class="glyphicon glyphicon-home"></span>&nbsp;
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
						href="client/societe/listeParCategorie?categorieId=${categorie.id }"><c:out
								value="${categorie.libelle }"></c:out></a></li>
				</c:forEach>


				<li><a class="nav-link dropdown" href="client/accueil">Tous</a></li>
			</ul>
		</div>
		<!-- Fin menu déroulant -->

		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">


				

				<li class="nav-item coordonnees"><a class="nav-link"
					href="client/modifier-coordonnees?id=${user_id}">
						<span class="glyphicon glyphicon-edit"></span>&nbsp Mes
						coordonnées
				</a></li>

				<li class="nav-item"><a class="nav-link"
					href="client/disconnect"><span
						class="glyphicon glyphicon-log-out"></span>&nbsp Déconnexion</a></li>

				<li class="nav-item"><a class="nav-link"
					href="client/contact?contact=false&id=0&user_id=${user_id }"><span
						class="glyphicon glyphicon-envelope"></span>&nbsp Contacter
						MelArtisan</a></li>

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
	<div class="container">
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

	<!-- Bootstrap core JavaScript -->
	<script src='resources/js/moment.min.js'></script>
	<script src='resources/js/fullcalendar.min.js'></script>
	<script src='resources/js/locale-all.js'></script>
	<!-- 		<script src="resources/js/bootstrap.min.js"></script> -->

</body>
</html>