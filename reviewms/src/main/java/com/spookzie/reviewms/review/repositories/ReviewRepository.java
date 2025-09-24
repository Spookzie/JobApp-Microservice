package com.spookzie.reviewms.review.repositories;

import com.spookzie.reviewms.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long>
{
    List<Review> findByCompanyId(Long companyId);
}