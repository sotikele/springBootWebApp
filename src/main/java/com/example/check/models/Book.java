package com.example.check.models;

import com.eurodyn.qlack.fuse.fileupload.model.DBFile;

import javax.persistence.*;



@Entity(name = "books")
public class Book  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private Integer userId;
    @OneToOne
    @JoinColumns({
            @JoinColumn(name="id"),
            @JoinColumn(name="chunkOrder")
    })
    private DBFile dbFile;

    public Book(){


    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book(String title, String author, Integer userId) {
        this.title = title;
        this.author = author;
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
