package com.visma.libraryapp.repository;

import com.visma.libraryapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByGuid(UUID guid);
    Optional<Book> findBookByIsbn(String isbn);

    List<Book> findByNameContaining(String name);

    List<Book> findByCategory(String category);

    List<Book> findByAuthor(String author);

    List<Book> findByLanguage(String language);

    List<Book> findBooksByIsbn(String isbn);

    List<Book> findByAvailable(Boolean available);

}
