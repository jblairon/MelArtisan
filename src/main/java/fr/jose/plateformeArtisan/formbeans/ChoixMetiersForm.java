package fr.jose.plateformeArtisan.formbeans;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import fr.jose.plateformeArtisan.beans.Metier;


public class ChoixMetiersForm {


//	@NotEmpty
	private long metiersId[];
	
//	@NotEmpty
	private long categorieId;
	
	private String nouveauMetier;
	
	private String metiersForm[];
	

	public ChoixMetiersForm() {

	}

	public ChoixMetiersForm(long[] metiersId, long categorieId) {
		super();
		
		this.metiersId = metiersId;
		this.categorieId = categorieId;
		
	}
	
	public ChoixMetiersForm(String nouveauMetier) {
		super();
		this.nouveauMetier = nouveauMetier;
	}

	public long[] getMetiersId() {
		return metiersId;
	}

	public void setMetiersId(long[] metiersId) {
		this.metiersId = metiersId;
	}

	public long getCategorieId() {
		return categorieId;
	}

	public void setCategorieId(long categorieId) {
		this.categorieId = categorieId;
	}

	public String getNouveauMetier() {
		return nouveauMetier;
	}

	public void setNouveauMetier(String nouveauMetier) {
		this.nouveauMetier = nouveauMetier;
	}

	public String[] getMetiersForm() {
		return metiersForm;
	}

	public void setMetiersForm(String[] metiersForm) {
		this.metiersForm = metiersForm;
	}
	
	



	
}
