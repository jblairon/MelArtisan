package fr.jose.plateformeArtisan.formbeans;

import javax.validation.constraints.NotEmpty;

public class PromotionForm {

	@NotEmpty
	private String description;
	
	private float remise;
	private float tauxReduction;
	
	@NotEmpty
	private String dateDebut;
	
	private String dateFin;
	
	private long promotionId;
	
	private long societeId;
	
	private String fichier;


	public PromotionForm() {

	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public float getRemise() {
		return remise;
	}


	public void setRemise(float remise) {
		this.remise = remise;
	}


	public float getTauxReduction() {
		return tauxReduction;
	}


	public void setTauxReduction(float tauxReduction) {
		this.tauxReduction = tauxReduction;
	}


	public String getDateDebut() {
		return dateDebut;
	}


	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}


	public String getDateFin() {
		return dateFin;
	}


	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}


	public long getPromotionId() {
		return promotionId;
	}


	public void setPromotionId(long promotionId) {
		this.promotionId = promotionId;
	}


	public long getSocieteId() {
		return societeId;
	}


	public void setSocieteId(long societeId) {
		this.societeId = societeId;
	}


	public String getFichier() {
		return fichier;
	}


	public void setFichier(String fichier) {
		this.fichier = fichier;
	}


	

			
}
