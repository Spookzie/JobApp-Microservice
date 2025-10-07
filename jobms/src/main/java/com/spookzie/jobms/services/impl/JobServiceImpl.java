package com.spookzie.jobms.services.impl;

import com.spookzie.jobms.clients.CompanyClient;
import com.spookzie.jobms.clients.ReviewClient;
import com.spookzie.jobms.domain.entities.Job;
import com.spookzie.jobms.repositories.JobRepository;
import com.spookzie.jobms.services.JobService;
import com.spookzie.jobms.domain.dtos.JobDto;
import com.spookzie.jobms.mappers.JobWithCompanyMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService
{
    private final JobRepository jobRepo;
    private final JobWithCompanyMapper jobWithCompanyMapper;
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;

    int attempts = 0;


    /*  GET     */
    @Override
    @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
//    @Retry(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
//    @RateLimiter(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    public List<JobDto> findAll()
    {
        System.out.println("Attempt " + (++attempts));

        List<Job> jobs = this.jobRepo.findAll();
        if(jobs.isEmpty())
            return new ArrayList<>();

        System.out.println(jobs.getFirst().getCompanyId());

        return jobs
                .stream()
                .map(job -> {
                    JobDto dto = this.jobWithCompanyMapper.toDto(job);
                    dto.setCompany(companyClient.getCompany(job.getCompanyId()));
                    dto.setReviews(reviewClient.getReviews(job.getCompanyId()));
                    return dto;
                })
                .toList();
    }

    @Override
    public JobDto findById(Long id)
    {
        Job job = this.jobRepo.findById(id).orElse(null);

        JobDto dto = this.jobWithCompanyMapper.toDto(job);
        if(dto != null)
        {
            dto.setCompany(companyClient.getCompany(job.getCompanyId()));
            dto.setReviews(reviewClient.getReviews(job.getCompanyId()));
        }

        return dto;
    }


    /*  POST    */
    @Override
    public void createJob(Job job)
    {
        job.setId(null);
        this.jobRepo.save(job);
    }


    /*  DELETE  */
    @Override
    public boolean deleteById(Long id)
    {
        try {
            this.jobRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /*  UPDATE  */
    @Override
    @Transactional
    public Job updateJob(Long id, Job job)
    {
        Optional<Job> jobOptional = this.jobRepo.findById(id);

        if(jobOptional.isPresent())
        {
            Job existingJob = jobOptional.get();

            existingJob.setTitle(job.getTitle());
            existingJob.setDescription(job.getDescription());
            existingJob.setMinSalary(job.getMinSalary());
            existingJob.setMaxSalary(job.getMaxSalary());
            existingJob.setLocation(job.getLocation());

            this.jobRepo.save(existingJob);
            return existingJob;
        }

        return null;
    }


    /*  Annotation Methods  */
    private List<String> companyBreakerFallback(Exception e)
    {
        List<String> l = new ArrayList<>();
        l.add("Cannot get listed jobs :(");
        return l;
    }
}