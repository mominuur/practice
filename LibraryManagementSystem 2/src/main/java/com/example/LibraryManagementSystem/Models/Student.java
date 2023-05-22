package com.example.LibraryManagementSystem.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;


    @Column(unique = true, nullable = false)
    private String email;

    @Column(columnDefinition = "varchar(255) default 'India'")
    private String country;

    @CreationTimestamp
    private Date createdOn;


    @UpdateTimestamp
    private Date updated;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Card card;



}
