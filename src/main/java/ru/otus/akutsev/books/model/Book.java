package ru.otus.akutsev.books.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "books")
public class Book {

	@Id
	private String id;

	@Field(name = "name")
	private String name;

	@DBRef
	private Author author;

	@DBRef
	private Genre genre;

	@DBRef
	private List<Comment> comments = new ArrayList<>();

	public Book(String id, String name, Author author, Genre genre) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.genre = genre;
		this.comments = new ArrayList<>();
	}

	public Book() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", name='" + name + '\'' +
				", author=" + author.getName() +
				", genre=" + genre.getGenreName() +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Book)) return false;
		Book book = (Book) o;
		return Objects.equals(id, book.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
