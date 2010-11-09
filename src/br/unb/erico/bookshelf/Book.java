package br.unb.erico.bookshelf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	private Integer year;

	public Book() {}
	
	public Book(Integer isbn, String name) {
		super();
		this.isbn = isbn;
		this.name = name;
	}

	public Book(Integer isbn, String name, String author) {
		this.isbn = isbn;
		this.name = name;
		this.author = author;
	}

	public Book(Integer isbn, String name, String author, Integer year) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.year = year;
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
