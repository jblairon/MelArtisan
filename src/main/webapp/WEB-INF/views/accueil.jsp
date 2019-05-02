<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MelArtisan</title>


<meta name="description"
	content="Diaporama jQuery, Rotation 3D sur l'axe horizontal de l'image découpée en tranches, navigation par bouton latéraux ou barre de navigation" />
<meta name="Robots" content="all" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="author" content="Pedro Botelho for Codrops" />


<link rel="stylesheet" type="text/css" href="resources/css/demo.css" />
<link rel="stylesheet" type="text/css" href="resources/css/slicebox.css" />
<link href='resources/fronts/glyphicons-halflings-regular.eot'
	rel='stylesheet' />


<link rel="stylesheet" type="text/css" href="resources/css/custom.css" />
<script type="text/javascript"
	src="resources/js/modernizr.custom.46884.js"></script>

<link href='resources/css/style.css' rel='stylesheet' />

</head>
<body>
<body>
	<div class="container" style="margin-bottom: 200px;">

		<div class="wrapper">
			<ul id="sb-slider" class="sb-slider">
				<li><a href="http://www.flickr.com/photos/strupler/2969141180"
					target="_blank"><img src="resources/images/diaporama/1.jpg"
						alt="image1" /></a>
					<div class="sb-description">
						<h3>Commentaires image 1</h3>
					</div></li>
				<li><a href="http://www.flickr.com/photos/strupler/2968268187"
					target="_blank"><img src="resources/images/diaporama/2.jpg"
						alt="image2" /></a>
					<div class="sb-description">
						<h3>Commentaires image 2</h3>
					</div></li>
				<li><a href="http://www.flickr.com/photos/strupler/2968114825"
					target="_blank"><img src="resources/images/diaporama/3.jpg"
						alt="image3" /></a>
					<div class="sb-description">
						<h3>Commentaires image 3</h3>
					</div></li>
				<li><a href="http://www.flickr.com/photos/strupler/2968122059"
					target="_blank"><img src="resources/images/diaporama/4.jpg"
						alt="image4" /></a>
					<div class="sb-description">
						<h3>Commentaires image 4</h3>
					</div></li>
				<li><a href="http://www.flickr.com/photos/strupler/2969119944"
					target="_blank"><img src="resources/images/diaporama/5.jpg"
						alt="image5" /></a>
					<div class="sb-description">
						<h3>Commentaires image 5</h3>
					</div></li>
				<li><a href="http://www.flickr.com/photos/strupler/2968126177"
					target="_blank"><img src="resources/images/diaporama/6.jpg"
						alt="image6" /></a>
					<div class="sb-description">
						<h3>Commentaires image 6</h3>
					</div></li>
				<li><a href="http://www.flickr.com/photos/strupler/2968945158"
					target="_blank"><img src="resources/images/diaporama/7.jpg"
						alt="image7" /></a>
					<div class="sb-description">
						<h3>Commentaires image 7</h3>
					</div></li>
			</ul>

			<div id="shadow" class="shadow"></div>

			<div id="nav-arrows" class="nav-arrows">
				<a href="#">suivante</a> <a href="#">précédente</a>
			</div>

			<div id="nav-dots" class="nav-dots">
				<span class="nav-dot-current"></span> <span></span> <span></span> <span></span>
				<span></span> <span></span> <span></span>
			</div>
		</div>
		<!-- /wrapper -->





		<!-- Navigation -->

		<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
			<div class="logo">
				<a class="navbar-brand titre-menu col-lg-12" href="categories">MelArtisans</a>
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
				id="navbarResponsive" style="margin: auto">
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


	</div>


	<!-- Bootstrap core JavaScript -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>





	<!-- 	<script type="text/javascript" -->
	<!-- 		src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script> -->
	<script type="text/javascript" src="resources/js/jquery.slicebox.js"></script>
	<script type="text/javascript">
		$(function() {

			var Page = (function() {

				var $navArrows = $('#nav-arrows').hide(), $shadow = $('#shadow')
						.hide(), slicebox = $('#sb-slider').slicebox({
					onReady : function() {

						$navArrows.show();
						$shadow.show();

					},
					orientation : 'r',
					cuboidsRandom : true
				}),

				init = function() {

					initEvents();

				}, initEvents = function() {

					// add navigation events
					$navArrows.children(':first').on('click', function() {

						slicebox.next();
						return false;

					});

					$navArrows.children(':last').on('click', function() {

						slicebox.previous();
						return false;

					});

				};

				return {
					init : init
				};

			})();

			Page.init();

		});
	</script>



	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js">
		
	</script>


</body>



</html>
