package com.prominiotech.jeep.dao;

import java.util.List;

import com.prominiotech.jeep.entity.Jeep;
import com.prominiotech.jeep.entity.JeepModel;

public interface JeepSalesDao {
    
    List<Jeep> fetchJeeps(JeepModel model, String trim);
}
