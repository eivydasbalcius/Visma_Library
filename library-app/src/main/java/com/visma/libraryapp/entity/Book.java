package com.visma.libraryapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String author;
    private String category;
    private String language;
    private Date publicationDate;
    private String isbn;
    private UUID guid = UUID.randomUUID();
    private boolean available = true;

    @JsonBackReference
    @OneToOne
    private Reader reader;


}
