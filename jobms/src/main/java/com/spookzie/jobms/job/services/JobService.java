package com.spookzie.jobms.job.services;

import com.spookzie.jobms.job.domain.entities.Job;
import com.spookzie.jobms.job.domain.dtos.JobDto;

import java.util.List;


public interface JobService
{
    List<JobDto> findAll();
    Job findById(Long id);

    void createJob(Job job);

    boolean deleteById(Long id);

    Job updateJob(Long id, Job job);
}