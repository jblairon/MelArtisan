<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>

<h2>${result}</h2>

<div class="container">

	<c:if test="${msg !=null }">
		<div class="alert alert-block alert-danger messageMail">
			<p>${msg }</p>
		</div>
	</c:if>

	<div class="row main ">

		<div class="col-lg-12" style="margin: auto">

			<div class="div-titre-h1">
				<h1 class="titre-h1">Création d'une catégorie</h1>
			</div>

		</div>
		<br /> <br />

		<div class="row col-lg-10 form-coordonnees" style="padding-top: 50px">

			<form:form id="choixCategorie" method="post"
				action="admin/categorie/sauvegarder-nouvelle-categorie"
				modelAttribute="categorieForm">

				<div style="margin-left: 200px">

					<p>Choix de la catégorie</p>

					<div class="form-group form-element">
						<div>
							<form:label path="categorie">Catégorie </form:label>
							<form:input path="categorie" class="form-control" />
						</div>
						<br />

						<!-- ************************************************* -->
						<!-- Pour les nouveaux métiers -->
						<!-- ************************************************* -->

						<!-- Input pour les nouveaux métiers -->
						<div>
							<label for=="metier"> Ajouter un métier</label>
						</div>

						<div class="form-group  nouveauxMetiers"
							style="float: left">
							<input id="metier" type="text" name="metier" class="form-control" />
						</div>

						<div class="input-group-append" style="margin-right: 30px">
							<div id="nouveauMetier"
								class="btn btn-primary form-group"
								type="button">Ajouter</div>

						</div>

					</div>

					<div id="nouveauxMetiers"></div>


					<div class="form-submit">
						<input type="submit" value="Valider"
							class="btn btn-lg btn-success" />
					</div>

				</div>

			</form:form>
		</div>
	</div>
</div>

<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<script>
	$("#nouveauMetier").click(
			function() {
				$("#nouveauxMetiers").append(
						"<div class=''><input type='checkbox' name='metiers' value='"
								+ $("#metier").val()
								+ "' id='metier' checked='checked' />"
								+ "&nbsp&nbsp<span class='spanCheckbox'>"
								+ $("#metier").val() + "</span><br /></div>");
			})
</script>


