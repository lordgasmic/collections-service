package com.lordgasmic.collections.gas.models;

import lombok.Data;

@Data
public class GasResponse {
    private String id;
    private String date;
    private String odometer;
    private String cost;
    private String gas;
    private String vehicle;
}
