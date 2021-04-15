package com.fieryrider.tmdbclone.services.impl;

import com.fieryrider.tmdbclone.models.dtos.StatisticsDto;
import com.fieryrider.tmdbclone.models.entities.StatisticsEntity;
import com.fieryrider.tmdbclone.repositories.StatisticsRepository;
import com.fieryrider.tmdbclone.services.StatisticsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    private final StatisticsRepository statisticsRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StatisticsServiceImpl(StatisticsRepository statisticsRepository, ModelMapper modelMapper) {
        this.statisticsRepository = statisticsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addResponse(int code) {
        Optional<StatisticsEntity> optionalStatisticsEntity = this.statisticsRepository.findByResponseCode(code);
        StatisticsEntity statisticsEntity;
        if (optionalStatisticsEntity.isEmpty()) {
            statisticsEntity = new StatisticsEntity();
            statisticsEntity.setResponseCode(code);
            statisticsEntity.setResponseCount(1);
        } else {
            statisticsEntity = optionalStatisticsEntity.get();
            statisticsEntity.setResponseCount(statisticsEntity.getResponseCount() + 1);
        }

        this.statisticsRepository.saveAndFlush(statisticsEntity);
    }

    @Override
    public List<StatisticsDto> getAllResponses() {
        List<StatisticsEntity> statisticsEntities = this.statisticsRepository.findAll();
        List<StatisticsDto> statisticsDtos = new ArrayList<>();
        for (StatisticsEntity statisticsEntity : statisticsEntities) {
            StatisticsDto statisticsDto = this.modelMapper.map(statisticsEntity, StatisticsDto.class);
            statisticsDtos.add(statisticsDto);
        }
        return statisticsDtos;
    }

    @Override
    public int getResponseCount(int code) {
        Optional<StatisticsEntity> statisticsEntity = this.statisticsRepository.findByResponseCode(code);
        if (statisticsEntity.isEmpty())
            return 0;
        else
            return statisticsEntity.get().getResponseCount();
    }
}
