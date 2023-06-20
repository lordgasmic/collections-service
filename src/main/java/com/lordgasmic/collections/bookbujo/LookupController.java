package com.lordgasmic.collections.bookbujo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class LookupController {

    private final LookupService lookupService;

    public LookupController(LookupService lookupService) {
        this.lookupService = lookupService;
    }

    @GetMapping("/api/v1/bookbujo/{isbn}")
    public Object getBookDataForISBN(@PathVariable long isbn) throws URISyntaxException, IOException, InterruptedException {
        return lookupService.getBookDataByISBN(isbn);
    }
}
