package com.lordgasmic.collections.gas.controller;

import com.lordgasmic.collections.gas.models.GasRequest;
import com.lordgasmic.collections.gas.models.GasResponse;
import com.lordgasmic.collections.gas.service.GasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class GasController {

    @Autowired
    private GasService service;

    @PutMapping("/api/v1/gas")
    public GasResponse addGas(@RequestBody final GasRequest request) throws SQLException {
        return service.addGas(request);
    }

    @GetMapping("/api/v1/gas")
    public List<GasResponse> getGas(@RequestParam final String vehicle) throws SQLException {
        return service.getGas(vehicle);
    }
}
