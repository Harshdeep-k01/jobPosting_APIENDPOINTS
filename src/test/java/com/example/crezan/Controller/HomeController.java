package com.example.crezan.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.crezan.Entity.JobPosting;
import com.example.crezan.Service.JobPostingService;

import java.util.List;

@RestController
@RequestMapping("/api/job-postings")
@Tag(name = "Job Posting API", description = "Operations pertaining to job postings")
public class HomeController {

    @Autowired
    private JobPostingService jobPostingService;

    @GetMapping("/")
    @Operation(summary = "Welcome message", description = "Displays a welcome message for the API")
    public String home() {
        return "Welcome to the Job Posting API!";
    }

    @GetMapping
    @Operation(summary = "Get all job postings", description = "Retrieves a list of all job postings")
    public ResponseEntity<List<JobPosting>> getAllJobPostings() {
        List<JobPosting> jobPostings = jobPostingService.getAllJobPostings();
        return new ResponseEntity<>(jobPostings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a job posting by ID", description = "Retrieves a job posting by its ID")
    public ResponseEntity<JobPosting> getJobPostingById(@PathVariable Long id) {
        return jobPostingService.getJobPostingById(id)
                .map(jobPosting -> new ResponseEntity<>(jobPosting, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(summary = "Create a new job posting", description = "Creates a new job posting")
    public ResponseEntity<JobPosting> createJobPosting(@RequestBody JobPosting jobPosting) {
        JobPosting createdJobPosting = jobPostingService.createJobPosting(jobPosting);
        return new ResponseEntity<>(createdJobPosting, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a job posting", description = "Updates an existing job posting")
    public ResponseEntity<JobPosting> updateJobPosting(@PathVariable Long id, @RequestBody JobPosting jobPosting) {
        JobPosting updatedJobPosting = jobPostingService.updateJobPosting(id, jobPosting);
        if (updatedJobPosting != null) {
            return new ResponseEntity<>(updatedJobPosting, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a job posting", description = "Deletes a job posting by its ID")
    public ResponseEntity<Void> deleteJobPosting(@PathVariable Long id) {
        jobPostingService.deleteJobPosting(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    @Operation(summary = "Search job postings", description = "Searches for job postings based on keywords, locations, and skills")
    public ResponseEntity<List<JobPosting>> searchJobPostings(
            @RequestParam String keyword,
            @RequestParam(required = false) List<String> locations,
            @RequestParam(required = false) List<String> skills) {
        List<JobPosting> searchResults = jobPostingService.searchJobPostings(keyword, locations, skills);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }
}
