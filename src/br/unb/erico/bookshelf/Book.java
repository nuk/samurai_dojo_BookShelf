package br.unb.erico.bookshelf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;

@Entity
public class Book {

	@Id
	@Column
	@GeneratedValue
	private Integer id;
	
	@Column
	private Integer isbn;

	@Column
	private String name;

	@Column
	private String author;
	
	@Column
	private Integer ano;
	
	public Book() {}
	
	public Book(Integer isbn, String name, String author, Integer ano) {
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.ano = ano;
	}

	public Integer getIsbn() {
		return isbn;
	}

	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
