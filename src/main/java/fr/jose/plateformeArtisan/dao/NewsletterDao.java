package fr.jose.plateformeArtisan.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.jose.plateformeArtisan.beans.Newsletter;
import fr.jose.plateformeArtisan.beans.Utilisateur;

public class NewsletterDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Newsletter> findAll() {
		List<Newsletter> newsletters = (List<Newsletter>) hibernateTemplate.find("FROM Newsletter");
		return newsletters;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Newsletter> findAll(int start, int maxElts) {
		List<Newsletter> newsletters = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("FROM Newsletter").setFirstResult(start).setMaxResults(maxElts).list();
		System.out.println(newsletters.size());
		return newsletters;
	}

	@Transactional(readOnly = true)
	public Newsletter findById(long id) {
		return hibernateTemplate.get(Newsletter.class, id);
	}

	@Transactional
	public void save(Newsletter p) {
		hibernateTemplate.save(p);
	}

	@Transactional
	public void update(Newsletter p) {
		hibernateTemplate.saveOrUpdate(p);
	}

	@Transactional(readOnly = true)
	public long nbNewsletters() {
		return (Long) hibernateTemplate.find("SELECT COUNT(c.id) FROM Newsletter c").get(0);
	}

	@Transactional(readOnly = false)
	public void delete(long id) {
		hibernateTemplate.delete(findById(id));
	}

	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
	public Newsletter findBySociete_id_utilisateur_id(long societe_id, long utilisateur_id) {
		List<Newsletter> n = (List<Newsletter>) hibernateTemplate.find(
				"FROM Newsletter n WHERE n.societe_id= ?" + " and n.utilisateur_id=?", societe_id, utilisateur_id);

//		List<Newsletter> n = (List<Newsletter>) hibernateTemplate.find("FROM Newsletter n WHERE n.societe_id= ?", societe_id);

		if (n != null && n.size() > 0) {
			return n.get(0);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
	public List<Newsletter> findBySociete_id(long societe_id) {
		System.out.println("---- societe_id = ");
		List<Newsletter> n = new ArrayList<Newsletter>();

		try {
			System.out.println("dans try de findBySociete_d");
			n = (List<Newsletter>) hibernateTemplate.find("FROM Newsletter n");
		} catch (Exception e) {
			System.out.println("exception = " + e.getMessage());
		}

		if (n != null && n.size() > 0) {
			System.out.println("n est non null");
			return n;
		} else {

			return null;
		}
	}

}
