package br.unb.erico.bookshelf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Author {

	@Id
	@Column
	@GeneratedValue
	private Integer id;
	
	@Column
	private String name;

	public Author() {}
	
	public Author(String authorName) {
		this.name = authorName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
