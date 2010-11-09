package br.unb.erico.bookshelf;

import static junit.framework.Assert.*;

import java.util.List;

import org.junit.Test;

public class BookServiceTest {

	@Test
	public void should_not_insert_a_Book_without_a_ISBN(){
		Book book = new Book(null,"As aventuras de pedrinho","Monteiro Lobato");
		BookService service = new BookService();
		try {
			service.save(book);
			fail("Should trhow a validation exception.");
		} catch (MissingFieldException e) {
			assertEquals("isbn",e.getMissingField());
			assertTrue(service.list().isEmpty());
		}
	}
	
	@Test
	public void should_not_insert_a_Book_without_a_Name(){
		Book book = new Book(123456789,null,"Monteiro Lobato");
		BookService service = new BookService();
		try {
			service.save(book);
			fail("Should throw a validation exception.");
		} catch (MissingFieldException e) {
			assertEquals("name",e.getMissingField());
			assertTrue(service.list().isEmpty());
		}
	}
	
	@Test
	public void should_insert_a_Book_with_all_data() throws Exception{
		Book book = new Book(123456789,"As aventura de pedrinho","Monteiro Lobato",1894);
		BookService service = new BookService();
		
		service.save(book);
		Book returnedBook = service.retrieve(123456789);
		
		assertEquals(book.getIsbn(),returnedBook.getIsbn());
		assertEquals(book.getName(),returnedBook.getName());
		assertEquals(book.getAuthor(),returnedBook.getAuthor());
		assertEquals(book.getYear(),returnedBook.getYear());
	}
	
	@Test
	public void should_insert_two_Books_with_only_name_and_isbn() throws Exception{
		BookService service = new BookService();

		Book as_aventuras_de_pedrinho = new Book(123456789,"As aventura de pedrinho");
		service.save(as_aventuras_de_pedrinho);
		
		Book a_fundacao = new Book(123456788,"A Fundação");
		service.save(a_fundacao);

		Book returnedBook = service.retrieve(123456789);
		assertEquals(as_aventuras_de_pedrinho.getIsbn(),returnedBook.getIsbn());
		assertEquals(as_aventuras_de_pedrinho.getName(),returnedBook.getName());
		
		returnedBook = service.retrieve(123456788);
		assertEquals(a_fundacao.getIsbn(),returnedBook.getIsbn());
		assertEquals(a_fundacao.getName(),returnedBook.getName());
	}
	
	@Test
	public void should_list_all_inserted_Books() throws Exception{
		BookService service = new BookService();
		
		Book as_aventuras_de_pedrinho = new Book(123456789,"As aventura de pedrinho");
		service.save(as_aventuras_de_pedrinho);
		
		Book a_fundacao = new Book(123456788,"A Fundação");
		service.save(a_fundacao);
		
		List<Book> books = service.list();
		assertEquals(2,books.size());
		assertEquals(as_aventuras_de_pedrinho.getIsbn(),books.get(0).getIsbn());
		assertEquals(a_fundacao.getIsbn(),books.get(1).getIsbn());
	}
	
}
