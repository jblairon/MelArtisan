<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link rel="stylesheet" href="resources/css/style_fenetre_modal.css"
	type="text/css" />

<style>
.image-entete {
	width: 100%;
}

.div-image-entete {
	width: 100%;
}


</style>

<!-- Pour la fenêtre modal des promotions -->
<div id="fond" style="margin-left: -500px; margin-top: -30px;"></div>

<!-- 	<script src="/resources/js/fenetre_modal.js" type="text/javascript"></script> -->
<div id="modal" class="popup" style="overflow-y: auto; height: 700px; ">

	<a href="artisan/promotion/creation-promotion?id=${societeId}">Nouvelle
		promotion</a><br>
	<c:forEach var="promo" items="${societe.promotions}">
		<img class="image-promo" alt="promo" src="<c:url value='/resources/images/societes/promotions/${promo.image }'/>"
		 style="width: 400px; float: right"/>
		 
		 <div class="promotion">
			<h1>- 
				<c:choose>
					<c:when test="${promo.tauxReduction > 0 }">
						<fmt:formatNumber  type="percent" pattern="##"  value="${promo.tauxReduction }"></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
						<c:out value="${promo.remise }" /> €
					</c:otherwise>
				</c:choose>
			 </h1>
		</div>
		 
		<fmt:parseDate value="${promo.dateDebut }" pattern="yyyy-MM-dd"
			var="parseDateDebut" type="both"></fmt:parseDate>
		<fmt:parseDate value="${promo.dateFin }" pattern="yyyy-MM-dd"
			var="parseDateFin" type="both"></fmt:parseDate>

		<h2 class="promotion-h2-h3 promotion-h2">
			Du
			<fmt:formatDate value="${parseDateDebut }" pattern="dd/MM/yyyy" />
			au
			<fmt:formatDate value="${parseDateFin }" pattern="dd/MM/yyyy" />
		</h2>
		<h3 class="promotion-h2-h3" style="margin-bottom: 50px;">
			<c:out value="${promo.description }"></c:out>
		</h3>

		<hr>
	</c:forEach>
	<div style="height: 200px;"></div>
</div>

<div id="contenu" class="container container-detail-client"
	style="background-color: #D0D5DC; margin-top: -50px; height: auto; padding-bottom: 300px;">



	<div class="div-image-entete">
		<img class="image-entete"
			src="<c:url value="/resources/images/societes/background/${societe.images[0].nom}"/>" />
	</div>



	<div class="div-titre-h1-detail">
		<h1 class="titre-h1-detail">
			<c:out value="${societe.nom }"></c:out>
		</h1>
	</div>
	<div>
		<input type="hidden" id="id" value="${societe.id }" />

		<div class="${divClass }">
			<h2>
				<c:out value="${messageOpenClose }"></c:out>
			</h2>
		</div>
		<c:if test="${messageVacances != null }">
			<div class="${divClassVacances } message-vacances" role="alert">
				<h2>
					<c:out value="${messageVacances}"></c:out>
				</h2>
			</div>
		</c:if>

		<div class="div-etoiles">
			<h3 class="titre-note">Note des utilisateurs</h3>
			<p class="note">
				<c:out value="${moyenne }/5 pour ${societe.notes.size() } avi(s)"></c:out>
			</p>


		</div>
		<div class="div-favoris">
			<a class="ajouter-aux-favoris"
				href="client/ajouter-aux-favoris?id=${societe.id }"> <img
				class="image-favoris"
				src="<c:url value="/resources/images/ajouter-aux-favoris-2.png" />"
				alt="ajouter aux favoris" title="Ajouter aux favoris" />Ajouter aux
				favoris
			</a><br> <a class="mes-favoris" href="client/mes-favoris"> <img
				class="image-favoris"
				src="<c:url value="/resources/images/mes-favoris.png" />"
				alt="mes favoris" title="Mes favoris" />Mes favoris

			</a><br>
			<div class="promotions">
				<a href="#" id="show">Promotions</a>
			</div>

		</div>
		<c:if test="${msgFavori !=null }">
			<div class="msgFavori">
				<h2>
					<c:out value="${msgFavori }"></c:out>
				</h2>
			</div>
		</c:if>

	</div>







	<div class="row">

		<div class="div-image-detail image-1">
			<img class="image-detail" alt="${societe.images[2].libelle }"
				src="<c:url value="/resources/images/societes/page/${societe.images[2].nom}"/>" />
		</div>
		<div class="description-detail">
			<h2 class="titre-detail-h2">
				Présentation de
				<c:out value="${societe.nom }"></c:out>
			</h2>
			<p>${societe.description }</p>

			<!-- ============================================== -->
			<!-- Affichage des métiers et de leurs prestations -->
			<!-- ============================================== -->

			<c:forEach var="m" items="${societe.metiers }">
				<div class="div-ul">
					<h4>
						<c:out value="${m.libelle }"></c:out>
					</h4>
					<ul>
						<c:forEach var="p" items="${m.prestations }">
							<li><c:out value="${p.libelle }"></c:out></li>
						</c:forEach>

					</ul>
				</div>

			</c:forEach>

		</div>
	</div>
	<div class="row">

		<div class="div-horaires">
			<h2 class="titre-detail h2">Horaires d'ouverture</h2>

			<!-- ============================================== -->
			<!-- Affichage des horaires -->
			<!-- ============================================== -->
			<ul class="page-detail">
				<c:forEach var="h" items="${societe.horaires}">
					<li><c:out value="${h.jour } "></c:out></li>
					<article>
						<c:choose>
							<c:when test="${h.amOpen != 'Fermé' && h.pmOpen != 'Fermé'}">
								<c:out value="${h.amOpen } - ${h.amClose } ----- "></c:out>
								<c:out value="${h.pmOpen } - ${h.pmClose }"></c:out>
							</c:when>
							
							<c:when test="${h.amOpen == 'Fermé' && h.pmOpen != 'Fermé'}">
								<c:out value="${h.amOpen } le matin--- "></c:out>
								<c:out value="${h.pmOpen } - ${h.pmClose }"></c:out>
							</c:when>
							
							<c:when test="${h.amOpen != 'Fermé' && h.pmOpen == 'Fermé'}">
								<c:out value="${h.amOpen } - ${h.amClose} ----- "></c:out>
								<c:out value="${h.pmOpen } l'après-midi"></c:out>
							</c:when>
							
							<c:otherwise>
								<c:out value="Fermé"></c:out>
							</c:otherwise>
						</c:choose>
						<br />
					</article>

				</c:forEach>
			</ul>

		</div>
		<div class="div-image-detail image-2">
			<img style="height: 500px;" class="image-detail img-2"
				alt="${societe.images[3].libelle }"
				src="<c:url value="/resources/images/societes/page/${societe.images[3].nom}"/>" />
			<p class="message-vacances sous-image">${messageVacances }</p>
		</div>

	</div>

	<div class="row ligne-3">
		<div class="div-image-detail image-3">
			<img class="image-detail" alt="${societe.images[4].libelle }"
				src="<c:url value="/resources/images/societes/page/${societe.images[4].nom}"/>" />
		</div>
		<div class="description-detail">
			<h2 class="titre-detail-h2">Informations générales</h2>
			<h3>Adresse</h3>
			<c:out value="${societe.nom }"></c:out>
			<c:out value="${societe.adresse.numero } ${societe.adresse.voie }"></c:out>
			<br />
			<c:out value="${societe.adresse.codePostal} ${societe.adresse.ville}"></c:out>
			<br />
			<h3>Contact</h3>
			<c:out value="${societe.contact.prenom } ${societe.contact.nom }"></c:out>
			<br /> Email :
			<c:out value="${societe.email }"></c:out>
			<br /> Tel :
			<c:out value="${societe.tel }"></c:out>
		</div>
	</div>

</div>

<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>

$(document).ready(function() {

	// Lorsque l'on clique sur show on affiche la fenêtre modale
	$('#show').click(function(e) {
		// On désactive le comportement du lien
		$("#contenu").addClass("fixer-container");
		e.preventDefault();
		showModal();
	});

	// Lorsque l'on clique sur le fond on cache la fenetre modale
	$('#fond').click(function() {
		hideModal();
		$("#contenu").removeClass("fixer-container");
	});

	// Lorsque l'on modifie la taille du navigateur la taille du fond change
	$(window).resize(function() {
		resizeModal()
	});

}); // fin doc ready

function showModal() {
	var id = '#modal';
// 	$(id).html('Voici ma fenetre modale<br/><a href="#" class="close">Fermer la fenetre</a>');
			
					

	// On definit la taille de la fenetre modale
	resizeModal();

	// Effet de transition
	$('#fond').fadeIn(1000);
	$('#fond').fadeTo("slow", 0.8);
	// Effet de transition
	$(id).fadeIn(2000);

	$('.popup .close').click(function(e) {
		// On désactive le comportement du lien
		e.preventDefault();
		// On cache la fenetre modale
		hideModal();
	});
}

function hideModal() {
	// On cache le fond et la fenêtre modale
	$('#fond, .popup').hide();
// 	$('.popup').html('');
}

function resizeModal(){
	   var modal = $('#modal');
	   // On récupère la largeur de l'écran et la hauteur de la page afin de
		// cacher la totalité de l'écran
	   var winH = $(window).height();
	   var winW = $(window).width();
	   
	   // le fond aura la taille de l'écran
// 	   $('#fond').css({'width':winW,'height':winH});
	   $('#fond').css({'width':winW,'height':3000});
	   
	   // On récupère la hauteur et la largeur de l'écran
	   var winH = $(window).height();
	   // On met la fenêtre modale au centre de l'écran
	   modal.css('top', winH/2 - modal.height()/2);
	   modal.css('left', winW/2 - modal.width()/2);
	}

</script>