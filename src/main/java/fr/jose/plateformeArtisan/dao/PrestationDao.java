package fr.jose.plateformeArtisan.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.jose.plateformeArtisan.beans.Prestation;

public class PrestationDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Prestation> findAll() {
		List<Prestation> prestations = (List<Prestation>) hibernateTemplate.find("FROM Prestation");
		System.out.println("prestations = " + prestations.get(1).getLibelle());
		return prestations;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Prestation> findAll(int start, int maxElts) {
		List<Prestation> prestations =  hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("FROM Prestation")
				.setFirstResult(start).setMaxResults(maxElts).list();
		System.out.println(prestations.size());
		return prestations;
	}

	@Transactional(readOnly = true)
	public Prestation findById(long id) {
		return hibernateTemplate.get(Prestation.class, id);
	}

	@Transactional
	public void save(Prestation p) {
		hibernateTemplate.save(p);
	}

	@Transactional
	public void update(Prestation p) {
		hibernateTemplate.saveOrUpdate(p);
	}

	@Transactional(readOnly = true)
	public long nbPrestations() {
		return (Long) hibernateTemplate.find("SELECT COUNT(c.id) FROM Prestation c").get(0);
	}

	@Transactional(readOnly = false)
	public void delete(long id) {
		hibernateTemplate.delete(findById(id));
	}


}
