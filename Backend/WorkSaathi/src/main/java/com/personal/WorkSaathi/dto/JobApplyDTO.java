package com.personal.WorkSaathi.dto;

import com.personal.WorkSaathi.enums.JobType;
import com.personal.WorkSaathi.enums.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JobApplyDTO {
    private String title;
    private String jobDescription;
    private String location;
    private double salary;
    private UserType userType;
    private JobType jobType;

    // Fields for email details
    private String emailTo;
    private String emailSubject;
    private String emailText;

}
