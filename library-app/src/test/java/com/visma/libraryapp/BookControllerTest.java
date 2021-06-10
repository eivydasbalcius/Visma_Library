package com.visma.libraryapp;

import com.visma.libraryapp.controller.BookController;
import com.visma.libraryapp.entity.Book;
import com.visma.libraryapp.repository.BookRepository;
import com.visma.libraryapp.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @MockBean
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findByGuid_whenCorrectGuid_thenReturnsOk() throws Exception {
        Book book = new Book();
        UUID guid = book.getGuid();

        String guidAsString = guid.toString();

        when(bookService.getBookByGuid(guidAsString)).thenReturn(book);

        this.mockMvc.perform(get("/api/books/getBookByGuid").param("guid", guidAsString))
                .andDo(print())
                .andExpect(status().isOk());
    }



}
