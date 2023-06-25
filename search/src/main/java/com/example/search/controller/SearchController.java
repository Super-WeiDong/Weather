package com.example.search.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

//visit http://localhost:9001/swagger-ui.html to get swagger documentation JSON
//visit http://localhost:9001//swagger-ui/index.html to get swagger documentation UI
@RestController
@Api(tags = "Student and University API")
public class SearchController {

    //get all students from DB and university in China, combine the result
    @GetMapping("/studentanduniversity/search")
    @HystrixCommand(fallbackMethod = "fallbackOperation")
    @ApiOperation("Get all students from DB and university in China, combine the result")
    public ResponseEntity<?> getDetails() {
        CompletableFuture<JsonNode> completableFutureStu = CompletableFuture.supplyAsync(() -> {
            try {
                URL url = new URL("http://localhost:8200/students");
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readTree(url);
            } catch (IOException e) {
                return null;
            }
        });
        CompletableFuture<JsonNode> completableFutureUni= CompletableFuture.supplyAsync(() -> {
            try {
                URL url = new URL("http://localhost:8086/university/search?country=china");
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readTree(url);
            } catch (IOException e) {
                return null;
            }
        });

        try {
            JsonNode jsonData = completableFutureStu.get();
            JsonNode uniData = completableFutureUni.get();
            Map<String,JsonNode> resMap = new HashMap<String,JsonNode>();
            resMap.put("Student Data",jsonData);
            resMap.put("Univeristy Data",uniData);
            return new ResponseEntity<>(resMap, HttpStatus.OK);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong, No data fetched", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> fallbackOperation(){
        return new ResponseEntity<>("Oops, Something went wrong,please try again later.", HttpStatus.NOT_FOUND);
    }
}
