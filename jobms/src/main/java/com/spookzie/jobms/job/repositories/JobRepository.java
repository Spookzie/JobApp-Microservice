package com.spookzie.jobms.job.repositories;

import com.spookzie.jobms.job.domain.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JobRepository extends JpaRepository<Job, Long>
{

}