package com.example.LibraryManagementSystem.Repository;

import com.example.LibraryManagementSystem.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
