package com.fieryrider.tmdbclone.services.impl;

import com.fieryrider.tmdbclone.models.entities.UserEntity;
import com.fieryrider.tmdbclone.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TmdbUserService implements UserDetailsService {
    private final UserRepository userRepository;

    public TmdbUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("User with username %s was not found!", username)));
        UserDetails userDetails = mapToUserDetails(userEntity);
        return userDetails;
    }

    private UserDetails mapToUserDetails(UserEntity userEntity) {
        List<SimpleGrantedAuthority> authorities = userEntity.getRoles().stream()
                .map(userRoleEntity -> new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getUserRole().name()))
                .collect(Collectors.toList());

        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }
}
