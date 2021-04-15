package com.fieryrider.tmdbclone.models.dtos;

public class StatisticsDto {
    private int responseCode;
    private int responseCount;

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

    public StatisticsDto() {
    }
}
