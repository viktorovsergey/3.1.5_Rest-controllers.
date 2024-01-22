package ru.kata.spring.boot_security.demo.exeption_handihg;

public class NoSuchUserException extends RuntimeException{
    public NoSuchUserException(String message) {
        super(message);
    }
}
