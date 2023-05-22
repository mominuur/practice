package com.example.LibraryManagementSystem.RequestDto;

import com.example.LibraryManagementSystem.Enums.Genre;
import lombok.Data;

import javax.persistence.Column;

@Data
public class BookRequestDto {

    @Column(nullable = false)
    private String name;

    private Genre genre;
    private int authorId;
}
