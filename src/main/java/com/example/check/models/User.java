package com.example.check.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer userId;
    @Column
    @NotEmpty(message = "name must not be empty")
    private String name;
    @Column
    @NotEmpty(message = "email must not be empty")
    private String email;
    @Column
    @NotEmpty(message = "password must not be empty")
    private String password;
    @Column
    @NotEmpty(message = "username must not be empty")
    private String username;
    @Column
    private String role;
    @OneToMany(mappedBy = "userId")
    private List<Book> library;

    public User() {
        this.role = "user";
    }

    public User(String name, String email, String password, String username, List<Book> library) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = "user";
        this.library = library;
    }

    public Integer getUserId() {
        return userId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getLibrary() {
        return library;
    }

    public void setLibrary(List<Book> library) {
        this.library = library;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
