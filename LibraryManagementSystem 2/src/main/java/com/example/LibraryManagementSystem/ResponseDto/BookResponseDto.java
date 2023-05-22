package com.example.LibraryManagementSystem.ResponseDto;

import com.example.LibraryManagementSystem.Enums.Genre;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class BookResponseDto {

    private String bookName;
    private int bookId;
    private Genre genre;
    private int authorId;
}
