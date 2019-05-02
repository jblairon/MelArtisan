<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>

<div class="container"
	style="background-color: #4DAA85; height: 2000px; background-attachment: fixed">
	<div class="col-md-12" style="margin-top: 100px">

		<div class="col-lg-4 ajouter">
			<h2>Liste des utilisateurs</h2>

			<a href="admin/ajouter-utilisateur"><button
					class="btn btn-lg btn-primary">Ajouter</button></a>
		</div>


		<div class="table-responsive">
			<table id="mytable" class="table table-hover table-dark">
				<tr>
					<th>Prénom</th>
					<th>Nom</th>
					<th>Email</th>
					<th>Admin ?</th>
					<th>Artisan ?</th>
					<th>Modifier</th>
					<th>Supprimer</th>
				</tr>
				<c:forEach var="u" items="${utilisateurs}">
					<tr>
						<td>${u.prenom}</td>
						<td>${u.nom}</td>
						<td>${u.email}</td>
						<td><c:choose>
								<c:when test="${u.admin}">Oui</c:when>
								<c:otherwise>Non</c:otherwise>
							</c:choose></td>

						<td><c:choose>
								<c:when test="${u.artisan}">Oui</c:when>
								<c:otherwise>Non</c:otherwise>
							</c:choose></td>

						<td><a href="admin/modifier-coordonnees?id=${u.id}">Modifier</a></td>
						<td><a href="admin/supprimer-utilisateur?id=${u.id}">Supprimer</a></td>
					</tr>
				</c:forEach>
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
</div>


