package com.lordgasmic.collections.funko;

import com.lordgasmic.collections.CollectionsFramework;
import com.lordgasmic.collections.repository.GSARepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class CollectionsServiceApplication {

    public static void main(final String... args) {
        SpringApplication.run(CollectionsServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
            final CollectionsFramework cf = CollectionsFramework.getInstance();
            final GSARepository repository = (GSARepository) cf.getGenericService("FunkoRepository");
            log.info("repository {}", repository);
        };
    }
}
