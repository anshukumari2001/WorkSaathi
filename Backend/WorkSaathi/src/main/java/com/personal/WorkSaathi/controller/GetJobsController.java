package com.personal.WorkSaathi.controller;

import com.personal.WorkSaathi.entity.JobPost;
import com.personal.WorkSaathi.service.GetJobService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class GetJobsController {
    private final GetJobService jobService;

    public GetJobsController(GetJobService jobService) {this.jobService = jobService;}

    @GetMapping("/location/{location}")
    public ResponseEntity<List<JobPost>> getAllJobPostsByLocation(@PathVariable String location) {
        List<JobPost> jobPosts = jobService.getAllJobPostsByLocation(location);
        return ResponseEntity.ok(jobPosts);
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<JobPost>> getAllJobPostsByJobType(@PathVariable String location) {
        List<JobPost> jobPosts = jobService.getAllJobPostsByLocation(location);
        return ResponseEntity.ok(jobPosts);
    }
}
