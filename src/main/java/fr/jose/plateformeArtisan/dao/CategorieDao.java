package fr.jose.plateformeArtisan.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.jose.plateformeArtisan.beans.Categorie;
import fr.jose.plateformeArtisan.beans.Societe;

public class CategorieDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Categorie> findAll() {
		
		List<Categorie> categories =  (List<Categorie>) hibernateTemplate.find("FROM Categorie");
		System.out.println("taille = " + categories.get(0).getMetiers().size());
		return categories;
	}
	
	

//	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
//	public List<Categorie> findAll(int start, int maxElts) {
//		System.out.println("Dans findAll");
//		return hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("FROM Categorie")
//				/*.setFirstResult(start).setMaxResults(maxElts)*/.list();
//	}

	@Transactional(readOnly = true)
	public Categorie findById(long id) {
		long d = 1000;
		Categorie categorie = hibernateTemplate.get(Categorie.class, id);
		return categorie;
	}

	@Transactional
	public void save(Categorie p) {
		hibernateTemplate.save(p);
	}

	@Transactional
	public void update(Categorie p) {
//		System.out.println("admin update = " + p.isAdmin());
		hibernateTemplate.saveOrUpdate(p);
	}

	@Transactional(readOnly = true)
	public long nbCategories() {
		return (Long) hibernateTemplate.find("SELECT COUNT(c.id) FROM Categorie c").get(0);
	}

	@Transactional(readOnly = false)
	public void delete(long id) {
		hibernateTemplate.delete(findById(id));
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Categorie findByEmail(String email) {
		List<Categorie> categories = (List<Categorie>) hibernateTemplate.find("FROM Categorie u WHERE u.email= ?", email);
		if (categories != null && categories.size() > 0) {
			return categories.get(0);
		}

		return null;
	}

}
