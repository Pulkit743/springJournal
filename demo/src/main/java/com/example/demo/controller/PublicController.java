package com.example.demo.controller;


import com.example.demo.Entity.UserEntity;
import com.example.demo.service.userservice;
//import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private userservice UserService;
    @PostMapping("create-user")
    public ResponseEntity<?> saveuser(@RequestBody UserEntity user)
    {
        UserService.savenewuser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
