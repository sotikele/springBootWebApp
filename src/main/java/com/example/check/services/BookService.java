package com.example.check.services;


import com.example.check.models.Book;

import com.example.check.repositories.BookRepository;
import com.example.check.repositories.UserRepository;
import com.example.check.security.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;



    public Optional<Book> getBook(String id){
        return bookRepository.findById(Integer.parseInt(id));
    }


    public void addBook( Book book) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserContext user= (UserContext) authentication.getPrincipal();
        book.setUserId(user.getUser().getUserId());
        bookRepository.save(book);
    }


    public ResponseEntity deleteBook(String title) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserContext user= (UserContext) authentication.getPrincipal();

        Book book= bookRepository.findBookByUserIdAndTitle(user.getUser().getUserId(),title);
        if(book!=null) {
            bookRepository.delete(book);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }



    public void updateBook(Book book,String id) {
        bookRepository.deleteById(Integer.parseInt(id));
        bookRepository.save(book);
    }
}
