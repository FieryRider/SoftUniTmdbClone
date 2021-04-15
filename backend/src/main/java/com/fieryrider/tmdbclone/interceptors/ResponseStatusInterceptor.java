package com.fieryrider.tmdbclone.interceptors;

import com.fieryrider.tmdbclone.services.StatisticsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ResponseStatusInterceptor implements HandlerInterceptor {
    private static final Log logger = LogFactory.getLog(ResponseStatusInterceptor.class);
    private StatisticsService statisticsService;

    @Autowired
    public void setStatisticsService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @Autowired
    public ResponseStatusInterceptor() {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        this.statisticsService.addResponse(response.getStatus());
    }
}
