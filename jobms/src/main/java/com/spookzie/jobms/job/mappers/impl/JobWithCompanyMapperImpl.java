package com.spookzie.jobms.job.mappers.impl;

import com.spookzie.jobms.job.domain.entities.Job;
import com.spookzie.jobms.job.domain.dtos.JobDto;
import com.spookzie.jobms.job.mappers.JobWithCompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class JobWithCompanyMapperImpl implements JobWithCompanyMapper
{
    @Override
    public JobDto toDto(Job job)
    {
        if(job == null)
            return null;

        JobDto jobDto = new JobDto();
        jobDto.setId(job.getId());
        jobDto.setTitle(job.getTitle());
        jobDto.setDescription(job.getDescription());
        jobDto.setLocation(job.getLocation());
        jobDto.setMaxSalary(job.getMaxSalary());
        jobDto.setMinSalary(job.getMinSalary());

        return jobDto;
    }
}