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

		<div class="div-titre-h1">
			<h1 class="titre-h1">Création d'un métier</h1>
		</div>
		<br /> <br />

		<div class="row col-lg-10 form-coordonnees" style="padding-top: 50px">

			<form:form id="choixCategorie" method="post"
				action="admin/metier/sauvegarder-nouveau-metier"
				modelAttribute="metierForm">

				<div style="margin-left: 200px">

					<p>Choix du métier</p>

					<div class="form-group form-element">
						<div>
							<form:label path="metier">Métier </form:label>
							<form:input path="metier" class="form-control" />
						</div>
						<br />

						<!--  choix de la catégorie -->
						<div class="form-group">
							<form:label path="categorieId">Catégorie </form:label>
							<div>
								<form:select path="categorieId"
									class="browser-default custom-select custom-select-lg mb-3">
									<c:forEach var="cat" items="${categories }">
										<form:option id="categorieId" value="${cat.id }">
											<c:out value="${cat.libelle }"></c:out>
										</form:option>
									</c:forEach>
								</form:select>
							</div>
						</div>

						<!-- ************************************************* -->
						<!-- Pour les nouvelles prestations -->
						<!-- ************************************************* -->

						<!-- Input pour les nouvelles prestations -->
						<div>
							<label for="prestation"> Ajouter une prestation</label>
						</div>

						<div class="form-group mb-3 nouvellesPrestations"
							style="float: left">
							<input id="prestation" type="text" name="prestation"
								class="form-control" />
						</div>

						<div class="input-group-append" style="margin-right: 30px">
							<div id="nouvellePrestation" class="btn btn-primary "
								type="button">Ajouter</div>

						</div>
						<br />

					</div>

					<div id="nouvellesPrestations" style="margin-left: 100px"></div>
					<br />

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
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>

<script>
	$("#nouvellePrestation")
			.click(
					function() {
						$("#nouvellesPrestations")
								.append(
										"<label class='container-checkbox'>"
												+ $("#prestation").val()
												+ "<input  type='checkbox' name='prestations' value='"
												+ $("#prestation").val()
												+ "' checked='checked' />"
												+ "<span class='checkmark'></span></label>");

						chargeCss();
					});

	function chargeCss() {

		$("head").append($(document.createElement("link")).attr({
			rel : "stylesheet",
			type : "text/css",
			href : "resources/css/style_checkbox.css"
		}));
	}
</script>


