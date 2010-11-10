package br.unb.erico.bookshelf;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	private Integer year;
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Author> authors;

	public Book() {}
	
	public Book(Integer isbn, String name) {
		super();
		this.isbn = isbn;
		this.name = name;
	}

	public Book(Integer isbn, String name, String author) {
		this.isbn = isbn;
		this.name = name;
		if (author != null){
			addAuthor(author);
		}
	}

	public Book(Integer isbn, String name, String author, Integer year) {
		super();
		this.isbn = isbn;
		this.name = name;
		if (author != null){
			addAuthor(author);
		}
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

	public void addAuthor(String authorName) {
		if (this.authors == null){
			this.authors = new HashSet<Author>();
		}
		this.authors.add(new Author(authorName));
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
	
}
