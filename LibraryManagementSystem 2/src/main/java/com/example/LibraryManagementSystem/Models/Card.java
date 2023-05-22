package com.example.LibraryManagementSystem.Models;

import com.example.LibraryManagementSystem.Enums.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cards")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;


    @OneToOne
    @JoinColumn
    private Student student;

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Book> booksIssued;

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Transaction> transactionList;


}
