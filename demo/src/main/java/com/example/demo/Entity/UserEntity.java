package com.example.demo.Entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("users")  // map pojo with database collection default use class name
@Data
@Builder
//@NoArgsConstructor

public class UserEntity{         // always start variable name with small cap
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String password;
    private List<String> roles;
    @DBRef
    private List<JournalEntity> journalentity=new ArrayList<>();  // foreign key type which takes reference of journalentity into our pojo

}
