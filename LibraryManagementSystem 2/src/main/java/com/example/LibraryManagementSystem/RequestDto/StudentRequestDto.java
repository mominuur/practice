package com.example.LibraryManagementSystem.RequestDto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class StudentRequestDto {

    @Column(nullable = false)
    private String name;

    private String email;
    private String password;
    private String roles;

    @Column(columnDefinition = "varchar(255) default 'India'")
    private String country="India";


}
