package com.example.demo.service;

import com.example.demo.Entity.JournalEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.repository.userrepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class userservice {

   @Autowired
    private userrepository userRepo;

   private static final PasswordEncoder passwordEncoder =new BCryptPasswordEncoder();


   public void saveuser(UserEntity user)
   {
       userRepo.save(user);
   }
    public void saveAdmin(UserEntity user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepo.save(user);
    }

    public boolean savenewuser(UserEntity user)
    {

        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepo.save(user);
            return  true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
   public List<UserEntity> getusers()
   {
       return userRepo.findAll();

   }
   public ResponseEntity<?> updateusername(UserEntity user,String username)
   {
       UserEntity userindb = userRepo.findByuserName(username);
       if (userindb!=null)
       {
           userindb.setUserName(user.getUserName());
           userindb.setPassword(user.getPassword());
           savenewuser(userindb);
           return new ResponseEntity<>(userindb, HttpStatus.OK);
       }
       return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);

   }

    public UserEntity FindByUserName(String username)
    {
         return userRepo.findByuserName(username);

    }





}
