package com.fieryrider.tmdbclone.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "statistics")
public class StatisticsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "response_code", nullable = false, unique = true)
    private int responseCode;
    @Column(name = "response_value", nullable = false)
    private int responseCount;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public int getResponseCount() {
        return this.responseCount;
    }

    public void setResponseCount(int responseCount) {
        this.responseCount = responseCount;
    }

    public StatisticsEntity() {
    }
}
