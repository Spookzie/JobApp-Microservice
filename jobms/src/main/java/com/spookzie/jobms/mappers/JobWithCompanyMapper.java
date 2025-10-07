package com.spookzie.jobms.mappers;

import com.spookzie.jobms.domain.entities.Job;
import com.spookzie.jobms.domain.dtos.JobDto;


public interface JobWithCompanyMapper
{
    JobDto toDto(Job job);
}