package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import com.example.demo.util.ResumeParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/resume")
@CrossOrigin(origins = "http://localhost:3000")
public class ResumeController {

    @Autowired
    private ResumeParser parser;

    @Autowired
    private SkillService skillService;

    @Autowired
    private ResumeRepository resumeRepo;

    @Autowired
    private JobRepository jobRepo;

    @Autowired
    private MatchingService matchingService;

    @PostMapping("/upload")
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file) throws Exception {

        String text = parser.extractText(file);
        Set<String> skills = skillService.extractSkills(text);

        Resume resume = new Resume();
        resume.setName(file.getOriginalFilename());
        resume.setContent(text);
        resume.setSkills(String.join(",", skills));

        resumeRepo.save(resume);

        List<Job> jobs = jobRepo.findAll();

        return matchingService.match(skills, jobs);
    }
}