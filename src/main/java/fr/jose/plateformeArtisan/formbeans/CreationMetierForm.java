package fr.jose.plateformeArtisan.formbeans;

public class CreationMetierForm {

	
	private String metier;
	private long metierId;
	
	private long categorieId;
	
	private long[] prestationsId;
	private String[] nouvellesPrestations;
	private String[] prestations;
	


	public CreationMetierForm() {

	}


	public String getMetier() {
		return metier;
	}

	public void setMetier(String metier) {
		this.metier = metier;
	}


	public long getMetierId() {
		return metierId;
	}


	public void setMetierId(long metierId) {
		this.metierId = metierId;
	}


	public long[] getPrestationsId() {
		return prestationsId;
	}


	public void setPrestationsId(long[] prestationsId) {
		this.prestationsId = prestationsId;
	}


	public String[] getNouvellesPrestations() {
		return nouvellesPrestations;
	}


	public void setNouvellesPrestations(String[] nouvellesPrestations) {
		this.nouvellesPrestations = nouvellesPrestations;
	}


	public long getCategorieId() {
		return categorieId;
	}


	public void setCategorieId(long categorieId) {
		this.categorieId = categorieId;
	}


	public String[] getPrestations() {
		return prestations;
	}


	public void setPrestations(String[] prestations) {
		this.prestations = prestations;
	}


	

}
