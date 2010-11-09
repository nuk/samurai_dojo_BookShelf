package br.unb.erico.bookshelf;

import junit.framework.Assert;

import org.junit.Test;

public class BookServiceTest {

	@Test
	public void should_not_insert_a_Book_without_a_ISBN(){
		Book book = new Book(null,"As aventuras de pedrinho","Monteiro Lobato");
		BookService service = new BookService();
		try {
			service.save(book);
			Assert.fail("Should trhow a validation exception.");
		} catch (Exception e) {
			Assert.assertNull(service.list());
		}
	}
	@Test
	public void should_not_insert_a_Book_without_a_Name(){
		Book book = new Book(123456789,null,"Monteiro Lobato");
		BookService service = new BookService();
		try {
			service.save(book);
			Assert.fail("Should throw a validation exception.");
		} catch (Exception e) {
			Assert.assertNull(service.list());
		}
	}
	
}
