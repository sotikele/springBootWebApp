package com.example.check.services;


import com.eurodyn.qlack.fuse.fileupload.dto.DBFileDTO;
import com.eurodyn.qlack.fuse.fileupload.service.impl.FileUploadImpl;
import com.example.check.dtos.BookDTO;
import com.example.check.mappers.BookMapper;
import com.example.check.models.Book;
import com.example.check.models.Image;
import com.example.check.repositories.BookRepository;
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
    private  BookRepository bookRepository;

    @Autowired
    private FileUploadImpl fileUpload;
    @Autowired
    private BookMapper bookMapper;


    public Optional<Book> getBook(String id) {
        return bookRepository.findById(id);
    }


    public void addBook(BookDTO bookDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserContext user = (UserContext) authentication.getPrincipal();


        DBFileDTO dbFile = fileUpload.getByID(bookDTO.getFile_id());
        Image image = new Image();
        image.setImageName(dbFile.getFilename());
        image.setData(dbFile.getFileData());


        Book book = bookMapper.bookDTOToBook(bookDTO);
        book.setUserId(user.getUser().getUserId());
        book.setImage(image);

        bookRepository.save(book);
    }


    public ResponseEntity deleteBook(String title) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserContext user = (UserContext) authentication.getPrincipal();

        Book book = bookRepository.findBookByUserIdAndTitle(user.getUser().getUserId(), title);
        if (book != null) {
            bookRepository.delete(book);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    public void updateBook(Book book, String id) {
        bookRepository.deleteById(id);
        bookRepository.save(book);
    }
}
