package com.prominiotech.jeep.service;

import java.util.Collections;
import java.util.List;

import com.prominiotech.jeep.dao.JeepSalesDao;
import com.prominiotech.jeep.entity.Jeep;
import com.prominiotech.jeep.entity.JeepModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultJeepSalesService implements JeepSalesService{
    
    @Autowired
    private JeepSalesDao jeepSalesDao;

    @Override
    public List<Jeep> fetchJeeps(JeepModel model, String trim) {
        // TODO Auto-generated method stub
        log.info("JeepModel={}, trim = {}", model, trim);
        List<Jeep> jeeps = jeepSalesDao.fetchJeeps(model, trim);
        Collections.sort(jeeps);
        return jeeps;
    }

}
