package com.example.demo.service;

import com.example.demo.model.Job;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchingService {

    public Map<String, Object> match(Set<String> resumeSkills, List<Job> jobs) {

    // ✅ FIX 1: handle null resumeSkills
    if (resumeSkills == null) {
        resumeSkills = new HashSet<>();
    }

    double bestScore = 0;
    Job bestJob = null;
    Set<String> missing = new HashSet<>();

    // ✅ FIX 2: handle null or empty jobs
    if (jobs == null || jobs.isEmpty()) {
        Map<String, Object> result = new HashMap<>();
        result.put("job", "No jobs available");
        result.put("score", 0);
        result.put("missingSkills", new HashSet<>());
        return result;
    }

    for (Job job : jobs) {

        // ✅ FIX 3: handle null requiredSkills
        String req = job.getRequiredSkills();
        if (req == null || req.isEmpty()) {
            continue;
        }

        Set<String> jobSkills = new HashSet<>(
                Arrays.asList(req.toLowerCase().split(","))
        );

        int matchCount = 0;

        for (String skill : jobSkills) {
            if (resumeSkills.contains(skill.trim())) {
                matchCount++;
            }
        }

        // ✅ FIX 4: avoid division by zero
        double score = jobSkills.size() > 0
                ? (double) matchCount / jobSkills.size() * 100
                : 0;

        if (score > bestScore) {
            bestScore = score;
            bestJob = job;

            missing = new HashSet<>(jobSkills);
            missing.removeAll(resumeSkills);
        }
    }

    Map<String, Object> result = new HashMap<>();
    result.put("job", bestJob != null ? bestJob.getTitle() : "No Match");
    result.put("score", bestScore);
    result.put("missingSkills", missing);

    return result;
}
}