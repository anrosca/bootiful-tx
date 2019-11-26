package com.endava.bootifultx.second;

import com.endava.bootifultx.config.TransactionConfiguration;
import com.endava.bootifultx.domain.Movie;
import com.endava.bootifultx.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
@Slf4j
@TransactionConfiguration
public class SecondBootifulTxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondBootifulTxApplication.class, args);
    }
}

@Service
@RequiredArgsConstructor
@Slf4j
class MovieService {

    private final MovieRepository movieRepository;

    @PostConstruct
    public void initMovies() {
        List<Movie> movies = List.of(
                Movie.builder().name("Pulp fiction").build(),
                Movie.builder().name("Lock, Stock and Two Smoking Barrels").build(),
                Movie.builder().name("Snatch").build()
        );
        for (Movie m : movies) {
            movieRepository.save(m);
        }
    }
}
