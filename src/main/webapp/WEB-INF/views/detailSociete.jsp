<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<style>
.image-entete {
	width: 100%;
}

.div-image-entete {
	width: 100%;
}
</style>



<div class="container"
	style="background-color: #D0D5DC; margin-top: -10px; padding-bottom: 200px;">

	<header>
		<div class="div-image-entete">
			<img class="image-entete"
				src="<c:url value="/resources/images/societes/background/${societe.images[0].nom}"/>" />
		</div>
	</header>

	<div class="div-titre-h1-detail">
		<h1 class="titre-h1-detail">
			<c:out value="${societe.nom }"></c:out>
		</h1>
	</div>

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



	<div class="row">

		<div class="div-image-detail">
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
			<h2 class="titre-detail h2">Horaires d'ouverture et de fermeture</h2>

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
			<img class="image-detail img-2" alt="${societe.images[3].libelle }"
				src="<c:url value="/resources/images/societes/page/${societe.images[3].nom}"/>" />
				<p class="message-vacances sous-image">${messageVacances }</p>
		</div>
	</div>

	<div class="row">
		<div class="div-image-detail image-3">
			<img class="image-detail img-3" alt="${societe.images[4].libelle }"
				src="<c:url value="/resources/images/societes/page/${societe.images[4].nom}"/>" />
		</div>
		<div class="description-detail" style="margin-left: 150px;">
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




