<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>

<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<h2>${result}</h2>

<div class="container">

	<c:if test="${msg !=null }">
		<div class="alert alert-block alert-danger messageMail">
			<p>${msg }</p>
		</div>
	</c:if>

	<div class="row main ">
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
		<br /> <br />

		<div class="row col-lg-10 form-coordonnees">

			<form:form id="choixMetiers" method="post"
				action="admin/societe/validerMetiers"
				modelAttribute="choixMetiersForm">

				<div style="margin: auto">
					<div class="col-lg-offset-7">
						<p>Choix des métiers</p>
						<p>Categorie ${categorie.libelle }</p>

						<div id="ajouterNouveauMetier" class="btn btn-primary">
							Ajouter un nouveau métier</div>
					</div>

					<div class="form-group form-element categorie" style="margin-left: 200px;">
						<div>
							<form:label path="metiersId">Metiers </form:label>
							<div>
								<form:select path="metiersId"
									class="browser-default custom-select custom-select-lg mb-3 col-md-9">
									<c:forEach var="metier" items="${metiers }">
										<form:option id="metierId" value="${metier.id }">
											<c:out value="${metier.libelle }"></c:out>
										</form:option>
									</c:forEach>
								</form:select>
							</div>



							<div class="form-group nouvelleCategorie" id="nouveauMetier">
								<div>
									<form:label path="nouveauMetier">Ajouter un nouveau métier</form:label>
								</div>
								<div class="form-group mb-3" style="float: left">
									<form:input id="metier" path="nouveauMetier"
										class="form-control" />
								</div>

								<div class="input-group-append" style="margin-right: 30px">
									<button id="validerNouveauMetier" class="btn btn-primary "
										type="button">Ajouter</button>
								</div>
							</div>


							<div style="display: none">
								<div id="listeMetiers" class="nouveauMetier">
									<form:checkbox path="metiersForm" value="" id="formLibelle" />
									&nbsp<span id="spanLibelle"></span>
								</div>
							</div>

							<div id="nouveauxMetiers"></div>


							<form:hidden path="categorieId" value="${cat.id }" />

							<br />
							<br />
							<br />
							<div class="row form-submit">
								<input type="submit" value="Valider" class="btn btn-success" />
							</div>

						</div>
						<br />
					</div>

				</div>

			</form:form>
		</div>
	</div>
</div>




<script>
	$(document).ready(function() {

		$("#ajouterNouveauMetier").click(function() {
			$("#nouveauMetier").show();
			// 		alert("nouvelle");
		});

		$("#validerNouveauMetier").click(function() {
			var copie = $("#listeMetiers").clone();
			copie.find("#formLibelle").val($("#metier").val());
			copie.find("#spanLibelle").html($("#metier").val());
			copie.appendTo("#nouveauxMetiers");
		});

	});
</script>
