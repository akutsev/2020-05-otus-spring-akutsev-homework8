package ru.otus.akutsev.books;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.akutsev.books.dao.AuthorDao;
import ru.otus.akutsev.books.model.Author;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест ДАО работы с авторами")
@DataMongoTest
public class AuthorDaoUnitTest {

	@Autowired
	private AuthorDao authorDao;

	@Autowired
	private MongoOperations mongoOperations;

	@DisplayName("вставка нового автора и получение его по ИД")
	@Test
	public void addAuthorGetByIdTest() {
		String name = "Aleksandr Pushkin";
		Author author = new Author();
		author.setName(name);

		String id = authorDao.save(author).getId();

		Author authorFromDb = authorDao.findById(id).get();

		assertEquals(name, authorFromDb.getName());
	}

	@DisplayName("удаление автора")
	@Test
	public void deleteAuthorTest() {
		mongoOperations.save(new Author("1", "Lev Tolstoy"));
		mongoOperations.save(new Author("3", "Mark Twen"));
		mongoOperations.save(new Author("5", "Daria Dontsova"));

		String id = "5";

		assertNotEquals(Optional.empty(), authorDao.findById(id));
		authorDao.deleteById(id);
		assertEquals(Optional.empty(), authorDao.findById(id));
	}

}
