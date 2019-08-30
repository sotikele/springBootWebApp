package com.example.check.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")

    private String userId;
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


}
