package com.endava.bootifultx.config;

import com.endava.bootifultx.repository.JpaMovieRepository;
import com.endava.bootifultx.repository.MovieRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class MovieRepositoryConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "springDataMovieRepository")
    public MovieRepository movieRepository(EntityManager entityManager) {
        return new JpaMovieRepository(entityManager);
    }
}
