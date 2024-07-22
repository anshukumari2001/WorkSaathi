package com.personal.WorkSaathi.service;

import com.personal.WorkSaathi.entity.JobPost;
import com.personal.WorkSaathi.repository.JobPostRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GetJobService {

    private final JobPostRepository jobPostRepository;

    public GetJobService(
        JobPostRepository jobPostRepository) {this.jobPostRepository = jobPostRepository;}

    public List<JobPost> getAllJobPostsByLocation(String location) {
        return jobPostRepository.findByLocation(location);
    }
}
