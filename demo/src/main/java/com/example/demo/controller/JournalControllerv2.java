package com.example.demo.controller;


import com.example.demo.Entity.JournalEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.repository.userrepository;
import com.example.demo.service.journalservice;
import com.example.demo.service.userservice;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalControllerv2 {

    @Autowired
    private journalservice journalService;

    @Autowired
    private userservice userService;


    @GetMapping()
    public ResponseEntity<?> getAllEntries() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntity userEntity = userService.FindByUserName(userName);
        if (userEntity == null) {
            return new ResponseEntity<>("No user name found in users collection", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userEntity, HttpStatus.OK);
        }

//        http://localhost:8080/journal/User?userName=ram
//        return null;
    }

    @PostMapping()
    public ResponseEntity<?> createEntryinUser(@RequestBody JournalEntity myEntity)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        if (userName == null || myEntity == null) {
            return ResponseEntity.badRequest().body("Invalid input data.");
        }

        UserEntity findUser = userService.FindByUserName(userName);
        if (findUser != null) {
            myEntity.setDate(LocalDateTime.now());
            journalService.saveEntry(myEntity, findUser);
            return new ResponseEntity<>(findUser, HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>("Not  found username", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getId/{id}")
    public Optional<?> getId(@PathVariable ObjectId id) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntity finduser=userService.FindByUserName(userName);

        List<JournalEntity> collect = finduser.getJournalentity().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty())
        {
            // collect.get(0).getId()
            Optional<?> j1;
            j1 = journalService.getID(id);
            if(j1.isPresent())
            {
                return Optional.of(ResponseEntity.ok().body(j1));
            }

        }
        return Optional.of(ResponseEntity.badRequest().body("not found"));
    }

    //
    @DeleteMapping("deleteId/{id}")
    public Optional<?> deleteId(@PathVariable ObjectId id) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        Optional<?> j1 = journalService.getID(id);

        if (j1.isPresent()) {
            boolean target=journalService.deleteById(id, userName);
            if(target==true)
                return Optional.of(ResponseEntity.ok().body("Succesfully deleted"));
            else
                return Optional.of(ResponseEntity.notFound());
        } else {
            return Optional.of(ResponseEntity.badRequest().body("Your Id Not Found in journalentity table " ));
        }
    }


    @PostMapping("updateId/{id}")
    public ResponseEntity<?> updateEntity(@PathVariable ObjectId id , @RequestBody JournalEntity myEntry) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntity finduser=userService.FindByUserName(userName);

        List<JournalEntity> collect = finduser.getJournalentity().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()) {
            Optional<JournalEntity> optionalEntity = (Optional<JournalEntity>) journalService.getID(id);
            if (optionalEntity.isPresent()) {
                JournalEntity existingEntity = optionalEntity.get();
                existingEntity.setContent(myEntry.getContent() == " " || myEntry.getContent().isEmpty() ? existingEntity.getContent() : myEntry.getContent());
                existingEntity.setTitle(myEntry.getTitle() == " " || myEntry.getTitle().isEmpty() ? existingEntity.getContent() : myEntry.getTitle());
                journalService.saveEntry(existingEntity);
                userService.saveuser(finduser);    // Is is optional by default it Update..
                return new ResponseEntity<>(existingEntity, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }   }







    // controller ->Service->Repository

