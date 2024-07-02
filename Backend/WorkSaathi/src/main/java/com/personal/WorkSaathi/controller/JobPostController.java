package com.personal.WorkSaathi.controller;

import com.personal.WorkSaathi.dto.JobPostDTO;
import com.personal.WorkSaathi.entity.JobPost;
import com.personal.WorkSaathi.service.JobPostService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobPostController {

    private JobPostService jobPostService;

    public JobPostController(JobPostService jobPostService) {
        this.jobPostService = jobPostService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<JobPost> createJobPost(@RequestBody JobPostDTO jobPostDTO,
                                                 @PathVariable int userId) {
        JobPost createdJobPost = jobPostService.createJobPost(jobPostDTO, userId);
        return ResponseEntity.ok(createdJobPost);
    }

    @GetMapping
    public ResponseEntity<List<JobPost>> getAllJobPosts() {
        List<JobPost> jobPosts = jobPostService.getAllJobPosts();
        return ResponseEntity.ok(jobPosts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPost> getJobPostById(@PathVariable Long id) {
        JobPost jobPost = jobPostService.getJobPostById(id);
        return ResponseEntity.ok(jobPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobPost(@PathVariable Long id) {
        jobPostService.deleteJobPost(id);
        return ResponseEntity.noContent().build();
    }
}
