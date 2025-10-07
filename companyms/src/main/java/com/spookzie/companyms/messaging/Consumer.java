package com.spookzie.companyms.messaging;

import com.spookzie.companyms.domain.dtos.ReviewDto;
import com.spookzie.companyms.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class Consumer
{
    private final CompanyService companyService;

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewDto reviewDto)
    {
        this.companyService.updateCompanyRating(reviewDto);
    }
}