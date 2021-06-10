package com.visma.libraryapp.service;

import com.visma.libraryapp.dto.ReaderDto;
import com.visma.libraryapp.entity.Reader;
import com.visma.libraryapp.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReaderService {

    @Autowired
    private ReaderRepository readerRepository;


    public String add(ReaderDto readerDto) {
        Reader reader = new Reader();
        reader.setName(readerDto.getName());
        reader.setSurname(readerDto.getSurname());

        readerRepository.save(reader);

        return reader.getName();
    }

}
