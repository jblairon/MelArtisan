package fr.jose.plateformeArtisan.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.jose.plateformeArtisan.beans.Contact;


public class ContactDao {
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Contact> findAll() {
		return (List<Contact>) hibernateTemplate.find("FROM Contact");
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Contact> findAll(int start, int maxElts) {
		return hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("FROM Contact")
				.setFirstResult(start).setMaxResults(maxElts).list();
	}

	@Transactional(readOnly = true)
	public Contact findById(long id) {
		return hibernateTemplate.get(Contact.class, id);
	}

	@Transactional
	public void save(Contact p) {
		hibernateTemplate.save(p);
	}

	@Transactional
	public void update(Contact p) {
//		System.out.println("admin update = " + p.isAdmin());
		hibernateTemplate.saveOrUpdate(p);
	}

	@Transactional(readOnly = true)
	public long nbContacts() {
		return (Long) hibernateTemplate.find("SELECT COUNT(c.id) FROM Contact c").get(0);
	}

	@Transactional(readOnly = false)
	public void delete(long id) {
		hibernateTemplate.delete(findById(id));
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Contact findByEmail(String email) {
		List<Contact> contacts = (List<Contact>) hibernateTemplate
				.find("FROM Contact u WHERE u.email= ?", email);
		if (contacts != null && contacts.size() > 0) {
			return contacts.get(0);
		}

		return null;
	}
}
