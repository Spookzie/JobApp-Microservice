package com.spookzie.jobms.job.domain.external;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Review
{
    private Long id;
    private String title;
    private String description;
    private double rating;
}
