package fr.jose.plateformeArtisan.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prestation")
public class Prestation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "libelle", length = 100, nullable = false)
	private String libelle;

	// bi-directional many-to-one association to Categorie
	 @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "metier_id")
	private Metier metier;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Metier getMetier() {
		return metier;
	}

	public void setMetier(Metier metier) {
		this.metier = metier;
	}

	@Override
	public String toString() {
		return "Prestation [id=" + id + ", libelle=" + libelle + ", metier=" + metier + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + ((metier == null) ? 0 : metier.hashCode());
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
		Prestation other = (Prestation) obj;
		if (id != other.id)
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (metier == null) {
			if (other.metier != null)
				return false;
		} else if (!metier.equals(other.metier))
			return false;
		return true;
	}
	
//	 @ManyToMany(mappedBy = "prestation")
//	 private List<Societe> societes;

			
}
