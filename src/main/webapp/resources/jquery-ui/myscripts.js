//Attendre la fin du chargement de la page
$.urlParam = function(name){
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results==null){
       return null;
    }
    else{
       return decodeURI(results[1]) || 0;
    }
}

$(function(){ //$(document).ready(function(){
	//$ ou jQuery
	//$("p") : sélectionne l'ensemble des <p>
	//$("#unid, .classe, nomBalise")
	//alert("jquery chargée");
	
	var trainingId = $.urlParam("trainingid");
	if(trainingId!='undefined'){
		$("#selectedTrainingId option[value="+trainingId+"]").attr("selected","selected");
	}
	
	$("#selectedTrainingId").change(function(){
		var idTrainingSel = $(this).val();
		window.location.href="http://localhost:8080/springmvc/inscription?trainingid="+idTrainingSel;
	});
	
	
});