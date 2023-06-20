package com.lordgasmic.collections.bookbujo.models;

import lombok.Data;

import java.util.List;

@Data
public class VolumeInfo {
    private String title;
    private List<String> authors;
    private int pageCount;
    private List<String> categories;
    private List<Identifier> industryIdentifiers;
}
