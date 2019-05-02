<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta charset="utf-8" />
<title>Inscription</title>

<style>
legend {
	margin-left: -500px;
}
</style>

</head>
<body>


	<div class="container" style="margin-bottom: 200px; margin-top: 50px;">

		<c:if test="${msg !=null }">
			<div class="alert alert-block alert-danger messageMail">
				<p>${msg }</p>
			</div>
		</c:if>


		<div class="div-titre-h1" style="margin-left: 100px;">
			<h1 class="titre-h1">Modifier mes coordonnées</h1>
		</div>

		<form:form method="post" action="valider-coordonnees"
			modelAttribute="signup-form" class="col-lg-10">

			<form:hidden path="id" />

			<div class="col-lg-12"
				style="background-color: #B4C8D4; padding: 25px;">
				<div class="col-lg-6"
					style="float: left; margin-bottom: 50px; margin-left: 0px; margin-right: 0px;">

					<div class="form-control form-element modifier-coordonnees">
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


					<div class="form-control form-element modifier-coordonnees">
						<form:label path="prenom">Prénom :</form:label>
						<form:input path="prenom" class="form-control" />
					</div>

					<div class="form-control form-element modifier-coordonnees">
						<form:label path="nom">Nom :</form:label>
						<form:input path="nom" class="form-control" />
					</div>

					<div class="form-control form-element modifier-coordonnees">
						<form:label path="dateDeNaissance">Date de naissance (jj/mm/aaaa) :</form:label>
						<form:input path="dateDeNaissance" class="form-control" />
					</div>

					<div class="form-control form-element modifier-coordonnees">
						<form:label path="email">Email :</form:label>
						<form:input path="email" class="form-control" />
					</div>
					<c:if test="${msgMail !=null }">
						<div class="alert alert-block alert-danger">
							<p>${msgMail }</p>
						</div>
					</c:if>

					<div class="form-control form-element modifier-coordonnees">
						<form:label path="mdp">Mot de passe :</form:label>
						<form:password path="mdp" class="form-control" />
					</div>
				</div>


				<!-- ======================================= -->
				<!-- 	Adresse postale -->
				<!-- ======================================= -->
				<div class="col-lg-6">

					<fieldset>
						<legend class="titre-legend-signup">Adresse postale</legend>

						<div class="form-control form-element modifier-coordonnees">
							<form:label path="numero">Numéro de voie :</form:label>
							<form:input path="numero" class="form-control" />
						</div>

						<div class="form-control form-element modifier-coordonnees">
							<form:label path="complement">Complément du numéro (A, B, C, Bis, Ter, Quater) :</form:label>
							<form:input path="complement" class="form-control" />
						</div>

						<div class="form-control form-element modifier-coordonnees">
							<form:label path="voie">Type et nom de voie :</form:label>
							<form:input path="voie" class="form-control" />
						</div>
						<div class="form-control form-element modifier-coordonnees">
							<form:label path="codePostal">Ccode postal :</form:label>
							<form:input path="codePostal" class="form-control" />
						</div>
						<div class="form-control form-element modifier-coordonnees">
							<form:label path="ville">ville :</form:label>
							<form:input path="ville" class="form-control" />
						</div>
					</fieldset>
				</div>

				<div class="form-element modifier-coordonnees" style="clear: both;">
					<input type="submit" value="Valider" class="bouton14" />
				</div>
			</div>

		</form:form>
	</div>

	</div>

	<br />
	<br />
	<br />
	<br />

	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</body>
</html>
