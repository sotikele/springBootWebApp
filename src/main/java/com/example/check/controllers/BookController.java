package com.example.check.controllers;

import com.example.check.services.BookService;
import com.example.check.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    BookService bookService;



    @GetMapping("/user/book/{id}")
    public Optional<Book> getBook(@PathVariable String id){
        return bookService.getBook(id);
    }

    @PostMapping("/user/book")
    public void addBook(@RequestBody Book book) {

        bookService.addBook(book);
    }

    @PostMapping("user/book/delete")
    public ResponseEntity deleteBook(@RequestBody Map<String,String> s) {
      return   bookService.deleteBook(s.get("title"));
    }

    @RequestMapping(method=RequestMethod.PUT,value = "/book/{id}")
    public void UpdateBook(@RequestBody Book book,@PathVariable String id) {
       bookService.updateBook(book,id);
    }
}
