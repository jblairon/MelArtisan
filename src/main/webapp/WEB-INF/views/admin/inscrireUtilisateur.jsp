<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta charset="utf-8" />
<title>Inscription</title>


</head>
<body>


	<div class="container signup-admin">

		<c:if test="${msg !=null }">
			<div class="alert alert-block alert-danger messageMail">
				<p>${msg }</p>
			</div>
		</c:if>

		<!-- 		<div class="row main "> -->
		<div class="div-titre-h1">
			<h1 class="titre-h1">Créer un compte</h1>
		</div>

		<!-- 			<div class="row col-lg-9 form-coordonnees"> -->
		<form:form method="post" action="validate-signup"
			modelAttribute="signup-form" class="col-lg-10">

			<!-- 					<h3>Vos coordonnées</h3> -->

			<div class="form-control form-element form-element-admin">
				<form:label path="genre">Civilité </form:label>
				<div style="height: 50px;">
					<form:select path="genre"
						class="browser-default custom-select custom-select-lg mb-3 col-md-9">
						<form:option value="Monsieur">Monsieur</form:option>
						<form:option value="Madame">Madame</form:option>
						<form:option value="Mademoiselle">Mademoiselle</form:option>
					</form:select>
				</div>
			</div>


			<div class="form-control form-element form-element-admin">
				<form:label path="prenom">Prénom :</form:label>
				<form:input path="prenom" class="form-control" />
			</div>

			<div class="form-control form-element form-element-admin">
				<form:label path="nom">Nom :</form:label>
				<form:input path="nom" class="form-control" />
			</div>

			<div class="form-control form-element form-element-admin">
				<form:label path="dateDeNaissance">Date de naissance (jj/mm/aaaa) :</form:label>
				<form:input path="dateDeNaissance" class="form-control" />
			</div>

			<div class="form-control form-element form-element-admin">
				<form:label path="email">Email :</form:label>
				<form:input path="email" class="form-control" />
			</div>
			<c:if test="${msgMail !=null }">
				<div class="alert alert-block alert-danger">
					<p>${msgMail }</p>
				</div>
			</c:if>

			<div class="form-control form-element form-element-admin">
				<form:label path="mdp">Mot de passe :</form:label>
				<form:password path="mdp" class="form-control" />
			</div>

			<div>

				<fieldset>
					<legend class="titre-legend-signup">Adresse postale</legend>

					<div class="form-control form-element form-element-admin">
						<form:label path="numero">Numéro de voie :</form:label>
						<form:input path="numero" class="form-control" />
					</div>

					<div class="form-control form-element form-element-admin">
						<form:label path="complement">Complément du numéro (A, B, C, Bis, Ter, Quater) :</form:label>
						<form:input path="complement" class="form-control" />
					</div>

					<div class="form-control form-element form-element-admin">
						<form:label path="voie">Type et nom de voie :</form:label>
						<form:input path="voie" class="form-control" />
					</div>
					<div class="form-control form-element form-element-admin">
						<form:label path="codePostal">Ccode postal :</form:label>
						<form:input path="codePostal" class="form-control" />
					</div>
					<div class="form-control form-element form-element-admin">
						<form:label path="ville">ville :</form:label>
						<form:input path="ville" class="form-control" />
					</div>
				</fieldset>
			</div>

			<div class="form-element form-element-admin">
				<label for="admin" class="checkbox-profil">Admin</label>
				<form:checkbox path="admin" />

				<label for="artisan" class="checkbox-profil">Artisan</label>
				<form:checkbox path="artisan" id="artisan" />

				<label for="client" class="checkbox-profil">Client</label>
				<form:checkbox path="client" />
			</div>
			<br>
			<div id="artisan-select" class="form-element form-element-admin"
				style="display: none">
				<form:label path="societeId">Ma société </form:label>
				<div>
					<form:select path="societeId"
						class="browser-default custom-select custom-select-lg mb-3 col-md-9">
						<c:forEach var="societe" items="${societes }">
							<form:option id="societeId" value="${societe.id }">
								<c:out value="${societe.nom}"></c:out>
							</form:option>
						</c:forEach>
					</form:select>
				</div>
			</div>

			<div class="form-element form-element-admin">
				<input type="submit" value="S'inscrire" class="bouton14" />
			</div>

		</form:form>

	</div>


	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>


	<script>
		$("#artisan").click(function() {
			if ($("#artisan").prop("checked")) {
				$("#artisan-select").show();
			}
			else {
				$("#artisan-select").hide();
			}
		});
	</script>

</body>
</html>
