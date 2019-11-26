package com.endava.bootifultx.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

import javax.persistence.EntityManagerFactory;

@Configuration
@EntityScan("com.endava.bootifultx.domain")
@EnableJpaRepositories(basePackages = "com.endava.bootifultx.repository")
@ComponentScan(basePackages = "com.endava.bootifultx.repository")
public class TransactionManagerConfiguration {

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf) {
            @Override
            protected void doBegin(Object transaction, TransactionDefinition definition) {
                System.out.println("***************************************");
                System.out.println("**************  BEGIN  ****************");
                System.out.println("***************************************");
                System.out.println();
                super.doBegin(transaction, definition);
            }

            @Override
            protected void doCommit(DefaultTransactionStatus status) {
                System.out.println("***************************************");
                System.out.println("**************  COMMIT ****************");
                System.out.println("***************************************");
                System.out.println();
                super.doCommit(status);
            }

            @Override
            protected void doRollback(DefaultTransactionStatus status) {
                System.out.println("****************************************");
                System.out.println("************** ROLLBACK ****************");
                System.out.println("****************************************");
                System.out.println();
                super.doRollback(status);
            }
        };
    }
}
