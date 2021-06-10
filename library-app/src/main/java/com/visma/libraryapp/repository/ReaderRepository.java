package com.visma.libraryapp.repository;

import com.visma.libraryapp.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReaderRepository extends JpaRepository<Reader, Long> {

    Optional<Reader> findById(Long id);

}
