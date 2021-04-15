package com.fieryrider.tmdbclone.services;

import com.fieryrider.tmdbclone.models.dtos.UserRegisterDto;

public interface UserService {
    void initAdminUser();
    void registerUser(UserRegisterDto userRegisterDto);
}
