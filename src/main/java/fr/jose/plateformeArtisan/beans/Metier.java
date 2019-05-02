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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Metier implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "libelle", length = 50, nullable = false, unique = true)
	private String libelle;

	// bi-directional many-to-one association to Categorie
	 @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "categorie_id")
	private Categorie categorie;

	// bi-directional many-to-one association to prestation
	@OneToMany(mappedBy="metier", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER)
	private List<Prestation> prestations;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy="metiers")
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

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Prestation> getPrestations() {
		return prestations;
	}

	public void setPrestations(List<Prestation> prestations) {
		this.prestations = prestations;
	}

	public List<Societe> getSocietes() {
		return societes;
	}

	public void setSocietes(List<Societe> societes) {
		this.societes = societes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categorie == null) ? 0 : categorie.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + ((prestations == null) ? 0 : prestations.hashCode());
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
		Metier other = (Metier) obj;
		if (categorie == null) {
			if (other.categorie != null)
				return false;
		} else if (!categorie.equals(other.categorie))
			return false;
		if (id != other.id)
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (prestations == null) {
			if (other.prestations != null)
				return false;
		} else if (!prestations.equals(other.prestations))
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
		return "Metier [id=" + id + ", libelle=" + libelle + ", categorie=" + categorie + ", prestations=" + prestations
				+ ", societes=" + societes + "]";
	}
	

				
	}
