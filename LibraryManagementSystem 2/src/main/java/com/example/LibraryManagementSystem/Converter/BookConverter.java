package com.example.LibraryManagementSystem.Converter;

import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.RequestDto.BookRequestDto;
import com.example.LibraryManagementSystem.ResponseDto.BookResponseDto;

public class BookConverter {

    public static Book convertDtoToBookEntity(BookRequestDto bookRequestDto){
        Book book = Book.builder().name(bookRequestDto.getName()).genre(bookRequestDto.getGenre()).build();
        return book;
    }
    public static BookResponseDto convertEntityToDto(Book book){

        BookResponseDto bookResponseDto = BookResponseDto.builder().bookId(book.getId()).bookName(book.getName()).genre(book.getGenre()).authorId(book.getId()).build();
        return bookResponseDto;
    }
}
