package fr.jose.plateformeArtisan.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.jose.plateformeArtisan.beans.Promotion;


public class PromotionDao {
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Promotion> findAll() {
		return (List<Promotion>) hibernateTemplate.find("FROM Promotion");
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Promotion> findAll(int start, int maxElts) {
		return hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("FROM Promotion")
				.setFirstResult(start).setMaxResults(maxElts).list();
	}

	@Transactional(readOnly = true)
	public Promotion findById(long id) {
		System.out.println("promo ========== =");
		Promotion promotion =  hibernateTemplate.get(Promotion.class, id);
		
		return promotion;
	}

	@Transactional
	public void save(Promotion p) {
		hibernateTemplate.save(p);
	}

	@Transactional
	public void update(Promotion p) {
//		System.out.println("admin update = " + p.isAdmin());
		hibernateTemplate.saveOrUpdate(p);
	}

	@Transactional(readOnly = true)
	public long nbPromotions() {
		return (Long) hibernateTemplate.find("SELECT COUNT(c.id) FROM Promotion c").get(0);
	}

	@Transactional(readOnly = false)
	public void delete(long id) {
		hibernateTemplate.delete(findById(id));
	}


}
