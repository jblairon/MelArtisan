<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport"
	content="width=device-width, initial-scale=1 shrink-to-fit=no ">
<meta charset="utf-8" />

<title>MelArtisans</title>

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

<style>
body {
	background-image: url("resources/images/background2.jpg");
	background-attachment: fixed;
	height: auto;
	margin-top: -50px;
	padding-top: 54px;
}

@media ( min-width : 992px) {
	body {
		/* 		padding-top: 56px; */
		
	}
}

.navbar {
	height: 70px;
	width: 100%;
	position: fixed;
	z-index: 1;
}

.logo {
 	padding-bottom: 30px; 
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
}

.pagination {
	margin-bottom: 30px;
}

.footer {
	margin-top: 50px;
}
</style>


</head>
<body>

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
		<div class="container logo">
			<a class="navbar-brand titre-menu" href="admin/accueil">
				LambersArtisans / Admin</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto ">
					<li class="nav-item active accueil"><a class="nav-link"
						href="admin/accueil"><span class="glyphicon glyphicon-home"></span>&nbsp;
							Accueil <span class="sr-only">(current)</span> </a></li>
				</ul>
			</div>
		</div>

		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item "><a class="nav-link"
					href="admin/liste-utilisateurs?page=1&max=15"> <span
						class="glyphicon glyphicon-user"></span> <span
						class="glyphicon glyphicon-user"></span>&nbsp Gestion utilisateurs
				</a></li>

				<li class="nav-item "><a class="nav-link"
					href="admin/societe/lister"> <span
						class="glyphicon glyphicon-signal"></span> <span
						class="glyphicon glyphicon-user"></span>&nbsp Gestion sociétés
				</a></li>

				<li class="nav-item "><a class="nav-link"
					href="admin/categorie/lister"> <span
						class="glyphicon glyphicon-user"></span> <span
						class="glyphicon glyphicon-list"></span>&nbsp Categories
				</a></li>

				<li class="nav-item "><a class="nav-link"
					href="admin/metier/lister"> <span
						class="glyphicon glyphicon-user"></span> <span
						class="glyphicon glyphicon-list"></span>&nbsp Métiers
				</a></li>

				<li class="nav-item"><a class="nav-link"
					href="client/disconnect"><span
						class="glyphicon glyphicon-log-out"></span>&nbsp Déconnexion</a></li>


			</ul>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">
		<decorator:body />
	</div>

	<!-- Footer -->
	<div class="footer">
		<footer class="bg-dark fixed-bottom">
			<div>
				<p class="text-center text-white copyright ">Copyright &copy;
					2019 : José Blairon</p>
			</div>
		</footer>
	</div>



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