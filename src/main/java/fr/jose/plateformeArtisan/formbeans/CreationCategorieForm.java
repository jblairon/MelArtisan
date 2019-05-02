package fr.jose.plateformeArtisan.formbeans;

public class CreationCategorieForm {

	
	private String categorie;
	private long categorieId;
	
	private long[] metiersId;
	private String[] nouveauxMetiers;
	private String[] metiers;
	


	public CreationCategorieForm() {

	}


	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}


	public long getCategorieId() {
		return categorieId;
	}


	public void setCategorieId(long categorieId) {
		this.categorieId = categorieId;
	}


	public String[] getNouveauxMetiers() {
		return nouveauxMetiers;
	}


	public void setNouveauxMetiers(String[] nouveauxMetiers) {
		this.nouveauxMetiers = nouveauxMetiers;
	}


	public long[] getMetiersId() {
		return metiersId;
	}


	public void setMetiersId(long[] metiersId) {
		this.metiersId = metiersId;
	}


	public String[] getMetiers() {
		return metiers;
	}


	public void setMetiers(String[] metiers) {
		this.metiers = metiers;
	}

	

}
