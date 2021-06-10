package com.visma.libraryapp.exception;

public class BookIsNotAvailableException extends Exception{
    public BookIsNotAvailableException() {
        super("Book is already taken");
    }
}
