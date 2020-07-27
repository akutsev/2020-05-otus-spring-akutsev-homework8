package ru.otus.akutsev.books.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookDao extends MongoRepository<Book, Long> {
	Book save(Book book);

	Optional<Book> findAById(String id);

	List<Book> findAll();

	void deleteById(String id);
}
