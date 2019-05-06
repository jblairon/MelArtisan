<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>
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
<link href='resources/fronts/glyphicons-halflings-regular.eot'
	rel='stylesheet' />

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<style>


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
	margin-bottom: 30px;
	border: 10px solid #ffffff;
}

.pagination {
	margin-bottom: 30px;
}
</style>

</head>

<body id="body-artisan">

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container logo">
			<a class="navbar-brand titre-menu"
				href="artisan/ma-societe?id=${societe.id }">MelArtisans +
				${societe.id }</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active accueil"><a class="nav-link"
						href="artisan/ma-societe?id=${societe.id }"><span
							class="glyphicon glyphicon-home"></span>&nbsp; Accueil + <c:out
								value="${societe.id }" /> <span class="sr-only">(current)</span>
					</a></li>
				</ul>
			</div>
		</div>


		<div class="collapse navbar-collapse coordonnees"
			id="navbarResponsive">
			<ul class="navbar-nav ml-auto">

				<li class="nav-item" style="width: 200px"><a class="nav-link"
					href="artisan/societe/mes-horaires?id=${societe.id }"><span
						class="glyphicon glyphicon-time"></span>&nbsp Modifier mes
						horaires</a></li>

				<li class="nav-item" style="width: 200px"><a class="nav-link"
					href="artisan/societe/mes-prochaines-vacances?id=${societe.id }"><span
						class="glyphicon glyphicon-calendar"></span>&nbsp Mes prochaines +
						${societe.id } vacances</a></li>

				<li class="nav-item"><a class="nav-link"
					href="artisan/disconnect"><span
						class="glyphicon glyphicon-log-out"></span>&nbsp Déconnexion</a></li>


				<li class="nav-item"><a class="nav-link"
					href="artisan/contact?contact=false&id=0&user_id=${societe.id }"><span
						class="glyphicon glyphicon-envelope"></span>&nbsp Contacter
						MelArtisan</a></li>
			</ul>
		</div>
	</nav>


	<!-- Page Content -->
	<div class="container" style="width: 75%; margin: auto; height: auto;">
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
	<!-- 	<script -->
	<!-- 		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->



	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js">
		
	</script>



</body>

</html>