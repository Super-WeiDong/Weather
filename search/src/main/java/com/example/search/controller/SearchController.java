package com.example.search.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
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


@RestController
public class SearchController {

    @GetMapping("/weather/search")
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
}
