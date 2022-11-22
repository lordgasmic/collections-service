package com.lordgasmic.collections.floss.models;

import lombok.Data;

@Data
public class PatternThreadResponse {
    private String name;
    private String brand;
    private String colorCode;
    private String cmRequired;
    private int stitchesPerInch;
    private int aidaCount;
}
