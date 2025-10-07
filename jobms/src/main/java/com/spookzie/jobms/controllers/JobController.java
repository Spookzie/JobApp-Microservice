package com.spookzie.jobms.controllers;

import com.spookzie.jobms.domain.entities.Job;
import com.spookzie.jobms.domain.dtos.JobDto;
import com.spookzie.jobms.services.JobService;
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
        return new ResponseEntity<>(
                this.jobService.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDto> findById(@PathVariable Long id)
    {
        return new ResponseEntity<>(
                this.jobService.findById(id),
                HttpStatus.OK
        );
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
            return new ResponseEntity<>(updatedJob, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}