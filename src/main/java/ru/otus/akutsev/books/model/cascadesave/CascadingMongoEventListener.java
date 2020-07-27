package ru.otus.akutsev.books.model.cascadesave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.otus.akutsev.books.model.Book;

@Component
public class CascadingMongoEventListener extends AbstractMongoEventListener<Object> {
	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public void onBeforeConvert(BeforeConvertEvent<Object> event) {
		Object source = event.getSource();
		if ((source instanceof Book) && (((Book) source).getAuthor() != null) && (((Book) source).getGenre() != null)) {
			mongoOperations.save(((Book) source).getAuthor());
			mongoOperations.save(((Book) source).getGenre());
		}
	}
}
