package com.visma.libraryapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookDto {
    private String name;
    private String author;
    private String category;
    private String language;
    private Date publicationDate;
    private String isbn;
}
