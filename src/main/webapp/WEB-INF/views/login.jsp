<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>



<div class="container">

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

	<c:if test="${messageSessionExpiree != null }">
		<div id="messageSessionExpiree"
			class="alert alert-block alert-danger col-lg-10 col-lg-offset-1"
			style="text-align: center; display: none">
			<h1>
				<c:out value="${messageSessionExpiree }"></c:out>
			</h1>
			<h2>Merci de vous reconnecter</h2>
		</div>
		<br>
		<br>
		<br>
		<br>
		<br>
	</c:if>

	<div>
		<div class="div-titre-h1"
			style="margin-top: 100px; margin-bottom: 100px;">
			<h1 class="titre-h1">Authentification</h1>
		</div>

		<div class="form-coordonnees"
			style="padding-left: 250px; background-color: #ffffff; margin-bottom: 500px;">
			<form:form method="post" action="check-login"
				modelAttribute="login-form">

				<p>Identifiez-vous</p>


				<div class="form-control form-element login">
					<form:label path="username" class="form-label">Email :</form:label>
					<form:input path="username" class="form-control" id="email" />
				</div>
				<div class="alert alert-block alert-danger alert_mail div-alert"
					style="display: none">
					<h4>Erreur !</h4>
					Vous devez entrer votre adresse mail !
				</div>
				<div class="alert alert-block alert-danger alert_mail2 div-alert"
					style="display: none">
					<h4>Erreur !</h4>
					Votre adresse mail est incorrecte!
				</div>

				<div class="form-control form-element login">
					<form:label path="mdp">Mot de passe :</form:label>
					<form:password path="mdp" class="form-control" id="mdp" />
				</div>
				<div class="alert alert-block alert-danger alert-mdp div-alert"
					style="display: none">
					<h4>Erreur !</h4>
					Vous devez entrer au moins 4 caractères !
				</div>

				<form:hidden path="contact" />

				<div class="form-element form-login" style="clear: both;">
					<input type="submit" value="Valider" class="bouton14" />
				</div>
			</form:form>

			<div>${msg}</div>
		</div>
	</div>
</div>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$('#email').focus();

		$(function() {
			$("form").on("submit", function() {
				if ($("#email").val().length < 1) {
					$("div.form-group.mail").addClass("has-error");
					$("div.alert_mail").show("slow").delay(6000).hide("slow");
					return false;
				} else {
					$("div.form-group.mail").addClass("has-success");
				}
			});
		});

		$(function() {
			$("form").on("submit", function() {
				if (!(isEmail($("#email").val()))) {
					$("div.form-group.mail").addClass("has-error");
					$("div.alert_mail2").show("slow").delay(6000).hide("slow");
					return false;
				} else {
					$("div.form-group.mail").addClass("has-success");
				}
			});
		});

		$(function() {
			$("form").on("submit", function() {
				if ($("#mdp").val().length < 4) {
					$("div.form-group.mdp").addClass("has-error");
					$("div.alert-mdp").show("slow").delay(6000).hide("slow");
					return false;
				} else {
					$("div.form-group.mdp").addClass("has-success");
				}
			});
		});

		$(function() {
			$("#messageSessionExpiree").show("slow");

		});

	}); // Fin doc ready

	//contrôle la forme d'une adresse mail
	function isEmail(myVar) {
		// La 1ère étape consiste à définir l'expression régulière d'une adresse email
		var regEmail = new RegExp(
				'^[0-9a-z._-]+@{1}[0-9a-z.-]{2,}[.]{1}[a-z]{2,5}$', 'i');

		return regEmail.test(myVar);
	}
</script>
