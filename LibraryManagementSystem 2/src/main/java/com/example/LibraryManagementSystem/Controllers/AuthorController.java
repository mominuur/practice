package com.example.LibraryManagementSystem.Controllers;

import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.RequestDto.AuthorRequestDto;
import com.example.LibraryManagementSystem.Service.AuthorService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<String> createAuthor(@RequestBody() AuthorRequestDto authorRequestDto){

        String response = authorService.createAuthor(authorRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/getBooksOfAuthor")
    public ResponseEntity<List<String>> getBooksOfAuthor(@RequestParam("id") int id){
     List<String> bookList = authorService.getBookListOfAuthor(id);
     return new ResponseEntity<>(bookList,HttpStatus.FOUND);
    }
    @GetMapping("/listOfAAuthors")
    public ResponseEntity<List<String>> authorList(){
        List<String> authorList = authorService.getAllAuthors();
        return new ResponseEntity<>(authorList,HttpStatus.FOUND);
    }
}
