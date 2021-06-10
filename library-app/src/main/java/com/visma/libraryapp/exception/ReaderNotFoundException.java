package com.visma.libraryapp.exception;

public class ReaderNotFoundException extends Exception {
    public ReaderNotFoundException() {
        super("Reader not found");
    }
}
