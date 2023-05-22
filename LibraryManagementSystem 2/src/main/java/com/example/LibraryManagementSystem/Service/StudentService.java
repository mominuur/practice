package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.Converter.BookConverter;
import com.example.LibraryManagementSystem.Converter.StudentConverter;
import com.example.LibraryManagementSystem.Enums.CardStatus;
import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Models.Card;
import com.example.LibraryManagementSystem.Models.Student;
import com.example.LibraryManagementSystem.Models.UserInfo;
import com.example.LibraryManagementSystem.Repository.StudentRepository;
import com.example.LibraryManagementSystem.Repository.UserInfoRepository;
import com.example.LibraryManagementSystem.RequestDto.StudentRequestDto;
import com.example.LibraryManagementSystem.RequestDto.StudentUpdateNameRequestDto;
import com.example.LibraryManagementSystem.ResponseDto.BookResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public String addStudent(StudentRequestDto studentRequestDto){

        try{
            Student student = StudentConverter.convertDtoToEntity(studentRequestDto);
            //student.setPassword(passwordEncoder.encode(student.getPassword()));
            Card card = new Card();
            card.setCardStatus(CardStatus.ACTIVATED);
            card.setStudent(student);
            student.setCard(card);

            UserInfo userInfo = UserInfo.builder().name(studentRequestDto.getName()).password(studentRequestDto.getPassword())
                    .roles(studentRequestDto.getRoles()).build();
            userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            userInfoRepository.save(userInfo);
            studentRepository.save(student);

        }catch (Exception e){
            return "Student could not be added";
        }

        return "Student added Successfully.";
    }
    public String updateName(StudentUpdateNameRequestDto studentUpdateNameRequestDto){
        try{
            Student originalStudent = studentRepository.findById(studentUpdateNameRequestDto.getStudentId()).get();
            originalStudent.setName(studentUpdateNameRequestDto.getName());
            studentRepository.save(originalStudent);
            return "Student name has been updated";
        }catch (Exception e){
            return "Student name couldn't be updated";
        }
    }
    public List<BookResponseDto> getIssuedBook(int id){
        Student student = studentRepository.findById(id).get();
        Card card = student.getCard();
        List<Book> bookList = card.getBooksIssued();
        List<BookResponseDto> books = new ArrayList<>();
        for(Book b: bookList){
            BookResponseDto bookResponseDto = BookConverter.convertEntityToDto(b);
            books.add(bookResponseDto);
        }
            //books.add(b.getName());

        return books;
    }
}
