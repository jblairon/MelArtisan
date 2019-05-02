<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

	
<a href="test-data">Insérer des données de test</a>
<br />

<h1 class="my-4">Evénements de la métropole de Lille </h1>
<%-- <a href="client/ajouter-event-agenda?eventId=${evt.uid }">Ajouter à l'agenda</a>	 --%>
<a href="client/ajouter-event-agenda?eventId=20081963">Ajouter à l'agenda</a>
	
	<div class="row">
	<c:forEach var="evt" items="${enents}" varStatus="st">
	    
	        <div class="col-sm-6 col-md-4 col-lg-3 portfolio-item">
	          <div class="card h-100">

	              <p class="card-text">
	             	<span>${evt.description}</span></p>
	             	</div>
	         
		</c:forEach>
		</div>
		
<!-- 		<ul class="pagination justify-content-center"> -->
<!--         <li class="page-item"> -->
<!--           <a class="page-link" href="#" aria-label="Previous"> -->
<!--             <span aria-hidden="true">«</span> -->
<!--             <span class="sr-only">Previous</span> -->
<!--           </a> -->
<!--         </li> -->
<!--         <li class="page-item"> -->
<!--           <a class="page-link" href="#">1</a> -->
<!--         </li> -->
        
<!--         <li class="page-item"> -->
<!--           <a class="page-link" href="#" aria-label="Next"> -->
<!--             <span aria-hidden="true">»</span> -->
<!--             <span class="sr-only">Next</span> -->
<!--           </a> -->
<!--         </li> -->
<!--       </ul>  -->