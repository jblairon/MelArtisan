<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>

<!-- <meta charset="utf-8" /> -->
<!-- <title>Inscription</title> -->




<div style="margin-left: -20px; margin-bottom: 1000px; width: 2000px">

	<c:if test="${msg !=null }">
		<div class="alert alert-block alert-danger messageMail">
			<p>${msg }</p>
		</div>
	</c:if>
	

	

	<div class="div-titre-h1" style="width: 60%; margin-left: 0px;">
		<h1 class="titre-h1">Créer un compte</h1>
	</div>
	
	<c:if test="${msgMail !=null }">
		<div class="alert alert-block alert-warning messageMail">
			<h1>${msgMail }</h1>
		</div>
	</c:if>
	

	<form:form method="post" action="validate-signup"
		modelAttribute="signup-form" class="col-lg-10">

		<div style="width: 35%; float: left; margin-left: 0px;">


			<div
				class="form-control form-element form-element-signup modifier-coordonnees">
				<form:label path="genre">Civilité </form:label>
				<div style="height: 50px;">
					<form:select path="genre"
						class="browser-default custom-select custom-select-lg mb-3 col-md-9">
						<form:option class="items" value="Monsieur">Monsieur</form:option>
						<form:option class="items" value="Madame">Madame</form:option>
						<form:option class="items" value="Mademoiselle">Mademoiselle</form:option>
					</form:select>
				</div>
			</div>


			<div
				class="form-control form-element form-element-signup modifier-coordonnees prenom">
				<form:label path="prenom">Prénom :</form:label>
				<form:input id="prenom" path="prenom" class="form-control" />
			</div>

			<div id="alert-prenom"
				class="alert alert-block alert-danger div-alert div-alert-signup prenom">
				<h3>Erreur !!!</h3>
				<h4>Le prénom est obligatoire</h4>
			</div>



			<div
				class="form-control form-element form-element-signup modifier-coordonnees nom">
				<form:label path="nom">Nom :</form:label>
				<form:input id="nom" path="nom" class="form-control" />
			</div>
			<div id="alert-nom"
				class="alert alert-block alert-danger div-alert div-alert-signup">
				<h3>Erreur !!!</h3>
				<h4>Le nom est obligatoire</h4>
			</div>



			<div
				class="form-control form-element form-element-signup modifier-coordonnees date-naissance">
				<form:label path="dateDeNaissance">Date de naissance (jj/mm/aaaa) :</form:label>
				<form:input id="date-naissance" path="dateDeNaissance" class="form-control" placeholder="01/01/1970"/>
			</div>
			<div id="alert-date-naissance"
				class="alert alert-block alert-danger div-alert div-alert-signup">
				<h3>Erreur !!!</h3>
				<h4>La date de naissance est obligatoire</h4>
				<p>Cette information nous est utile pour des statistiques</p>
			</div>
			<div id="alert-date-format"
				class="alert alert-block alert-danger div-alert div-alert-signup">
				<h3>Erreur !!!</h3>
				<h4>Le format de la date est incorrect</h4>
				<p>Respectez le format "jj/mm/aaa"</p>
			</div>



			<div
				class="form-control form-element form-element-signup modifier-coordonnees email">
				<form:label  path="email">Email :</form:label>
				<form:input id="email" path="email" class="form-control" />
			</div>
			<div id="alert-email-manquant"
				class="alert alert-block alert-danger div-alert div-alert-signup">
				<h3>Erreur !!!</h3>
				<h4>L'adresse email est obligatoire</h4>
			</div>
			<div id="alert-email-format"
				class="alert alert-block alert-danger div-alert div-alert-signup">
				<h3>Erreur !!!</h3>
				<h4>Le format de l'adresse email est incorrect</h4>
			</div>




			<div
				class="form-control form-element form-element-signup modifier-coordonnees mdp">
				<form:label path="mdp">Mot de passe :</form:label>
				<form:password id="mdp" path="mdp" class="form-control" />
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


		<fieldset style="width: 35%;">
			<legend class="titre-legend-signup">Adresse postale</legend>

			<div
				class="form-control form-element form-element-signup modifier-coordonnees numero">
				<form:label path="numero">Numéro de voie :</form:label>
				<form:input id="numero" path="numero" class="form-control" />
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
				class="form-control form-element form-element-signup modifier-coordonnees voie">
				<form:label path="voie">Type et nom de voie :</form:label>
				<form:input id="voie" path="voie" class="form-control" />
			</div>
			<div id="alert-voie"
				class="alert alert-block alert-danger div-alert div-alert-signup">
				<h3>Erreur !!!</h3>
				<h4> Cette information est obligatoire</h4>
			</div>
			
			
			
			<div
				class="form-control form-element form-element-signup modifier-coordonnees code-postal">
				<form:label  path="codePostal">Ccode postal :</form:label>
				<form:input id="code-postal" path="codePostal" class="form-control" />
			</div>
			<div id="alert-code-postal"
				class="alert alert-block alert-danger div-alert div-alert-signup">
				<h3>Erreur !!!</h3>
				<h4>Le code postal est obligatoire</h4>
			</div>
			
			
			
			<div
				class="form-control form-element form-element-signup modifier-coordonnees ville">
				<form:label path="ville">ville :</form:label>
				<form:input id="ville" path="ville" class="form-control" />
			</div>
			<div id="alert-ville"
				class="alert alert-block alert-danger div-alert div-alert-signup">
				<h3>Erreur !!!</h3>
				<h4>La ville est obligatoire</h4>
			</div>
		</fieldset>
		<!-- 		</div> -->

		<div class=" col-lg-5 "
			style="clear: both; margin-left: 200px; margin-top: 100px; background-color:">
			<input type="submit" value="Valider" class="bouton14"
				style="height: 50px; font-size: 25px;" />
		</div>
	</form:form>

</div>


<!-- <script -->
<!-- 	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script> -->
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>

	$(document).ready(function(){
		
		$(function() {
			$("form").on("submit", function() {
				if ($("#prenom").val().length < 2) {
					$("div.prenom").addClass("has-error");
					$("#alert-prenom").show("slow").delay(6000).hide("slow");
					return false;
				} else {
					$("div.prenom").addClass("has-success");
				}
			});
		});
		
		$(function() {
			$("form").on("submit", function() {
				if ($("#nom").val().length < 2) {
					$("div.nom").addClass("has-error");
					$("#alert-nom").show("slow").delay(6000).hide("slow");
					return false;
				} else {
					$("div.nom").addClass("has-success");
				}
			});
		});
		
		$(function() {
			$("form").on("submit", function() {
				if ($("#date-naissance").val().length < 2) {
					$("div.date-naissance").addClass("has-error");
					$("#alert-date-naissance").show("slow").delay(6000).hide("slow");
					return false;
				} else {
					$("div.date-naissance").addClass("has-success");
				}
			});
		});
		
		$(function() {
			$("form").on("submit", function() {
				if (!(isDate($("#date-naissance").val()))) {
					$("div.date-naissance").addClass("has-error");
					$("#alert-date-format").show("slow").delay(6000).hide("slow");
					return false;
				} else {
					$("div.date-naissance").addClass("has-success");
				}
			});
		});
		
		$(function() {
			$("form").on("submit", function() {
				if ($("#email").val().length < 2) {
					$("div.email").addClass("has-error");
					$("#alert-email-manquant").show("slow").delay(6000).hide("slow");
					return false;
				} else {
					$("div.form-element.email").addClass("has-success");
				}
			});
		});
		
		
		$(function() {
			$("form").on("submit", function() {
				if (!(isEmail($("#email").val()))) {
					$("div.email").addClass("has-error");
					$("#alert-email-format").show("slow").delay(6000).hide("slow");
					return false;
				} else {
					$("div.form-element.email").addClass("has-success");
				}
			});
		});
		
		$(function() {
			$("form").on("submit", function() {
				if ($("#mdp").val().length < 2) {
					$("div.mdp").addClass("has-error");
					$("#alert-mdp").show("slow").delay(6000).hide("slow");
					return false;
				} else {
					$("div.mdp").addClass("has-success");
				}
			});
		});
		
		$(function() {
			$("form").on("submit", function() {
				if ($("#numero").val().length < 1) {
					$("div.numero").addClass("has-error");
					$("#alert-numero").show("slow").delay(6000).hide("slow");
					return false;
				} else {
					$("div.numero").addClass("has-success");
				}
			});
		});
		
		$(function() {
			$("form").on("submit", function() {
				if ($("#voie").val().length < 1) {
					$("div.voie").addClass("has-error");
					$("#alert-voie").show("slow").delay(6000).hide("slow");
					return false;
				} else {
					$("div.voie").addClass("has-success");
				}
			});
		});
		
		$(function() {
			$("form").on("submit", function() {
				if ($("#code-postal").val().length < 1) {
					$("div.code-postal").addClass("has-error");
					$("#alert-code-postal").show("slow").delay(6000).hide("slow");
					return false;
				} else {
					$("div.code-postal").addClass("has-success");
				}
			});
		});
		
		$(function() {
			$("form").on("submit", function() {
				if ($("#ville").val().length < 1) {
					$("div.ville").addClass("has-error");
					$("#alert-ville").show("slow").delay(6000).hide("slow");
					return false;
				} else {
					$("div.ville").addClass("has-success");
				}
			});
		});
		
		$("#date-naissance").keyup(function(){
			var date = $("#date-naissance").val();
			if($("#date-naissance").val().length === 2 || $("#date-naissance").val().length === 5){
				$("#date-naissance").val(date + "/");
			}

		});
		
		
	}); // Fin doc ready
	
	function isDate(date){
		// La 1ère étape consiste à définir l'expression régulière d'une datel
		var regDate = new RegExp(
				/^\d{2}\/\d{2}\/\d{4}$/);

		return regDate.test(date);
	}

	//contrôle la forme d'une adresse mail
	function isEmail(myVar) {
		// La 1ère étape consiste à définir l'expression régulière d'une adresse email
		var regEmail = new RegExp(
				'^[0-9a-z._-]+@{1}[0-9a-z.-]{2,}[.]{1}[a-z]{2,5}$', 'i');

		return regEmail.test(myVar);
	}

</script>