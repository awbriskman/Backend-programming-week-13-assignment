package com.prominiotech.jeep.controller;

import java.util.List;

import com.prominiotech.jeep.entity.Jeep;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultJeepSalesController implements JeepSalesController{

    @Override
    public List<Jeep> fetchJeeps(String model, String trim) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
