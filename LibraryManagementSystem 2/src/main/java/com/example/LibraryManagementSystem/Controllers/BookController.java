package com.example.LibraryManagementSystem.Controllers;

import com.example.LibraryManagementSystem.Enums.Genre;
import com.example.LibraryManagementSystem.RequestDto.BookRequestDto;
import com.example.LibraryManagementSystem.ResponseDto.BookResponseDto;
import com.example.LibraryManagementSystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String > addBook(@RequestBody() BookRequestDto bookRequestDto){
        String response = bookService.addBook(bookRequestDto);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @GetMapping("/findBookByName")
    public ResponseEntity<String> findBookAndStatus(@RequestParam("name")String name){
        String response = bookService.findBookByName(name);
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }
    @GetMapping("/findAllBooks")
    public ResponseEntity<List<String>> findAllBooks(){
        List<String> books = bookService.getAllBooks();
        return  new ResponseEntity<>(books,HttpStatus.FOUND);
    }
    @GetMapping("/findBooksByGenre")
    public ResponseEntity<List<String>> findBookByGenre(@RequestParam("genre")Genre genre){
        List<String> books = bookService.getBooksByGenre(genre);
        return new ResponseEntity<>(books,HttpStatus.FOUND);
    }
    @GetMapping("/findAllAvailableBooks")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<BookResponseDto>> findAllAvailableBooks(){
        List<BookResponseDto> bookResponseDtoList = bookService.getAllAvailableBooks();
        return new ResponseEntity<>(bookResponseDtoList,HttpStatus.OK);
    }
}
