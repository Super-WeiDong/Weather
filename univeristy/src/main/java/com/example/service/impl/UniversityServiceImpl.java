package com.example.service.impl;

import com.example.model.University;
import com.example.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

import static com.example.constants.Url.UNIVERSITY_API_URL;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final RestTemplate restTemplate;

    @Autowired
    public UniversityServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<University> searchByName(String name) {
        String api = UNIVERSITY_API_URL + String.format("/search?name=%s", name);
        List<University> universityList = restTemplate.getForObject(api, List.class);
        return universityList;
    }

    @Override
    public List<University> searchByCountry(String country) {
        String api = UNIVERSITY_API_URL + String.format("/search?country=%s", country);
        List<University> universityList = restTemplate.getForObject(api, List.class);
        return universityList;
    }
}
