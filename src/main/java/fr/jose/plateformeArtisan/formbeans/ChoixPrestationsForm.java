package fr.jose.plateformeArtisan.formbeans;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import fr.jose.plateformeArtisan.beans.Metier;


public class ChoixPrestationsForm {


	@NotEmpty
	private long prestationsId[];
	

	public long[] getPrestationsId() {
		return prestationsId;
	}

	public void setPrestationsId(long[] prestationsId) {
		this.prestationsId = prestationsId;
	}

	public ChoixPrestationsForm() {

	}

	public ChoixPrestationsForm(long[] metiersId, long categorieId) {
		super();
		
		this.prestationsId = metiersId;
		
	}


		
}
