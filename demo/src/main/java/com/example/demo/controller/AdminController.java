package com.example.demo.controller;

import com.example.demo.Entity.UserEntity;
import com.example.demo.service.journalservice;
import com.example.demo.service.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    journalservice JournalService;
    @Autowired
    userservice UserService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getallusers()
    {
        List<UserEntity> AllUsers = UserService.getusers();
        if (AllUsers!=null && !AllUsers.isEmpty())
            return new ResponseEntity<>(AllUsers, HttpStatus.OK);
        return new ResponseEntity<>("NO user found ",HttpStatus.NOT_FOUND);

    }
    @PostMapping("/Save-Admin")
    public ResponseEntity<?> saveadmin(@RequestBody UserEntity User)
    {
              UserService.saveAdmin(User);
              return new ResponseEntity<>("Saved",HttpStatus.OK);
    }

}
