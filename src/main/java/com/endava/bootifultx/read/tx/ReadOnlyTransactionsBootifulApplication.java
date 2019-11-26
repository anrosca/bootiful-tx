package com.endava.bootifultx.read.tx;

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
public class ReadOnlyTransactionsBootifulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadOnlyTransactionsBootifulApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(MovieService movieService) {
        return args -> {
            movieService.readMovie();
            log.info("All Movies");
            movieService.readAll().forEach(movie -> log.info("Movie from db: {}", movie));
        };
    }
}

@Service
@RequiredArgsConstructor
class MovieService {
    private final MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public void readMovie() {
        Movie movie = Movie.builder()
                .name("Pulp fiction")
                .build();
        movieRepository.save(movie);
    }

    public Iterable<Movie> readAll() {
        return movieRepository.findAll();
    }
}
