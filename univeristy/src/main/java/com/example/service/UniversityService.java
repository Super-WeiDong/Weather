package com.example.service;

import com.example.entity.University;

import java.util.List;

public interface UniversityService {

    List<University> searchByName(String name);

    List<University> searchByCountry(String country);
}
