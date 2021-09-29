package ru.job4j.forum.exceptionhandling;

public class NoSuchPostException extends RuntimeException {
    public NoSuchPostException(String message) {
        super(message);
    }
}
