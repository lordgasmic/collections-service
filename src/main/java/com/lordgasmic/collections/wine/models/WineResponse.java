package com.lordgasmic.collections.wine.models;

import lombok.Data;

@Data
public class WineResponse {
    private String name;
    private String rating;
    private String date;
    private String style;
}
