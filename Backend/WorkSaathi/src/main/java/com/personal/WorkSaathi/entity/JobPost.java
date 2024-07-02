package com.personal.WorkSaathi.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.personal.WorkSaathi.enums.UserType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "job_posts")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String jobDescription;
    private String location;
    private double salary;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Builder.Default
    @Setter
    protected Instant createdAt = Instant.now();

    @Builder.Default
    @Setter
    protected Instant updatedAt = Instant.now();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

