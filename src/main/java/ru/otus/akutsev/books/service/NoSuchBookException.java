package ru.otus.akutsev.books.service;

public class NoSuchBookException extends RuntimeException {
	public NoSuchBookException(String message) {
		super(message);
	}
}
