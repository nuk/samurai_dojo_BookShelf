package br.unb.erico.bookshelf;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class BookService {

	private SessionFactory sessionFactory;
	
	public BookService(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(Book book) throws MissingFieldException,DuplicateException{
		
		validate(book);
		sessionFactory.openSession().save(book);
	}

	public Book retrieve(Integer isbn) {
		Criteria criteria = sessionFactory.openSession().createCriteria(Book.class);
		criteria.add(Restrictions.eq("isbn", isbn));
		return (Book) criteria.uniqueResult();
	}

	public Book retrieve(String string) {
		Criteria criteria = sessionFactory.openSession().createCriteria(Book.class);
		criteria.add(Restrictions.ilike("name", string, MatchMode.ANYWHERE));
		return (Book) criteria.uniqueResult();
	}
	
	private void validate (Book book) throws MissingFieldException, DuplicateException  {
		if (book.getIsbn() == null){
			throw new MissingFieldException("isbn");
		}else if ((book.getName() == null) || (book.getName().trim().isEmpty())){
			throw new MissingFieldException("name");
		}
		if (retrieve(book.getIsbn()) != null){
			throw new DuplicateException();
		}
		
		
	}

}
