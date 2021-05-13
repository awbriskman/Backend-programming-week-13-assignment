package com.prominiotech.jeep.controller;

import java.util.List;

import com.prominiotech.jeep.entity.Jeep;
import com.prominiotech.jeep.entity.JeepModel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/jeeps")
@OpenAPIDefinition(info = @Info(title = "Jeep Sales Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface JeepSalesController {
    @Operation(
        summary = "returns list of Jeeps",
        description = "Returns list of Jeeps given ",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "a list of jeeps is returned", 
                content = @Content(
                    mediaType =  "application/json", 
                    schema = @Schema(implementation = Jeep.class))),
            @ApiResponse(
                responseCode = "400", 
                description = "request parameters invalid", 
                content = @Content(mediaType =  "application/json")),
            @ApiResponse(
                responseCode = "404", 
                description = "No jeeps matched input criteria", 
                content = @Content(mediaType =  "application/json")),
            @ApiResponse(
                responseCode = "500", 
                description = "Unexpected error occured", 
                content = @Content(mediaType =  "application/json"))
        },
        parameters = {
            @Parameter(
                name = "model", 
                allowEmptyValue = false, 
                required = false, 
                description = "the model name eg.(WRANGLER ect)"),
            @Parameter(
                name = "trim", 
                allowEmptyValue = false, 
                required = false,
                description = "the trim level eg. (sport ect)")
        }
    )
        @GetMapping
        @ResponseStatus(code = HttpStatus.OK)
    List<Jeep> fetchJeeps(
        @RequestParam(required = false) JeepModel model, 
        @RequestParam(required = false) String trim);

}
