package ru.otus.akutsev.books.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.akutsev.books.model.Author;

import java.util.Optional;

@Repository
public interface AuthorDao extends MongoRepository<Author, String> {
	Author save(Author author);

	Optional<Author> findById(String id);

	void deleteById(String id);
}