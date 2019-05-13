<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style>
fieldset {
	margin-left: 200px;
}

.ui-datepicker {
	font-size: 18px;
}
</style>


<h2>${result}</h2>

<div class="container" style="padding-bottom: 500px;">

	<c:if test="${msg !=null }">
		<div class="alert alert-block alert-danger messageMail">
			<p>${msg }</p>
		</div>
	</c:if>

	<div class="row main ">

		<div class="col-lg-12" style="margin: auto">

			<div class="div-titre-h1 div-titre-h1-artisan  col-lg-offset-5">
				<h1 class="titre-h1 titre-h1-artisan">Modification d'une promotion</h1>
			</div>

		</div>
		<br /> <br />

		<div class="row col-lg-10 " style="height: auto; margin: auto;">

			<form:form method="post"
				action="artisan/promotion/sauvegarder-promotion"
				modelAttribute="promotionForm">

				<form:hidden path="promotionId" />

				<div class="dates-vacances col-lg-12" style="padding-top: 50px; padding-bottom: 50px;">
					<div class="form-group col-lg-5 col-lg-offset-2">

						<div
							class="form-control form-element form-element-artisan-vacances"
							style="margin: 15px;">
							<form:label path="description">Description </form:label>
							<form:textarea path="description" cols="80" rows="6"
								class="form-control" />
						</div>

						<div
							class="form-control form-element form-element-artisan-vacances"
							style="margin: 15px;">
							<form:label path="remise">Remise en € </form:label>
							<form:input path="remise" class="form-control" />
						</div>

						<div
							class="form-control form-element form-element-artisan-vacances"
							style="margin: 15px;">
							<form:label path="tauxReduction">Taux de réduction en % </form:label>
							<form:input path="tauxReduction" class="form-control" />
						</div>
					</div>


					<div
						class="form-control form-element form-element-artisan-vacances"
						style="width: 345px; float: left;">
						<form:label path="dateDebut">Date début :</form:label>
						<form:input path="dateDebut" id="datepicker" class="form-control" />
					</div>



					<div
						class="form-control form-element form-element-artisan-vacances"
						style="width: 345px; float: left;">
						<form:label path="dateFin">Date fin :</form:label>
						<form:input path="dateFin" id="datepicker2" class="form-control" />
					</div>

					<div class=" col-lg-7 col-lg-offset-3">
						<input id="submit" type="submit" value="valider" class="bouton14"
							style="height: 40px; font-size: 20px; margin-top: 30px;" />
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
	$("#submit").click(function() {
		// 		alert("heure = " + $("#amOpenHeure").val());
	});

	// 	$(function() {
	// 		$("#datepicker").datepicker();
	// 	});

	$(function() {
		$("#datepicker").datepicker(
				{
					altField : "#datepicker",
					closeText : 'Fermer',
					prevText : 'Précédent',
					nextText : 'Suivant',
					currentText : 'Aujourd\'hui',
					monthNames : [ 'Janvier', 'Février', 'Mars', 'Avril',
							'Mai', 'Juin', 'Juillet', 'Août', 'Septembre',
							'Octobre', 'Novembre', 'Décembre' ],
					monthNamesShort : [ 'Janv.', 'Févr.', 'Mars', 'Avril',
							'Mai', 'Juin', 'Juil.', 'Août', 'Sept.', 'Oct.',
							'Nov.', 'Déc.' ],
					dayNames : [ 'Dimanche', 'Lundi', 'Mardi', 'Mercredi',
							'Jeudi', 'Vendredi', 'Samedi' ],
					dayNamesShort : [ 'Dim.', 'Lun.', 'Mar.', 'Mer.', 'Jeu.',
							'Ven.', 'Sam.' ],
					dayNamesMin : [ 'D', 'L', 'M', 'M', 'J', 'V', 'S' ],
					weekHeader : 'Sem.',
					dateFormat : 'dd/mm/yy'
				});
	});

	$(function() {
		$("#datepicker2").datepicker(
				{
					altField : "#datepicker2",
					closeText : 'Fermer',
					prevText : 'Précédent',
					nextText : 'Suivant',
					currentText : 'Aujourd\'hui',
					monthNames : [ 'Janvier', 'Février', 'Mars', 'Avril',
							'Mai', 'Juin', 'Juillet', 'Août', 'Septembre',
							'Octobre', 'Novembre', 'Décembre' ],
					monthNamesShort : [ 'Janv.', 'Févr.', 'Mars', 'Avril',
							'Mai', 'Juin', 'Juil.', 'Août', 'Sept.', 'Oct.',
							'Nov.', 'Déc.' ],
					dayNames : [ 'Dimanche', 'Lundi', 'Mardi', 'Mercredi',
							'Jeudi', 'Vendredi', 'Samedi' ],
					dayNamesShort : [ 'Dim.', 'Lun.', 'Mar.', 'Mer.', 'Jeu.',
							'Ven.', 'Sam.' ],
					dayNamesMin : [ 'D', 'L', 'M', 'M', 'J', 'V', 'S' ],
					weekHeader : 'Sem.',
					dateFormat : 'dd/mm/yy'
				});
	});

	// 	jQuery(function($) {
	// 		$.datepicker.regional['fr'] = {
	// 			closeText : 'Fermer',
	// 			prevText : '&#x3c;Préc',
	// 			nextText : 'Suiv&#x3e;',
	// 			currentText : 'Aujourd\'hui',
	// 			monthNames : [ 'Janvier', 'Fevrier', 'Mars', 'Avril', 'Mai',
	// 					'Juin', 'Juillet', 'Aout', 'Septembre', 'Octobre',
	// 					'Novembre', 'Decembre' ],
	// 			monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Avr', 'Mai', 'Jun',
	// 					'Jul', 'Aou', 'Sep', 'Oct', 'Nov', 'Dec' ],
	// 			dayNames : [ 'Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi',
	// 					'Vendredi', 'Samedi' ],
	// 			dayNamesShort : [ 'Dim', 'Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam' ],
	// 			dayNamesMin : [ 'Di', 'Lu', 'Ma', 'Me', 'Je', 'Ve', 'Sa' ],
	// 			weekHeader : 'Sm',
	// 			dateFormat : 'dd-mm-yy',
	// 			firstDay : 1,
	// 			isRTL : false,
	// 			showMonthAfterYear : false,
	// 			yearSuffix : '',
	// 			minDate : 0,
	// 			maxDate : '+12M +0D',
	// 			numberOfMonths : 2,
	// 			showButtonPanel : true
	// 		};
	// 		$.datepicker.setDefaults($.datepicker.regional['fr']);
	// 	});
</script>


