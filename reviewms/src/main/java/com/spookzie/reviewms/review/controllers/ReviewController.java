package com.spookzie.reviewms.review.controllers;

import com.spookzie.reviewms.review.services.ReviewService;
import com.spookzie.reviewms.review.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController
{
    private final ReviewService reviewService;

    /*  GET */
    @GetMapping
    public ResponseEntity<List<Review>> findAll(@RequestParam Long companyId)
    {
        return new ResponseEntity<>(
                this.reviewService.findAll(companyId),
                HttpStatus.OK
        );
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> findById(@PathVariable Long reviewId)
    {
        return new ResponseEntity<>(
                this.reviewService.findById(reviewId),
                HttpStatus.OK
        );
    }


    /*  POST    */
    @PostMapping
    public ResponseEntity<Review> addReview(@RequestParam Long companyId, @RequestBody Review review)
    {
        Review addedReview = this.reviewService.addReview(companyId, review);
        if(review != null)
        {
            return new ResponseEntity<>(
                addedReview,
                HttpStatus.CREATED
            );
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    /*  PUT */
    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> fullUpdateReview(@PathVariable Long reviewId, @RequestBody Review review)
    {
        Review updatedReview = this.reviewService.fullUpdateReview(reviewId, review);
        if(updatedReview != null)
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    /*  DELETE  */
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId)
    {
        boolean isDeleted = this.reviewService.deleteReview(reviewId);

        if(isDeleted)
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.NO_CONTENT);

        return new ResponseEntity<>("Unable to delete review", HttpStatus.NOT_FOUND);
    }
}