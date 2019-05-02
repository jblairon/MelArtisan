<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<style>

.img {
	width: 300px;
}
</style>

<br />	
<!-- <a href="test-data">Insérer des données de test</a> -->
<br />
	
<%-- <h1 class="my-4 client-h1">Sociétés catégorie <c:out value="${societes.get(0).categorie.libelle }"></c:out> </h1> --%>


	
	<div class="container" style="margin-top: 100px;">
	<c:forEach var="soc" items="${societes}" varStatus="st">
	    
	        <div class="col-sm-6 col-md-4 col-lg-3 portfolio-item">
	          <div class="card h-100">
	            <a href="client/societe/societe-detail?id=${soc.id }"><img
					class="card-img-top"
					src="<c:url value="/resources/images/societes/vignettes/${soc.images[1].nom }"/>"
					alt="${soc.images[1].libelle}" /></a>
	            <div class="card-body">
	              <h4 class="card-title">${soc.nom}</h4>

	              	<span>
	              		<c:forEach var="met" items="${soc.metiers}">
							<c:out value="${met.libelle}" />
							<br />
						</c:forEach>
					</span>
					<br />

	            </div>
	          </div>
	        </div>
		</c:forEach>
		</div>

