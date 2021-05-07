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
        return String.format("htttp://localhost:%d/jeeps", servicePort);
    }


    @Test
    void JtestThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied (){
        //given: model, trim, uri
        JeepModel model = JeepModel.WRANGLER;
        String trim = "Sport";
        String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
        //when: connection made to uri
        //ResponseEntity<Jeep> response = getRestTemplate().getForEntity(uri, Jeep.class);
        ResponseEntity<List<Jeep>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        //then: success (200) status code is returned
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}
