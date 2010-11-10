package br.unb.erico.bookshelf;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

public class BookServiceTest {

	private BookService service;
	
	@Before
	public void setUp(){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		service = new BookService(sessionFactory);
	}
	
	@Test
	public void should_not_insert_a_Book_without_a_ISBN() throws Exception{
		Book book = new Book(null,"As aventuras de pedrinho","Monteiro Lobato");
		try {
			service.save(book);
			fail("Should trhow a validation exception.");
		} catch (MissingFieldException e) {
			assertEquals("isbn",e.getMissingField());
			assertTrue(service.list().isEmpty());
		}
	}
	
	@Test
	public void should_not_insert_a_Book_without_a_Name() throws Exception{
		Book book = new Book(123456789,null,"Monteiro Lobato");
		try {
			service.save(book);
			fail("Should throw a validation exception.");
		} catch (MissingFieldException e) {
			assertEquals("name",e.getMissingField());
			assertTrue(service.list().isEmpty());
		}
	}
	
	@Test
	public void should_not_insert_a_Book_with_the_same_isbn_as_other() throws Exception{
		Book as_aventuras_de_pedrinho = new Book(123456789,"As aventuras de pedrinho","Monteiro Lobato");
		service.save(as_aventuras_de_pedrinho);
		try {
			Book as_viagens_de_pedrinho = new Book(123456789,"As viagens de pedrinho","Monteiro Lobato");
			service.save(as_viagens_de_pedrinho);
			fail("Should throw a validation exception.");
		} catch (UniqueFieldViolationException e) {
			assertEquals("isbn",e.getDuplicatedField());
			assertEquals(1,service.list().size());
		}
	}
	
	@Test
	public void should_insert_a_Book_with_all_data() throws Exception{
		Book book = new Book(123456789,"As aventura de pedrinho","Monteiro Lobato",1894);
		
		service.save(book);
		Book returnedBook = service.retrieve(123456789);
		
		assertEquals(book.getIsbn(),returnedBook.getIsbn());
		assertEquals(book.getName(),returnedBook.getName());
		assertEquals(book.getAuthors().size(),returnedBook.getAuthors().size());
		assertEquals(book.getYear(),returnedBook.getYear());
	}
	
	@Test
	public void should_insert_two_Books_with_only_name_and_isbn() throws Exception{
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
	public void should_insert_a_book_with_multiple_authors() throws Exception{
		Book the_google = new Book(113456788,"The Google");
		the_google.addAuthor("Larry Page");
		the_google.addAuthor("Sergey Brin");
		service.save(the_google);
		
		Book returnedBook = service.retrieve(113456788);
		assertEquals(the_google.getIsbn(),returnedBook.getIsbn());
		assertEquals(the_google.getName(),returnedBook.getName());
		assertEquals(2,returnedBook.getAuthors().size());
	}
	
	@Test
	public void should_list_all_inserted_Books() throws Exception{
		Book as_aventuras_de_pedrinho = new Book(123456789,"As aventura de pedrinho");
		service.save(as_aventuras_de_pedrinho);
		
		Book a_fundacao = new Book(123456788,"A Fundação");
		service.save(a_fundacao);
		
		List<Book> books = service.list();
		assertEquals(2,books.size());
		assertEquals(as_aventuras_de_pedrinho.getIsbn(),books.get(0).getIsbn());
		assertEquals(a_fundacao.getIsbn(),books.get(1).getIsbn());
	}
	
	@Test
	public void should_retrieve_nothing_for_an_empty_database(){
		assertNull(service.retrieve(123456789));
	}
	
	@Test
	public void should_list_nothing_for_an_empty_database(){
		assertTrue(service.list().isEmpty());
	}
	
	@Test
	public void should_list_book_with_a_part_if_the_name_in_the_end() throws Exception{
		Book as_aventuras_de_pedrinho = new Book(123456789,"As aventura de pedrinho");
		service.save(as_aventuras_de_pedrinho);
		
		Book a_fundacao = new Book(123456788,"A Fundação");
		service.save(a_fundacao);
		
		Book a_2a_fundacao = new Book(123456787,"A Segunda Fundação");
		service.save(a_2a_fundacao);
		
		assertEquals(2,service.list("Fundação").size());
	}
	
	@Test
	public void should_list_book_with_a_part_if_the_name_in_the_middle() throws Exception{
		Book as_aventuras_de_pedrinho = new Book(123456789,"As aventura de pedrinho");
		service.save(as_aventuras_de_pedrinho);
		
		Book a_fundacao = new Book(123456788,"A Fundação");
		service.save(a_fundacao);
		
		Book a_fundacao_e_o_imperio = new Book(123456786,"A Fundação e o Império");
		service.save(a_fundacao_e_o_imperio);
		
		assertEquals(2,service.list("Fundação").size());
	}
	
	@Test
	public void should_list_nothing_for_a_name_that_does_not_exist() throws Exception{
		Book as_aventuras_de_pedrinho = new Book(123456789,"As aventura de pedrinho");
		service.save(as_aventuras_de_pedrinho);
		
		Book a_fundacao = new Book(123456788,"A Fundação");
		service.save(a_fundacao);
		
		Book a_fundacao_e_o_imperio = new Book(123456786,"A Fundação e o Império");
		service.save(a_fundacao_e_o_imperio);
		
		assertTrue(service.list("Anéis").isEmpty());
	}
	
	@Test
	public void should_list_all_books_from_an_author() throws Exception{
		Book a_fundacao = new Book(123456788,"A Fundação","Isaac Asimov");
		service.save(a_fundacao);
		
		Book a_fundacao_e_o_imperio = new Book(123456786,"A Fundação e o Império","Isaac Asimov");
		service.save(a_fundacao_e_o_imperio);
		
		assertEquals(2,service.listByAuthor("Isaac Asimov").size());
	}
	
	@Test
	public void should_list_all_books_from_an_author_ignoring_case() throws Exception{
		Book a_fundacao = new Book(123456788,"A Fundação","Isaac Asimov");
		service.save(a_fundacao);
		
		Book a_fundacao_e_o_imperio = new Book(123456786,"A Fundação e o Império","Isaac Asimov");
		service.save(a_fundacao_e_o_imperio);
		
		assertEquals(2,service.listByAuthor("isaac asimov").size());
	}
	
	@Test
	public void should_only_list_books_from_that_author() throws Exception{
		Book as_aventuras_de_pedrinho = new Book(123456789,"As aventura de pedrinho","Monteiro Lobato");
		service.save(as_aventuras_de_pedrinho);
		
		Book a_fundacao = new Book(123456788,"A Fundação","Isaac Asimov");
		service.save(a_fundacao);
		
		Book a_fundacao_e_o_imperio = new Book(123456786,"A Fundação e o Império","Isaac Asimov");
		service.save(a_fundacao_e_o_imperio);
		
		assertEquals(2,service.listByAuthor("Isaac Asimov").size());
	}
	
	@Test
	public void should_list_nothing_for_a_non_existing_author() throws Exception{
		Book as_aventuras_de_pedrinho = new Book(123456789,"As aventura de pedrinho","Monteiro Lobato");
		service.save(as_aventuras_de_pedrinho);
		
		Book a_fundacao = new Book(123456788,"A Fundação","Isaac Asimov");
		service.save(a_fundacao);
		
		Book a_fundacao_e_o_imperio = new Book(123456786,"A Fundação e o Império","Isaac Asimov");
		service.save(a_fundacao_e_o_imperio);
		
		assertTrue(service.listByAuthor("J.R.R. Tolkien").isEmpty());
	}
	
}
