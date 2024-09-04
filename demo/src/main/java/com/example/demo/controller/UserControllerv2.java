package com.example.demo.controller;


import com.example.demo.Entity.JournalEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.repository.userrepository;
import com.example.demo.service.journalservice;
import com.example.demo.service.userservice;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserControllerv2 {

    @Autowired
    private userservice userService;
    @Autowired
    private userrepository UserRepository;

//    @GetMapping
//    public List<UserEntity> getallusers()
//    {
//        return userService.getusers();
//    }

//    @PostMapping
//    public ResponseEntity<?>saveuser(@RequestBody UserEntity user)
//    {
//
//        userService.savenewuser(user);
//        return new ResponseEntity<>(user,HttpStatus.OK);
//    }

    @PutMapping
    public ResponseEntity<?> updateusename(@RequestBody UserEntity user)
    {
//        System.out.println("hello");
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();   // here auth is check when we try to give user name and pass then securitycontextholder contain  the user and pass
        String userName=authentication.getName();
        return userService.updateusername(user,userName);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserBYId()
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        UserRepository.deleteByuserName((authentication.getName()));
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }









  

//
//    @GetMapping()
//    public List<UserEntity> getAllEntries ()
//    {
//       return userService.
////        return null;
//    }
//
//    @PostMapping
//    public JournalEntity createEntry(@RequestBody JournalEntity myEntity)
//    {   myEntity.setDate(LocalDateTime.now());
//        .saveEntry(myEntity);
//        return myEntity;
//    }

//    @GetMapping("id/{id}")
//    public Optional<?> getId(@PathVariable String id){
//
//        ObjectId objectId = new ObjectId(id);
//        Optional<?> j1;
//        j1=journalService.getID(objectId);
//        if (!j1.isPresent())
//        {
//                return Optional.of(ResponseEntity.badRequest().body("not found"));
//        }
//        return Optional.of(j1);
//
//    }
////
//    @PostMapping("deleteid/{id}")
//    public Optional<?> deleteId(@PathVariable ObjectId id){
//
//        Optional<?> j1=journalService.getID(id);
//
//        if(j1.isPresent()){
//
//        journalService.deleteById(id);
//        return Optional.of(ResponseEntity.ok(j1)); // Return the deleted JournalEntity object
//
//        }
//        else {return Optional.of(ResponseEntity.badRequest().body("Your Id Not Found"));}
//    }
//
//
//    @PutMapping("id/{id}")
//    public ResponseEntity<?> updateEntity(@PathVariable String id, @RequestBody JournalEntity myEntry) {
//        ObjectId objectId = new ObjectId(id);
//        Optional<JournalEntity> optionalEntity = journalService.getID(objectId);
//
//        if (optionalEntity.isPresent()) {
//            JournalEntity existingEntity = optionalEntity.get();
//
//            existingEntity.setContent(myEntry.getContent()==" " || myEntry.getContent().isEmpty()? existingEntity.getContent() : myEntry.getContent());
//            existingEntity.setTitle(myEntry.getTitle()==" "|| myEntry.getTitle().isEmpty()?existingEntity.getContent(): myEntry.getTitle());
//
//
//            // Save the updated entity
//            journalService.saveEntry(existingEntity);
//
//            return new ResponseEntity<>(existingEntity,HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
//        }
//    }

    }




    // controoler ->sevice->repository

