package fr.jose.plateformeArtisan.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.jose.plateformeArtisan.beans.Adresse;


public class AdresseDao {
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Adresse> findAll() {
		return (List<Adresse>) hibernateTemplate.find("FROM Adresse");
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Adresse> findAll(int start, int maxElts) {
		return hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("FROM Adresse")
				.setFirstResult(start).setMaxResults(maxElts).list();
	}

	@Transactional(readOnly = true)
	public Adresse findById(long id) {
		return hibernateTemplate.get(Adresse.class, id);
	}

	@Transactional
	public void save(Adresse p) {
		hibernateTemplate.save(p);
	}

	@Transactional
	public void update(Adresse p) {
//		System.out.println("admin update = " + p.isAdmin());
		hibernateTemplate.saveOrUpdate(p);
	}

	@Transactional(readOnly = true)
	public long nbAdresses() {
		return (Long) hibernateTemplate.find("SELECT COUNT(c.id) FROM Adresse c").get(0);
	}

	@Transactional(readOnly = false)
	public void delete(long id) {
		hibernateTemplate.delete(findById(id));
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Adresse findByEmail(String email) {
		List<Adresse> adresses = (List<Adresse>) hibernateTemplate
				.find("FROM Adresse u WHERE u.email= ?", email);
		if (adresses != null && adresses.size() > 0) {
			return adresses.get(0);
		}

		return null;
	}
}
