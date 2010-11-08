package br.unb.erico.bookshelf;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class BookService {

	private SessionFactory sessionFactory;
	
	public BookService(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void save(Book book) {
		sessionFactory.openSession().save(book);
	}

	public Book retrieve(String isbn) {
		Criteria criteria = sessionFactory.openSession().createCriteria(Book.class);
		criteria.add(Restrictions.eq("isbn", isbn));
		return (Book) criteria.uniqueResult();
	}

}
