package com.visma.libraryapp;

import com.visma.libraryapp.dto.BookDto;
import com.visma.libraryapp.repository.BookRepository;
import com.visma.libraryapp.service.BookService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    @Test
    public void addBook_whenAdded_thenReturnsBookName() {
        BookDto bookDto = buildBookDto();

        String bookName = bookService.add(bookDto);

        Assertions.assertEquals(bookDto.getName(), bookName);
    }

    private BookDto buildBookDto() {
        BookDto bookDto = new BookDto();
        bookDto.setName("Knyga");
        bookDto.setAuthor("Autorius");
        bookDto.setCategory("Kategorija");
        bookDto.setIsbn("123124");
        bookDto.setLanguage("Kalba");
        bookDto.setPublicationDate(new Date());

        return bookDto;
    }
}
