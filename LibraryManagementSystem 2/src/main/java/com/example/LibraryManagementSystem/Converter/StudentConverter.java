package com.example.LibraryManagementSystem.Converter;

import com.example.LibraryManagementSystem.Models.Student;
import com.example.LibraryManagementSystem.RequestDto.StudentRequestDto;

public class StudentConverter {

    public static Student convertDtoToEntity(StudentRequestDto studentRequestDto){
        Student student = Student.builder().name(studentRequestDto.getName()).email(studentRequestDto.getEmail()).
        country(studentRequestDto.getCountry()).build();

        return student;
    }
}
