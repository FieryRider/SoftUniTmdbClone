package com.fieryrider.tmdbclone.web;

import com.fieryrider.tmdbclone.models.dtos.StatisticsDto;
import com.fieryrider.tmdbclone.services.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path ="/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/response/all")
    public List<StatisticsDto> getAll() {
        return this.statisticsService.getAllResponses();
    }

    @GetMapping("/response/{code}")
    public String get(@PathVariable int code ) {
        return String.valueOf(this.statisticsService.getResponseCount(code));
    }
}
