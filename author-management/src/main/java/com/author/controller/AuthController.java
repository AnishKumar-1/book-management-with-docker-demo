package com.author.controller;

import com.author.dto.AuthDtoResponse;
import com.author.dto.AuthorRequestDto;
import com.author.service.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@Validated
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<AuthDtoResponse> create(@Valid @RequestBody AuthorRequestDto request){
        return authService.createAuth(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthDtoResponse> getById(@NotNull @PathVariable Long id){
        return authService.getById(id);
    }
}
