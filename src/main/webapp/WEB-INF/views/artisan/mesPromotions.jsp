<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>

<!-- <div class="container" -->
<!-- 	style="background-color: #D8ECD8; height: 800px; background-attachment: fixed; margin-top: 100px;"> -->

	<div class="div-titre-h1 div-titre-h1-artisan">
		<h1 class="titre-h1 titre-h1-artisan">Promotions pour <c:out value="${societe.nom }"></c:out></h1>
	</div>

	<div class="col-lg-12 " style="margin-top: 100px; background-color: #D8ECD8;">

		<div class="table-responsive ">
			<table id="mytable" class="table table-hover ">
				<tbody>
					<tr>
						<th scope="col" class="col-lg-2">Date début</th>
						<th scope="col" class="col-lg-2">Date fin</th>
						<th scope="col">Description de la promotion</th>
						<th scope="col">Remise</th>
						<th scope="col">Taux de réduction</th>
						<th scope="col">Modifier</th>
						<th scope="col">Supprimer</th>
					</tr>
					<c:forEach var="p" items="${promotions}">
						<fmt:parseDate value="${p.dateDebut }" pattern="yyyy-MM-dd" var="parseDateDebut" type="both"></fmt:parseDate>
						<fmt:parseDate value="${p.dateFin }" pattern="yyyy-MM-dd" var="parseDateFin" type="both"></fmt:parseDate>
						

						<tr>
							<td scope="row"><fmt:formatDate value="${parseDateDebut }" pattern="dd/MM/yyyy"/></td>
							<td><fmt:formatDate value="${parseDateFin }" pattern="dd/MM/yyyy"/></td>
							<td>${p.description}</td>
							<td>${p.remise} €</td>
							<td>${p.tauxReduction} %</td>

							<td><a href="artisan/promotion/modifier-promotion?promo_id=${p.id}">Modifier</a></td>
							<td><a href="artisan/promotion/supprimer-promotion?promo_id=${p.id}">Supprimer</a></td>
						</tr>
						
						
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
<!-- </div> -->


