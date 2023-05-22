package com.example.LibraryManagementSystem.RequestDto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class AuthorRequestDto {

    @Column(nullable = false)
    private String name;

    private int age;

    @Column(unique = true,nullable = false)
    private String email;


}
