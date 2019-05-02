<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<style>
.img {
	width: 100px;
}



#p-left {
	margin-left: -300px;
	float: left;
}



.p1a {
	margin-top: 50px;
	padding-top: 40px;
}

.p2 {
	margin-top: -100px;
	padding-top: 60px;
	width: 250px;
	margin-left: 150px;
	border-radius: 125px;
	height: 250px;
	padding-top: 60px;
	width: 250px;
}

.p2a {
	margin-top: 50px;
	padding-top: 40px;
}

#p-right {
	margin-right: -300px;
	float: right;
}
</style>

<br />
<br />






<!-- <div class="row" style="height: 200px; background-color: red; width: 400px; font-size: 30px;"> -->
<!-- 	<a href="test-data">ajouter des données</a> -->
<!-- </div> -->

<div style="margin-left: 100px; margin-bottom: 550px;">

	<div class=" div-titre-h1 " style="margin-left: 50px;">
		<h1 class="my-4 titre-h1 ">Catégories de métiers</h1>
	</div>


	<div id="p-left">
		<div class="left-top paragraphe p1 " id="divTop">
			<p>MelArtisan vous propose toute une gamme d'artisans dans les
				domaines les plus variés.</p>
		</div>
		<div class="paragraphe left-middle" id="divLeft">
			<p>Quelque soit votre recherche, quelque soit votre projet, vous
				trouverez la solution à vos interrogations.</p>
		</div>
		<div class="paragraphe p1 p1a left-bottom" id="divBottom">
			<P>Vous pourrez filtrer votre recherche, la simplicité,
				l'accessiblité et l'ergonomie ont été pensées pour vous faciliter la
				vie.</p>
		</div>
	</div>
	<div id="p-right">
		<div class="paragraphe p2 p1a" id="divTop">
			<p>Vous pourrez nous contacter pour nous apporter vos remarques
				qui nous feront grandir.</p>
		</div>
		<div class="paragraphe" id="divRight">
			<p>Vous pourrez contacter l'artisan que vous souhaitez (si vous
				êtes inscrit.</p>
		</div>
		<div class="paragraphe p2 p1a p2a" class="p2" id="divBottom">
			<p>
				Vos suggestions <br> seront les bienvenues afin d'améliorer
				cette application, notre but est de vous <br> satisfaire.
			</p>

		</div>
	</div>


	<c:set var="i" value="0" scope="page" />

	<div class="row " 
		style="margin-bottom: 200px; margin-left: -50px;">
		<c:forEach var="i" begin="0" end="${categories.size()-1 }">
			<div id="anim${i }"
				class="   portfolio-item animation-${i }  delay-${i}  "
				style="margin-right: 20px; margin-bottom: 50px;">
				<div class="card h-100"
					style="background-color: #222222; color: #ffffff">
					<a
						href="societe/listeParCategorie?categorieId=${categories.get(i).id }"><img
						class="card-img-top"
						src="<c:url value="/resources/images/categories/${categories.get(i).image }"/>"
						alt="${categories.get(i).libelle }" /></a>
					<div class="card-body">
						<h2 class="card-title" style="text-align: center">${categories.get(i).libelle }</h2>
					</div>
				</div>
			</div>


		</c:forEach>
	</div>
</div>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var delai1 = 200;

		for (var i = 0; i < 6; i++) {
			$("#anim" + i).hide(0).delay(delai1).show(2000);
			delai1 += 2000;
		}
		;
	});
</script>


