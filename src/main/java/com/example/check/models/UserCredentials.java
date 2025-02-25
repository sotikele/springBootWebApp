package com.example.check.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentials implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;
    private String username;
    private String password;

}