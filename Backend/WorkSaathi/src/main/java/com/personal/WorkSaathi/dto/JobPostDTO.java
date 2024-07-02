package com.personal.WorkSaathi.dto;

import com.personal.WorkSaathi.enums.UserType;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JobPostDTO {
    private String title;
    private String jobDescription;
    private String location;
    private double salary;
    private UserType userType;
}
