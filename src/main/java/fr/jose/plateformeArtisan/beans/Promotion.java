package fr.jose.plateformeArtisan.beans;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Promotion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	

	@Column(name = "description", nullable = false, length = 500)
	private String description;

	@Column(name = "dateDebut", nullable = false)
	private LocalDate dateDebut;

	@Column(name = "dateFin", nullable = true)
	private LocalDate dateFin;

	@Column(name = "remise", nullable = true)
	private float remise;
	
	@Column(name = "tauxReduction", nullable = true)
	private float tauxReduction;

	// bi-directional many-to-one association to Societe
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "societe_id")
	private Societe societe;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
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

	public Societe getSociete() {
		return societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDebut == null) ? 0 : dateDebut.hashCode());
		result = prime * result + ((dateFin == null) ? 0 : dateFin.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + Float.floatToIntBits(remise);
		result = prime * result + ((societe == null) ? 0 : societe.hashCode());
		result = prime * result + Float.floatToIntBits(tauxReduction);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promotion other = (Promotion) obj;
		if (dateDebut == null) {
			if (other.dateDebut != null)
				return false;
		} else if (!dateDebut.equals(other.dateDebut))
			return false;
		if (dateFin == null) {
			if (other.dateFin != null)
				return false;
		} else if (!dateFin.equals(other.dateFin))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (Float.floatToIntBits(remise) != Float.floatToIntBits(other.remise))
			return false;
		if (societe == null) {
			if (other.societe != null)
				return false;
		} else if (!societe.equals(other.societe))
			return false;
		if (Float.floatToIntBits(tauxReduction) != Float.floatToIntBits(other.tauxReduction))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Promotion [id=" + id + ", description=" + description + ", dateDebut=" + dateDebut + ", dateFin="
				+ dateFin + ", remise=" + remise + ", tauxReduction=" + tauxReduction + ", societe=" + societe + "]";
	}

	
}
