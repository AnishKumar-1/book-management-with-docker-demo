package com.book.services;

import com.book.dto.AuthDto;
import com.book.feign.AuthFeign;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AuthorClientService {

    private final RestTemplate restTemplate;
    private final WebClient webClient;
    private final AuthFeign authFeign;

    public AuthorClientService(RestTemplate restTemplate,WebClient webClient,AuthFeign authFeign) {
        this.restTemplate = restTemplate;
        this.webClient=webClient;
        this.authFeign=authFeign;
    }


    @CircuitBreaker(name = "authorCB", fallbackMethod = "authFallback")
    public AuthDto authFeignRequest(Long authorId) {
        return authFeign.getAuthorById(authorId);
    }

    public AuthDto authFallback(Long authorId, Throwable ex) {
        return AuthDto.builder().author_id(authorId).author_name("service unavailable at this time.").build();
    }


}
