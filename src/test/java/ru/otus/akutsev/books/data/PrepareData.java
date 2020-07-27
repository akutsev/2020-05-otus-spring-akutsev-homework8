package ru.otus.akutsev.books.data;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import ru.otus.akutsev.books.model.Author;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Comment;
import ru.otus.akutsev.books.model.Genre;

@Repository
public class PrepareData {
	public void fillData(MongoOperations mongoOperations) {
		Genre genre2 = new Genre("2", "Drama");
		Genre genre4 = new Genre("4", "Thriller");
		Genre genre6 = new Genre("6", "Adventures");
		Genre genre8 = new Genre("8", "Historical");

		mongoOperations.save(genre2);
		mongoOperations.save(genre4);
		mongoOperations.save(genre6);
		mongoOperations.save(genre8);

		Author author1 = new Author("1", "Lev Tolstoy");
		Author author3 = new Author("3", "Mark Twen");
		Author author5 = new Author("5", "Daria Dontsova");

		mongoOperations.save(author1);
		mongoOperations.save(author3);
		mongoOperations.save(author5);

		Comment comment1 = new Comment("1", "Book that you will never forget, 5 stars!");
		Comment comment2 = new Comment("2", "Honestly I did not like that, 3 stars");
		Comment comment3 = new Comment("3", "Be sure you will like it!");
		Comment comment4 = new Comment("4", "Really nice book about friendship");

		mongoOperations.save(comment1);
		mongoOperations.save(comment2);
		mongoOperations.save(comment3);
		mongoOperations.save(comment4);

		Book book1 = new Book("1", "War and Piece", author1, genre2);
		book1.addComment(comment1);
		book1.addComment(comment2);
		Book book2 = new Book("2", "Tom Soyer", author3, genre6);
		book2.addComment(comment3);
		book2.addComment(comment4);

		mongoOperations.save(book1);
		mongoOperations.save(book2);
	}

	public void clearData(MongoOperations mongoOperations) {
		mongoOperations.dropCollection(Book.class);
		mongoOperations.dropCollection(Comment.class);
		mongoOperations.dropCollection(Author.class);
		mongoOperations.dropCollection(Genre.class);
	}

}
