<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>

<!-- <meta charset="utf-8" /> -->
<!-- <title>Inscription</title> -->



<!-- 	<div class="container" style="margin-left: -50px; padding-left: 150px; padding-bottom: 500px;"> -->
<div style="margin-bottom: 1000px; width: 2000px">

	<c:if test="${msg !=null }">
		<div class="alert alert-block alert-danger messageMail">
			<p>${msg }</p>
		</div>
	</c:if>

	<div class="div-titre-h1" style="width: 60%; margin-left: 0px;">
		<h1 class="titre-h1">Créer un compte</h1>
	</div>

	<form:form method="post" action="validate-signup"
		modelAttribute="signup-form" class="col-lg-10">

		<div style="width: 35%; float: left; margin-left: 0px;">


			<div
				class="form-control form-element form-element-signup modifier-coordonnees">
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


			<div
				class="form-control form-element form-element-signup modifier-coordonnees">
				<form:label path="prenom">Prénom :</form:label>
				<form:input path="prenom" class="form-control" />
			</div>

			<div id="alert-prenom"
				class="alert alert-block alert-danger div-alert div-alert-signup">
				<h3>Erreur !!!</h3>
				<h4>Le prénom est obligatoire</h4>
			</div>



			<div
				class="form-control form-element form-element-signup modifier-coordonnees">
				<form:label path="nom">Nom :</form:label>
				<form:input path="nom" class="form-control" />
			</div>
			<div id="alert-nom"
				class="alert alert-block alert-danger div-alert div-alert-signup">
				<h3>Erreur !!!</h3>
				<h4>Le nom est obligatoire</h4>
			</div>



			<div
				class="form-control form-element form-element-signup modifier-coordonnees">
				<form:label path="dateDeNaissance">Date de naissance (jj/mm/aaaa) :</form:label>
				<form:input path="dateDeNaissance" class="form-control" />
			</div>
			<div id="alert-date-naissance"
				class="alert alert-block alert-danger div-alert div-alert-signup">
				<h3>Erreur !!!</h3>
				<h4>La date de naissance est obligatoire</h4>
				<p>Cette information nous est utile pour des statistiques</p>
			</div>



			<div
				class="form-control form-element form-element-signup modifier-coordonnees">
				<form:label path="email">Email :</form:label>
				<form:input path="email" class="form-control" />
			</div>
			<div id="alert-email-manquant"
				class="alert alert-block alert-danger div-alert div-alert-signup">
				<h3>Erreur !!!</h3>
				<h4>L'adresse email est obligatoire</h4>
			</div>
			<div id="alert-email-format"
				class="alert alert-block alert-danger div-alert div-alert-signup">
				<h3>Erreur !!!</h3>
				<h4>L'adresse email est obligatoire</h4>
			</div>




			<div
				class="form-control form-element form-element-signup modifier-coordonnees">
				<form:label path="mdp">Mot de passe :</form:label>
				<form:password path="mdp" class="form-control" />
			</div>
			<div id="alert-mdp"
				class="alert alert-block alert-danger div-alert div-alert-signup">
				<h3>Erreur !!!</h3>
				<h4>Le mot de passe est obligatoire</h4>
			</div>
		</div>


		<!-- ======================================= -->
		<!-- 	Adresse postale -->
		<!-- ======================================= -->
		<!-- 		<div style="width: 45%;"> -->

		<fieldset style="width: 35%;">
			<legend class="titre-legend-signup">Adresse postale</legend>

			<div
				class="form-control form-element form-element-signup modifier-coordonnees">
				<form:label path="numero">Numéro de voie :</form:label>
				<form:input path="numero" class="form-control" />
			</div>
			<div id="alert-numero"
				class="alert alert-block alert-danger div-alert div-alert-signup">
				<h3>Erreur !!!</h3>
				<h4>Le numéro est obligatoire</h4>
			</div>



			<div
				class="form-control form-element form-element-signup modifier-coordonnees">
				<form:label path="complement">Complément du numéro (A, B, C, Bis, Ter, Quater) :</form:label>
				<form:input path="complement" class="form-control" />
			</div>
			
			
			

			<div
				class="form-control form-element form-element-signup modifier-coordonnees">
				<form:label path="voie">Type et nom de voie :</form:label>
				<form:input path="voie" class="form-control" />
			</div>
			<div id="alert-voie"
				class="alert alert-block alert-danger div-alert div-alert-signup">
				<h3>Erreur !!!</h3>
				<h4> Cette information est obligatoire</h4>
			</div>
			
			
			
			<div
				class="form-control form-element form-element-signup modifier-coordonnees">
				<form:label path="codePostal">Ccode postal :</form:label>
				<form:input path="codePostal" class="form-control" />
			</div>
			<div id="alert-code-postal"
				class="alert alert-block alert-danger div-alert div-alert-signup">
				<h3>Erreur !!!</h3>
				<h4>Le code postal est obligatoire</h4>
			</div>
			
			
			
			<div
				class="form-control form-element form-element-signup modifier-coordonnees">
				<form:label path="ville">ville :</form:label>
				<form:input path="ville" class="form-control" />
			</div>
			<div id="alert-ville"
				class="alert alert-block alert-danger div-alert div-alert-signup">
				<h3>Erreur !!!</h3>
				<h4>La ville est obligatoire</h4>
			</div>
		</fieldset>
		<!-- 		</div> -->

		<div class=" col-lg-5 "
			style="clear: both; margin-left: 250px; margin-top: 100px; background-color:">
			<input type="submit" value="Valider" class="bouton14"
				style="height: 50px; font-size: 25px;" />
		</div>
	</form:form>

</div>


<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>