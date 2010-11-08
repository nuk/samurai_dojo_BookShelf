package br.unb.erico.bookshelf;

import org.hibernate.SessionFactory;

public class BookService {

	private SessionFactory sessionFactory;
	
	public BookService(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void save(Book book) {
		sessionFactory.openSession().save(book);
	}

	public Book retrieve(String isbn) {
		return (Book) sessionFactory.openSession().createCriteria(Book.class).uniqueResult();
	}

}
