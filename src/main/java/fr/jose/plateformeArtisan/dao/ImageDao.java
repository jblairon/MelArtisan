package fr.jose.plateformeArtisan.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.jose.plateformeArtisan.beans.Image;
import fr.jose.plateformeArtisan.beans.Societe;

public class ImageDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Image> findAll() {
		System.out.println("dans findAll");
		
		List<Image> images =  (List<Image>) hibernateTemplate.find("FROM Image");
//		System.out.println("taille = " + images.get(0).getMetiers().size());
		return images;
	}
	
	

//	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
//	public List<Image> findAll(int start, int maxElts) {
//		System.out.println("Dans findAll");
//		return hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("FROM Image")
//				/*.setFirstResult(start).setMaxResults(maxElts)*/.list();
//	}

	@Transactional(readOnly = true)
	public Image findById(long id) {
		Image image = hibernateTemplate.get(Image.class, id);
		return image;
	}

	@Transactional
	public void save(Image p) {
		hibernateTemplate.save(p);
	}

	@Transactional
	public void update(Image p) {
//		System.out.println("admin update = " + p.isAdmin());
		hibernateTemplate.saveOrUpdate(p);
	}

	@Transactional(readOnly = true)
	public long nbImages() {
		return (Long) hibernateTemplate.find("SELECT COUNT(c.id) FROM Image c").get(0);
	}

	@Transactional(readOnly = false)
	public void delete(long id) {
		hibernateTemplate.delete(findById(id));
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Image findByEmail(String email) {
		List<Image> images = (List<Image>) hibernateTemplate.find("FROM Image u WHERE u.email= ?", email);
		if (images != null && images.size() > 0) {
			return images.get(0);
		}

		return null;
	}

}
