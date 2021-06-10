package com.visma.libraryapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;

    @OneToMany
    private List<Book> takenBooks;


    public void addBook(Book book) {
        takenBooks.add(book);
    }
}
