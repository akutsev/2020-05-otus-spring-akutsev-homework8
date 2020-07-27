package ru.otus.akutsev.books.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.akutsev.books.model.Genre;

import java.util.Optional;

@Repository
public interface GenreDao extends MongoRepository<Genre, Long> {
	Genre save(Genre genre);

	Optional<Genre> findById(String id);

	void deleteById(String id);
}
