package com.spookzie.reviewms.messaging;

import com.spookzie.reviewms.domain.dtos.ReviewDto;
import com.spookzie.reviewms.domain.entities.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class Publisher
{
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(Review review)
    {
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setDescription(review.getDescription());
        reviewDto.setRating(review.getRating());
        reviewDto.setCompanyId(review.getCompanyId());

        rabbitTemplate.convertAndSend("companyRatingQueue", reviewDto);
    }
}