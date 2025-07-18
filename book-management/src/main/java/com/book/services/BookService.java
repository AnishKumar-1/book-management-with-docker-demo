package com.book.services;
import com.book.dto.AuthDto;
import com.book.dto.BookDtoResponse;
import com.book.dto.BookRequestDto;
import com.book.exception.DataNotFound;
import com.book.feign.AuthFeign;
import com.book.module.Book;
import com.book.repository.BookRepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorClientService authorClientService;



    public ResponseEntity<BookDtoResponse> createBook(Long authId,BookRequestDto auth){

        if(authId==null){
            throw new IllegalArgumentException("author id is required");
        }
        Optional<Book>response=bookRepo.findByName(auth.getName());
       if(response.isPresent())
           throw new IllegalArgumentException("book already exists");
        AuthDto authDto=authorClientService.authFeignRequest(authId);
        Book request=Book.builder().name(auth.getName()).authId(authId).build();
        Book result=bookRepo.save(request);
        BookDtoResponse res=BookDtoResponse.builder().id(result.getId()).name(result.getName()).author(authDto).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }


    //get by id
    public ResponseEntity<BookDtoResponse> getById(Long id){
        Optional<Book> result=bookRepo.findById(id);
        if(result.isEmpty())
            throw new DataNotFound("record not found with this id : "+id);

        AuthDto authDto=authorClientService.authFeignRequest(result.get().getAuthId());

        BookDtoResponse res=BookDtoResponse.builder().id(result.get().getId())
                .name(result.get().getName()).author(authDto).build();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
