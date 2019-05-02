package fr.jose.plateformeArtisan.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.jose.plateformeArtisan.beans.Note;

public class NoteDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Note> findAll() {
		List<Note> notes = (List<Note>) hibernateTemplate.find("FROM Note");
		System.out.println("!taille notes = " + notes.size());
		return notes;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Note> findAll(int start, int maxElts) {
		return hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("FROM Note")
				.setFirstResult(start).setMaxResults(maxElts).list();
	}

	@Transactional(readOnly = true)
	public Note findById(long id) {
		Note m = hibernateTemplate.get(Note.class, id);
		return m;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Note findByLibelle(String libelle) {
//		Note note = (Note) hibernateTemplate.find("FROM Note u WHERE u.libelle= ?",
//				libelle);
		List<Note> note = (List<Note>) hibernateTemplate.find("FROM Note m WHERE m.libelle= ?",libelle);
		if (note != null ) {
			return note.get(0);
		}

		return null;
	}

	@Transactional
	public void save(Note p) {
		hibernateTemplate.save(p);
	}

	@Transactional
	public void update(Note m) {
		hibernateTemplate.update(m);
	}

	@Transactional(readOnly = true)
	public long nbNotes() {
		return (Long) hibernateTemplate.find("SELECT COUNT(c.id) FROM Note c").get(0);
	}

	@Transactional(readOnly = false)
	public void delete(long id) {
		hibernateTemplate.delete(findById(id));
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Note> findByCategorie(long id) {
		List<Note> notes = (List<Note>) hibernateTemplate.find("FROM Note u WHERE u.categorie.id= ?",
				id);
		if (notes != null && notes.size() > 0) {
			return notes;
		}

		return null;
	}

}
