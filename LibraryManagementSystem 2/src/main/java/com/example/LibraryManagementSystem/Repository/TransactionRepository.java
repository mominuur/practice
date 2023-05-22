package com.example.LibraryManagementSystem.Repository;

import com.example.LibraryManagementSystem.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    @Query(value = "select * from transactions where book_id=:bookId and card_id=:cardId and is_issue_operation=true",
    nativeQuery = true)
    List<Transaction> getTransactionForBookAndCard(int bookId, int cardId);
}
