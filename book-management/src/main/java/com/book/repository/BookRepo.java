package com.book.repository;
import com.book.module.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepo extends JpaRepository<Book,Long> {
   Optional<Book> findByName(String name);
}
