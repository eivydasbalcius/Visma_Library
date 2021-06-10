package com.visma.libraryapp.controller;

import com.visma.libraryapp.dto.BookDto;
import com.visma.libraryapp.dto.TakeBookDto;
import com.visma.libraryapp.entity.Book;
import com.visma.libraryapp.exception.*;
import com.visma.libraryapp.repository.BookRepository;
import com.visma.libraryapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/getBookByGuid")
    public ResponseEntity<Book> getBookByGuid(@RequestParam String guid) {
        try {
            Book book = bookService.getBookByGuid(guid);
            if (book == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            List<Book> books = bookRepository.findAll();
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody BookDto bookDto) {
        try {
            String bookName = bookService.add(bookDto);
            return new ResponseEntity<>(String.format("Book %s was successfully added", bookName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/take")
    public ResponseEntity<String> getTakenBook(@RequestBody TakeBookDto takeBookDto) {
        try {
            String bookName = bookService.takeBook(takeBookDto);
            return new ResponseEntity<>(String.format("Book %s was taken successfully", bookName), HttpStatus.OK);
        } catch (BookNotFoundException | ReaderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (InvalidReturnDateException | BookIsNotAvailableException
                | ReaderCannotHaveMoreThanThreeBooksException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/del")
    public ResponseEntity<String> deleteBook(@RequestParam String isbn) {
        try {
            bookService.deleteBook(isbn);
            return new ResponseEntity<>(String.format("Book with ISBN: %s deleted successfully", isbn),HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/findBooksByName")
    public ResponseEntity<List<Book>> findBooksByName(@RequestParam("name") String name) {
        try {
            List<Book> booksFound = bookRepository.findByNameContaining(name);
            if (booksFound.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(booksFound, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findBooksByAuthor")
    public ResponseEntity<List<Book>> findBooksByAuthor(@RequestParam("author") String author) {
        try {
            List<Book> booksFound = bookRepository.findByAuthor(author);
            if (booksFound.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(booksFound, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findBooksByCategory")
    public ResponseEntity<List<Book>> findBooksByCategory(@RequestParam("category") String category) {
        try {
            List<Book> booksFound = bookRepository.findByCategory(category);
            if (booksFound.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(booksFound, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findBooksByLanguage")
    public ResponseEntity<List<Book>> findBooksByLanguage(@RequestParam("language") String language) {
        try {
            List<Book> booksFound = bookRepository.findByLanguage(language);
            if (booksFound.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(booksFound, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findBooksByIsbn")
    public ResponseEntity<List<Book>> findBooksByIsbn(@RequestParam("isbn") String isbn) {
        try {
            List<Book> booksFound = bookRepository.findBooksByIsbn(isbn);
            if (booksFound.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(booksFound, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByAvailable")
    public ResponseEntity<List<Book>> findByAvailable(@RequestParam("available") Boolean available) {
        try {
            List<Book> booksFound = bookRepository.findByAvailable(available);
            if (booksFound.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(booksFound, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
