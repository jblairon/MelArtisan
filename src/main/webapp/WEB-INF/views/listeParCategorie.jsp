<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<style>
.img {
	width: 100px;
}

span {
	font-size: 13px;
}
</style>

<br />
<!-- <a href="test-data">Insérer des données de test</a> -->
<br />

<div class="container" style="height: auto; padding-bottom: 200px;">

	<div class="div-titre-h1" style="margin-left: 100px;">
		<h1 class="titre-h1">
			Sociétés de
			<c:out value="${categorie.libelle}"></c:out>
		</h1>
	</div>

	<input id="bouton-filtre" type="button" value="Filtrer votre recherche"
		class="bouton5">


	<!-- Pour filtrer la recherche -->
	<form:form id="choixMetiers" method="post"
		action="societe/filtrer-par-metiers-ville" modelAttribute="filtreMetiersVilleForm">
		<form:hidden path="categorieId" value="${ categorie.id}" />

		<div id="filtre" class="form-element col-lg-10"
			style="height: auto; display: none; margin-left: 15px; margin-bottom: 100px; border-radius: 50px;">
			<input id="bouton-masquer-filtre" type="button" value="Masquer"
				class="bouton6">
			<fieldset>
				<legend>Filtrer votre recherche</legend>
				<br><br>
				<h3>Métiers</h3>
				<ul class="list-unstyled list-inline">
					<c:forEach var="m" items="${metiers }">
						<li><form:label path="metiersId" class="checkbox inline"></form:label>
							<form:checkbox value="${m.id }" path="metiersId"></form:checkbox>
							<span><c:out value="  ${m.libelle}" /></span></li>
					</c:forEach>
				</ul><br><br>
				
				<h3>Villes</h3>
				<ul class="list-unstyled list-inline">
					<c:forEach var="v" items="${villes }">
						<li><form:label path="ville" class="checkbox inline"></form:label>
							<form:checkbox value="${v }" path="ville"></form:checkbox>
							<span><c:out value="  ${v}" /></span></li>
					
					</c:forEach>
				</ul>
				
			</fieldset>

			<div class="form-element modifier-coordonnees " style="border-radius: 50px;">
				<input type="submit" value="Valider" class="bouton14"
					style="width: 200px; margin-left: 200px;;" />
			</div>

		</div>
	</form:form>

	<div style="margin-top: 50px;">
		<c:forEach var="soc" items="${societes}" varStatus="st">

			<div class="col-sm-6 col-md-4 col-lg-3 portfolio-item"
				style="height: 400px; margin-left: 30px;">
				<div class="card h-100">
					<a href="societe/societe-detail?id=${soc.id }"><img
						class="card-img-top"
						src="<c:url value="/resources/images/societes/vignettes/${soc.images[1].nom }"/>"
						alt="${soc.nom}" /></a>
					<div class="card-body">
						<h5 class="card-title">${soc.nom}</h5>

						<ul class="list-unstyled list-inline"> <c:forEach var="met" items="${soc.metiers}">
								<li><c:out value="${met.libelle}" /></li>
							</c:forEach>
						</ul> <br />

					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$("#bouton-filtre").click(function() {
		$("#filtre").slideDown(2000);
		$("#bouton-filtre").hide(2000);
	});

	$("#bouton-masquer-filtre").click(function() {
		$("#filtre").slideUp(2000);
		$("#bouton-filtre").slideDown(2000);
	});
</script>