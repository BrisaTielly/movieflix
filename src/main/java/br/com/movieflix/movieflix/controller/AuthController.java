package br.com.movieflix.movieflix.controller;

import br.com.movieflix.movieflix.request.UserRequest;
import br.com.movieflix.movieflix.response.UserResponse;
import br.com.movieflix.movieflix.service.UserService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieflix/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest userRequest){
        UserResponse saved = userService.save(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
