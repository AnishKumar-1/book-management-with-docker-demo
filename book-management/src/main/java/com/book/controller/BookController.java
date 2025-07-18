package com.book.controller;
import com.book.dto.BookDtoResponse;
import com.book.dto.BookRequestDto;
import com.book.services.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/{authId}")
    public ResponseEntity<BookDtoResponse> createBook(@NotNull @PathVariable long authId,@Valid @RequestBody BookRequestDto request){
        return bookService.createBook(authId,request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDtoResponse> getBookById(@NotNull @PathVariable Long id){
        return bookService.getById(id);
    }
}
