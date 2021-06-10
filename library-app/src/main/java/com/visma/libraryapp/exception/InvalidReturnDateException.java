package com.visma.libraryapp.exception;

public class InvalidReturnDateException extends Exception {
    public InvalidReturnDateException() {
        super("Book cannot be taken for more than 2 months");
    }
}
