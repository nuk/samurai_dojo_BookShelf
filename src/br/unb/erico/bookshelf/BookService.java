package br.unb.erico.bookshelf;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class BookService {

	private SessionFactory sessionFactory;
	
	public BookService(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void save(Book book) throws MissingFieldException, UniqueFieldViolationException {
		if (book.getIsbn() == null){
			throw new MissingFieldException("isbn");
		}else if (book.getName() == null){
			throw new MissingFieldException("name");
		}else if (retrieve(book.getIsbn()) != null){
			throw new UniqueFieldViolationException("isbn");
		}
		Session session = sessionFactory.openSession();
		session.save(book);
		session.flush();
		session.close();
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

	@SuppressWarnings("unchecked")
	public List<Book> list(String name) {
		Criteria criteria = sessionFactory.openSession().createCriteria(Book.class);
		criteria.add(Restrictions.like("name", name,MatchMode.ANYWHERE));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Book> listByAuthor(String authorName) {
		Criteria criteria = sessionFactory.openSession().createCriteria(Book.class);
		criteria.createCriteria("authors")
			.add(Restrictions.ilike("name", authorName));
		return criteria.list();
	}

}
