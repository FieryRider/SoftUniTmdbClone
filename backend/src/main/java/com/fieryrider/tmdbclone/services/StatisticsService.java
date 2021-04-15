package com.fieryrider.tmdbclone.services;

import com.fieryrider.tmdbclone.models.dtos.StatisticsDto;

import java.util.List;

public interface StatisticsService {
    List<StatisticsDto> getAllResponses();
    int getResponseCount(int code);
    void addResponse(int code);
}
