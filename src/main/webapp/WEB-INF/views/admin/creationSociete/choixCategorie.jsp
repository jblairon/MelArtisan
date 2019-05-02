<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>

<style>
	fieldset {
		margin-left: 200px;
	}

</style>


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
					<div class="div-titre-h1  col-lg-offset-5">
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

			<form:form id="choixCategorie" method="post"
				action="admin/societe/validerCategorie"
				modelAttribute="choixCategorieForm">

				<div style="margin: auto">

					<div class="col-lg-offset-6">
						<p>Choix de la catégorie</p>

						<div id="ajouterNouvelleCategorie" class="btn btn-primary"
							style="margin: 20px">Ajouter une nouvelle catégorie</div>
					</div>

					<div class="form-group form-element categorie" style="margin-left: 200px;">
						<div >
							<form:label path="categorieId">Catégorie </form:label>
							<div>
								<form:select path="categorieId" class="custom-select col-lg-12">
									<c:forEach var="cat" items="${categories }">
										<form:option id="categorieId" value="${cat.id }">
											<c:out value="${cat.libelle }"></c:out>
										</form:option>
									</c:forEach>
								</form:select>
							</div>
							<br /> <br />

							<div class="nouvelleCategorie" style="float: right"
								id="nouvelleCategorie">
								<form:label path="nouvelleCategorie">Nom de la nouvelle catégorie</form:label>
								<form:input path="nouvelleCategorie" class="form-control" />
							</div>
						</div>

						<div class="form-submit">
							<input type="submit" value="Valider" class="btn btn-success" />
						</div>

						<br />
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
	$("#ajouterNouvelleCategorie").click(function() {
		$("#nouvelleCategorie").show();
		// 		alert("nouvelle");
	});
</script>


