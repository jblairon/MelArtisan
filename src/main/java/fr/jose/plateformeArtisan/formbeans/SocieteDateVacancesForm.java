package fr.jose.plateformeArtisan.formbeans;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SocieteDateVacancesForm {
	
	@NotEmpty
	private String dateDebut;
	
	@NotEmpty
	private String dateFin;

	private String raison;
	

	public SocieteDateVacancesForm() {
		super();
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


	public String getRaison() {
		return raison;
	}


	public void setRaison(String raison) {
		this.raison = raison;
	}
	
	

		
}
