package com.spookzie.jobms.job.mappers;

import com.spookzie.jobms.job.domain.entities.Job;
import com.spookzie.jobms.job.domain.dtos.JobDto;


public interface JobWithCompanyMapper
{
    JobDto toDto(Job job);
}