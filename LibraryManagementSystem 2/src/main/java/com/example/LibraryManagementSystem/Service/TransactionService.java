package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.Enums.CardStatus;
import com.example.LibraryManagementSystem.Enums.TransactionStatus;
import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Models.Card;
import com.example.LibraryManagementSystem.Models.Transaction;
import com.example.LibraryManagementSystem.Repository.BookRepository;
import com.example.LibraryManagementSystem.Repository.CardRepository;
import com.example.LibraryManagementSystem.Repository.TransactionRepository;
import com.example.LibraryManagementSystem.RequestDto.IssueBooRequestDto;
import com.example.LibraryManagementSystem.RequestDto.ReturnBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    public  String issueBooks(IssueBooRequestDto issueBookRequestDto)throws Exception{


        int bookId = issueBookRequestDto.getBookId();
        int cardId = issueBookRequestDto.getCardId();

        //Get the Book Entity and Card Entity ??? Why do we need this
        //We are this bcz we want to set the Transaction attributes...

        Book book = bookRepository.findById(bookId).get();

        Card card = cardRepository.findById(cardId).get();


        //Final goal is to make a transaction Entity, set its attribute
        //and save it.
        Transaction transaction = new Transaction();

        //Setting the attributes
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setIssueOperation(true);
        transaction.setTransactionStatus(TransactionStatus.PENDING);


        //attribute left is success/Failure
        //Check for validations
        if(book==null || book.isIssued()==true){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            throw new Exception("Book is not available");

        }
        if(card==null || (card.getCardStatus()==CardStatus.MAXED)){

            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            throw  new Exception("You have already issued 5 books");
        }
        if(card==null || (card.getCardStatus()!=CardStatus.ACTIVATED)){

            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            throw  new Exception("Card is not valid");
        }




        //We have reached a success case now.

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);


        //set attributes of book
        book.setIssued(true);
        //book.setCard(card);
        //Btw the book and transaction : bidirectional
        List<Transaction> listOfTransactionForBook = book.getTransactionList();
        listOfTransactionForBook.add(transaction);
        book.setTransactionList(listOfTransactionForBook);



        //I need to make changes in the card
        //Book and the card
        List<Book> issuedBooksForCard = card.getBooksIssued();
        issuedBooksForCard.add(book);
        if(issuedBooksForCard.size()==5)
            card.setCardStatus(CardStatus.MAXED);
        //card.setBooksIssued(issuedBooksForCard);

//        for(Book b: issuedBooksForCard){
//            System.out.println(b.getName());
//        }

        //Card and the Transaction : bidirectional (parent class)
        List<Transaction> transactionsListForCard = card.getTransactionList();
        transactionsListForCard.add(transaction);
        //card.setTransactionList(transactionsListForCard);

        //save the parent.
        book.setCard(card);
        cardRepository.save(card);
        //automatically by cascading : book and transaction will be saved.
        //Saving the parent
        System.out.println(issuedBooksForCard.size());

        return "Book issued successfully";
    }




    public String returnBook(ReturnBookDto returnBookDto){
        try{
            Book book = bookRepository.findById(returnBookDto.getBookId()).get();
            Card card = book.getCard();
            Transaction transaction = book.getTransactionList().get(0);

            book.setCard(null);
            book.setIssued(false);
            List<Book>issuedBooks = card.getBooksIssued();
            issuedBooks.remove(book);
            card.setBooksIssued(issuedBooks);

            transaction.setReturnDate(new Date());
            cardRepository.saveAndFlush(card);


        }catch(Exception e){
            return "Book can not be returned";
        }

        //String transactionId = transactionRepository.findById()
        return "returned successfully";
    }




    public List<String> getTransaction(int bookId, int cardId){
        List<Transaction> transactionList = transactionRepository.getTransactionForBookAndCard(bookId,cardId);
        List<String> listOfTransactionId=new ArrayList<>();
        for(Transaction t:transactionList){
            listOfTransactionId.add(t.getTransactionId());
        }
        //String transactionId = transactionList.get(0).getTransactionId();

        return listOfTransactionId;
    }
}
