package com.spookzie.jobms.job.mappers.impl;

import com.spookzie.jobms.job.domain.entities.Job;
import com.spookzie.jobms.job.domain.dtos.JobDto;
import com.spookzie.jobms.job.domain.external.Company;
import com.spookzie.jobms.job.mappers.JobWithCompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@RequiredArgsConstructor
public class JobWithCompanyMapperImpl implements JobWithCompanyMapper
{
    private final RestTemplate restTemplate;


    @Override
    public JobDto toDto(Job job)
    {
        JobDto jobDto = new JobDto();
        jobDto.setJob(job);

        Company company = restTemplate.getForObject(
                "http://localhost:8081/companies/" + job.getCompanyId(),
                Company.class
        );
        jobDto.setCompany(company);

        return jobDto;
    }
}