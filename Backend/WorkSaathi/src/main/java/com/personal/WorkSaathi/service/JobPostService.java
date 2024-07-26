package com.personal.WorkSaathi.service;

import com.personal.WorkSaathi.dto.JobPostDTO;
import com.personal.WorkSaathi.entity.JobPost;
import com.personal.WorkSaathi.entity.User;
import com.personal.WorkSaathi.repository.JobPostRepository;
import com.personal.WorkSaathi.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class JobPostService {

    private JobPostRepository jobPostRepository;
    private UserRepository userRepository;

    public JobPostService(JobPostRepository jobPostRepository, UserRepository userRepository) {
        this.jobPostRepository = jobPostRepository;
        this.userRepository = userRepository;
    }

    public JobPost createJobPost(JobPostDTO jobPostDTO, int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        JobPost jobPost= JobPost.builder()
            .title(jobPostDTO.getTitle())
            .jobDescription(jobPostDTO.getJobDescription())
            .location(jobPostDTO.getLocation())
            .salary(jobPostDTO.getSalary())
            .user(user)
            .jobType(jobPostDTO.getJobType())
            .build();

        return jobPostRepository.save(jobPost);
    }

    public List<JobPost> getAllJobPosts() {
        return jobPostRepository.findAll();
    }

    public JobPost getJobPostById(Long id) {
        return jobPostRepository.findById(id).orElse(null);
    }

    public void deleteJobPost(Long id) {
        jobPostRepository.deleteById(id);
    }
}
