package br.unb.erico.bookshelf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookService {

	private Map<Integer,Book> savedBooks = new HashMap<Integer, Book>();
	
	public void save(Book book) throws MissingFieldException {
		if (book.getIsbn() == null){
			throw new MissingFieldException("isbn");
		}else if (book.getName() == null){
			throw new MissingFieldException("name");
		}
		savedBooks.put(book.getIsbn(),book);
	}

	public List<Book> list() {
		return new ArrayList<Book>(savedBooks.values());
	}

	public Book retrieve(Integer isbn) {
		return savedBooks.get(isbn);
	}

}
