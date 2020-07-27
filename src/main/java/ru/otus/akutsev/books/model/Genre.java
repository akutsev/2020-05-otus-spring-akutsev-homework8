package ru.otus.akutsev.books.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

@Document(collection = "genres")
public class Genre {

	@Id
	private String id;

	@Field(name = "genre_name")
	private String genreName;

	public Genre(String id, String genreName) {
		this.id = id;
		this.genreName = genreName;
	}

	public Genre() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Genre)) return false;
		Genre genre = (Genre) o;
		return Objects.equals(id, genre.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
