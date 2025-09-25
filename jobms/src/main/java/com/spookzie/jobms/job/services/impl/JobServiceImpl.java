package com.spookzie.jobms.job.services.impl;

import com.spookzie.jobms.job.domain.entities.Job;
import com.spookzie.jobms.job.repositories.JobRepository;
import com.spookzie.jobms.job.services.JobService;
import com.spookzie.jobms.job.domain.dtos.JobDto;
import com.spookzie.jobms.job.mappers.JobWithCompanyMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService
{
    private final JobRepository jobRepo;
    private final JobWithCompanyMapper jobWithCompanyMapper;


    /*  GET     */
    @Override
    public List<JobDto> findAll()
    {
        List<Job> jobs = this.jobRepo.findAll();

        return jobs
                .stream()
                .map(this.jobWithCompanyMapper::toDto)
                .toList();
    }

    @Override
    public JobDto findById(Long id)
    {
        return this.jobWithCompanyMapper.toDto(
                this.jobRepo.findById(id).orElse(null)
        );
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
}