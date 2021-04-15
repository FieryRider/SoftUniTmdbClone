package com.fieryrider.tmdbclone.web;

import com.fieryrider.tmdbclone.models.dtos.UserRegisterDto;
import com.fieryrider.tmdbclone.services.UserService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        try {
            this.userService.registerUser(userRegisterDto);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.status(409).build();
        }
    }

    @GetMapping("/user")
    public String getUser(Principal principal) {
        return principal.getName();
    }
}
