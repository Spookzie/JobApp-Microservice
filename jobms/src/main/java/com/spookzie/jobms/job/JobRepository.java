package com.spookzie.jobms.job;

import org.springframework.data.jpa.repository.JpaRepository;


public interface JobRepository extends JpaRepository<Job, Long>
{

}