package com.lordgasmic.collections.funko.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FunkoController {

    @GetMapping("/v1/funkos")
    public List<String> getAllFunkos() {
        final List<String> list = new ArrayList<>();
        

        return list;
    }
}
