package com.example.LibraryManagementSystem.Controllers;

import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Repository.StudentRepository;
import com.example.LibraryManagementSystem.RequestDto.StudentRequestDto;
import com.example.LibraryManagementSystem.RequestDto.StudentUpdateNameRequestDto;
import com.example.LibraryManagementSystem.ResponseDto.BookResponseDto;
import com.example.LibraryManagementSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<String> addStudent(@RequestBody()StudentRequestDto studentRequestDto){

        String response=studentService.addStudent(studentRequestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



    @PutMapping("/update_name")
    public ResponseEntity<String> updateStudentName(@RequestBody()StudentUpdateNameRequestDto studentUpdateNameRequestDto){
        String response = studentService.updateName(studentUpdateNameRequestDto);

        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }



    @GetMapping("/getAllBooksIssuedByStudent")
    @CrossOrigin(origins = "http://localhost:8080")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<BookResponseDto>> getBooks(@RequestParam("id") int id){

        List<BookResponseDto> books = studentService.getIssuedBook(id);

        return new ResponseEntity<>(books,HttpStatus.OK);
    }
}
