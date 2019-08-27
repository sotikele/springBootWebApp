package com.example.check.dtos;


public class BookDTO {


    private String title;

    private String author;

    private String file_id;


    public BookDTO(String title, String author, String file_id) {
        this.title = title;
        this.author = author;
        this.file_id = file_id;

    }


    public BookDTO() {


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

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }


}
