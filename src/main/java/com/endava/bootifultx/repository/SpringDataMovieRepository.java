package com.endava.bootifultx.repository;

import com.endava.bootifultx.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMovieRepository extends JpaRepository<Movie, Long>, MovieRepository {
}
