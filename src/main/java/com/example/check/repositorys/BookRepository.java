package com.example.check.repositorys;

import com.example.check.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BookRepository extends JpaRepository<Book,Integer> {

    Book findBookByUserIdAndTitle(Integer userId,String title);

}
