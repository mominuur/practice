package com.example.LibraryManagementSystem.Models;

import com.example.LibraryManagementSystem.Enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;


    private String transactionId;

    @CreationTimestamp
    private Date transactionDate;

    private  Date returnDate;

    private boolean isIssueOperation;


    //Things I will write for connecting it book.
    @ManyToOne
    @JoinColumn
    private Book book; //book entity pk will come here and become a foreign key.

    //We need to connect it to the Card class.
    @ManyToOne
    @JoinColumn
    private Card card;
}
