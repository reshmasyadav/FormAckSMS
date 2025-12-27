package com.collegeProject.FormAckSMS.repository;

import com.collegeProject.FormAckSMS.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    // Check if a Submission exists by PRN Number
    boolean existsByPrnNumber(String prnNumber);

    // Find Submission by PRN Number
    Submission findByPrnNumber(String prnNumber);

    // Delete Submission by PRN Number
    void deleteByPrnNumber(String prnNumber);
}
