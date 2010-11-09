package br.unb.erico.bookshelf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class BookService {

	private SessionFactory sessionFactory;
	
	public BookService(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void save(Book book) throws MissingFieldException {
		if (book.getIsbn() == null){
			throw new MissingFieldException("isbn");
		}else if (book.getName() == null){
			throw new MissingFieldException("name");
		}
		sessionFactory.openSession().save(book);
	}

	@SuppressWarnings("unchecked")
	public List<Book> list() {
		Criteria criteria = sessionFactory.openSession().createCriteria(Book.class);
		return criteria.list();
	}

	public Book retrieve(Integer isbn) {
		Criteria criteria = sessionFactory.openSession().createCriteria(Book.class);
		criteria.add(Restrictions.eq("isbn", isbn));
		return (Book) criteria.uniqueResult();
	}

}
