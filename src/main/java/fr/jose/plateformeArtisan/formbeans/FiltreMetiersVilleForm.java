package fr.jose.plateformeArtisan.formbeans;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import fr.jose.plateformeArtisan.beans.Metier;


public class FiltreMetiersVilleForm {


//	@NotEmpty
	private long metiersId[];
	
//	@NotEmpty
	private long categorieId;

	
	private String metiersForm[];
	private String ville;
	

	public FiltreMetiersVilleForm() {

	}

	public FiltreMetiersVilleForm(long[] metiersId, long categorieId) {
		super();
		
		this.metiersId = metiersId;
		this.categorieId = categorieId;
		
	}

	

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
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


	public String[] getMetiersForm() {
		return metiersForm;
	}

	public void setMetiersForm(String[] metiersForm) {
		this.metiersForm = metiersForm;
	}
	
	



	
}
