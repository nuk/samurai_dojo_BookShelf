package br.unb.erico.bookshelf;

import junit.framework.Assert;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

public class TestServiceBook {

	private BookService service;
	
	@Before
	public void setUp(){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); 
		service = new BookService(sessionFactory);
	}
	
	@Test
	public void should_not_insert_a_book_without_a_isbn() throws Exception{
		Book book = new Book(null,"As aventuras de Pedrinho", "Monteiro Lobato", 1900);
		try{
			service.save(book);
			Assert.fail("Expected a validation Exception.");
		}catch(MissingFieldException e){
			Assert.assertEquals("isbn",e.getMissingField());
		}
	}
	
	@Test
	public void should_not_insert_a_book_without_a_name() throws Exception{
		Book book = new Book(123456789,null, "Monteiro Lobato", 1900);
		try{
			service.save(book);
			Assert.fail("Expected a validation Exception.");
		}catch(MissingFieldException e){
			Assert.assertEquals("name",e.getMissingField());
		}
	}
	
	@Test
	public void should_not_insert_a_book_with_a_blank_name() throws Exception{
		Book book = new Book(2234234, "", "Machado de Assis", 1890);
		try{
			service.save(book);
			Assert.fail("Expected a validation Exception.");
		}catch(MissingFieldException e){
			Assert.assertEquals("name", e.getMissingField());
		}
	}
	
	@Test
	public void should_not_insert_a_book_with_a_name_with_space() throws Exception{
		Book book = new Book(2234234, " ", "Machado de Assis", 1890);
		try{
			service.save(book);
			Assert.fail("Expected a validation Exception.");
		}catch(MissingFieldException e){
			Assert.assertEquals("name", e.getMissingField());
		}
	}
	
	@Test 
	public void should_not_allow_to_insert_two_books_with_the_same_isbn() throws Exception{
		Book as_aventuras_de_pedrinho = new Book(123456789,"As aventuras de Pedrinho", "Monteiro Lobato", 1900);
		service.save(as_aventuras_de_pedrinho);
		
		Book sitio_do_pica_pau = new Book(123456789,"Sítio do Pica Pau Amarelo", "Monteiro Lobato", 1900);
		try {
			service.save(sitio_do_pica_pau);
			Assert.fail("Duplicated Exception expected.");
		} catch (DuplicateException e) {}// SUCCESS
	}
	
	@Test 
	public void should_retrieve_a_inserted_book() throws Exception{
		Book book = new Book(123456789,"As aventuras de Pedrinho", "Monteiro Lobato", 1900);
		service.save(book);
		
		Book returnedBook = service.retrieve(123456789);
		
		Assert.assertEquals(book.getIsbn(), returnedBook.getIsbn());
		Assert.assertEquals(book.getName(), returnedBook.getName());
		Assert.assertEquals(book.getAuthor(), returnedBook.getAuthor());
		Assert.assertEquals(book.getAno(), returnedBook.getAno());
	}
	
	@Test 
	public void should_retrieve_two_different_inserted_books() throws Exception{
		Book as_aventuras_de_pedrinho = new Book(123456789,"As aventuras de Pedrinho", "Monteiro Lobato", 1900);
		service.save(as_aventuras_de_pedrinho);
		
		Book a_fundacao = new Book(1111111111,"A Fundação", "Isaac Asimov", 1976);
		service.save(a_fundacao);
		
		Book returnedBook = service.retrieve(123456789);
		Assert.assertEquals(as_aventuras_de_pedrinho.getIsbn(), returnedBook.getIsbn());
		
		returnedBook = service.retrieve(1111111111);
		Assert.assertEquals(a_fundacao.getIsbn(), returnedBook.getIsbn());
	}

	@Test
	public void should_retrieve_a_book_by_part_of_name() throws Exception{
		Book book = new Book(2234234, "Sitio do Pica Pau Amarelo", "Machado de Assis", 1890);
		service.save(book);
		Book returnedBook = service.retrieve("Sitio");
		
		Assert.assertEquals(book.getIsbn(), returnedBook.getIsbn());
	}
	
	
}
