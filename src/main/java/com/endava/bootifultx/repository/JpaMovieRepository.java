package com.endava.bootifultx.repository;

import com.endava.bootifultx.domain.Movie;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class JpaMovieRepository implements MovieRepository {

    private final EntityManager entityManager;

    @Override
    public List<Movie> findAll() {
        return entityManager.createQuery("select m from Movie m", Movie.class)
                .getResultList();
    }

    @Override
    public Movie save(Movie movie) {
        entityManager.persist(movie);
        return movie;
    }

    @Override
    public Movie saveAndFlush(Movie movie) {
        entityManager.persist(movie);
        entityManager.flush();
        return movie;
    }
}
