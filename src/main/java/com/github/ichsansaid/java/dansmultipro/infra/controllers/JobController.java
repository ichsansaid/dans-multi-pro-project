package com.github.ichsansaid.java.dansmultipro.infra.controllers;

import com.github.ichsansaid.java.dansmultipro.domain.entities.JobEntity;
import com.github.ichsansaid.java.dansmultipro.domain.services.job.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("")
    public List<JobEntity> getAllJobs() {
        return this.jobService.getALlJobs();
    }

    @GetMapping("/{jobId}")
    public JobEntity getDetailJob(@PathVariable String jobId) {
        return this.jobService.getDetailJobs(jobId);
    }
}
