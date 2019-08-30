package com.example.check.repositories;

import com.example.check.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BookRepository extends JpaRepository<Book,String> {

    Book findBookByUserIdAndTitle(String userId,String title);

}
