package com.fieryrider.tmdbclone.web;

import com.fieryrider.tmdbclone.models.dtos.UserRegisterDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.TokenDto;
import com.fieryrider.tmdbclone.services.UserService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public TokenDto login(@RequestAttribute("token") String token, @RequestAttribute("roles") List<String> roles) {
        System.out.println(token);
        return new TokenDto(token, roles);
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
