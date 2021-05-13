package com.prominiotech.jeep.controller;

import com.prominiotech.jeep.entity.Jeep;
import com.prominiotech.jeep.entity.JeepModel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import lombok.Getter;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
    "classpath:flyway/migrations/V1.1__Jeep_Data.sql"}, 
    config = @SqlConfig(encoding = "utf-8"))

public class FetchJeepTest {
    
    @LocalServerPort
    private int servicePort;

    @Autowired
    @Getter
    private TestRestTemplate restTemplate;

    protected String getBaseUri(){
        return String.format("http://localhost:%d/jeeps", servicePort);
    }


    @Test
    void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied (){
        //given: model, trim, uri
        JeepModel model = JeepModel.WRANGLER;
        String trim = "Sport";
        String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
        //when: connection made to uri
        //ResponseEntity<Jeep> response = getRestTemplate().getForEntity(uri, Jeep.class);
        ResponseEntity<List<Jeep>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        //then: success (200) status code is returned
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); 
        List<Jeep> expected = BuildExpected();
        System.out.println(expected);
        assertThat(response.getBody()).isEqualTo(expected);
    }
    
    List<Jeep> BuildExpected(){
        List<Jeep> list = new ArrayList<>();

        list.add(Jeep.builder()
            .modelId(JeepModel.WRANGLER)
            .trimLevel("Sport")    
            .numDoors(2)
            .wheelSize(17)
            .basePrice(new BigDecimal("28475.00"))
            .build());

        list.add(Jeep.builder()
            .modelId(JeepModel.WRANGLER)
            .trimLevel("Sport")    
            .numDoors(4)
            .wheelSize(17)
            .basePrice(new BigDecimal("31975.00"))
            .build());
        return list;
    }
    
}
