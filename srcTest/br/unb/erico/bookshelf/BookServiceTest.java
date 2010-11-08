package br.unb.erico.bookshelf;

import junit.framework.Assert;

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
	public void should_retrieve_the_book_i_have_inserted(){
		Book as_aventuras_de_pedrinho = new Book("As aventuras de Pedrinho.","Monteiro Lobato","8571398291");
		service.save(as_aventuras_de_pedrinho);
		Assert.assertEquals(as_aventuras_de_pedrinho.getAuthor(), service.retrieve("8571398291").getAuthor());
		Assert.assertEquals(as_aventuras_de_pedrinho.getName(), service.retrieve("8571398291").getName());
	}
	
	@Test
	public void should_retrieve_the_books_i_have_inserted(){
		Book as_aventuras_de_pedrinho = new Book("As aventuras de Pedrinho.","Monteiro Lobato","8571398291");
		service.save(as_aventuras_de_pedrinho);
		Book a_fundacao = new Book("A Fundação.","Isaac Asimov","8571398292");
		service.save(a_fundacao);
		Book o_senhor_dos_aneis = new Book("O senhor dos Aneis.","J.R.R. Tolkien","8571398293");
		service.save(o_senhor_dos_aneis);
		
		Assert.assertEquals(as_aventuras_de_pedrinho.getAuthor(), service.retrieve("8571398291").getAuthor());
		Assert.assertEquals(as_aventuras_de_pedrinho.getName(), service.retrieve("8571398291").getName());
		Assert.assertEquals(a_fundacao.getAuthor(), service.retrieve("8571398292").getAuthor());
		Assert.assertEquals(a_fundacao.getName(), service.retrieve("8571398292").getName());
		Assert.assertEquals(o_senhor_dos_aneis.getAuthor(), service.retrieve("8571398293").getAuthor());
		Assert.assertEquals(o_senhor_dos_aneis.getName(), service.retrieve("8571398293").getName());
	}
	
	@Test
	public void should_retrieve_the_same_book_i_have_inserted_always(){
		Book as_aventuras_de_pedrinho = new Book("As aventuras de Pedrinho.","Monteiro Lobato","8571398291");
		service.save(as_aventuras_de_pedrinho);
		
		Assert.assertEquals(as_aventuras_de_pedrinho.getAuthor(), service.retrieve("8571398291").getAuthor());
		Assert.assertEquals(as_aventuras_de_pedrinho.getName(), service.retrieve("8571398291").getName());
		Assert.assertEquals(as_aventuras_de_pedrinho.getAuthor(), service.retrieve("8571398291").getAuthor());
		Assert.assertEquals(as_aventuras_de_pedrinho.getName(), service.retrieve("8571398291").getName());
		Assert.assertEquals(as_aventuras_de_pedrinho.getAuthor(), service.retrieve("8571398291").getAuthor());
		Assert.assertEquals(as_aventuras_de_pedrinho.getName(), service.retrieve("8571398291").getName());
	}
	
}
