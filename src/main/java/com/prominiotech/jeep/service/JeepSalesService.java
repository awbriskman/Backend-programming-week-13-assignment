package com.prominiotech.jeep.service;

import java.util.List;

import com.prominiotech.jeep.entity.Jeep;
import com.prominiotech.jeep.entity.JeepModel;

public interface JeepSalesService {
    
    List<Jeep> fetchJeeps(JeepModel model, String trim);

}
