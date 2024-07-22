package com.personal.WorkSaathi.repository;

import com.personal.WorkSaathi.entity.JobPost;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {
    List<JobPost> findByLocation(String location);
}
