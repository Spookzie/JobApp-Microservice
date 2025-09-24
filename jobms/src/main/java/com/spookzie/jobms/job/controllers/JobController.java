package com.spookzie.jobms.job.controllers;

import com.spookzie.jobms.job.domain.entities.Job;
import com.spookzie.jobms.job.domain.dtos.JobDto;
import com.spookzie.jobms.job.services.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController
{
    private final JobService jobService;


    /*  GET     */
    @GetMapping
    public ResponseEntity<List<JobDto>> findAll()
    {
        List<JobDto> jobsFound = this.jobService.findAll();
        if(jobsFound.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(jobsFound, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findById(@PathVariable Long id)
    {
        Job jobFound =  this.jobService.findById(id);

        if(jobFound == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(jobFound, HttpStatus.OK);
    }


    /*  POST    */
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job)
    {
        this.jobService.createJob(job);

        return new ResponseEntity<>(
                "Job created successfully",
                HttpStatus.CREATED
        );
    }


    /*  DELETE  */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id)
    {
        boolean deleted = this.jobService.deleteById(id);

        if(deleted)
            return new ResponseEntity<>("Job was successfully deleted", HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    /*  UPDATE  */
    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job job)
    {
        Job updatedJob = this.jobService.updateJob(id, job);

        if(updatedJob != null)
            return new ResponseEntity<>(updatedJob, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}