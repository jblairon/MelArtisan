
// Tableau de memorisation des notes pour chaque liste
var ArrListeEtoile = new Object();

//-------------------------------------------------------
// Gestion de la visibilite des etoiles au survol
//-------------------------------------------------------
function GestionHover(idListe, indice, nbEtoile){
	for (i=1; i<= nbEtoile; i++)
	{
		var idoff = "staroff-" + idListe + "-" + i;
		var idon = "staron-" + idListe + "-" + i;
		
		if(indice == -1)
		{
			// Sortie du survol de la liste des etoiles
			if (ArrListeEtoile[idListe] >= i){
				document.getElementById(idoff).style.display ="none";
				document.getElementById(idon).style.display ="block";
			}
			else{
				document.getElementById(idoff).style.display ="block";
				document.getElementById(idon).style.display ="none";
			}
		}
		else
		{
			// Survol de la liste des etoiles
			if(i <= indice){
				document.getElementById(idoff).style.display ="none";
				document.getElementById(idon).style.display ="block";
			}
			else{
				document.getElementById(idoff).style.display ="block";
				document.getElementById(idon).style.display ="none";
			}
		}
	}
}

//-------------------------------------------------------
// Selection d une note pour une liste
//-------------------------------------------------------
function ChoixSelection(idListe, indice, nbEtoile, id){
	ArrListeEtoile[idListe] = indice;
	var score = "score-" + idListe;
	var lien = "<a href=\"client/societe/societe-voter?id="+id+"&note="+indice+"\">"
		+"<button class=\"btn btn-success\">Voter</button></a>";
	document.getElementById(score).innerHTML = " " + indice + "/" + nbEtoile + "   " + lien;

}

//-------------------------------------------------------
// Creation d une liste de notation unique
//-------------------------------------------------------
function CreateListeEtoile(idListe, nbEtoile, id){
	ArrListeEtoile[idListe] = 0;

	var renduListe = "";
	renduListe += "<div class=\"listeEtoile\" onmouseout=\"GestionHover('" + idListe + "', -1, '" + nbEtoile + "')\">";
	renduListe += "<ul>";
	renduListe += "<span class=\"note\">Votre avis    </span>";
	
	for(i=1; i<=nbEtoile; i++){
		renduListe += "<li style=\"margin-left:10px;\">";
		renduListe += "<a href=\"javascript:ChoixSelection('" + idListe + "', '" + i + "', '" + nbEtoile+ "', '" + id + "')\" onmouseover=\"GestionHover('" + idListe + "', '" + i + "', '" + nbEtoile + "')\">";
		renduListe += "<img id=\"staroff-" + idListe + "-" + i + "\" src=\"resources/images/etoiles/staroff.gif\" border=\"0\" title=\"" + i + "\" style=\"border-width: 0px; display: block; height: 20px; \">";
		renduListe += "<img id=\"staron-" + idListe + "-" + i + "\" src=\"resources/images/etoiles/staron.gif\" border=\"0\" title=\"" + i + "\" style=\"border-width: 0px; display: none; height: 20px; \">";
		renduListe += "</a>";
		renduListe += "</li>";
	}
	
	renduListe += "	</ul>";
	renduListe += "</div>";
	renduListe += "<label id=\"score-" + idListe + "\"></label>";
	
	document.getElementById(idListe).outerHTML = renduListe;
}

//function validerNote(){
//	$("#score")
//}
