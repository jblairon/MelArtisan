package fr.jose.plateformeArtisan.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.internal.logging.Logger;

import fr.jose.plateformeArtisan.beans.Metier;
import fr.jose.plateformeArtisan.beans.Societe;

public class SocieteDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Societe> findAll() {
		
		List<Societe> societes =  (List<Societe>) hibernateTemplate.find("FROM Societe");
		return societes;
	}

//	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
//	public List<Societe> findAll(int start, int maxElts) {
//		System.out.println("Dans findAll");
//		return hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("FROM Societe")
//				/*.setFirstResult(start).setMaxResults(maxElts)*/.list();
//	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Societe> findByCategorie(int start, int max, long categorieId) {
		System.out.println("categorie_id = " + categorieId);
		List<Societe> societes = (List<Societe>) hibernateTemplate.find("FROM Societe u WHERE u.categorie.id= ?", categorieId);
		System.out.println("societes = " + societes.size());
		for (int i = 0; i < societes.size(); i++) {
			System.out.println("taille metiers = " + societes.get(i).getMetiers().size());
			System.out.println("categorie = " + societes.get(i).getCategorie().getLibelle());
		}
		
		return societes;
	}
	
//	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
//	public Societe findByMetier( long metierId) {
//		Societe s = hibernateTemplate.get(Societe.class, id);
//		
//		return societe;
//	}

	@Transactional(readOnly = true)
	public Societe findById(long id) {
		Societe s = hibernateTemplate.get(Societe.class, id);
		if(s!=null && s.getCategorie()!=null)
			System.out.println("dans societe.findById categorie id = " + s.getCategorie().getId());
		if(s!=null && s.getMetiers()!=null)
			System.out.println("dans societe.findById metier.size() = " + s.getMetiers().size());
		if(s!=null && s.getContact()!=null)
			System.out.println("dans societe.findById contact id = " + s.getContact().getId());
		if(s!=null && s.getPrestations()!=null)
			System.out.println("dans societe.findById prestation taille = " + s.getPrestations().size());
		if(s!=null && s.getAdresse()!=null)
			System.out.println("dans societe.findById adresse = " + s.getAdresse().getVille());
		if(s!=null )
			System.out.println("dans societe.findById horaires taille = " + s.getHoraires().size());
		if(s!=null && s.getNotes()!=null )
			System.out.println("dans societe.findById notes taille = " + s.getNotes().size());
		if(s!=null && s.getPromotions()!=null )
			System.out.println("dans societe.findById promotions taille = " + s.getPromotions().size());
		
		return s;
	}

	@Transactional
	public void save(Societe p) {
		hibernateTemplate.save(p);
	}

	@Transactional
	public void update(Societe p) {
		hibernateTemplate.saveOrUpdate(p);
	}

	@Transactional(readOnly = true)
	public long nbSocietes() {
		return (Long) hibernateTemplate.find("SELECT COUNT(c.id) FROM Societe c").get(0);
	}

	@Transactional(readOnly = false)
	public void delete(long id) {
		hibernateTemplate.delete(findById(id));
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Societe findByEmail(String email) {
		List<Societe> societes = (List<Societe>) hibernateTemplate.find("FROM Societe u WHERE u.email= ?", email);
		if (societes != null && societes.size() > 0) {
			return societes.get(0);
		}

		return null;
	}

}
