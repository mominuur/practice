package com.example.LibraryManagementSystem.Repository;

import com.example.LibraryManagementSystem.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    Optional<Student> findByName(String userName);
}
