package com.author.service;

import com.author.dto.AuthDtoResponse;
import com.author.dto.AuthorRequestDto;
import com.author.exception.DataNotFound;
import com.author.module.Author;
import com.author.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthorRepo authorRepo;

    public ResponseEntity<AuthDtoResponse> createAuth(AuthorRequestDto auth){
       Optional<Author>response=authorRepo.findByName(auth.getName());
       if(response.isPresent())
           throw new IllegalArgumentException("author already exists");
       Author request=Author.builder().name(auth.getName()).build();
        Author result=authorRepo.save(request);
        AuthDtoResponse res=AuthDtoResponse.builder().id(result.getId()).name(result.getName()).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    //get by id
    public ResponseEntity<AuthDtoResponse> getById(Long id){
        Optional<Author> result=authorRepo.findById(id);
        if(result.isEmpty())
            throw new DataNotFound("record not found with this id : "+id);
        AuthDtoResponse res=AuthDtoResponse.builder().id(result.get().getId())
                .name(result.get().getName()).build();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
