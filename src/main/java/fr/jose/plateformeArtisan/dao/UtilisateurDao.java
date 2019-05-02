package fr.jose.plateformeArtisan.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.jose.plateformeArtisan.beans.Utilisateur;;

public class UtilisateurDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Utilisateur> findAll(){
		return (List<Utilisateur>)hibernateTemplate.find("FROM Utilisateur");
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Utilisateur> findAll(int start, int maxElts){
		return hibernateTemplate.getSessionFactory().getCurrentSession()
		.createQuery("FROM Utilisateur")
		.setFirstResult(start)
		.setMaxResults(maxElts)
		.list();
	}
	
	
	@Transactional(readOnly=true)
	public Utilisateur findById(long id) {
		Utilisateur utilisateur = hibernateTemplate.get(Utilisateur.class, id);
		System.out.println("utilisateur societes = " + utilisateur.getSocietes().size());
		return utilisateur;
	}
	
	@Transactional
	public void save(Utilisateur p) {
		hibernateTemplate.save(p);
	}
	
	@Transactional
	public void update(Utilisateur p) {
//		System.out.println("admin update = " + p.isAdmin());
		hibernateTemplate.saveOrUpdate(p);
	}
	
	@Transactional(readOnly=true)
	public long nbUtilisateurs() {
		return (Long)hibernateTemplate.find("SELECT COUNT(c.id) FROM Utilisateur c").get(0);
	}
	
	@Transactional(readOnly=false)
	public void delete(long id) {
		hibernateTemplate.delete(findById(id));
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public Utilisateur findByEmail(String email){
		List<Utilisateur> utilisateurs = (List<Utilisateur>) hibernateTemplate.find("FROM Utilisateur u WHERE u.email= ?", email);
		if(utilisateurs!=null && utilisateurs.size()>0) {
			return utilisateurs.get(0);
		}
		
		return null;
	}
	
	
	

	
	
}
