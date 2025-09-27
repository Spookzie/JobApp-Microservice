package com.spookzie.jobms.job.mappers.impl;

import com.spookzie.jobms.job.clients.CompanyClient;
import com.spookzie.jobms.job.clients.ReviewClient;
import com.spookzie.jobms.job.domain.entities.Job;
import com.spookzie.jobms.job.domain.dtos.JobDto;
import com.spookzie.jobms.job.domain.external.Company;
import com.spookzie.jobms.job.domain.external.Review;
import com.spookzie.jobms.job.mappers.JobWithCompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
@RequiredArgsConstructor
public class JobWithCompanyMapperImpl implements JobWithCompanyMapper
{
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;


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
        jobDto.setCompany(
                companyClient.getCompany(job.getCompanyId())
        );
        jobDto.setReview(
                reviewClient.getReviews(job.getCompanyId())
        );

        return jobDto;
    }
}