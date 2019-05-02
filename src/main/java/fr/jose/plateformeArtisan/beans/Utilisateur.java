package fr.jose.plateformeArtisan.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Utilisateur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Genre {
		Monsieur, Madame, Mademoiselle
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "genre", length = 30)
	private Genre genre;

	@Column(name = "prenom", length = 50, nullable = false)
	private String prenom;

	@Column(name = "nom", length = 50, nullable = false)
	private String nom;

	@ManyToOne
//	@Column(name = "adresse", nullable = false)
	private Adresse adresse;

	@Column(name = "email", length = 60, nullable = true)
	private String email;

	@Column(name = "mdp", length = 20, nullable = true)
	private String mdp;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "utilisateur_societe", joinColumns = { @JoinColumn(name = "utilisateur_id") }, inverseJoinColumns = {
			@JoinColumn(name = "societe_id") })
	private List<Societe> societes;

	@Column(name = "admin", nullable = false)
	private boolean admin;
	
	@Column(name = "artisan", nullable = true)
	private boolean artisan;

	@Column(name = "client", nullable = false)
	private boolean client;
	

	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
	@Column
	private LocalDate dateDeNaissance;
	
	//Pour l'artisan
	@OneToOne(fetch=FetchType.LAZY, mappedBy="utilisateur")
	private Societe maSociete;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public List<Societe> getSocietes() {
		return societes;
	}

	public void setSocietes(List<Societe> societes) {
		this.societes = societes;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isArtisan() {
		return artisan;
	}

	public void setArtisan(boolean artisan) {
		this.artisan = artisan;
	}

	public boolean isClient() {
		return client;
	}

	public void setClient(boolean client) {
		this.client = client;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public LocalDate getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(LocalDate dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public Societe getMaSociete() {
		return maSociete;
	}

	public void setMaSociete(Societe maSociete) {
		this.maSociete = maSociete;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (admin ? 1231 : 1237);
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + (artisan ? 1231 : 1237);
		result = prime * result + (client ? 1231 : 1237);
		result = prime * result + ((dateCreation == null) ? 0 : dateCreation.hashCode());
		result = prime * result + ((dateDeNaissance == null) ? 0 : dateDeNaissance.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((maSociete == null) ? 0 : maSociete.hashCode());
		result = prime * result + ((mdp == null) ? 0 : mdp.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
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
		Utilisateur other = (Utilisateur) obj;
		if (admin != other.admin)
			return false;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (artisan != other.artisan)
			return false;
		if (client != other.client)
			return false;
		if (dateCreation == null) {
			if (other.dateCreation != null)
				return false;
		} else if (!dateCreation.equals(other.dateCreation))
			return false;
		if (dateDeNaissance == null) {
			if (other.dateDeNaissance != null)
				return false;
		} else if (!dateDeNaissance.equals(other.dateDeNaissance))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (genre != other.genre)
			return false;
		if (id != other.id)
			return false;
		if (maSociete == null) {
			if (other.maSociete != null)
				return false;
		} else if (!maSociete.equals(other.maSociete))
			return false;
		if (mdp == null) {
			if (other.mdp != null)
				return false;
		} else if (!mdp.equals(other.mdp))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
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
		return "Utilisateur [id=" + id + ", genre=" + genre + ", prenom=" + prenom + ", nom=" + nom + ", adresse="
				+ adresse + ", email=" + email + ", mdp=" + mdp + ", societes=" + societes + ", admin=" + admin
				+ ", artisan=" + artisan + ", client=" + client + ", dateCreation=" + dateCreation
				+ ", dateDeNaissance=" + dateDeNaissance + ", maSociete=" + maSociete + "]";
	}

								
}
