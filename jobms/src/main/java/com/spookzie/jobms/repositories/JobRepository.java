package com.spookzie.jobms.repositories;

import com.spookzie.jobms.domain.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JobRepository extends JpaRepository<Job, Long>
{

}