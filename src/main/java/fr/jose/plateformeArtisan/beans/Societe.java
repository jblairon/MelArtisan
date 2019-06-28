package fr.jose.plateformeArtisan.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "societe")
public class Societe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "nom", length = 60, nullable = false)
	private String nom;

	@Column(name = "description", length = 1000)
	private String description;

	@Column(name = "tel", nullable = false)
	private String tel;

	@Column(name = "email", length = 60, nullable = false)
	private String email;

	// bi-directional many-to-one association to Categorie
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "adresse_id")
	private Adresse adresse;

	// bi-directional many-to-one association to Categorie
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categorie_id")
	private Categorie categorie;

	// bi-directional one to many association to Image
	@OneToMany(mappedBy = "societe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Image> images;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_id")
	private Contact contact;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "societedatevacances_id")
	private SocieteDateVacances societedatevacances;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utilisateur_id")
	private Utilisateur utilisateur;

	@Temporal(TemporalType.DATE)
	private Date dateCreation;

//	@NotEmpty
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "societe_metier", joinColumns = { @JoinColumn(name = "societe_id") }, inverseJoinColumns = {
			@JoinColumn(name = "metier_id") })
	private List<Metier> metiers;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "societe_prestation", joinColumns = { @JoinColumn(name = "societe_id") }, inverseJoinColumns = {
			@JoinColumn(name = "prestation_id") })
	private List<Prestation> prestations;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "societe_horaires", joinColumns = { @JoinColumn(name = "societe_id") }, inverseJoinColumns = {
			@JoinColumn(name = "horaire_id") })
	private List<Horaire> horaires;

	// bi-directional one to many association to newsletter
//	@OneToMany(mappedBy = "societe", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Newsletter> newsletters;

	// bi-directional many-to-one association to promotion
	@OneToMany(mappedBy = "societe", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Promotion> promotions;

	private boolean amCloseToDay;
	private boolean pmCloseToDay;

	// bi-directional one to many association to Image
	@OneToMany(mappedBy = "societe", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Note> notes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public SocieteDateVacances getSocietedatevacances() {
		return societedatevacances;
	}

	public void setSocietedatevacances(SocieteDateVacances societedatevacances) {
		this.societedatevacances = societedatevacances;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public List<Metier> getMetiers() {
		return metiers;
	}

	public void setMetiers(List<Metier> metiers) {
		this.metiers = metiers;
	}

	public List<Prestation> getPrestations() {
		return prestations;
	}

	public void setPrestations(List<Prestation> prestations) {
		this.prestations = prestations;
	}

	public List<Horaire> getHoraires() {
		return horaires;
	}

	public void setHoraires(List<Horaire> horaires) {
		this.horaires = horaires;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public boolean isAmCloseToDay() {
		return amCloseToDay;
	}

	public void setAmCloseToDay(boolean amCloseToDay) {
		this.amCloseToDay = amCloseToDay;
	}

	public boolean isPmCloseToDay() {
		return pmCloseToDay;
	}

	public void setPmCloseToDay(boolean pmCloseToDay) {
		this.pmCloseToDay = pmCloseToDay;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + (amCloseToDay ? 1231 : 1237);
		result = prime * result + ((categorie == null) ? 0 : categorie.hashCode());
		result = prime * result + ((contact == null) ? 0 : contact.hashCode());
		result = prime * result + ((dateCreation == null) ? 0 : dateCreation.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((horaires == null) ? 0 : horaires.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((images == null) ? 0 : images.hashCode());
		result = prime * result + ((metiers == null) ? 0 : metiers.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + (pmCloseToDay ? 1231 : 1237);
		result = prime * result + ((prestations == null) ? 0 : prestations.hashCode());
		result = prime * result + ((promotions == null) ? 0 : promotions.hashCode());
		result = prime * result + ((societedatevacances == null) ? 0 : societedatevacances.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
		result = prime * result + ((utilisateur == null) ? 0 : utilisateur.hashCode());
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
		Societe other = (Societe) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (amCloseToDay != other.amCloseToDay)
			return false;
		if (categorie == null) {
			if (other.categorie != null)
				return false;
		} else if (!categorie.equals(other.categorie))
			return false;
		if (contact == null) {
			if (other.contact != null)
				return false;
		} else if (!contact.equals(other.contact))
			return false;
		if (dateCreation == null) {
			if (other.dateCreation != null)
				return false;
		} else if (!dateCreation.equals(other.dateCreation))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (horaires == null) {
			if (other.horaires != null)
				return false;
		} else if (!horaires.equals(other.horaires))
			return false;
		if (id != other.id)
			return false;
		if (images == null) {
			if (other.images != null)
				return false;
		} else if (!images.equals(other.images))
			return false;
		if (metiers == null) {
			if (other.metiers != null)
				return false;
		} else if (!metiers.equals(other.metiers))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (pmCloseToDay != other.pmCloseToDay)
			return false;
		if (prestations == null) {
			if (other.prestations != null)
				return false;
		} else if (!prestations.equals(other.prestations))
			return false;
		if (promotions == null) {
			if (other.promotions != null)
				return false;
		} else if (!promotions.equals(other.promotions))
			return false;
		if (societedatevacances == null) {
			if (other.societedatevacances != null)
				return false;
		} else if (!societedatevacances.equals(other.societedatevacances))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		if (utilisateur == null) {
			if (other.utilisateur != null)
				return false;
		} else if (!utilisateur.equals(other.utilisateur))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Societe [id=" + id + ", nom=" + nom + ", description=" + description + ", tel=" + tel + ", email="
				+ email + ", adresse=" + adresse + ", categorie=" + categorie + ", images=" + images + ", contact="
				+ contact + ", societedatevacances=" + societedatevacances + ", utilisateur=" + utilisateur
				+ ", dateCreation=" + dateCreation + ", metiers=" + metiers + ", prestations=" + prestations
				+ ", horaires=" + horaires + ", promotions=" + promotions + ", amCloseToDay=" + amCloseToDay
				+ ", pmCloseToDay=" + pmCloseToDay + ", notes=" + notes + "]";
	}

	
}
