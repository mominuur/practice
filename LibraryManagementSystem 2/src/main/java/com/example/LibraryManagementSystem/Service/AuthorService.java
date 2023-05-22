package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.Converter.AuthorConvertor;
import com.example.LibraryManagementSystem.Models.Author;
import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Repository.AuthorRepository;
import com.example.LibraryManagementSystem.RequestDto.AuthorRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String createAuthor(AuthorRequestDto authorRequestDto){
        try {
            Author author = AuthorConvertor.convertDtoToEntity(authorRequestDto);
            authorRepository.save(author);

        }catch (Exception e){
            return "createAuthor did not work successfully";

        }
        return "Author Created successfully";
    }

    public List<String> getBookListOfAuthor(int id) {
        Author author = authorRepository.findById(id).get();
        List<Book> bookList = author.getBooksWritten();
        List<String> bookName = new ArrayList<>();
        for(Book book:bookList)
            bookName.add(book.getName());
        return bookName;
    }

    public List<String> getAllAuthors() {
        List<Author> authorList = authorRepository.findAll();
        List<String> authorNameList = new ArrayList<>();
        for(Author author:authorList)
            authorNameList.add(author.getName());
        return authorNameList;
    }
}
