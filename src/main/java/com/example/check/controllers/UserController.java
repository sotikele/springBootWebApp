package com.example.check.controllers;

import com.example.check.models.Book;
import com.example.check.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;





    @GetMapping("user/books")
    public List<Book> getUserBooks() {
        return userService.getUserBooks();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user/changePassword")
    public ResponseEntity UpdateUsersPassword(@RequestBody Map<String, String> s) {
        return userService.changePassword(s.get("password"), s.get("oldPassword"));
    }
}
