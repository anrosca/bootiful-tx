package com.endava.bootifultx.fourth;

import com.endava.bootifultx.config.TransactionConfiguration;
import com.endava.bootifultx.domain.Movie;
import com.endava.bootifultx.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@Slf4j
@TransactionConfiguration
public class FourthBootifulTxApplication {

    public static void main(String[] args) {
        SpringApplication.run(FourthBootifulTxApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(MovieService movieService) {
        return args -> {
            Movie movie = Movie.builder().name("Pulp fiction").build();
            movieService.createMovie(movie);
        };
    }
}

interface MovieService {
    @Transactional
    void createMovie(Movie movie);
}

@Service
@Slf4j
@RequiredArgsConstructor
class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public void createMovie(Movie movie) {
        movieRepository.save(movie);
        log.info("Created movie: {}", movie);
    }
}
