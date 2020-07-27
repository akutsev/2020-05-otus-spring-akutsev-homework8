package ru.otus.akutsev.books.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentDao extends MongoRepository<Comment, Long> {
	Comment save(Comment comment);

	Optional<Comment> findById(String id);

	void deleteById(String id);
}
