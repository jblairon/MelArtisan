<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<style>
.img {
	width: 200px;
}
</style>

<div class="container" style="margin-bottom: 500px;">

	<div style="float: left; margin-top: 30px; margin-bottom: 50px;">
		<h1 class="client-h2">
			Bonjour
			<c:out value="${sessionScope.user_prenom} ${sessionScope.user_nom }" />
		</h1>
	</div>
	
	<div class="div-favoris-accueil" >

		<a class="mes-favoris" href="client/mes-favoris"> <img
			class="image-favoris"
			src="<c:url value="/resources/images/mes-favoris.png" />"
			alt="mes favoris" title="Mes favoris" />Mes favoris

		</a>
	</div>

	<br /> <br />

	<c:if test="${messageSuccess !=null}">

		<div class="alert alert-block alert-success"
			style="text-align: center; cliar: both">
			<h2>
				<c:out value="${messageSuccess }"></c:out>
			</h2>
		</div>
	</c:if>


	<div class="div-titre-h1" style="clear: both">
		<h1 class="my-4 titre-h1">Catégories de métiers</h1>
	</div>

	<!-- <div> -->
	<!-- 	<a href="test-data">Insérer des données de test</a> -->
	<!-- </div> -->



	<div class="row" style="margin-bottom: 50px">
		<c:forEach var="cat" items="${categories}" varStatus="st">

			<div class="col-sm-6 col-md-4 col-lg-3 portfolio-item">
				<div class="card h-100"
					style="background-color: #222222; color: #ffffff">
					<a href="client/societe/listeParCategorie?categorieId=${cat.id }"><img
						class="card-img-top"
						src="<c:url value="/resources/images/categories/${cat.image }"/>"
						alt="${cat.libelle }" /></a>
					<div class="card-body">
						<h3 class="card-title" style="text-align: center">${cat.libelle }</h3>

					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<br /> <br /> <br /> <br />
</div>


