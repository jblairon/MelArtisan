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

			<div class="div-titre-h1 div-titre-h1-artisan  col-lg-offset-5">
				<h1 class="titre-h1 titre-h1-artisan">Modifier les horaires</h1>
			</div>

		</div>
		<br /> <br />

		<div class="row col-lg-10 form-coordonnees artisan">
			<div class="col-lg-12" style="text-align: center">
				<h2>
					<c:out value="${jour }"></c:out>
				</h2>
			</div>

			<form:form method="post" action="artisan/societe/valider-horaires"
				modelAttribute="horairesForm">

				<form:hidden path="horaireId" />

				<div style="margin-left: 80px">
					<div style="float: left">
						<h3 style="text-align: center">Matin</h3>
						<br>
						<div class="col-lg-6" style="margin: auto; float: left">
							<p>Ouverture matin</p>

							<div class="form-group form-element " style="margin-left: 0px;">
								<div>
									<form:label path="amOpenHeure">Heure </form:label>
									<div>
										<form:select path="amOpenHeure"
											class="custom-select col-lg-12">
											<c:forEach var="i" begin="0" end="${heures.size()-13 }">
												<form:option id="amOpenHeure" value="${heures.get(i) }">
													<c:out value="${heures.get(i) }"></c:out>
												</form:option>
											</c:forEach>
											<form:option value="${heures.get(heures.size()-1) }">
												<c:out value="${heures.get(heures.size()-1) }"></c:out>
											</form:option>
										</form:select>
									</div>


								</div>
							</div>

							<div class="form-group form-element " style="margin-left: 0px;">
								<div>
									<form:label path="amOpenMinutes">Minutes </form:label>
									<div>
										<form:select path="amOpenMinutes"
											class="custom-select col-lg-12">
											<c:forEach var="i" begin="0" end="${minutes.size()-1 }">
												<form:option value="${minutes.get(i) }">
													<c:out value="${minutes.get(i) }"></c:out>
												</form:option>
											</c:forEach>
										</form:select>
									</div>
									<br /> <br />

								</div>
							</div>

							<br>


						</div>



						<!-- ============================================= -->
						<!-- 	Fermeture matin -->
						<!-- ============================================= -->

						<div class="col-lg-6" style="margin: auto; float: left">
							<p>Fermeture matin</p>

							<div class="form-group form-element " style="margin-left: 0px;">
								<div>
									<form:label path="amCloseHeure">Heure </form:label>
									<div>
										<form:select path="amCloseHeure"
											class="custom-select col-lg-12">
											<c:forEach var="i" begin="0" end="${heures.size()-13 }">
												<form:option value="${heures.get(i) }">
													<c:out value="${heures.get(i) }"></c:out>
												</form:option>
											</c:forEach>
											<form:option value="${heures.get(heures.size()-1) }">
												<c:out value="${heures.get(heures.size()-1) }"></c:out>
											</form:option>
										</form:select>
									</div>


								</div>
							</div>

							<div class="form-group form-element " style="margin-left: 0px;">
								<div>
									<form:label path="amCloseMinutes">Minutes </form:label>
									<div>
										<form:select path="amCloseMinutes"
											class="custom-select col-lg-12">
											<c:forEach var="i" begin="0" end="${minutes.size()-1 }">
												<form:option value="${minutes.get(i) }">
													<c:out value="${minutes.get(i) }"></c:out>
												</form:option>
											</c:forEach>
										</form:select>
									</div>
									<br /> <br />

								</div>
							</div>

							<br>

						</div>
					</div>

					<!-- ============================================= -->
					<!-- 	Ouverture après-midi-->
					<!-- ============================================= -->

					<div style="float: left; margin-left: 100px">
						<h3 style="text-align: center">Après-midi</h3>
						<br>
						<div class="col-lg-6" style="margin: auto; float: left">
							<p>Ouverture après-midi</p>

							<div class="form-group form-element " style="margin-left: 0px;">
								<div>
									<form:label path="pmOpenHeure">Heure </form:label>
									<div>
										<form:select path="pmOpenHeure"
											class="custom-select col-lg-12">
											<c:forEach var="i" begin="12" end="${heures.size()-1 }">
												<form:option value="${heures.get(i) }">
													<c:out value="${heures.get(i) }"></c:out>
												</form:option>
											</c:forEach>
										</form:select>
									</div>


								</div>
							</div>

							<div class="form-group form-element " style="margin-left: 0px;">
								<div>
									<form:label path="pmOpenMinutes">Minutes </form:label>
									<div>
										<form:select path="pmOpenMinutes"
											class="custom-select col-lg-12">
											<c:forEach var="i" begin="0" end="${minutes.size()-1 }">
												<form:option value="${minutes.get(i) }">
													<c:out value="${minutes.get(i) }"></c:out>
												</form:option>
											</c:forEach>
										</form:select>
									</div>
									<br /> <br />

								</div>
							</div>

							<br>


						</div>



						<!-- ============================================= -->
						<!-- 	Fermeture après-midi -->
						<!-- ============================================= -->

						<div class="col-lg-6" style="margin: auto; float: left">
							<p>Fermeture après-midi</p>

							<div class="form-group form-element " style="margin-left: 0px;">
								<div>
									<form:label path="pmCloseHeure">Heure </form:label>
									<div>
										<form:select path="pmCloseHeure"
											class="custom-select col-lg-12">
											<c:forEach var="i" begin="12" end="${heures.size()-1 }">
												<form:option value="${heures.get(i) }">
													<c:out value="${heures.get(i) }"></c:out>
												</form:option>
											</c:forEach>
										</form:select>
									</div>


								</div>
							</div>

							<div class="form-group form-element " style="margin-left: 0px;">
								<div>
									<form:label path="pmCloseMinutes">Minutes </form:label>
									<div>
										<form:select path="pmCloseMinutes"
											class="custom-select col-lg-12">
											<c:forEach var="i" begin="0" end="${minutes.size()-1 }">
												<form:option value="${minutes.get(i) }">
													<c:out value="${minutes.get(i) }"></c:out>
												</form:option>
											</c:forEach>
										</form:select>
									</div>
									<br /> <br />

								</div>
							</div>

							<br>

						</div>
					</div>
				</div>

				<div class=" col-lg-7 col-lg-offset-4">
					<input id="submit" type="submit" value="valider" class="bouton14"
						style="height: 40px; font-size: 20px; margin-top: 30px;" />
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
</script>


