package com.example.LibraryManagementSystem.Converter;

import com.example.LibraryManagementSystem.Models.Author;
import com.example.LibraryManagementSystem.RequestDto.AuthorRequestDto;

public class AuthorConvertor {
    public static Author convertDtoToEntity(AuthorRequestDto authorRequestDto) {

        Author author = Author.builder().name(authorRequestDto.getName()).age(authorRequestDto.getAge())
                .email(authorRequestDto.getEmail()).build();

        return author;
    }
}
