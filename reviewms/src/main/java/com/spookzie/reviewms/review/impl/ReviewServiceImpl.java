package com.spookzie.reviewms.review.impl;

import com.spookzie.reviewms.review.Review;
import com.spookzie.reviewms.review.ReviewRepository;
import com.spookzie.reviewms.review.ReviewService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService
{
    private final ReviewRepository reviewRepo;


    /*  GET */
    @Override
    public List<Review> findAll(Long companyId)
    {
        return this.reviewRepo.findByCompanyId(companyId);
    }

    @Override
    public Review findById(Long id)
    {
        return this.reviewRepo.findById(id).orElse(null);
    }


    /*  POST    */
    @Override
    public Review addReview(Long companyId, Review review)
    {
        if(companyId != null)
        {
            review.setCompanyId(companyId);
            return this.reviewRepo.save(review);
        }

        return null;
    }


    /*  PUT */
    @Override
    public Review fullUpdateReview(Long reviewId, Review review)
    {
        Review updatedReview = this.reviewRepo.findById(reviewId).orElse(null);

        if(updatedReview != null)
        {
            updatedReview.setTitle(review.getTitle());
            updatedReview.setDescription(review.getDescription());
            updatedReview.setRating(review.getRating());
            updatedReview.setCompanyId(review.getCompanyId());

            this.reviewRepo.save(updatedReview);
            return updatedReview;
        }

        return null;
    }


    /*  DELETE  */
    @Override
    @Transactional
    public boolean deleteReview(Long id)
    {
        Review review = this.reviewRepo.findById(id).orElse(null);

        if(review != null)
        {
            this.reviewRepo.deleteById(id);
            return true;
        }

        return false;

    }
}