package com.personal.WorkSaathi.repository;

import com.personal.WorkSaathi.entity.JobApply;
import com.personal.WorkSaathi.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplyRepository extends JpaRepository<JobApply, Long> {
}
