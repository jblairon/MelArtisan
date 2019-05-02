package fr.jose.plateformeArtisan.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.jose.plateformeArtisan.beans.Metier;

public class MetierDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Metier> findAll() {
		List<Metier> metiers = (List<Metier>) hibernateTemplate.find("FROM Metier");
		System.out.println("!taille metiers = " + metiers.size());
		return metiers;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Metier> findAll(int start, int maxElts) {
		return hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("FROM Metier")
				.setFirstResult(start).setMaxResults(maxElts).list();
	}

	@Transactional(readOnly = true)
	public Metier findById(long id) {
		Metier m = hibernateTemplate.get(Metier.class, id);
		System.out.println("metier societes = " + m.getSocietes().size());
		return m;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Metier findByLibelle(String libelle) {
//		Metier metier = (Metier) hibernateTemplate.find("FROM Metier u WHERE u.libelle= ?",
//				libelle);
		List<Metier> metier = (List<Metier>) hibernateTemplate.find("FROM Metier m WHERE m.libelle= ?",libelle);
		if (metier != null ) {
			return metier.get(0);
		}

		return null;
	}

	@Transactional
	public void save(Metier p) {
		hibernateTemplate.save(p);
	}

	@Transactional
	public void update(Metier m) {
		hibernateTemplate.update(m);
	}

	@Transactional(readOnly = true)
	public long nbMetiers() {
		return (Long) hibernateTemplate.find("SELECT COUNT(c.id) FROM Metier c").get(0);
	}

	@Transactional(readOnly = false)
	public void delete(long id) {
		hibernateTemplate.delete(findById(id));
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Metier> findByCategorie(long id) {
		List<Metier> metiers = (List<Metier>) hibernateTemplate.find("FROM Metier u WHERE u.categorie.id= ?",
				id);
		if (metiers != null && metiers.size() > 0) {
			return metiers;
		}

		return null;
	}

}
