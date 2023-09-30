package com.github.ichsansaid.java.dansmultipro.domain.services.job;

import com.github.ichsansaid.java.dansmultipro.domain.entities.JobEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class JobService implements  IJobService{
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("jobUrl")
    private String jobUrl;

    @Override
    public List<JobEntity> getALlJobs() {
        ResponseEntity<JobEntity[]> response = restTemplate.getForEntity(
                this.jobUrl + ".json",
                JobEntity[].class
        );
        if (response.getStatusCode() == HttpStatus.OK){
            List<JobEntity> entities = new ArrayList<>();
            if (response.getBody() != null) {
                entities = Arrays.asList(response.getBody());
            }
            return entities;
        }
        return Collections.emptyList();
    }

    @Override
    public JobEntity getDetailJobs(String id) {
        ResponseEntity<JobEntity> response = this.restTemplate.getForEntity(this.jobUrl + "/" + id, JobEntity.class);
        if (response.getStatusCode() == HttpStatus.OK){
            return response.getBody();
        }
        return null;
    }
}
