package com.enigma.sepotify.repository;

import com.enigma.sepotify.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, String>, JpaSpecificationExecutor<Genre> {
}
