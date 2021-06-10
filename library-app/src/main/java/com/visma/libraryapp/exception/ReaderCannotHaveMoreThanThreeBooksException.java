package com.visma.libraryapp.exception;

public class ReaderCannotHaveMoreThanThreeBooksException extends Exception{
    public ReaderCannotHaveMoreThanThreeBooksException() {
        super("Reader cannot have more than 3 books");
    }
}
