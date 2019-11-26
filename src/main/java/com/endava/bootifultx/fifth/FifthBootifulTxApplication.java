package com.endava.bootifultx.fifth;

import com.endava.bootifultx.config.TransactionConfiguration;
import com.endava.bootifultx.domain.Movie;
import com.endava.bootifultx.domain.MovieAudit;
import com.endava.bootifultx.repository.MovieAuditRepository;
import com.endava.bootifultx.repository.MovieRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@Slf4j
@TransactionConfiguration
public class FifthBootifulTxApplication {

    public static void main(String[] args) {
        SpringApplication.run(FifthBootifulTxApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(MovieService movieService, MovieAuditRepository movieAuditRepository) {
        return args -> {
            Movie movie = Movie.builder().name("Pulp fiction").build();
            movieService.saveMovie(movie);
            movieAuditRepository.findAll().forEach(audit -> log.info("Movie audit: {}", audit));
        };
    }
}

@Service
@Slf4j
@RequiredArgsConstructor
class MovieService {

    private final MovieRepository movieRepository;

    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void saveMovie(Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        log.info("Saved movie: {}", savedMovie);
        eventPublisher.publishEvent(new MovieSavedEvent(savedMovie));
    }
}

@RequiredArgsConstructor
@Getter
class MovieSavedEvent {
    private final Movie savedMovie;
}

@Component
@RequiredArgsConstructor
@Slf4j
class MovieSavedEventListener {

    private final MovieAuditRepository movieAuditRepository;

    @Transactional
    @EventListener
    public void onMovieSaved(MovieSavedEvent event) {
        MovieAudit movieAudit = MovieAudit.builder()
                .movie(event.getSavedMovie())
                .name("Movie saved")
                .build();
        movieAuditRepository.save(movieAudit);
        log.info("Movie audit saved");
    }
}
