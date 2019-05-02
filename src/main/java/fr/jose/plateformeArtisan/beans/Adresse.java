package fr.jose.plateformeArtisan.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Adresse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "numero", nullable = false)
	private int numero;

	@Column(name = "complement", nullable = true, length = 10)
	private String complement;

	@Column(name = "voie", length = 60, nullable = false)
	private String voie;

	@Column(name = "codePostal", nullable = false)
	private int codePostal;

	// bi-directional many-to-one association to societe
	@OneToMany(mappedBy = "adresse", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Societe> societes;

	@Column(name = "ville", length = 60, nullable = false)
	private String ville;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getVoie() {
		return voie;
	}

	public void setVoie(String voie) {
		this.voie = voie;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public List<Societe> getSocietes() {
		return societes;
	}

	public void setSocietes(List<Societe> societes) {
		this.societes = societes;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codePostal;
		result = prime * result + ((complement == null) ? 0 : complement.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + numero;
		result = prime * result + ((societes == null) ? 0 : societes.hashCode());
		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
		result = prime * result + ((voie == null) ? 0 : voie.hashCode());
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
		Adresse other = (Adresse) obj;
		if (codePostal != other.codePostal)
			return false;
		if (complement == null) {
			if (other.complement != null)
				return false;
		} else if (!complement.equals(other.complement))
			return false;
		if (id != other.id)
			return false;
		if (numero != other.numero)
			return false;
		if (societes == null) {
			if (other.societes != null)
				return false;
		} else if (!societes.equals(other.societes))
			return false;
		if (ville == null) {
			if (other.ville != null)
				return false;
		} else if (!ville.equals(other.ville))
			return false;
		if (voie == null) {
			if (other.voie != null)
				return false;
		} else if (!voie.equals(other.voie))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Adresse [id=" + id + ", numero=" + numero + ", complement=" + complement + ", voie=" + voie
				+ ", codePostal=" + codePostal + ", societes=" + societes + ", ville=" + ville + "]";
	}

				
}
