<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false" isELIgnored="false"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
	<style>
	
		fieldset {
			margin-left: 200px;
		}
	</style>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<script>
	$(document).ready(function() {
		$('#boutonCategorie').click(function(event) {
			var categorieId = $('#categorieId').val();

			var data = 'categorieId=' + encodeURIComponent(categorieId);

			$.ajax({
				url : $("#categorieForm").attr("action"),
				data : data,
				type : "GET",

				success : function(response) {
					alert(response);
				},
				error : function(xhr, status, error) {
					alert(xhr.responseText);
				}
			});
			return false;
		});
	});
</script>


<h2>${result}</h2>

<div class="container" style="margin-top: 50px;">


	<!-- pour l'affichage des erreurs -->
	<c:if test="${errors != null }">
		<div class="alert alert-danger">
			<spring:hasBindErrors name="login-form">
				<c:forEach var="err" items="${errors.allErrors}">
					<c:out value="${err.field}" /> :
			<c:out value="${err.defaultMessage}" />
					<br />
				</c:forEach>
			</spring:hasBindErrors>
		</div>
	</c:if>

	<div class="row main " style="margin: auto">

		<div class="col-lg-12" style="margin: auto">
			<c:choose>
				<c:when test="${societe!=null }">
					<div class="div-titre-h1">
						<h1 class="titre-h1">
							Société
							<c:out value="${societe.nom }" />
						</h1>
					</div>

				</c:when>
				<c:otherwise>
					<div class=" div-titre-h1">
						<h1 class=" titre-h1">
							Création d'une société
							<c:out value="${societe.nom }" />
						</h1>
					</div>
				</c:otherwise>

			</c:choose>
		</div>

		<div class="row col-lg-10 form-coordonnees">

			<form:form method="post" action="societe/sauvegarderSociete"
				modelAttribute="ajouterSocieteForm">
				<h2 class="titre-form-coordonnees col-lg-offset-4">
					Société de
					<c:out value="${categorie.libelle }" />
				</h2>

				<form:hidden path="categorieId" />

				<fieldset>
					<legend class="titre-legend">Choix des prestations</legend>

					<c:forEach var="metier" items="${metiers }">
						<div id="metier${metier.id }">
							<p>${metier.libelle }<span id="metierId${metier.id }"
									style="display: none"><c:out value="${metier.id }"></c:out></span>
								<span class="btn btn-outline-info btn-sm" id="ajouterPrestation"
									onClick="ajouterPrestation(${metier.id}, '${metier.libelle }')">
									Ajouter prestation</span>
							</p>
						</div>

						<!-- Affichage des checkboxes des prestations -->

						<ul class="list-unstyled ulForm">
							<c:forEach var="prestation" items="${metier.prestations }">
								<li><form:label path="prestationsId"
										class="checkbox inline"></form:label> <form:checkbox
										value="${prestation.id }" path="prestationsId"></form:checkbox>
									<span><c:out value="  ${prestation.libelle}" /></span></li>
							</c:forEach>





							<!-- ******************************************************************************* -->
							<!--           Début Traitement des nouvellles prestations -->
							<!-- ******************************************************************************* -->

							<li>
								<!--  div où sera inserées les nouvelles prestations par métier -->
								<div id="nouvellesPrestations${metier.id }"></div>
							</li>

						</ul>


						<!-- Input pour les nouvelles prestations -->
						<div class="input-group mb-3 nouvellePrestation"
							id="nouvellePrestation${metier.id }" style="float: left">
							<form:label path="nouvellePrestation"> Ajouter une nouvelle prestation</form:label>
							<form:input id="prestation${metier.id }"
								path="nouvellePrestation" class="form-control" />
							<div class="input-group-append">
								<div id="validerNouvellePrestation"
									class="btn btn-outline-secondary btn-primary" type="button"
									onClick="cloneChecbox(${metier.id}, '${metier.libelle }')">Ajouter</div>

							</div>
						</div>

						<!-- input contenant l'id du métier en question -->
						<div style="display: none">
							<form:checkbox path="metiersId" value="${metier.id }"
								id="metierId" checked="checked" />
							<span><c:out value="${metier.id }" /></span>
						</div>

					</c:forEach>
				</fieldset>
				<br />
				<br />

				<!-- ******************************************************************************* -->
				<!--           Fin Traitement des nouvellles prestations -->
				<!-- ******************************************************************************* -->

				<!-- ******************************************************************************* -->
				<!--           Début pour les images -->
				<!-- ******************************************************************************* -->

				<fieldset>
					<legend class="titre-legend">Pour les imagess</legend>

					<div class="form-control form-element creerSociete">
						<form:label path="imageBackground">Description de l'image d'arrière plan :</form:label>
						<form:input path="imageBackground" class="form-control" />

						<form:label path="imageVignette">Description de l'image vignette :</form:label>
						<form:input path="imageVignette" class="form-control" />

						<form:label path="image1">Description de l'image 1 :</form:label>
						<form:input path="image1" class="form-control" />

						<form:label path="image2">Description de l'image 2 :</form:label>
						<form:input path="image2" class="form-control" />

						<form:label path="image3">Description de l'image 3 :</form:label>
						<form:input path="image3" class="form-control" />

					</div>
				</fieldset>
				<br />

				<fieldset>
					<legend class="titre-legend">Informations personnelles</legend>

					<div class="form-control form-element creerSociete">
						<form:label path="nom">Nom de la société :</form:label>
						<form:input path="nom" class="form-control" />
					</div>

					<div class="form-control form-element creerSociete">
						<form:label path="description">Description :</form:label>
						<form:textarea path="description" class="form-control" />
					</div>

					<br />


					<div class="form-control form-element creerSociete">
						<form:label path="email">Email :</form:label>
						<form:input path="email" class="form-control" />
					</div>
					<c:if test="${msgMail !=null }">
						<div class="alert alert-block alert-danger">
							<p>${msgMail }</p>
						</div>
					</c:if>

					<div class="form-control form-element creerSociete">
						<form:label path="tel">Téléphone :</form:label>
						<form:input path="tel" class="form-control" />
					</div>

				</fieldset>
				<br />
				<br />
				
				
				<fieldset>
					<legend class="titre-legend">Adresse postale</legend>

					<div class="form-control form-element creerSociete">
						<form:label path="numero">Numéro de voie :</form:label>
						<form:input path="numero" class="form-control" />
					</div>

					<div class="form-control form-element creerSociete">
						<form:label path="complement">Complément du numéro (A, B, C, Bis, Ter, Quater) :</form:label>
						<form:input path="complement" class="form-control" />
					</div>

					<div class="form-control form-element creerSociete">
						<form:label path="voie">Type et nom de voie :</form:label>
						<form:input path="voie" class="form-control" />
					</div>
					<div class="form-control form-element creerSociete">
						<form:label path="codePostal">Ccode postal :</form:label>
						<form:input path="codePostal" class="form-control" />
					</div>
					<div class="form-control form-element creerSociete">
						<form:label path="ville">ville :</form:label>
						<form:input path="ville" class="form-control" />
					</div>
				</fieldset>

				<br />
				<br />

				<fieldset>
					<legend class="titre-legend">Contact</legend>

					<div class="form-control form-element creerSociete">
						<form:label path="prenomContact">Prénom du contact :</form:label>
						<form:input path="prenomContact" class="form-control" />
					</div>

					<div class="form-control form-element creerSociete">
						<form:label path="nomContact">Nom du contact :</form:label>
						<form:input path="nomContact" class="form-control" />
					</div>

					<div class="form-control form-element creerSociete">
						<form:label path="emailContact">Email du contact :</form:label>
						<form:input path="emailContact" class="form-control" />
					</div>

					<div class="form-control form-element creerSociete">
						<form:label path="telContact">Téléphone du contact :</form:label>
						<form:input path="telContact" class="form-control" />
					</div>

					<div class="form-control form-element creerSociete">
						<form:label path="fonctionContact">Fonction du contact :</form:label>
						<form:input path="fonctionContact" class="form-control" />
					</div>

				</fieldset>

				<div class="form-submit ">
					<input type="submit" value="Enregistrer"
						class="btn btn-lg btn-success form-control col-lg-offset-4" />
				</div>
			</form:form>
		</div>
	</div>
</div>


<script>

		function ajouterPrestation(metierId, metierLibelle){
			var prestationsId = "prestations"+metierId;
			var libelle = metierLibelle;
			$(this).removeClass("btn-outline-info").addClass("btn-success");
			$("#nouvellePrestation"+metierId).slideToggle("slow");
// 			alert("metierId = " + $("#metierId").val())
		};

		
		function cloneChecbox(metierId, metierLibelle){
			var cloneListePrestations = "#cloneListePrestations"+metierId;
// 			if($("#nouvellesPrestations"+metierId).html()===""){
			
				$("#nouvellesPrestations"+metierId).append("<input type='checkbox' name='prestations"+metierId+"' value='"
						+$("#prestation"+metierId).val()+"' id='cloneSpanLibelle"+metierId+"' checked='checked' />"+
				"&nbsp&nbsp<span class='spanCheckbox'>"+$("#prestation"+metierId).val()+"</span><br />");

			
			
			$("#nouvellePrestation"+metierId).slideUp("slow");

		};

</script>