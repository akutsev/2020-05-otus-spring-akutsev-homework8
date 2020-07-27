package ru.otus.akutsev.books.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

@Document(collection = "comments")
public class Comment {

	@Id
	private String id;

	@Field(name = "text")
	private String text;

	public Comment(String id, String text) {
		this.id = id;
		this.text = text;
	}

	public Comment(String text) {
		this.text = text;
	}

	public Comment() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Comment)) return false;
		Comment comment = (Comment) o;
		return Objects.equals(id, comment.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Comment{" +
				"text='" + text + '\'' +
				'}';
	}
}
