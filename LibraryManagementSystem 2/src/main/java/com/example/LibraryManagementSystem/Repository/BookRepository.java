package com.example.LibraryManagementSystem.Repository;

import com.example.LibraryManagementSystem.Enums.Genre;
import com.example.LibraryManagementSystem.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    Book findByName(String name);

    List<Book> findAllBookByGenre(Genre genre);
}
