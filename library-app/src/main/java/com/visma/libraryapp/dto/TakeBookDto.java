package com.visma.libraryapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TakeBookDto {

    private String isbn;
    private Long readerId;
    private Date returnDate;

}
