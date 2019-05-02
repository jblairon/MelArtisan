package fr.jose.plateformeArtisan.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categorie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "libelle", length = 50, nullable = false)
	private String libelle;

	@Column(name = "image", length = 50, nullable = false)
	private String image;

	// bi-directional many-to-one association to Metier
	@OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Metier> metiers;

	// bi-directional many-to-one association to Metier
	@OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Societe> societes;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Metier> getMetiers() {
		return metiers;
	}

	public void setMetiers(List<Metier> metiers) {
		this.metiers = metiers;
	}

	public List<Societe> getSocietes() {
		return societes;
	}

	public void setSocietes(List<Societe> societes) {
		this.societes = societes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + ((metiers == null) ? 0 : metiers.hashCode());
		result = prime * result + ((societes == null) ? 0 : societes.hashCode());
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
		Categorie other = (Categorie) obj;
		if (id != other.id)
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (metiers == null) {
			if (other.metiers != null)
				return false;
		} else if (!metiers.equals(other.metiers))
			return false;
		if (societes == null) {
			if (other.societes != null)
				return false;
		} else if (!societes.equals(other.societes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Categorie [id=" + id + ", libelle=" + libelle + ", image=" + image + ", metiers=" + metiers
				+ ", societes=" + societes + "]";
	}

	
}
