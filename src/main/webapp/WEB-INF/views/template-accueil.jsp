

<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<!DOCTYPE html>
<html>
<head>


<base
	href="<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/"%>" />

<!-- <link href="resources/css/bootstrap.min.css" rel="stylesheet" -->
<!-- 	id="bootstrap-css" /> -->

<!-- <link href='resources/css/style.css' rel='stylesheet' /> -->



<!-- body { -->
<!-- 	background-image: url("resources/images/background_liste_front.jpg"); -->
<!-- 	padding-top: 54px; -->
<!-- 	background-attachment: fixed; -->
<!-- } -->

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

				<li><a class="nav-link dropdown"
					href="evenements?tag=exposition">Expositions</a></li>

				<li><a class="nav-link dropdown" href="evenements?tag=concert">Concerts</a></li>

				<li class="nav-item dropdown"><a class="nav-link"
					href="evenements?tag=SPECTACLE">Spectacles</a></li>

				<li><a class="nav-link dropdown" href="evenements?tag=sport">Sport</a></li>

				<li><a class="nav-link dropdown" href="evenements?tag=loisirs">Loisirs</a></li>

				<li><a class="nav-link dropdown" href="evenements">Tous</a></li>
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
						class="glyphicon glyphicon-user"></span>&nbsp Login</a></li>

				<li class="nav-item"><a class="nav-link" href="signup"> <span
						class="glyphicon glyphicon-pencil"></span>&nbsp S'inscrire
				</a></li>

				<li class="nav-item"><a class="nav-link"
					href="authenticate?contact=true"> <span
						class="glyphicon glyphicon-envelope"></span>&nbsp Contact
				</a></li>
			</ul>
		</div>
	</nav>
	

	<!-- Page Content -->
	<div class="container" style="width: 75%; margin: auto; height: auto; ">
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