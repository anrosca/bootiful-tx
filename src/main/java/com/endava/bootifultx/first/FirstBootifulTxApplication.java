package com.endava.bootifultx.first;

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

import java.util.List;

@SpringBootApplication
@Slf4j
@TransactionConfiguration //Custom annotation to aggregate all java-config classes
public class FirstBootifulTxApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstBootifulTxApplication.class, args);
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
@RequiredArgsConstructor
class MovieService {

    private final MovieRepository movieRepository;

    public void createAll(List<Movie> movies) {
        for (Movie m : movies) {
            movieRepository.save(m);
        }
    }
}
