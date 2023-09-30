package com.github.ichsansaid.java.dansmultipro.domain.services.job;

import com.github.ichsansaid.java.dansmultipro.domain.entities.JobEntity;

import java.util.List;

public interface IJobService {
    List<JobEntity> getALlJobs();
    JobEntity getDetailJobs(String id);
}
