<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false" isELIgnored="false"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="col-md-12 col-md-offset-1">
	<div class="col-lg-4" style="background-color: #dddddd; padding: 10px">
		<h2>Liste des catégories</h2>

		<a href="admin/categorie/creation-categorie"><button
				class="btn btn-lg btn-primary">Ajouter</button></a>
	</div>
	<br />

	<div class="table-responsive">
		<table id="mytable" class="table table-hover table-dark">
			<thead>
				<tr>
					<th scope="col">Nom</th>
					<th>Métiers</th>
					<th>Modifier</th>
					<th>Supprimer</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="c" items="${categories}">
					<tr>
						<td>${c.libelle}</td>

						<td>
							<ul>
								<c:forEach var="m" items="${c.metiers }">
									<li><c:out value="${m.libelle }" /></li>
								</c:forEach>
							</ul>
						</td>
						<td><a href="admin/categorie/modifier-categorie?id=${c.id}">Modifier</a></td>
						<td><a href="admin/supprimer-categorie?id=${c.id}">Supprimer</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="clearfix"></div>

		<ul class="pagination pull-center">
			<c:if test="${page>1}">
				<li><a href="admin/liste-categories?page=${page-1}&max=15"><span
						class="glyphicon glyphicon-chevron-left"></span></a></li>
			</c:if>
			<li class="active"><a href="#">${page}</a></li>
			<c:if test="${suivExist}">
				<li><a href="admin/liste-categories?page=${page+1}&max=15"><span
						class="glyphicon glyphicon-chevron-right"></span></a></li>
			</c:if>
		</ul>
	</div>
</div>


