package br.unb.erico.bookshelf;

public class Book {

	private Integer isbn;
	private String name;
	private String author;
	private Integer year;

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
	
}
