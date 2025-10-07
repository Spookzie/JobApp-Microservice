package com.spookzie.reviewms.repositories;

import com.spookzie.reviewms.domain.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long>
{
    List<Review> findByCompanyId(Long companyId);
}