package ru.otus.akutsev.books;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.akutsev.books.dao.BookDao;
import ru.otus.akutsev.books.data.PrepareData;
import ru.otus.akutsev.books.model.Author;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Comment;
import ru.otus.akutsev.books.model.Genre;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест ДАО работы с книгами")
@DataMongoTest
@Import(PrepareData.class)
public class BookDaoUnitTest {

	@Autowired
	private BookDao bookDao;

	@Autowired
	private MongoOperations mongoOperations;

	@Autowired
	private PrepareData prepareData;

	@BeforeEach
	private void prepareData() {
		prepareData.fillData(mongoOperations);
	}

	@DisplayName("вставка новой книги и получение ее по ИД")
	@Test
	public void addBookGetByIdTest() {
		String authorId = "1";
		Author author = mongoOperations.findById(authorId, Author.class);

		String genreId = "2";
		Genre genre = mongoOperations.findById(genreId, Genre.class);

		Comment comment1 = new Comment();
		Comment comment2 = new Comment();
		comment1.setText("Very booooooring book");
		comment2.setText("really depressive");
		mongoOperations.save(comment1);
		mongoOperations.save(comment2);

		Book book = new Book();
		String bookName = "Anna Karenina";
		book.setName(bookName);
		book.setAuthor(author);
		book.setGenre(genre);
		book.addComment(comment1);
		book.addComment(comment2);

		String id = bookDao.save(book).getId();

		Book bookFromDb = bookDao.findAById(id).get();

		assertEquals(bookName, bookFromDb.getName());
		assertEquals(author, bookFromDb.getAuthor());
		assertEquals(genre, bookFromDb.getGenre());
		assertArrayEquals(new Comment[]{comment1, comment2}, bookFromDb.getComments().toArray());
	}

	@DisplayName("возвращает ИД всех книг")
	@Test
	public void getAllTest() {
		String[] expected = {"1", "2"};

		List<Book> allBooks = bookDao.findAll();
		String[] actual = allBooks.stream()
				.map(Book::getId)
				.toArray(String[]::new);

		assertArrayEquals(expected, actual);
	}

	@DisplayName("удаление книги")
	@Test
	public void deleteBookTest() {
		String id = "2";

		assertNotEquals(Optional.empty(), bookDao.findAById(id));
		bookDao.deleteById(id);
		assertEquals(Optional.empty(), bookDao.findAById(id));
	}

	@AfterEach
	private void clearData() {
		prepareData.clearData(mongoOperations);
	}
}
