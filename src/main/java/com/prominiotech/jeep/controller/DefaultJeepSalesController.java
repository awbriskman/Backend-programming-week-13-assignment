package com.prominiotech.jeep.controller;

import java.util.List;

import com.prominiotech.jeep.entity.Jeep;
import com.prominiotech.jeep.entity.JeepModel;

import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultJeepSalesController implements JeepSalesController{

    @Override
    public List<Jeep> fetchJeeps(JeepModel model, String trim) {
        // TODO Auto-generated method stub
        log.info("model={}, trim={}", model, trim);
        return null;
    }
    
}
