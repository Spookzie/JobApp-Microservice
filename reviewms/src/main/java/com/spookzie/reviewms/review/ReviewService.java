package com.spookzie.reviewms.review;

import java.util.List;


public interface ReviewService
{
    List<Review> findAll(Long companyId);

    Review findById(Long id);

    Review addReview(Long companyId, Review review);

    Review fullUpdateReview(Long reviewId, Review review);

    boolean deleteReview(Long id);
}