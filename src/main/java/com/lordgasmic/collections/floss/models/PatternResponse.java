package com.lordgasmic.collections.floss.models;

import lombok.Data;

@Data
public class PatternResponse {
    private String name;
    private int stitchCount;
    private String clothType;
    private int size;
    private String finishedSize;
    private int threadsPerStitch;
    private int totalColors;
}
