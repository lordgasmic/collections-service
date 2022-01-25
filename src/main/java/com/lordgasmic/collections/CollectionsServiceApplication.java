package com.lordgasmic.collections;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CollectionsServiceApplication {

    public static void main(final String... args) {
        SpringApplication.run(CollectionsServiceApplication.class, args);
        //        final GSARepository repository = (GSARepository) cf.getGenericService("WineTastingRepository");
        //        log.info("repository {}", repository);
        //        repository.getAllRepositoryItems("winery").forEach(System.out::println);
        Nucleus.start();
    }
}
