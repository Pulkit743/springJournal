package com.example.demo.service;

import com.example.demo.Entity.JournalEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.repository.journalrepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class journalservice {

    @Autowired
    private journalrepository journalRepository;
    @Autowired
    private userservice userService;

    @Transactional
    public void saveEntry(JournalEntity journalentity, UserEntity finduser) {
        try {
            journalentity.setDate(LocalDateTime.now());
            JournalEntity save = journalRepository.save(journalentity);


//            finduser.setUserName(null); // Here error is coming so it should that no entry saved in journalentry   @Transactional
            finduser.getJournalentity().add(save);
            userService.saveuser(finduser);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException("An error is occured", exception);
        }

    }


    public void saveEntry(JournalEntity journalentity) {
        journalRepository.save(journalentity);
    }

    public List<JournalEntity> getall() {
        return journalRepository.findAll();
    }

    public Optional<?> getID(ObjectId id) {
        return journalRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        try {
            UserEntity userEntity = userService.FindByUserName(userName);

            boolean removed = userEntity.getJournalentity().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveuser(userEntity);
                journalRepository.deleteById(id);
                return removed;
            }
            else
                throw new RuntimeException("No id found corresponding to user");
            }
        catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occured while deleteing the id",e);
        }
    }
}

