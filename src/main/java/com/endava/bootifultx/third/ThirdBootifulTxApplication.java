package com.endava.bootifultx.third;

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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
@TransactionConfiguration
public class ThirdBootifulTxApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThirdBootifulTxApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(MovieService movieService) {
        return args -> {
            List<Movie> movies = List.of(
                    Movie.builder().name("Pulp fiction").build(),
                    Movie.builder().name("Lock, Stock and Two Smoking Barrels").build(),
                    Movie.builder().name("Snatch").build()
            );
            movieService.createAll(movies);
        };
    }
}

@Service
@Slf4j
@RequiredArgsConstructor
class MovieService {

    private final MovieRepository movieRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Movie create(Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        log.info("Creating movie: {}", savedMovie);
        return savedMovie;
    }

    @Transactional
    public List<Movie> createAll(List<Movie> movies) {
        log.info("Batch creating movies");
        return movies.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}


















//-javaagent:spring-agent-2.5.6.SEC03.jar -javaagent:aspectjweaver-1.9.2.jar