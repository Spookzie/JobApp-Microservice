package com.spookzie.jobms.mappers.impl;

import com.spookzie.jobms.domain.entities.Job;
import com.spookzie.jobms.domain.dtos.JobDto;
import com.spookzie.jobms.mappers.JobWithCompanyMapper;
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