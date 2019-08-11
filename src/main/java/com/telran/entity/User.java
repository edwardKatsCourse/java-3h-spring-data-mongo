package com.telran.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "mongo-users") //if > 16 MB -> FS Grid
public class User {

    @Id
    private String id;

    @Field(value = "username")
    private String name;

    @Indexed(unique = true)
    private String email;

    private UserDetails userDetails;

    @DBRef
    private UserPhoneNumber userPhoneNumber; //userPhoneNumber.$id
}
