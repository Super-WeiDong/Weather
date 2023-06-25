package com.example.controller;

import com.example.model.University;
import com.example.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/university")
public class UniversityController {

    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping(value = "/search", params = "name")
    public ResponseEntity<List<University>> searchByName(@RequestParam String name) {
        List<University> universityList = universityService.searchByName(name);
        return new ResponseEntity<>(universityList, HttpStatus.OK);
    }

    @GetMapping(value = "/search", params = "country")
    public ResponseEntity<List<University>> searchByCountry(@RequestParam String country) {
        List<University> universityList = universityService.searchByCountry(country);
        return new ResponseEntity<>(universityList, HttpStatus.OK);
    }
}
