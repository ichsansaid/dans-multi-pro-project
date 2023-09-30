package com.github.ichsansaid.java.dansmultipro.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class JobEntity {
    @Id
    private UUID id;
    private String type;
    private String url;
    private String createdAt;
    private String company;
    private String companyUrl;
    private String location;
    private String title;

    private String description;

    @JsonProperty("how_to_apply")
    private String howToApply;

    @JsonProperty("company_logo")
    private String companyLogo;
}
