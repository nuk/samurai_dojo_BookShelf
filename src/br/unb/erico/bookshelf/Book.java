package br.unb.erico.bookshelf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String autor;
	
	@Column
	private String isbn;
	
	public Book() {}
	
	public Book(String nome, String autor, String isbn) {}

}
