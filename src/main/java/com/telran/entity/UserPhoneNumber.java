package com.telran.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document("user_phone_number")
public class UserPhoneNumber {

    @Id
    private String id; //ObjectId(hex string)

    private String phoneNumber;
    private String phoneBrand;
    private String phoneModel;

}
