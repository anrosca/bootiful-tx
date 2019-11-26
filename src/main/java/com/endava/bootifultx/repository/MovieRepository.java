package com.endava.bootifultx.repository;

import com.endava.bootifultx.domain.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> findAll();

    Movie save(Movie movie);

    Movie saveAndFlush(Movie movie);
}
