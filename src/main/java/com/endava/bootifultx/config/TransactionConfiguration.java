package com.endava.bootifultx.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Configuration
@Import({
        TransactionManagerConfiguration.class,
        MovieRepositoryConfiguration.class
})
@EnableAsync
public @interface TransactionConfiguration {
}
