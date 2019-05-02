package fr.jose.plateformeArtisan.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.jose.plateformeArtisan.beans.Horaire;

public class HoraireDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Horaire> findAll() {
		
		List<Horaire> horaires =  (List<Horaire>) hibernateTemplate.find("FROM Horaire");
		return horaires;
	}

	@Transactional(readOnly = true)
	public Horaire findById(long id) {
		Horaire horaire = hibernateTemplate.get(Horaire.class, id);
		return horaire;
	}

	@Transactional
	public void save(Horaire p) {
		hibernateTemplate.save(p);
	}

	@Transactional
	public void update(Horaire p) {
		hibernateTemplate.saveOrUpdate(p);
	}

	@Transactional(readOnly = true)
	public long nbHoraires() {
		return (Long) hibernateTemplate.find("SELECT COUNT(c.id) FROM Horaire c").get(0);
	}

	@Transactional(readOnly = false)
	public void delete(long id) {
		hibernateTemplate.delete(findById(id));
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Horaire findByEmail(String email) {
		List<Horaire> horaires = (List<Horaire>) hibernateTemplate.find("FROM Horaire u WHERE u.email= ?", email);
		if (horaires != null && horaires.size() > 0) {
			return horaires.get(0);
		}

		return null;
	}

}
