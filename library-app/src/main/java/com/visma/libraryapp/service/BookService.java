package com.visma.libraryapp.service;

import com.visma.libraryapp.dto.BookDto;
import com.visma.libraryapp.dto.TakeBookDto;
import com.visma.libraryapp.entity.Book;
import com.visma.libraryapp.entity.Reader;
import com.visma.libraryapp.exception.*;
import com.visma.libraryapp.repository.BookRepository;
import com.visma.libraryapp.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderRepository readerRepository;


    public Book getBookByGuid(String guid) {
        UUID uuid = UUID.fromString(guid);

        return bookRepository.findByGuid(uuid);
    }


    public String add(BookDto bookDto) {

        Book book = new Book();
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setCategory(bookDto.getCategory());
        book.setIsbn(bookDto.getIsbn());
        book.setLanguage(bookDto.getLanguage());
        book.setPublicationDate(bookDto.getPublicationDate());

        bookRepository.save(book);

        return book.getName();
    }

    public String takeBook(TakeBookDto takeBookDto) throws ReaderNotFoundException, BookNotFoundException, InvalidReturnDateException, BookIsNotAvailableException, ReaderCannotHaveMoreThanThreeBooksException {
        Reader reader = readerRepository.findById(takeBookDto.getReaderId()).orElseThrow(ReaderNotFoundException::new);

        Book book = bookRepository.findBookByIsbn(takeBookDto.getIsbn()).orElseThrow(BookNotFoundException::new);

        if (!isReturnDateLessThanTwoMonths(takeBookDto.getReturnDate())) {
            throw new InvalidReturnDateException();
        }

        if (reader.getTakenBooks().size() >= 3) {
            throw new ReaderCannotHaveMoreThanThreeBooksException();
        }

        if (book.isAvailable()) {
            reader.addBook(book);
            book.setAvailable(false);
            book.setReader(reader);
            readerRepository.save(reader);
            bookRepository.save(book);
            return book.getName();
        } else {
            throw new BookIsNotAvailableException();
        }

    }


    private boolean isReturnDateLessThanTwoMonths(Date returnDate) {
        Date today = new Date();
        long difference = returnDate.getTime() - today.getTime();
        long differenceInDays = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
        return differenceInDays < 60 && differenceInDays >= 0;

    }

    public void deleteBook(String isbn) throws BookNotFoundException, BookIsNotAvailableException {

        Book book = bookRepository.findBookByIsbn(isbn).orElseThrow(BookNotFoundException::new);

        if (book.isAvailable()) {
            bookRepository.delete(book);
        } else {
            throw new BookIsNotAvailableException();
        }

    }
}












