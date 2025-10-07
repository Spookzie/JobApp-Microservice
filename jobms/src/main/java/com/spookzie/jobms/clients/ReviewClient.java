package com.spookzie.jobms.clients;

import com.spookzie.jobms.domain.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "REVIEW-SERVICE")
public interface ReviewClient
{
    @GetMapping("/reviews")
    List<Review> getReviews(@RequestParam Long companyId);
}