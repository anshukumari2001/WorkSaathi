package com.personal.WorkSaathi.controller;

import com.personal.WorkSaathi.dto.JobApplyDTO;
import com.personal.WorkSaathi.dto.JobPostDTO;
import com.personal.WorkSaathi.entity.JobApply;
import com.personal.WorkSaathi.entity.JobPost;
import com.personal.WorkSaathi.service.EmailService;
import com.personal.WorkSaathi.service.JobApplyService;
import com.personal.WorkSaathi.service.JobPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobApplyController {
    //send mail to service provider user when service seeker user applies for job
    //send mail to service seeker user when service provider user accepts the job
    //send mail to service seeker user when service provider user rejects the job
    //send mail to service provider user when service seeker user cancels the job
    //send mail to service seeker user when service provider user completes the job

    //send mail to service provider user when service seeker user applies for job write the logic here

    @PostMapping("/jobApply")
    public void jobApply() {
        //write the logic here
    }

    @Autowired
    private JobApplyService jobApplyService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/jobApply/{userId}")
    public ResponseEntity<JobApply> createJobApply(@RequestBody JobApplyDTO jobApplyDTO, @PathVariable int userId) {
        // Extract email details from JobApplyDTO
        String to = jobApplyDTO.getEmailTo();
        String subject = jobApplyDTO.getEmailSubject();
        String text = jobApplyDTO.getEmailText();

        // Send email
        emailService.sendEmail(to, subject, text);

        // Save job application to database
        JobApply createdJobApply = jobApplyService.createJobApply(jobApplyDTO, userId);
        return ResponseEntity.ok(createdJobApply);
    }
}
