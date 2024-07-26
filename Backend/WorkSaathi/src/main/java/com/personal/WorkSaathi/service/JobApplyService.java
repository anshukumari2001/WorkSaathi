package com.personal.WorkSaathi.service;

import com.personal.WorkSaathi.dto.JobApplyDTO;
import com.personal.WorkSaathi.entity.JobApply;
import com.personal.WorkSaathi.repository.JobApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobApplyService {

    @Autowired
    private JobApplyRepository jobApplyRepository;

    public JobApply createJobApply(JobApplyDTO jobApplyDTO, int userId) {
        JobApply jobApply = new JobApply();
        // Set properties from jobApplyDTO to jobApply
        jobApply.setTitle(jobApplyDTO.getTitle());
        jobApply.setJobDescription(jobApplyDTO.getJobDescription());
        jobApply.setLocation(jobApplyDTO.getLocation());
        jobApply.setSalary(jobApplyDTO.getSalary());
        jobApply.setUserType(jobApplyDTO.getUserType());
        jobApply.setJobType(jobApplyDTO.getJobType());
        // Set the user ID or other necessary fields
        return jobApplyRepository.save(jobApply);
    }
}

