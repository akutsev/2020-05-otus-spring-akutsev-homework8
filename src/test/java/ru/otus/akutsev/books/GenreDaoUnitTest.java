package ru.otus.akutsev.books;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.akutsev.books.dao.GenreDao;
import ru.otus.akutsev.books.model.Author;
import ru.otus.akutsev.books.model.Genre;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тест ДАО работы с жанрами")
@DataMongoTest
public class GenreDaoUnitTest {

	@Autowired
	private GenreDao genreDao;

	@Autowired
	private MongoOperations mongoOperations;

	@DisplayName("вставка нового жанра и получение его по ИД")
	@Test
	public void addGenreGetByIdTest() {
		String name = "Non fiction";
		Genre genre = new Genre();
		genre.setGenreName(name);

		String id = genreDao.save(genre).getId();

		Genre genreFromDb = genreDao.findById(id).get();

		assertEquals(name, genreFromDb.getGenreName());
	}

	@DisplayName("удаление жанра")
	@Test
	public void deleteGenreTest() {
		mongoOperations.save(new Genre("2", "Drama"));
		mongoOperations.save(new Genre("4", "Thriller"));
		mongoOperations.save(new Genre("6", "Adventures"));
		mongoOperations.save(new Genre("8", "Historical"));

		String id = "8";

		assertNotEquals(Optional.empty(), genreDao.findById(id));
		genreDao.deleteById(id);
		assertEquals(Optional.empty(), genreDao.findById(id));
	}
}
