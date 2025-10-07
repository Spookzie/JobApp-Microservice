package com.spookzie.jobms.job.domain.dtos;

import com.spookzie.jobms.job.domain.external.Company;
import com.spookzie.jobms.job.domain.external.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class JobDto
{
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;

    private Company company;
    private List<Review> reviews;
}