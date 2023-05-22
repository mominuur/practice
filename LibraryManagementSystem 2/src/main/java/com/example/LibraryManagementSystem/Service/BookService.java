package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.Converter.BookConverter;
import com.example.LibraryManagementSystem.Enums.Genre;
import com.example.LibraryManagementSystem.Models.Author;
import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Repository.AuthorRepository;
import com.example.LibraryManagementSystem.Repository.BookRepository;
import com.example.LibraryManagementSystem.RequestDto.BookRequestDto;
import com.example.LibraryManagementSystem.ResponseDto.BookResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public String addBook(BookRequestDto bookRequestDto){
        try{
            Book book = BookConverter.convertDtoToBookEntity(bookRequestDto);
            book.setIssued(false);

            //find author of the book and set author in book entity

            Author author = authorRepository.findById(bookRequestDto.getAuthorId()).get();
            book.setAuthor(author);


            //update list of books written by author
            List<Book> currentBooksWritten = author.getBooksWritten();
            currentBooksWritten.add(book);
            //System.out.println(currentBooksWritten.size());
            //author.setBooksWritten(currentBooksWritten);

            authorRepository.save(author);

            //book.bookRepository.save() not required as it is taken care by authorRepository.save() as it is parent
        }catch (Exception e){
            return "Could not add book";
        }
        return "Book added successfully";
    }

    public String findBookByName(String name) {
        Book book = bookRepository.findByName(name);
        String issued;
        if(book.isIssued())
            issued="Not Available";
        else issued = "Available";
        String response = book.getName().toUpperCase(Locale.ROOT)+" --->"+book.getAuthor().getName()+"----->"+" "+issued;
        return response;
    }
    public List<String> getAllBooks(){
        List<Book> allBooks = bookRepository.findAll();
        List<String> bookNames = new ArrayList<>();
        for(Book book:allBooks)
            bookNames.add(book.getName());
        return bookNames;
    }

    public List<String> getBooksByGenre(Genre genre) {
        List<Book> bookList = bookRepository.findAllBookByGenre(genre);
        List<String> bookNameList = new ArrayList<>();
        for(Book book : bookList)
            bookNameList.add(book.getName());
        return bookNameList;
    }
    public List<BookResponseDto> getAllAvailableBooks(){
        List<Book> books = bookRepository.findAll();
        List<BookResponseDto> bookResponseDtoList = new ArrayList<>();
        for(Book book : books){
            if(!book.isIssued()){
                BookResponseDto bookResponseDto = BookConverter.convertEntityToDto(book);
                bookResponseDtoList.add(bookResponseDto);
            }
        }
        return bookResponseDtoList;
    }
}
