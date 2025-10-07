package com.spookzie.jobms.services;

import com.spookzie.jobms.domain.entities.Job;
import com.spookzie.jobms.domain.dtos.JobDto;

import java.util.List;


public interface JobService
{
    List<JobDto> findAll();
    JobDto findById(Long id);

    void createJob(Job job);

    boolean deleteById(Long id);

    Job updateJob(Long id, Job job);
}