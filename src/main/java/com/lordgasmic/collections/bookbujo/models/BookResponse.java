package com.lordgasmic.collections.bookbujo.models;

import lombok.Data;

import java.util.List;

@Data
public class BookResponse {
    private String kind;
    private List<Items> items;
}
