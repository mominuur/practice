package com.example.LibraryManagementSystem.Controllers;

import com.example.LibraryManagementSystem.RequestDto.IssueBooRequestDto;
import com.example.LibraryManagementSystem.RequestDto.ReturnBookDto;
import com.example.LibraryManagementSystem.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/issueBook")
    public ResponseEntity<String> issueBook(@RequestBody()IssueBooRequestDto issueBooRequestDto){
        try{
            String response = transactionService.issueBooks(issueBooRequestDto);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getTxnInfo")
    public ResponseEntity<List<String>> getTransactionEntry(@RequestParam("bookId")Integer bookId, @RequestParam
            ("cardId")Integer cardId){
        List<String> response = transactionService.getTransaction(bookId,cardId);
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }


    @PutMapping("/return_book")
    public ResponseEntity<String> returnBook(@RequestBody()ReturnBookDto returnBookDto){
        String response = transactionService.returnBook(returnBookDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
