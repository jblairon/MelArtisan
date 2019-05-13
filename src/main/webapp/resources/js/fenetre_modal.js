$(document).ready(function() {

	// Lorsque l'on clique sur show on affiche la fenêtre modale
	$('#show').click(function(e) {
		// On désactive le comportement du lien
		e.preventDefault();
		alert("oui");
		showModal();
	});

	// Lorsque l'on clique sur le fond on cache la fenetre modale
	$('#fond').click(function() {
		hideModal();
	});

	// Lorsque l'on modifie la taille du navigateur la taille du fond change
	$(window).resize(function() {
		resizeModal()
	});

}); // fin doc ready

function showModal() {
	var id = '#modal';
	$(id)
			.html(
					'Voici ma fenetre modale<br/><a href="#" class="close">Fermer la fenetre</a>');

	// On definit la taille de la fenetre modale
	resizeModal();

	// Effet de transition
	$('#fond').fadeIn(1000);
	$('#fond').fadeTo("slow", 0.8);
	// Effet de transition
	$(id).fadeIn(2000);

	$('.popup .close').click(function(e) {
		// On désactive le comportement du lien
		e.preventDefault();
		// On cache la fenetre modale
		hideModal();
	});
}

function hideModal() {
	// On cache le fond et la fenêtre modale
	$('#fond, .popup').hide();
	$('.popup').html('');
}

function resizeModal(){
	   var modal = $('#modal');
	   // On récupère la largeur de l'écran et la hauteur de la page afin de
		// cacher la totalité de l'écran
	   var winH = $(document).height();
	   var winW = $(window).width();
	   
	   // le fond aura la taille de l'écran
	   $('#fond').css({'width':winW,'height':winH});
	   
	   // On récupère la hauteur et la largeur de l'écran
	   var winH = $(window).height();
	   // On met la fenêtre modale au centre de l'écran
	   modal.css('top', winH/2 - modal.height()/2);
	   modal.css('left', winW/2 - modal.width()/2);
	}
