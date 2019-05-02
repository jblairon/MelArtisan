<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false" isELIgnored="false"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="col-md-12 col-md-offset-1">
	<div class="col-lg-4 ajouter" >
		<h2>Liste des sociétés</h2>

		<a href="admin/societe/choixCategorie?id=0"><button
				class="btn btn-lg btn-primary">Ajouter</button></a>
	</div>

	<div class="table-responsive">
		<table id="mytable" class="table table-hover table-dark">
			<thead>
				<tr>
					<th scope="col">Nom</th>
					<th>Tel</th>
					<th>Email</th>
					<th>Adresse</th>
					<th>Modifier</th>
					<th>Supprimer</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="s" items="${societes}">
					<tr>
						<td>${s.nom}</td>
						<td>${s.tel}</td>
						<td>${s.email}</td>
						<td>${s.adresse.numero}${s.adresse.complement}
							${s.adresse.voie} ${s.adresse.codePostal} ${s.adresse.ville}</td>

						<td><a href="admin/societe/choixCategorie?id=${s.id}">Modifier</a></td>
						<td><a href="admin/supprimer-societe?id=${s.id}">Supprimer</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="clearfix"></div>

		<ul class="pagination pull-center">
			<c:if test="${page>1}">
				<li><a href="admin/liste-utilisateurs?page=${page-1}&max=15"><span
						class="glyphicon glyphicon-chevron-left"></span></a></li>
			</c:if>
			<li class="active"><a href="#">${page}</a></li>
			<c:if test="${suivExist}">
				<li><a href="admin/liste-utilisateurs?page=${page+1}&max=15"><span
						class="glyphicon glyphicon-chevron-right"></span></a></li>
			</c:if>
		</ul>
	</div>
</div>


