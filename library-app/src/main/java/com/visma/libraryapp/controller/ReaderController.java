package com.visma.libraryapp.controller;

import com.visma.libraryapp.dto.BookDto;
import com.visma.libraryapp.dto.ReaderDto;
import com.visma.libraryapp.repository.ReaderRepository;
import com.visma.libraryapp.service.BookService;
import com.visma.libraryapp.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reader")
public class ReaderController {

    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private ReaderService readerService;

    @PostMapping("/add")
    public ResponseEntity<String> addReader(@RequestBody ReaderDto readerDto) {
        try {
            String readerName = readerService.add(readerDto);
            return new ResponseEntity<>(String.format("Reader %s was successfully added", readerName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
