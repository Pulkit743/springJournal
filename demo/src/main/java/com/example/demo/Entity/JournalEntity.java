package com.example.demo.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("journalentries")  // map pojo with database collection
@Data
public class JournalEntity {         // always start variable name with small cap
    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;
}
