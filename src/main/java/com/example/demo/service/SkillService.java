package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SkillService {

    private final List<String> skillsList = List.of(
            "java", "spring", "spring boot",
            "react", "angular",
            "mysql", "sql",
            "python", "pandas", "numpy",
            "machine learning", "data analysis",
            "excel", "power bi"
    );

    public Set<String> extractSkills(String text) {
        Set<String> foundSkills = new HashSet<>();
        String lower = text.toLowerCase();

        for (String skill : skillsList) {
            if (lower.contains(skill)) {
                foundSkills.add(skill);
            }
        }
        return foundSkills;
    }
}