package com.telran.repository;

import com.telran.entity.UserPhoneNumber;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserPhoneNumberRepository extends MongoRepository<UserPhoneNumber, String> {
}
