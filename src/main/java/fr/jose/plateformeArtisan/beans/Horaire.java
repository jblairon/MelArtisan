package fr.jose.plateformeArtisan.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Horaire implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String amOpen;
	private String amClose;
	private String pmOpen;
	private String pmClose;
	
	private Jour jour;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAmOpen() {
		return amOpen;
	}

	public void setAmOpen(String amOpen) {
		this.amOpen = amOpen;
	}

	public String getAmClose() {
		return amClose;
	}

	public void setAmClose(String amClose) {
		this.amClose = amClose;
	}

	public String getPmOpen() {
		return pmOpen;
	}

	public void setPmOpen(String pmOpen) {
		this.pmOpen = pmOpen;
	}

	public String getPmClose() {
		return pmClose;
	}

	public void setPmClose(String pmClose) {
		this.pmClose = pmClose;
	}

	public Jour getJour() {
		return jour;
	}

	public void setJour(Jour jour) {
		this.jour = jour;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amClose == null) ? 0 : amClose.hashCode());
		result = prime * result + ((amOpen == null) ? 0 : amOpen.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((jour == null) ? 0 : jour.hashCode());
		result = prime * result + ((pmClose == null) ? 0 : pmClose.hashCode());
		result = prime * result + ((pmOpen == null) ? 0 : pmOpen.hashCode());
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
		Horaire other = (Horaire) obj;
		if (amClose == null) {
			if (other.amClose != null)
				return false;
		} else if (!amClose.equals(other.amClose))
			return false;
		if (amOpen == null) {
			if (other.amOpen != null)
				return false;
		} else if (!amOpen.equals(other.amOpen))
			return false;
		if (id != other.id)
			return false;
		if (jour != other.jour)
			return false;
		if (pmClose == null) {
			if (other.pmClose != null)
				return false;
		} else if (!pmClose.equals(other.pmClose))
			return false;
		if (pmOpen == null) {
			if (other.pmOpen != null)
				return false;
		} else if (!pmOpen.equals(other.pmOpen))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Horaire [id=" + id + ", amOpen=" + amOpen + ", amClose=" + amClose + ", pmOpen=" + pmOpen + ", pmClose="
				+ pmClose + ", jour=" + jour + "]";
	}
	
	
}
