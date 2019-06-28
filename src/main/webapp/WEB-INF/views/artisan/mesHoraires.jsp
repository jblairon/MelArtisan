<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>

<div class="container"
	style="background-color: #D8ECD8; height: 800px; background-attachment: fixed; margin-top: 100px;">

	<div class="div-titre-h1 div-titre-h1-artisan">
		<h1 class="titre-h1 titre-h1-artisan">Horaires d'ouverture et fermeture</h1>
	</div>
	
	<div class="${classMsgEnvoiMail }" style="text-align: center">
		<h2><c:out value="${msgEnvoiMail }"/></h2>
	</div>

	<div class="col-lg-8 col-lg-offset-2" style="margin-top: 100px">

		<div class="table-responsive ">
			<table id="mytable" class="table table-hover ">
				<tbody>
					<tr>
						<th></th>
						<th scope="col">Ouverture matin</th>
						<th scope="col">Fermeture matin</th>
						<th scope="col">Ouverture après-midi</th>
						<th scope="col">Fermetrue après-midi</th>
						<th scope="col">Modifier</th>
					</tr>
					<c:forEach var="h" items="${horaires}">

						<tr>
							<th scope="row">${h.jour }</th>
							<td>${h.amOpen}</td>
							<td>${h.amClose}</td>
							<td>${h.pmOpen}</td>
							<td>${h.pmClose }</td>

							<td><a href="artisan/societe/modifier-horaires?id=${h.id}&societe_id=${societe.id}">Modifier</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
</div>


