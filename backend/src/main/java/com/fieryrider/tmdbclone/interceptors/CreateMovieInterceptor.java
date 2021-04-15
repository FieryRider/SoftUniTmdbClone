package com.fieryrider.tmdbclone.interceptors;

import com.fieryrider.tmdbclone.jwt.JwtConfiguration;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public class CreateMovieInterceptor implements HandlerInterceptor {
    private static final Log logger = LogFactory.getLog(CreateMovieInterceptor.class);
    private static Map<String, Integer> moviesCreatedBy;
    private JwtConfiguration jwtConfiguration;

    @Autowired
    public void setJwtConfiguration(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }

    public CreateMovieInterceptor() { }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (response.getStatus() == 201) {
            String token = request.getHeader("authorization");
            JwtParser jwtParser = Jwts.parserBuilder()
                    .setSigningKey(jwtConfiguration.getSecretKeyHash())
                    .build();
            String user = jwtParser.parseClaimsJws(token.replace(jwtConfiguration.getTokenPrefix(), ""))
                    .getBody()
                    .getSubject();
            if ("POST".equals(request.getMethod())) {
                if ("/people".equals(request.getRequestURI()))
                    logger.info(String.format("Person was created successfully by: %s%n", user));
                else if ("/movies".equals(request.getRequestURI()))
                    logger.info(String.format("Movie was created successfully by: %s%n", user));
                else if ("/tv-shows".equals(request.getRequestURI()))
                    logger.info(String.format("TV Show was created successfully by: %s%n", user));
            }
        }
    }
}
