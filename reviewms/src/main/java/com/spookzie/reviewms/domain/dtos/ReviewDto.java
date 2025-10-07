package com.spookzie.reviewms.domain.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReviewDto
{
    private Long id;
    private String title;
    private String description;
    private double rating;
    private Long companyId;
}