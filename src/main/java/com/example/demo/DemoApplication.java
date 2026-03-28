package com.example.demo;

import com.example.demo.model.Job;
import com.example.demo.repository.JobRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // 🔥 ADD THIS BELOW main() METHOD
    @Bean
    public org.springframework.boot.CommandLineRunner init(JobRepository repo) {
        return args -> {
            repo.save(new Job(null, "Data Analyst", "python,sql,excel,power bi", "Analyze data"));
            repo.save(new Job(null, "Java Developer", "java,spring,mysql", "Backend dev"));
        };
    }
}