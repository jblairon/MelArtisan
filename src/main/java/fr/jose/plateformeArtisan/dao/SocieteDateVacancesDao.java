package fr.jose.plateformeArtisan.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.jose.plateformeArtisan.beans.SocieteDateVacances;


public class SocieteDateVacancesDao {
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<SocieteDateVacances> findAll() {
		return (List<SocieteDateVacances>) hibernateTemplate.find("FROM SocieteDateVacances");
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<SocieteDateVacances> findAll(int start, int maxElts) {
		return hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("FROM SocieteDateVacances")
				.setFirstResult(start).setMaxResults(maxElts).list();
	}

	@Transactional(readOnly = true)
	public SocieteDateVacances findById(long id) {
		return hibernateTemplate.get(SocieteDateVacances.class, id);
	}

	@Transactional
	public void save(SocieteDateVacances p) {
		hibernateTemplate.save(p);
	}

	@Transactional
	public void update(SocieteDateVacances p) {
		hibernateTemplate.saveOrUpdate(p);
	}

	@Transactional(readOnly = true)
	public long nbSocieteDateVacancess() {
		return (Long) hibernateTemplate.find("SELECT COUNT(c.id) FROM SocieteDateVacances c").get(0);
	}

	@Transactional(readOnly = false)
	public void delete(long id) {
		hibernateTemplate.delete(findById(id));
	}


}
