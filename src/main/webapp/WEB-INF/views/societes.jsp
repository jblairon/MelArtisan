<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>



<br />
<br />
<!-- <div class="container" style="width: 80%; margin-left: 50px;"> -->

	<div class="div-titre-h1">
		<h1 class="my-4 titre-h1">Liste des sociétés</h1>
	</div>

	

	<div class="row" style="margin-bottom: 50px">
		<c:forEach var="s" items="${societes}" varStatus="st">

			<div class="col-sm-4 col-md-6 col-lg-3 portfolio-item" style="margin: 40px;">
				<div class="card h-100"
					style="background-color: #222222; color: #ffffff">
					<a href="societe/societe-detail?id=${s.id }"><img
						class="card-img-top"
						src="<c:url value="/resources/images/societes/vignettes/${s.images[1].nom}"/>"
						alt="${s.images[1].nom }" /></a>
					<div class="card-body">
						<h3 class="card-title" style="text-align: center">${s.nom }</h3>

					</div>
				</div>
			</div>
		</c:forEach>
	</div>
<!-- </div> -->
<br />
<br />
<br />
<br />


