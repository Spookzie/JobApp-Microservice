package com.spookzie.jobms.job.domain.dtos;

import com.spookzie.jobms.job.domain.entities.Job;
import com.spookzie.jobms.job.domain.external.Company;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class JobDto
{
    private Job job;
    private Company company;
}