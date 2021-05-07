package com.prominiotech.jeep.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import lombok.Getter;

public class BaseTest {

    @LocalServerPort
    private int servicePort;

    @Autowired
    @Getter
    private TestRestTemplate restTemplate;

    protected String getBaseUri(){
        return String.format("htttp://localhost:%d/jeeps",servicePort);
    }
}
