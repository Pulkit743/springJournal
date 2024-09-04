package com.example.demo.repository;

import com.example.demo.Entity.JournalEntity;
import com.example.demo.Entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface userrepository extends MongoRepository<UserEntity,ObjectId> {
UserEntity findByuserName(String userName);
void  deleteByuserName(String userName);

}
