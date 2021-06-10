package com.visma.libraryapp;

import com.visma.libraryapp.entity.Book;
import com.visma.libraryapp.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void findByGuid_success() {
        Book book = new Book();
        UUID guid = book.getGuid();

        bookRepository.save(book);

        Book book2 = bookRepository.findByGuid(guid);

        assertEquals(book, book2);
    }
}
