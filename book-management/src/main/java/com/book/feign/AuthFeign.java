package com.book.feign;

import com.book.dto.AuthDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "author-management")
public interface AuthFeign {
    @GetMapping("author/{id}")
    AuthDto getAuthorById(@PathVariable("id") Long id);
}
