package ru.otus.akutsev.books;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.akutsev.books.dao.CommentDao;
import ru.otus.akutsev.books.data.PrepareData;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Comment;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест ДАО работы с комментариями")
@DataMongoTest
@Import(PrepareData.class)
public class CommentDaoUnitTest {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private MongoOperations mongoOperations;

	@Autowired
	private PrepareData prepareData;

	@BeforeEach
	private void prepareData() {
		prepareData.fillData(mongoOperations);
	}

	@DisplayName("вставка нового комментария и получение его по ИД")
	@Test
	public void addCommentGetByIdTest() {
		String text = "Very booooooring book";
		Comment comment = new Comment();
		comment.setText(text);

		String id = commentDao.save(comment).getId();

		Comment commentFromDb = commentDao.findById(id).get();

		assertEquals(text, commentFromDb.getText());
	}

	@DisplayName("удаление комментария")
	@Test
	public void deleteGenreTest() {
		String id = "4";

		assertNotEquals(Optional.empty(), commentDao.findById(id));
		commentDao.deleteById(id);
		assertEquals(Optional.empty(), commentDao.findById(id));
	}

	@AfterEach
	private void clearData() {
		prepareData.clearData(mongoOperations);
	}
}
