package com.example.LibraryManagementSystem.RequestDto;

import lombok.Data;

@Data
public class StudentUpdateNameRequestDto {
    private int studentId;
    private String name;
}
