package fr.jose.plateformeArtisan.beans;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Newsletter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private boolean newsletter;

	// bi-directional many-to-one association to societe
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "societe_id")
//	private Societe societe;
	private long societe_id;

	// bi-directional many-to-one association to utilisateur
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "utilisateur_id")
//	private Utilisateur utilisateur;
	private long utilisateur_id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isNewsletter() {
		return newsletter;
	}

	public void setNewsletter(boolean newsletter) {
		this.newsletter = newsletter;
	}

	public long getSociete_id() {
		return societe_id;
	}

	public void setSociete_id(long societe_id) {
		this.societe_id = societe_id;
	}

	public long getUtilisateur_id() {
		return utilisateur_id;
	}

	public void setUtilisateur_id(long utilisateur_id) {
		this.utilisateur_id = utilisateur_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (newsletter ? 1231 : 1237);
		result = prime * result + (int) (societe_id ^ (societe_id >>> 32));
		result = prime * result + (int) (utilisateur_id ^ (utilisateur_id >>> 32));
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
		Newsletter other = (Newsletter) obj;
		if (id != other.id)
			return false;
		if (newsletter != other.newsletter)
			return false;
		if (societe_id != other.societe_id)
			return false;
		if (utilisateur_id != other.utilisateur_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Newsletter [id=" + id + ", newsletter=" + newsletter + ", societe_id=" + societe_id
				+ ", utilisateur_id=" + utilisateur_id + "]";
	}

	
}
